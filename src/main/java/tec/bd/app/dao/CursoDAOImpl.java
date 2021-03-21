package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;

import java.util.List;

public class CursoDAOImpl extends GenericSetDAOImpl<Curso, Integer> implements CursoDAO{

    public CursoDAOImpl (SetDB setDb, Class<Curso> clazz){
        super(setDb, clazz);
    }

    @Override
    protected Curso rowToEntity(Row row) {
        return null;
    }

    @Override
    protected Row entityToRow(Curso e) {
        return null;
    }

    @Override
    public List<Curso> findByDepartment(String department) {
        return null;
    }
}
