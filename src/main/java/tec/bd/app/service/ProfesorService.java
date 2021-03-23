package tec.bd.app.service;

import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    List<Profesor> getAll();

    Optional<Profesor> getById(int id);

    void addNew(Profesor p);

    Optional<Profesor> updateProfesor(Profesor p);

    void deleteProfesor(int id);

    List<Profesor> getProfesorByCity(String department);
}
