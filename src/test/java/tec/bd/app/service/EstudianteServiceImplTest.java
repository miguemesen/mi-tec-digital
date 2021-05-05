
package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.domain.Estudiante;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceImplTest {

    @Mock
    private EstudianteDAO estudianteDAO;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.estudianteDAO.findAll()).willReturn(Collections.emptyList());

        var estudiantes = this.estudianteService.getAll();

        verify(this.estudianteDAO, times(1)).findAll();

        assertThat(estudiantes).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        given(this.estudianteDAO.findAll()).willReturn(List.of(
                mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class)
        ));

        var estudiantes = this.estudianteService.getAll();

        verify(this.estudianteDAO, times(1)).findAll();

        assertThat(estudiantes).hasSize(3);

    }


    @Test
    public void getStudentsSortedByLastName() throws Exception {

    }

    @Test
    public void getStudentsByLastName() throws Exception {
        //TODO: hay que implementarlo
    }

}