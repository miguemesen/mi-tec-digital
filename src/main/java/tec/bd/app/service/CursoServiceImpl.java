package tec.bd.app.service;

import tec.bd.app.dao.CursoDAO;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Estudiante;

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
        if (id > 0){
            return this.cursoDAO.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public void addNew(Curso c) {
        Optional<Curso> curso = this.cursoDAO.findById(c.getId());
        if(!curso.isPresent()) {
            this.cursoDAO.save(c);
        }
    }

    @Override
    public Optional<Curso> updateCurso(Curso c) {
        if (cursoDAO.findById(c.getId()).isPresent()){
            return this.cursoDAO.update(c);
        }
        return Optional.empty();
    }

    @Override
    public void deleteCurso(int id) {
//        if (this.cursoDAO.findById(id).isEmpty()){
//            return;
//        }
        this.cursoDAO.delete(id);
    }

    @Override
    public List<Curso> getByDepartment(String department) {
        if (department.isBlank() | department.isEmpty()){
            return Collections.emptyList();
        }
        return this.cursoDAO.findByDepartment(department);
    }
}
