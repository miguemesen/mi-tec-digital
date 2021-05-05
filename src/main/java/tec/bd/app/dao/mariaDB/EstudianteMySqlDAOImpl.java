package tec.bd.app.dao.mariaDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Estudiante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class EstudianteMySqlDAOImpl extends GenericMySqlDAOImpl<Estudiante, Integer> implements EstudianteDAO {

    private static final Logger LOG = LoggerFactory.getLogger(EstudianteMySqlDAOImpl.class);

    private final DBProperties dbProperties;

    private static final String SQL_SELECT_ESTUDIANTES = "select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante;";
    private static final String SQL_SELECT_ESTUDIANTES_APELLIDO_ASC = "select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante order by apellido asc;";
    private static final String SQL_SELECT_ESTUDIANTE_ID = "select id, nombre, apellido, fecha_nacimiento, total_creditos from estudiante where id = %d";
    private static final String SQL_INSERT_ESTUDIANTE = "insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(%d, '%s', '%s', '%tF', %d)";
    private static final String SQL_UPDATE_ESTUDIANTE = "update estudiante set nombre = '%s', apellido = '%s', fecha_nacimiento = '%tF', total_creditos = %d where id = %d";
    private static final String SQL_DELETE_ESTUDIANTE = "delete from estudiante where id = %d";

    public EstudianteMySqlDAOImpl(DBProperties dbProperties) {
        this.dbProperties = dbProperties;
    }

    @Override
    public List<Estudiante> findByLastName(String lastName) {
        return this.findAll().stream().filter(e -> e.getApellido().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public List<Estudiante> findAllSortByLastName() {
        return this.request(SQL_SELECT_ESTUDIANTES_APELLIDO_ASC,dbProperties);
    }

    @Override
    public List<Estudiante> findAll() {
        return this.request(SQL_SELECT_ESTUDIANTES,dbProperties);
    }

    @Override
    public Optional<Estudiante> findById(Integer id) {
        var sql = String.format(SQL_SELECT_ESTUDIANTE_ID, id);
        return this.request(sql,dbProperties).stream().findFirst();
    }

    @Override
    public void save(Estudiante estudiante) {
        var sql = String.format(SQL_INSERT_ESTUDIANTE,
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getFechaNacimiento(),
                estudiante.getTotalCreditos()
        );
        this.requestSave(sql,dbProperties);
    }

    @Override
    public Optional<Estudiante> update(Estudiante estudiante) {

        var sql = String.format(SQL_UPDATE_ESTUDIANTE,
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getFechaNacimiento(),
                estudiante.getTotalCreditos(),
                estudiante.getId()
        );
        this.requestUpdate(sql,dbProperties);
        return Optional.of(estudiante);
    }


    @Override
    public void delete(Integer id) {
        var sql = String.format(SQL_DELETE_ESTUDIANTE, id);
        this.requestDelete(sql,dbProperties);
    }

    @Override
    protected Estudiante resultSetToEntity(ResultSet resultSet) throws SQLException {
        var id = resultSet.getInt("id");
        var nombre = resultSet.getString("nombre");
        var apellido = resultSet.getString("apellido");
        var fechaNacimiento = resultSet.getDate("fecha_nacimiento");
        var totalCreditos = resultSet.getInt("total_creditos");
        return new Estudiante(id, nombre, apellido, fechaNacimiento, totalCreditos);
    }

    @Override
    protected List<Estudiante> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        while(resultSet.next()) {
            estudiantes.add(this.resultSetToEntity(resultSet));
        }
        return estudiantes;
    }

}
