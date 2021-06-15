package tec.bd.app.dao.mysql.routine;

import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Profesor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProfesorMySqlDAOImpl extends GenericMySqlDAOImpl<Profesor,Integer> implements ProfesorDAO {
    public ProfesorMySqlDAOImpl(DBProperties dbProperties) {
        super(dbProperties);
    }

    private static final String ALL_PROFESORES_PROCEDURE = "{call all_profesores()}";
    private static final String FIND_PROFESOR_BY_ID_PROCEDURE = "{call find_profesor_by_id(?)}";
    private static final String FIND_PROFESOR_BY_CITY_PROCEDURE = "{call find_profesor_by_city(?)}";
    private static final String SAVE_PROFESOR_PROCEDURE = "{call save_profesor(?,?,?,?)}";
    private static final String UPDATE_PROFESOR_PROCEDURE = "{call update_profesor(?,?,?,?)}";
    private static final String DELETE_PROFESOR_PROCEDURE = "{call delete_profesor(?)}";

    @Override
    public List<Profesor> findAll() {
        ResultSet resultSet;
        try(Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(ALL_PROFESORES_PROCEDURE)){

            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Profesor> findById(Integer integer) {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(FIND_PROFESOR_BY_ID_PROCEDURE)){

            statement.setInt(1,integer);
            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet).stream().findFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Profesor profesor) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(SAVE_PROFESOR_PROCEDURE)){

            statement.setInt(1,profesor.getId());
            statement.setString(2, profesor.getNombre());
            statement.setString(3,profesor.getApellido());
            statement.setString(4,profesor.getCiudad());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Profesor> update(Profesor profesor) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(UPDATE_PROFESOR_PROCEDURE)){

            statement.setInt(1,profesor.getId());
            statement.setString(2, profesor.getNombre());
            statement.setString(3,profesor.getApellido());
            statement.setString(4,profesor.getCiudad());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(profesor);
    }

    @Override
    public void delete(Integer integer) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(DELETE_PROFESOR_PROCEDURE)){

            statement.setInt(1, integer);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Profesor> findByCity(String city) {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
             CallableStatement statement = connection.prepareCall(FIND_PROFESOR_BY_CITY_PROCEDURE)){

            statement.setString(1,city);
            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
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
