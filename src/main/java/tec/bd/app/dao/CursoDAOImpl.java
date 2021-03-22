package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;

import java.util.List;
import java.util.stream.Collectors;

public class CursoDAOImpl extends GenericSetDAOImpl<Curso, Integer> implements CursoDAO{

    public CursoDAOImpl (SetDB setDb, Class<Curso> clazz){
        super(setDb, clazz);
    }

    @Override
    protected Curso rowToEntity(Row row) {
        Curso curso = new Curso();
        curso.setId(row.intAttributeValue("id"));
        curso.setNombre(row.stringAttributeValue("nombre"));
        curso.setDepartamento(row.stringAttributeValue("departamento"));
        curso.setCreditos(row.intAttributeValue("creditos"));
        return curso;
    }

    @Override
    protected Row entityToRow(Curso c) {
        return new Row(new RowAttribute[]{
           new RowAttribute("id", c.getId()),
           new RowAttribute("nombre", c.getNombre()),
           new RowAttribute("departamento", c.getDepartamento()),
           new RowAttribute("creditos", c.getCreditos())
        });
    }

    @Override
    public List<Curso> findByDepartment(String department) {
        return this.findAll().stream().filter(c -> c.getDepartamento().equals(department)).collect(Collectors.toList());
    }
}
