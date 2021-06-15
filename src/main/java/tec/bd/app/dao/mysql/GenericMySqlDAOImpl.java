package tec.bd.app.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.app.MySQLDemo;
import tec.bd.app.dao.GenericDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Entity;

import java.io.Serializable;
import java.sql.*;
import java.util.Collections;
import java.util.List;

public abstract class GenericMySqlDAOImpl<T extends Entity, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Logger LOG = LoggerFactory.getLogger(MySQLDemo.class);

    protected DBProperties dbProperties;

    protected  GenericMySqlDAOImpl(DBProperties dbProperties){
        this.dbProperties = dbProperties;
    }

    public void requestDelete(String query) {
        try {
            try (Connection connection = this.dbProperties.getConnection()) {
                try (Statement stmt = connection.createStatement()) {
                    LOG.info(query);
                    int rowCount = stmt.executeUpdate(query);
                    LOG.debug("{} fila borrada", rowCount);
                }
            }
        } catch (SQLException e) {
            LOG.error("Error when running {}", query, e);
        }
    }


    public void requestUpdate(String query) {

        try {
            try (Connection connection = this.dbProperties.getConnection()) {
                try (Statement stmt = connection.createStatement()) {
                    LOG.info(query);
                    int rowCount = stmt.executeUpdate(query);
                    LOG.debug("{} fila actualizada", rowCount);

                }
            }
        } catch (SQLException e) {
            LOG.error("Error when running {}", query, e);
        }

    }


    public void requestSave(String query) {
        try {
            try (Connection connection = this.dbProperties.getConnection()) {
                try (Statement stmt = connection.createStatement()) {
                    int rowCount = stmt.executeUpdate(query);
                    LOG.debug("{} fila agregada", rowCount);
                }
            }
        } catch (SQLException e) {
            LOG.error("Error when running {}", query, e);
        }
    }

    public List<T> request(String query){
        try{
            try (Connection connection = this.dbProperties.getConnection()) {
                try (Statement stmt = connection.createStatement()){
                    // execute query
                    try (ResultSet resultSet = stmt.executeQuery(query)){

                        return this.resultSetToList(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Error when running {}",query,e);
        }
        return Collections.emptyList();
    }

    protected abstract T resultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract List<T> resultSetToList(ResultSet resultSet) throws SQLException;

}
