package tec.bd.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.app.dao.mariaDB.CursoMySqlDAOImpl;
import tec.bd.app.dao.mariaDB.EstudianteMySqlDAOImpl;
import tec.bd.app.dao.mariaDB.ProfesorMySqlDAOImpl;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;

import java.io.File;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class MySQLDemo {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLDemo.class);
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/universidad";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "my-secret-pw";



    private static final String SQL_SELECT_ESTUDIANTES = "select id, nombre,apellido,fecha_nacimiento, DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), fecha_nacimiento)), \"%Y\")+0 as edad, total_creditos from estudiante order by nombre asc";

    public static void main(String... args) throws SQLException {

        DBProperties dbProperties = new DBProperties("jdbc:mariadb://localhost:3306/universidad","root","my-secret-pw");


        EstudianteMySqlDAOImpl estudianteMySqlDAO = new EstudianteMySqlDAOImpl(dbProperties);

        ProfesorMySqlDAOImpl profesorMySqlDAO = new ProfesorMySqlDAOImpl(dbProperties);

        CursoMySqlDAOImpl cursoMySqlDAO = new CursoMySqlDAOImpl(dbProperties);

        List<Curso> cursoList = cursoMySqlDAO.findAll();

        for (Curso curso : cursoList){
            System.out.println(curso.getNombre());
        }

        List<Profesor> profesorList = profesorMySqlDAO.findAll();

        for (Profesor profesor : profesorList) {
            System.out.println(profesor.getNombre());
        }

//        Date myDate = new java.util.Date("08/04/2000");
//        Estudiante miguel = new Estudiante(11, "Miguel", "Mesen", myDate,17);
//        estudianteMySqlDAO.save(miguel);


        List<Estudiante> list = estudianteMySqlDAO.findAll();



        for (Estudiante estudiante : list){
            System.out.println(estudiante);
        }





//        try{
//            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING,DB_USERNAME,DB_PASSWORD)) {
//                LOG.info(SQL_SELECT_ESTUDIANTES);
//                try (Statement stmt = connection.createStatement()){
//                    // execute query
//                    try (ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ESTUDIANTES)){
//                        System.out.println("ID\t\t Nombre\t\t Apellido\t\t Fecha Nacimiento\t\t Edad\t\t Creditos");
//                        System.out.println("-------------------------------------------------------------------------");
//                        while (resultSet.next()){
//                            System.out.println(
//                                    resultSet.getInt("id") + "\t\t" +
//                                    resultSet.getString("nombre") + "\t\t" +
//                                    resultSet.getString("apellido") + "\t\t" +
//                                    resultSet.getDate("fecha_nacimiento") + "\t\t" +
//                                    resultSet.getInt("edad") + "\t\t" +
//                                    resultSet.getInt("total_creditos")
//                            );
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            LOG.error("Error when running {}",SQL_SELECT_ESTUDIANTES,e);
//        }
    }
}
