package tec.bd.app.service;

import tec.bd.app.dao.CursoDAO;
import tec.bd.app.domain.Curso;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService{

    private CursoDAO cursoDAO;

    public CursoServiceImpl(CursoDAO cursoDAO){
        this.cursoDAO = cursoDAO;
    }


    @Override
    public List<Curso> getAll() {
        return this.cursoDAO.findAll();
    }

    @Override
    public Optional<Curso> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void addNew(Curso c) {

    }

    @Override
    public Optional<Curso> updateCurso(Curso c) {
        return Optional.empty();
    }

    @Override
    public void deleteCurso(int id) {

    }

    @Override
    public List<Curso> getByDepartment(String department) {
        if (department.isBlank() | department.isEmpty()){
            return Collections.emptyList();
        }
        return this.cursoDAO.findByDepartment(department);
    }
}
