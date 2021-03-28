package tec.bd.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class MySQLDemo {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLDemo.class);
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/universidad";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static final String SQL_SELECT_ESTUDIANTES = "select id, nombre,apellido,fecha_nacimiento, DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), fecha_nacimiento)), \"%Y\")+0 as edad, total_creditos from estudiante order by nombre asc";

    public static void main(String... args){
        try{
            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING,DB_USERNAME,DB_PASSWORD)) {
                LOG.info(SQL_SELECT_ESTUDIANTES);
                try (Statement stmt = connection.createStatement()){
                    // execute query
                    try (ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ESTUDIANTES)){
                        System.out.println("ID\t\t Nombre\t\t Apellido\t\t Fecha Nacimiento\t\t Edad\t\t Creditos");
                        System.out.println("-------------------------------------------------------------------------");
                        while (resultSet.next()){
                            System.out.println(
                                    resultSet.getInt("id") + "\t\t" +
                                    resultSet.getString("nombre") + "\t\t" +
                                    resultSet.getString("apellido") + "\t\t" +
                                    resultSet.getDate("fecha_nacimiento") + "\t\t" +
                                    resultSet.getInt("edad") + "\t\t" +
                                    resultSet.getInt("total_creditos")
                            );
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Error when running {}",SQL_SELECT_ESTUDIANTES,e);
        }
    }
}
