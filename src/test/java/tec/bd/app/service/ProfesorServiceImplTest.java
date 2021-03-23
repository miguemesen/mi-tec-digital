package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceImplTest {

    @Mock
    private ProfesorDAO profesorDAO;

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.profesorDAO.findAll()).willReturn(Collections.emptyList());

        var profesores = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).findAll();

        assertThat(profesores).hasSize(0);
    }


    @Test
    public void getAllTest() throws Exception {

        given(this.profesorDAO.findAll()).willReturn(List.of(
                mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)
        ));

        var profesores = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).findAll();

        assertThat(profesores).hasSize(3);

    }

    @Test
    public void addNewProfesor() throws Exception {

        given(this.profesorDAO.findAll()).willReturn(
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class), mock(Profesor.class))
        );

        var profesoresBeforeSave = this.profesorService.getAll();

        var ignacio = new Profesor(52, "Ignacio", "Jimenez", "Aragon");
        profesorService.addNew(ignacio);

        var profesoresAfterSave = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).save(ignacio);
        assertThat(profesoresAfterSave.size()).isGreaterThan(profesoresBeforeSave.size());
    }


    @Test
    public void deleteProfesor() throws Exception {

        given(this.profesorDAO.findAll()).willReturn(
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
                List.of(mock(Profesor.class), mock(Profesor.class))
        );

        var profesoresBeforeSave = this.profesorService.getAll();

        profesorService.deleteProfesor(2);

        var profesoresAfterSave = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).delete(2);
        assertThat(profesoresAfterSave.size()).isLessThan(profesoresBeforeSave.size());
    }

    @Test
    public void updateProfesores() throws Exception {

        given(this.profesorDAO.findById(anyInt())).willReturn(
                Optional.of(mock(Profesor.class)),
                Optional.of(mock(Profesor.class))
        );

        var profesorBefore = this.profesorService.getById(2);

        var ignacio = new Profesor(53, "Ignacio", "Jimenez", "Aragon");
        profesorService.updateProfesor(ignacio);

        var profesorAfter = this.profesorService.getById(2);

        verify(this.profesorDAO, times(1)).update(ignacio);
        assertThat(profesorAfter).isNotSameAs(profesorBefore);
    }



}
