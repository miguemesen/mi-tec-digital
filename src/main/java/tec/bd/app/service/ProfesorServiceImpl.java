package tec.bd.app.service;

import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.domain.Profesor;

import java.util.List;
import java.util.Optional;

public class ProfesorServiceImpl implements ProfesorService{

    private ProfesorDAO profesorDAO;

    public ProfesorServiceImpl(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    @Override
    public List<Profesor> getAll() {
        return this.profesorDAO.findAll();
    }

    @Override
    public Optional<Profesor> getById(int id) {
        if (id > 0){
            return this.profesorDAO.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public void addNew(Profesor p) {
        Optional<Profesor> profesor = this.profesorDAO.findById(p.getId());
        if (!profesor.isPresent()){
            this.profesorDAO.save(p);
        }
    }

    @Override
    public Optional<Profesor> updateProfesor(Profesor p) {
        if (profesorDAO.findById(p.getId()).isPresent()){
            return this.profesorDAO.update(p);
        }
        return Optional.empty();
    }

    @Override
    public void deleteProfesor(int id) {
//        if (this.profesorDAO.findById(id).isEmpty()){
//            return;
//        }
        this.profesorDAO.delete(id);
    }

    @Override
    public List<Profesor> getProfesorByCity(String department) {
        return this.profesorDAO.findByCity(department);
    }
}
