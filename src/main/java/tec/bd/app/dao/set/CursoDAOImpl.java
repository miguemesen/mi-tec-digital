package tec.bd.app.dao.set;

import tec.bd.app.dao.CursoDAO;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;

import java.util.List;
import java.util.stream.Collectors;

public class CursoDAOImpl extends GenericSetDAOImpl<Curso, Integer> implements CursoDAO {

    public CursoDAOImpl (SetDB setDb, Class<Curso> clazz){
        super(setDb, clazz);
    }

    @Override
    protected Curso rowToEntity(Row row) {
        var id = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var departamento = row.stringAttributeValue("departamento");
        var creditos = row.intAttributeValue("creditos");
        return new Curso(id,nombre,departamento,creditos);
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
