package tec.bd.app.dao.mariaDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.app.dao.CursoDAO;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.domain.Curso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CursoMySqlDAOImpl extends GenericMySqlDAOImpl<Curso, Integer> implements CursoDAO {

    private static final Logger LOG = LoggerFactory.getLogger(CursoMySqlDAOImpl.class);

    private static final String SQL_SELECT_CURSOS = "select id, nombre, creditos, departamento from curso;";
    private static final String SQL_SELECT_CURSO_ID = "select id, nombre, creditos, departamento from curso where id = %d";
    private static final String SQL_INSERT_CURSO = "insert into curso(id, nombre, creditos, departamento) values(%d, '%s', %d, '%s')";
    private static final String SQL_UPDATE_CURSO = "update curso set nombre = '%s', departamento = '%s', creditos = %d where id = %d";
    private static final String SQL_DELETE_CURSO = "delete from curso where id = %d";


    public CursoMySqlDAOImpl(DBProperties dbProperties) {
        super(dbProperties);
    }


    @Override
    public List<Curso> findByDepartment(String department) {
        return this.findAll().stream().filter(c -> c.getDepartamento().equals(department)).collect(Collectors.toList());
    }

    @Override
    public List<Curso> findAll() {
        return this.request(SQL_SELECT_CURSOS);
    }

    @Override
    public Optional<Curso> findById(Integer id) {
        var sql = String.format(SQL_SELECT_CURSO_ID, id);
        return this.request(sql).stream().findFirst();
    }

    @Override
    public void save(Curso curso) {
        var sql = String.format(SQL_INSERT_CURSO,
                curso.getId(),
                curso.getNombre(),
                curso.getCreditos(),
                curso.getDepartamento()
        );
        this.requestSave(sql);
    }

    @Override
    public Optional<Curso> update(Curso curso) {
        var sql = String.format(SQL_UPDATE_CURSO,
                curso.getNombre(),
                curso.getDepartamento(),
                curso.getCreditos(),
                curso.getId()
        );
        this.requestUpdate(sql);
        return Optional.of(curso);
    }

    @Override
    public void delete(Integer id) {
        var sql = String.format(SQL_DELETE_CURSO, id);
        this.requestDelete(sql);
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
