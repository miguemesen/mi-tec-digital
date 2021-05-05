package tec.bd.app.dao.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.dao.set.ProfesorDAOImpl;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Profesor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class ProfesorDAOImplTest {

    private ProfesorDAOImpl profesorDAO;

    @BeforeEach
    public void setUp() throws Exception{
        var albertoId = new RowAttribute("id", 100);
        var albertoNombre = new RowAttribute("nombre", "Alberto");
        var albertoApellido = new RowAttribute("apellido", "Vargas");
        var albertoCiudad = new RowAttribute("ciudad", "Santa Ana");
        var albertoRow = new Row(new RowAttribute[]{albertoId,albertoNombre, albertoApellido,albertoCiudad});

        var isaacId = new RowAttribute("id", 101);
        var isaacNombre = new RowAttribute("nombre", "Isaac");
        var isaacApellido = new RowAttribute("apellido", "Ramirez");
        var isaacCiudad = new RowAttribute("ciudad", "Cartago");
        var isaacRow = new Row(new RowAttribute[]{isaacId,isaacNombre, isaacApellido,isaacCiudad});

        var sofiaId = new RowAttribute("id", 102);
        var sofiaNombre = new RowAttribute("nombre", "Sofia");
        var sofiaApellido = new RowAttribute("apellido", "Hernandez");
        var sofiaCiudad = new RowAttribute("ciudad", "Uvita");
        var sofiaRow = new Row(new RowAttribute[]{sofiaId,sofiaNombre, sofiaApellido,sofiaCiudad});

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Profesor.class, new HashSet<>(){{
            add(albertoRow);
            add(isaacRow);
            add(sofiaRow);
        }});
        var setDB = new SetDB(tables);
        this.profesorDAO = new ProfesorDAOImpl(setDB,Profesor.class);
    }

    @Test
    public void findAll() throws Exception{
        var actual = this.profesorDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var profesor = this.profesorDAO.findById(101);
        assertThat(profesor.get().getId()).isEqualTo(101);
        assertThat(profesor.get().getNombre()).isEqualTo("Isaac");
        assertThat(profesor.get().getApellido()).isEqualTo("Ramirez");
        assertThat(profesor.get().getCiudad()).isEqualTo("Cartago");
    }

    @Test
    public void save() throws Exception {
        assertThat(this.profesorDAO.findAll()).hasSize(3);
        this.profesorDAO.save(new Profesor(80, "Jorge", "Chacon", "Alajuela"));
        var profesor = this.profesorDAO.findById(80);
        assertThat(this.profesorDAO.findAll()).hasSize(4);
        assertThat(profesor.isPresent()).isTrue();
        assertThat(profesor.get().getId()).isEqualTo(80);
        assertThat(profesor.get().getNombre()).isEqualTo("Jorge");
        assertThat(profesor.get().getApellido()).isEqualTo("Chacon");
        assertThat(profesor.get().getCiudad()).isEqualTo("Alajuela");
    }

    @Test
    public void update() throws Exception {
        var current = this.profesorDAO.findById(101);
        current.get().setApellido("Rodriguez");
        current.get().setCiudad("Perez Zeledon");
        var actual = this.profesorDAO.update(current.get());
        assertThat(this.profesorDAO.findAll()).hasSize(3);
        assertThat(actual.get().getId()).isEqualTo(101);
        assertThat(actual.get().getNombre()).isEqualTo("Isaac");
        assertThat(actual.get().getApellido()).isEqualTo("Rodriguez");
        assertThat(actual.get().getCiudad()).isEqualTo("Perez Zeledon");
    }

    @Test
    public void delete() throws Exception {
        assertThat(this.profesorDAO.findAll()).hasSize(3);
        this.profesorDAO.delete(100);
        assertThat(this.profesorDAO.findAll()).hasSize(2);
    }

    @Test
    public void findByCity() throws Exception {
        var profesor1 = this.profesorDAO.findByCity("Cartago");
        var profesor2 = this.profesorDAO.findByCity("Uvita");
        assertThat(profesor1.get(0).getNombre()).isEqualTo("Isaac");
        assertThat(profesor2.get(0).getNombre()).isEqualTo("Sofia");
    }
}
