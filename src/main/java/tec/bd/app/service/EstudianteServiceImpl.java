
package tec.bd.app.service;

import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.domain.Estudiante;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EstudianteServiceImpl implements EstudianteService {

    private EstudianteDAO estudianteDAO;

    public EstudianteServiceImpl(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    @Override
    public List<Estudiante> getAll() {
        return this.estudianteDAO.findAll();
    }

    @Override
    public Optional<Estudiante> getById(int carne) {
        if (carne > 0){
            return this.estudianteDAO.findById(carne);
        }
        return Optional.empty();

    }

    @Override
    public void addNew(Estudiante e) {
        Optional<Estudiante> estudiante = this.estudianteDAO.findById(e.getCarne());
        if(!estudiante.isPresent()) {
            this.estudianteDAO.save(e);
        }
    }

    @Override
    public Optional<Estudiante> updateStudent(Estudiante e) {
        if (estudianteDAO.findById(e.getCarne()).isPresent()){
            return this.estudianteDAO.update(e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteStudent(int carne) {
        if (this.estudianteDAO.findById(carne).isEmpty()){
            return;
        }
        this.estudianteDAO.delete(carne);
    }

    @Override
    public List<Estudiante> getStudentsSortedByLastName() {
        return this.estudianteDAO.findAllSortByLastName();
    }

    @Override
    public List<Estudiante> getStudentsByLastName(String lastName) {
        if (!estudianteDAO.findByLastName(lastName).isEmpty()){
            return this.estudianteDAO.findByLastName(lastName);
        }
        return Collections.emptyList();
    }

}