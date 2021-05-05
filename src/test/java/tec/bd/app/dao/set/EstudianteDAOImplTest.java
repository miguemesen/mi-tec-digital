package tec.bd.app.dao.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.dao.set.EstudianteDAOImpl;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Estudiante;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class EstudianteDAOImplTest {

//    private EstudianteDAOImpl estudianteDAO;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//
//        var juanId = new RowAttribute("id", 1);
//        var juanNombre = new RowAttribute("nombre", "Juan");
//        var juanApellido = new RowAttribute("apellido", "Perez");
//        var juanEdad = new RowAttribute("edad", 20);
//        var juanRow = new Row(new RowAttribute[]{ juanId, juanNombre, juanApellido, juanEdad });
//
//        var anaId = new RowAttribute("id", 4);
//        var anaNombre = new RowAttribute("nombre", "Ana");
//        var anaApellido = new RowAttribute("apellido", "Perez");
//        var anaEdad = new RowAttribute("edad", 25);
//        var anaRow = new Row(new RowAttribute[]{ anaId, anaNombre, anaApellido, anaEdad });
//
//        var mariaId = new RowAttribute("id", 3);
//        var mariaNombre = new RowAttribute("nombre", "Maria");
//        var mariaApellido = new RowAttribute("apellido", "Rojas");
//        var mariaEdad = new RowAttribute("edad", 21);
//        var mariaRow = new Row(new RowAttribute[]{ mariaId, mariaNombre, mariaApellido, mariaEdad });
//
//        var pedroId = new RowAttribute("id", 2);
//        var pedroNombre = new RowAttribute("nombre", "Pedro");
//        var pedroApellido = new RowAttribute("apellido", "Infante");
//        var pedroEdad = new RowAttribute("edad", 23);
//        var pedroRow = new Row(new RowAttribute[]{ pedroId, pedroNombre, pedroApellido, pedroEdad });
//
//        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
//        tables.put(Estudiante.class, new HashSet<>() {{
//            add(juanRow);
//            add(mariaRow);
//            add(pedroRow);
//            add(anaRow);
//        }});
//        var setDB = new SetDB(tables);
//        this.estudianteDAO = new EstudianteDAOImpl(setDB, Estudiante.class);
//    }
//
//    @Test
//    public void findAll() throws Exception {
//        var actual = this.estudianteDAO.findAll();
//        assertThat(actual).hasSize(4);
//    }
//
//    @Test
//    public void findById() throws Exception {
//        var student = this.estudianteDAO.findById(3);
//        assertThat(student.get().getCarne()).isEqualTo(3);
//        assertThat(student.get().getNombre()).isEqualTo("Maria");
//        assertThat(student.get().getApellido()).isEqualTo("Rojas");
//
//    }
//
//    @Test
//    public void save() throws Exception {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = simpleDateFormat.parse("01/01/2000");
//
//        this.estudianteDAO.save(new Estudiante(40, "Jorge", "Chacon", date,15));
//        var estudiante = this.estudianteDAO.findById(40);
//        assertThat(this.estudianteDAO.findAll()).hasSize(5);
//        assertThat(estudiante.isPresent()).isTrue();
//        assertThat(estudiante.get().getCarne()).isEqualTo(40);
//        assertThat(estudiante.get().getNombre()).isEqualTo("Jorge");
//        assertThat(estudiante.get().getApellido()).isEqualTo("Chacon");
//    }
//
//    @Test
//    public void update() throws Exception {
//        var current = this.estudianteDAO.findById(3);
//        current.get().setApellido("Rodriguez");
//        var actual = this.estudianteDAO.update(current.get());
//        assertThat(this.estudianteDAO.findAll()).hasSize(4);
//        assertThat(actual.get().getCarne()).isEqualTo(3);
//        assertThat(actual.get().getNombre()).isEqualTo("Maria");
//        assertThat(actual.get().getApellido()).isEqualTo("Rodriguez");
//    }
//
//    @Test
//    public void delete() throws Exception {
//        this.estudianteDAO.delete(2);
//        assertThat(this.estudianteDAO.findAll()).hasSize(3);
//    }
//
//    @Test
//    public void findByLastName() throws Exception {
//        var estudiante2 = this.estudianteDAO.findByLastName("Rojas");
//        var estudiante3 = this.estudianteDAO.findByLastName("Infante");
//        assertThat(estudiante2.get(0).getNombre()).isEqualTo("Maria");
//        assertThat(estudiante3.get(0).getNombre()).isEqualTo("Pedro");
//    }
//
//    @Test
//    public void findByLastName2() throws Exception {
//        var estudiantes = this.estudianteDAO.findByLastName("Perez");
//        assertThat(estudiantes.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void findAllSortedByLastName() throws Exception {
//        var estudiantes = this.estudianteDAO.findAllSortByLastName();
//
//        assertThat(estudiantes.get(0).getApellido()).isEqualTo("Infante");
//        assertThat(estudiantes.get(1).getApellido()).isEqualTo("Perez");
//        assertThat(estudiantes.get(2).getApellido()).isEqualTo("Perez");
//        assertThat(estudiantes.get(3).getApellido()).isEqualTo("Rojas");
//
//    }


}