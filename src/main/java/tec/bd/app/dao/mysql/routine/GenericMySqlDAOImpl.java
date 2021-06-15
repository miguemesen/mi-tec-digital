package tec.bd.app.dao.mysql.routine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.app.MySQLDemo;
import tec.bd.app.dao.GenericDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Entity;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericMySqlDAOImpl<T extends Entity, ID extends Serializable> implements GenericDAO<T,ID> {
    private final Logger LOG = LoggerFactory.getLogger(MySQLDemo.class);

    protected DBProperties dbProperties;

    protected  GenericMySqlDAOImpl(DBProperties dbProperties){
        this.dbProperties = dbProperties;
    }

    protected abstract T resultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract List<T> resultSetToList(ResultSet resultSet) throws SQLException;

}
