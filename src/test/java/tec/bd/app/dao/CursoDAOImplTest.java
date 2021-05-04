package tec.bd.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.dao.set.CursoDAOImpl;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class CursoDAOImplTest {

    private CursoDAOImpl cursoDAO;

    @BeforeEach
    public void setUp() throws Exception{
        var basesDeDatosId = new RowAttribute("id", 1);
        var basesDeDatosNombre = new RowAttribute("nombre", "Bases de Datos");
        var basesDeDatosDepartamento = new RowAttribute("departamento", "Informatica");
        var basesDeDatosCreditos = new RowAttribute("creditos", 4);
        var basesDeDatos = new Row(new RowAttribute[]{ basesDeDatosId,basesDeDatosNombre,basesDeDatosDepartamento,basesDeDatosCreditos});

        var geneticaId = new RowAttribute("id", 10);
        var geneticaNombre = new RowAttribute("nombre", "Genetica");
        var geneticaDepartamento = new RowAttribute("departamento", "Biologia");
        var geneticaCreditos = new RowAttribute("creditos", 4);
        var genetica = new Row(new RowAttribute[]{ geneticaId,geneticaNombre,geneticaDepartamento,geneticaCreditos});

        var introBioId = new RowAttribute("id", 20);
        var introBioNombre = new RowAttribute("nombre", "Intro Biologia");
        var introBioDepartamento = new RowAttribute("departamento", "Biologia");
        var introBioCreditos = new RowAttribute("creditos", 4);
        var introBio = new Row(new RowAttribute[]{ introBioId,introBioNombre,introBioDepartamento,introBioCreditos});

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Curso.class, new HashSet<>(){{
            add(basesDeDatos);
            add(genetica);
            add(introBio);
        }});

        var setDb = new SetDB(tables);
        this.cursoDAO = new CursoDAOImpl(setDb, Curso.class);
    }


    @Test
    public void findAll() throws Exception {
        var actual = this.cursoDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var curso = this.cursoDAO.findById(1);
        assertThat(curso.get().getId()).isEqualTo(1);
        assertThat(curso.get().getNombre()).isEqualTo("Bases de Datos");
        assertThat(curso.get().getDepartamento()).isEqualTo("Informatica");
        assertThat(curso.get().getCreditos()).isEqualTo(4);
    }

    @Test
    public void save() throws Exception {
        this.cursoDAO.save(new Curso(40, "Fisica", "Fisica", 4));
        var curso = this.cursoDAO.findById(40);
        assertThat(this.cursoDAO.findAll()).hasSize(4);
        assertThat(curso.isPresent()).isTrue();
        assertThat(curso.get().getId()).isEqualTo(40);
        assertThat(curso.get().getNombre()).isEqualTo("Fisica");
        assertThat(curso.get().getDepartamento()).isEqualTo("Fisica");
        assertThat(curso.get().getCreditos()).isEqualTo(4);
    }

    @Test
    public void update() throws Exception {
        var current = this.cursoDAO.findById(1);
        current.get().setDepartamento("Matematica");
        current.get().setCreditos(3);
        var actual = this.cursoDAO.update(current.get());
        assertThat(this.cursoDAO.findAll()).hasSize(3);
        assertThat(actual.get().getId()).isEqualTo(1);
        assertThat(actual.get().getNombre()).isEqualTo("Bases de Datos");
        assertThat(actual.get().getDepartamento()).isEqualTo("Matematica");
        assertThat(actual.get().getCreditos()).isEqualTo(3);
    }

    @Test
    public void delete() throws Exception {
        this.cursoDAO.delete(1);
        assertThat(this.cursoDAO.findAll()).hasSize(2);
    }

    @Test
    public void findByDepartamento() throws Exception {
        var curso1 = this.cursoDAO.findByDepartment("Informatica");
        assertThat(curso1.get(0).getNombre()).isEqualTo("Bases de Datos");
    }


}
