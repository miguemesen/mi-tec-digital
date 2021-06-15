package tec.bd.app;

import tec.bd.app.dao.*;
import tec.bd.app.dao.mysql.routine.CursoMySqlDAOImpl;
import tec.bd.app.dao.mysql.routine.EstudianteMySqlDAOImpl;
import tec.bd.app.dao.mysql.routine.ProfesorMySqlDAOImpl;
import tec.bd.app.dao.set.CursoDAOImpl;
import tec.bd.app.dao.set.EstudianteDAOImpl;
import tec.bd.app.dao.set.ProfesorDAOImpl;
import tec.bd.app.database.mariaDB.DBProperties;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;
import tec.bd.app.service.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ApplicationContext {

    private SetDB setDB;

    private EstudianteDAO estudianteSetDAO;
    private EstudianteService estudianteServiceSet;

    private CursoDAO cursoSetDAO;
    private CursoService cursoService;

    private ProfesorDAO profesorSetDAO;
    private ProfesorService profesorService;

    DBProperties dbProperties = new DBProperties("jdbc:mariadb://localhost:3306/universidad","universidad_user","universidad_pass");

    private ApplicationContext() {

    }

    public static ApplicationContext init() {
        ApplicationContext applicationContext = new ApplicationContext();

        applicationContext.profesorSetDAO = initProfesorMysqlDAO(applicationContext.dbProperties);
        applicationContext.profesorService = initProfesorService(applicationContext.profesorSetDAO);

        applicationContext.estudianteSetDAO = initEstudianteMysqlDAO(applicationContext.dbProperties);
        applicationContext.estudianteServiceSet = initEstudianteSetService(applicationContext.estudianteSetDAO);

        applicationContext.cursoSetDAO = initCursoMysqlDAO(applicationContext.dbProperties);
        applicationContext.cursoService = initCursoService(applicationContext.cursoSetDAO);

//        applicationContext.setDB = initSetDB();
//        applicationContext.estudianteSetDAO = initEstudianteSetDAO(applicationContext.setDB);
//        applicationContext.estudianteServiceSet = initEstudianteSetService(applicationContext.estudianteSetDAO);
//        applicationContext.cursoSetDAO = initCursoSetDAO(applicationContext.setDB);
//        applicationContext.cursoService = initCursoService(applicationContext.cursoSetDAO);
//        applicationContext.profesorSetDAO = initProfesorSetDAO(applicationContext.setDB);
//        applicationContext.profesorService = initProfesorService(applicationContext.profesorSetDAO);
        return applicationContext;
    }

    private static SetDB initSetDB() {
        // Registros de la tabla estudiante
        var juanId = new RowAttribute("id", 1);
        var juanNombre = new RowAttribute("nombre", "Juan");
        var juanApellido = new RowAttribute("apellido", "Perez");
        var juanEdad = new RowAttribute("edad", 20);
        var juanRow = new Row(new RowAttribute[]{ juanId, juanNombre, juanApellido, juanEdad });

        var mariaId = new RowAttribute("id", 3);
        var mariaNombre = new RowAttribute("nombre", "Maria");
        var mariaApellido = new RowAttribute("apellido", "Rojas");
        var mariaEdad = new RowAttribute("edad", 21);
        var mariaRow = new Row(new RowAttribute[]{ mariaId, mariaNombre, mariaApellido, mariaEdad });

        var pedroId = new RowAttribute("id", 2);
        var pedroNombre = new RowAttribute("nombre", "Pedro");
        var pedroApellido = new RowAttribute("apellido", "Infante");
        var pedroEdad = new RowAttribute("edad", 23);
        var pedroRow = new Row(new RowAttribute[]{ pedroId, pedroNombre, pedroApellido, pedroEdad });

        var raquelId = new RowAttribute("id", 10);
        var raquelNombre = new RowAttribute("nombre", "Raquel");
        var raquelApellido = new RowAttribute("apellido", "Rojas");
        var raquelEdad = new RowAttribute("edad", 25);
        var raquelRow = new Row(new RowAttribute[]{ raquelId, raquelNombre, raquelApellido, raquelEdad });

        // ---------------------------------------------------------------
        // Registros de la tabla curso
        // ---------------------------------------------------------------

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

        // ---------------------------------------------------------------
        // Registros de la tabla profesor
        // ---------------------------------------------------------------

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
        tables.put(Estudiante.class, new HashSet<>() {{
            add(juanRow);
            add(mariaRow);
            add(pedroRow);
            add(raquelRow);
        }});

        // Agregar las filas de curso y estudiante a tables
        tables.put(Curso.class, new HashSet<>(){{
            add(basesDeDatos);
            add(genetica);
            add(introBio);
        }});

        // tables.put(Profesor.class, new HashSet<>() {{ ... }}
        tables.put(Profesor.class, new HashSet<>(){{
            add(albertoRow);
            add(sofiaRow);
            add(isaacRow);
        }});

        return new SetDB(tables);
    }




    private static EstudianteDAO initEstudianteMysqlDAO(DBProperties dbProperties){
        return new EstudianteMySqlDAOImpl(dbProperties);
    }

    private static CursoDAO initCursoMysqlDAO(DBProperties dbProperties){
        return new CursoMySqlDAOImpl(dbProperties);
    }

    private static ProfesorDAO initProfesorMysqlDAO(DBProperties dbProperties){
        return new ProfesorMySqlDAOImpl(dbProperties);
    }




    //___________________________________________________________________________________________

    private static EstudianteDAO initEstudianteSetDAO(SetDB setDB) {
        return new EstudianteDAOImpl(setDB, Estudiante.class);
    }
    private static EstudianteService initEstudianteSetService(EstudianteDAO estudianteDAO) {
        return new EstudianteServiceImpl(estudianteDAO);
    }

    private static CursoDAO initCursoSetDAO(SetDB setDB){
        return new CursoDAOImpl(setDB, Curso.class);
    }
    private static CursoService initCursoService(CursoDAO cursoDAO){
        return new CursoServiceImpl(cursoDAO);
    }

    private static ProfesorDAO initProfesorSetDAO(SetDB setDB){
        return new ProfesorDAOImpl(setDB, Profesor.class);
    }
    private static ProfesorService initProfesorService(ProfesorDAO profesorDAO){
        return new ProfesorServiceImpl(profesorDAO);
    }


    public SetDB getSetDB() {
        return this.setDB;
    }

    public EstudianteDAO getEstudianteSetDAO() {
        return this.estudianteSetDAO;
    }

    public EstudianteService getEstudianteServiceSet() {
        return this.estudianteServiceSet;
    }

    public CursoDAO getCursoSetDAO(){
        return this.cursoSetDAO;
    }

    public CursoService getCursoService(){
        return this.cursoService;
    }

    public ProfesorDAO getProfesorSetDAO() {
        return profesorSetDAO;
    }

    public ProfesorService getProfesorService() {
        return profesorService;
    }
}