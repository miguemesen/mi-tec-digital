package tec.bd.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.app.dao.mariaDB.GenericMySqlDAOImpl;
import tec.bd.app.domain.Entity;

import java.sql.*;
import java.util.Optional;
import java.util.Set;

public class RequestSQL {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLDemo.class);
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/universidad";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "my-secret-pw";

    public ResultSet request(String query){
        try{
            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING,DB_USERNAME,DB_PASSWORD)) {
                try (Statement stmt = connection.createStatement()){
                    // execute query
                    try (ResultSet resultSet = stmt.executeQuery(query)){
                                                while (resultSet.next()){
                        }
                        return resultSet;
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Error when running {}",query,e);
        }
        return null;
    }

}
