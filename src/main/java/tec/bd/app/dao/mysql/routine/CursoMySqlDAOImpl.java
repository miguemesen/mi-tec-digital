package tec.bd.app.dao.mysql.routine;

import tec.bd.app.dao.CursoDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Curso;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



public class CursoMySqlDAOImpl extends GenericMySqlDAOImpl<Curso,Integer> implements CursoDAO {
    public CursoMySqlDAOImpl(DBProperties dbProperties) {
        super(dbProperties);
    }

    private static final String All_COURSES_PROCEDURE = "{call all_courses()}";
    private static final String FIND_COURSE_BY_ID_PROCEDURE = "{call find_course_by_id(?)}";
    private static final String FIND_COURSE_BY_DEPARTMENT_PROCEDURE = "{call find_course_by_department(?)}";
    private static final String SAVE_COURSE_PROCEDURE = "{call save_course(?,?,?,?)}";
    private static final String UPDATE_COURSE_PROCEDURE = "{call update_course(?,?,?,?)}";
    private static final String DELETE_COURSE_PROCEDURE = "{call delete_course(?)}";

    @Override
    public List<Curso> findByDepartment(String department) {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(FIND_COURSE_BY_DEPARTMENT_PROCEDURE)){

            statement.setString(1,department);
            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Curso> findAll() {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(All_COURSES_PROCEDURE)){
            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }



    @Override
    public Optional<Curso> findById(Integer integer) {
        ResultSet resultSet;
        try (Connection connection = this.dbProperties.getConnection();
             CallableStatement statement = connection.prepareCall(FIND_COURSE_BY_ID_PROCEDURE)){

            statement.setInt(1,integer);
            resultSet = statement.executeQuery();
            return this.resultSetToList(resultSet).stream().findFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Curso curso) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(SAVE_COURSE_PROCEDURE)){

            statement.setInt(1,curso.getId());
            statement.setString(2,curso.getNombre());
            statement.setString(3,curso.getDepartamento());
            statement.setInt(4,curso.getCreditos());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Curso> update(Curso curso) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(UPDATE_COURSE_PROCEDURE)){

            statement.setInt(1,curso.getId());
            statement.setString(2,curso.getNombre());
            statement.setString(3,curso.getDepartamento());
            statement.setInt(4,curso.getCreditos());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(curso);
    }

    @Override
    public void delete(Integer integer) {
        try (Connection connection = this.dbProperties.getConnection();
            CallableStatement statement = connection.prepareCall(DELETE_COURSE_PROCEDURE)){
            statement.setInt(1,integer);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected Curso resultSetToEntity(ResultSet resultSet) throws SQLException {
        var id = resultSet.getInt("id");
        var nombre = resultSet.getString("nombre");
        var creditos = resultSet.getInt("creditos");
        var departamento = resultSet.getString("departamento");

        return new Curso(id, nombre, departamento, creditos);
    }

    @Override
    protected List<Curso> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        while(resultSet.next()) {
            cursos.add(this.resultSetToEntity(resultSet));
        }
        return cursos;
    }


}
