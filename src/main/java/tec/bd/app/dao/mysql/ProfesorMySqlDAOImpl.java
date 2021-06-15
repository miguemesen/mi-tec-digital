package tec.bd.app.dao.mysql;

import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Profesor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesorMySqlDAOImpl extends GenericMySqlDAOImpl<Profesor,Integer> implements ProfesorDAO {



    private static final String SQL_SELECT_PROFESORES = "select id, nombre, apellido, ciudad from profesor;";
    private static final String SQL_SELECT_PROFESOR_ID = "select id, nombre, apellido, ciudad from profesor where id = %d";
    private static final String SQL_INSERT_PROFESOR = "insert into profesor(id, nombre, apellido, ciudad) values(%d, '%s', '%s', '%s')";
    private static final String SQL_UPDATE_PROFESOR = "update profesor set nombre = '%s', apellido = '%s', ciudad = '%s' where id = %d";
    private static final String SQL_DELETE_PROFESOR = "delete from profesor where id = %d";
    private static final String SQL_SELECT_PROFESOR_CIUDAD = "select id, nombre, apellido, ciudad from profesor where ciudad = '%s'";

    public ProfesorMySqlDAOImpl(DBProperties dbProperties) {
        super(dbProperties);
    }


    @Override
    public List<Profesor> findAll() {
        return this.request(SQL_SELECT_PROFESORES);
    }

    @Override
    public Optional<Profesor> findById(Integer id) {
        var sql = String.format(SQL_SELECT_PROFESOR_ID, id);
        return this.request(sql).stream().findFirst();
    }

    @Override
    public void save(Profesor profesor) {
        var sql = String.format(SQL_INSERT_PROFESOR,
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getCiudad()
        );
        this.requestSave(sql);
    }

    @Override
    public Optional<Profesor> update(Profesor profesor) {
        var sql = String.format(SQL_UPDATE_PROFESOR,
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getCiudad(),
                profesor.getId()
        );
        this.requestUpdate(sql);
        return Optional.of(profesor);
    }

    @Override
    public void delete(Integer id) {
        var sql = String.format(SQL_DELETE_PROFESOR, id);
        this.requestDelete(sql);
    }

    @Override
    public List<Profesor> findByCity(String ciudad) {
        var sql = String.format(SQL_SELECT_PROFESOR_CIUDAD, ciudad);
        return this.request(sql);
    }

    @Override
    protected Profesor resultSetToEntity(ResultSet resultSet) throws SQLException {
        var id = resultSet.getInt("id");
        var nombre = resultSet.getString("nombre");
        var apellido = resultSet.getString("apellido");
        var ciudad = resultSet.getString("ciudad");
        return new Profesor(id,nombre,apellido,ciudad);
    }

    @Override
    protected List<Profesor> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        while (resultSet.next()){
            profesores.add(this.resultSetToEntity(resultSet));
        }
        return profesores;
    }
}
