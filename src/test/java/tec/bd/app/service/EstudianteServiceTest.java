package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.bd.SetDB;
import tec.bd.app.domain.Estudiante;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class EstudianteServiceTest {

    private EstudianteService estudianteService;

    @BeforeEach
    public void setUp() throws Exception{
        Set<Estudiante> estudiantesTable = new HashSet<>(){{
            add(new Estudiante(1,"Juan", "Perez", 20));
            add(new Estudiante(5,"Adriana", "Hernandez", 22));
            add(new Estudiante(10,"Alberto", "Brenes", 23));
        }};
        SetDB database = new SetDB(estudiantesTable);
        this.estudianteService = new EstudianteService(database);
    }

    @Test
    public void getById_ExistingId() throws Exception{
        var estudiante = this.estudianteService.getById(1);

        assertThat(estudiante.getNombre()).isEqualTo("Juan");
    }

    @Test
    public void getById_NonExistingId() throws Exception{
        var estudiante = this.estudianteService.getById(0);
    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {
        SetDB database = new SetDB(Collections.emptySet());
        EstudianteService LocalEstudianteService = new EstudianteService(database);
        var estudiantes = LocalEstudianteService.getAll();

        assertThat(estudiantes).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception{
        var estudiantes = this.estudianteService.getAll();

        assertThat(estudiantes).hasSize(3);
    }

    @Test
    public void addNewStudent_NonExistentStudentId() throws Exception{
        var karol = new Estudiante(2, "Karol", "Arguedas",21);
        estudianteService.addNew(karol);

        var estudiantes = this.estudianteService.getAll();

        assertThat(estudiantes).hasSize(4);
    }

    @Test
    public void addNewStudent_ExistentStudentId() throws Exception{
        var karol = new Estudiante(1, "Karol", "Arguedas",21);
        estudianteService.addNew(karol);

        var estudiantes = this.estudianteService.getAll();

        assertThat(estudiantes).hasSize(3);
    }

    @Test
    public void deleteStudent_ExistentStudentId() throws Exception{
        estudianteService.deleteEstudiante(1);

        assertThat(estudianteService.getById(1)).isNull();
    }

    @Test
    public void deleteStudent_NonExistentStudentId() throws Exception{
        estudianteService.deleteEstudiante(0);

        assertThat(estudianteService.getById(0)).isNull();
    }

    @Test
    public void updateStudent_ExistentStudentId() throws Exception{
        var Juanda = new Estudiante(1,"Juanda", "Perez",22);
        estudianteService.updateEstudiante(Juanda);

        var estudiante = this.estudianteService.getById(1);

        assertThat(estudiante.getNombre()).isEqualTo(Juanda.getNombre());
    }

    @Test
    public void updateStudent_NonExistentStudentId() throws Exception{
        var Juanda = new Estudiante(0,"Juanda", "Perez",22);
        estudianteService.updateEstudiante(Juanda);
    }

}
