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
        return null;
    }

    @Override
    public Optional<Profesor> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void addNew(Profesor p) {

    }

    @Override
    public Optional<Profesor> updateProfesor(Profesor p) {
        return Optional.empty();
    }

    @Override
    public void deleteProfesor(int id) {

    }
}
