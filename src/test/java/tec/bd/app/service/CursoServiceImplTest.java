package tec.bd.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tec.bd.app.dao.CursoDAO;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Estudiante;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CursoServiceImplTest {

    @Mock
    CursoDAO cursoDAO;

    @InjectMocks
    CursoServiceImpl cursoService;


    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.cursoDAO.findAll()).willReturn(Collections.emptyList());

        var cursos = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).findAll();

        assertThat(cursos).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        given(this.cursoDAO.findAll()).willReturn(List.of(
                mock(Curso.class), mock(Curso.class), mock(Curso.class)
        ));

        var cursos = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).findAll();

        assertThat(cursos).hasSize(3);

    }

    @Test
    public void addNewCurso() throws Exception {

        given(this.cursoDAO.findAll()).willReturn(
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class)),
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class), mock(Curso.class))
        );

        var cursosBeforeSave = this.cursoService.getAll();

        var quimica = new Curso(57, "Quimica", "Quimica", 4);
        cursoService.addNew(quimica);

        var cursosAfterSave = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).save(quimica);
        assertThat(cursosAfterSave.size()).isGreaterThan(cursosBeforeSave.size());
    }

    @Test
    public void deleteCurso() throws Exception {

        given(this.cursoDAO.findAll()).willReturn(
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class)),
                List.of(mock(Curso.class), mock(Curso.class))
        );

        var cursosBeforeSave = this.cursoService.getAll();

        cursoService.deleteCurso(2);

        var cursosAfterSave = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).delete(2);
        assertThat(cursosAfterSave.size()).isLessThan(cursosBeforeSave.size());
    }

    @Test
    public void updateCurso() throws Exception {

        given(this.cursoDAO.findById(anyInt())).willReturn(
                Optional.of(mock(Curso.class)),
                Optional.of(mock(Curso.class))
        );

        var cursoBefore = this.cursoService.getById(2);

        var quimica = new Curso(2, "Quimica", "Quimica", 4);
        cursoService.updateCurso(quimica);

        var cursoAfter = this.cursoService.getById(2);

        verify(this.cursoDAO, times(1)).update(quimica);
        assertThat(cursoAfter).isNotSameAs(cursoBefore);
    }

    @Test
    public void getByDepartment() throws Exception{

        List<Curso> cursos = Arrays.asList(mock(Curso.class), mock(Curso.class));

        given(cursoDAO.findByDepartment(anyString())).willReturn(cursos);

        var actual = this.cursoService.getByDepartment("Biologia");

        verify(cursoDAO, times(1)).findByDepartment("Biologia");

        assertThat(actual).hasSize(2);
    }

}
