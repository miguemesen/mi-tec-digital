package tec.bd.app.dao.mysql.routine;

import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EstudianteMySqlDAOImpl extends GenericMySqlDAOImpl<Estudiante,Integer> implements EstudianteDAO {
    public EstudianteMySqlDAOImpl(DBProperties dbProperties) {
        super(dbProperties);
    }

    private static final String FIND_ESTUDIANTE_BY_LASTNAME = "{call find_estudiante_by_last_name(?)}";
    private static final String FIND_ALL_ESTUDIANTE_LASTNAME_SORTED = "{call find_all_estudiante_sorted_lastname()}";
    private static final String FIND_ALL_ESTUDIANTE = "{call find_all_estudiante()}";
    private static final String FIND_ESTUDIANTE_BY_ID = "{call find_estudiante_by_id(?)}";
    private static final String SAVE_ESTUDIANTE = "{call save_estudiante(?,?,?,?,?)}";
    private static final String UPDATE_ESTUDIANTE = "update estudiante set nombre = ?, apellido = ?, fecha_nacimiento = ?, total_creditos = ? where id = ?";
    private static final String DELETE_ESTUDIANTE = "{call delete_estudiante(?)}";


    @Override
    public List<Estudiante> findByLastName(String lastName) {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(FIND_ESTUDIANTE_BY_LASTNAME)){

            statement.setString(1,lastName);
            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Estudiante> findAllSortByLastName() {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(FIND_ALL_ESTUDIANTE_LASTNAME_SORTED)){

            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Estudiante> findAll() {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(FIND_ALL_ESTUDIANTE)){

            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Estudiante> findById(Integer integer) {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(FIND_ESTUDIANTE_BY_ID)){

            statement.setInt(1, integer);
            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet).stream().findFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Estudiante estudiante) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(SAVE_ESTUDIANTE)){

            statement.setInt(1,estudiante.getCarne());
            statement.setString(2,estudiante.getNombre());
            statement.setString(3,estudiante.getApellido());
            statement.setDate(4, (Date) estudiante.getFechaNacimiento());
            statement.setInt(5,estudiante.getTotalCreditos());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Estudiante> update(Estudiante estudiante) {
        try (Connection connection = this.dbProperties.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_ESTUDIANTE)){
                connection.setAutoCommit(false);

                statement.setString(1,estudiante.getNombre());
                statement.setString(2,estudiante.getApellido());
                statement.setDate(3, (Date) estudiante.getFechaNacimiento());
                statement.setInt(4,estudiante.getTotalCreditos());
                statement.setInt(5,estudiante.getCarne());

                statement.executeUpdate();

                connection.commit();

            } catch (SQLException e) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(estudiante);
    }

    @Override
    public void delete(Integer integer) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(DELETE_ESTUDIANTE)){

            statement.setInt(1, integer);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
