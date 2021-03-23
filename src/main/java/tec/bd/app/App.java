
package tec.bd.app;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;
import tec.bd.app.service.CursoService;
import tec.bd.app.service.EstudianteService;
import tec.bd.app.service.ProfesorService;

import java.util.Optional;

public class App  {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {

        ApplicationContext applicationContext = ApplicationContext.init();
        var estudianteService = applicationContext.getEstudianteServiceSet();
        var cursoService = applicationContext.getCursoService();
        var profesorService = applicationContext.getProfesorService();

        Options options = new Options();

        //------------------------------------------------------------------------
        // Opciones para estudiante

        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("Ayuda: ver argumentos soportados")
                .required(false)
                .build());

        options.addOption(Option.builder("ec")
                .longOpt("estudiante-nuevo")
                .hasArg(true)
                .numberOfArgs(4)
                .desc("Agregar Estudiante: carne, nombre y apellido son requeridos")
                .required(false)
                .build());

        options.addOption(Option.builder("er")
                .longOpt("estudiate-ver-todos")
                .desc("Ver todos los estudiantes")
                .required(false)
                .build());

        options.addOption(Option.builder("eid")
                .longOpt("estudiante-por-carne")
                .hasArg()
                .desc("Ver Estudiante por carne: ver un estudiante por carne")
                .required(false)
                .build());

        options.addOption(Option.builder("eu")
                .longOpt("estudiante-actualizar")
                .numberOfArgs(4)
                .desc("Actualizar estudiante: carne, nombre y apellido son requeridos")
                .required(false)
                .build());

        options.addOption(Option.builder("ed")
                .longOpt("estudiante-borrar")
                .hasArg(true)
                .desc("Borrar estudiante: el carne es requerido")
                .required(false)
                .build());

        options.addOption(Option.builder("erln")
                .longOpt("estudiante-ordenar-por-apellido")
                .desc("Ver los estudiantes ordenados por apellido")
                .required(false)
                .build());

        options.addOption(Option.builder("eln")
                .longOpt("estudiante-buscar-por-apellido")
                .hasArg(true)
                .desc("Buscar los estudiantes por apellido")
                .required(false)
                .build());

        //------------------------------------------------------------------------
        // Opciones para curso

        options.addOption(Option.builder("cc")
                .longOpt("curso-nuevo")
                .hasArg(true)
                .numberOfArgs(4)
                .desc("Agregar Curso: id, nombre, departamento, creditos son requeridos")
                .required(false)
                .build());

        options.addOption(Option.builder("cr")
                .longOpt("cursos-ver-todos")
                .desc("Ver todos los cursos.")
                .required(false)
                .build());

        options.addOption(Option.builder("cid")
                .longOpt("curso-por-id")
                .hasArg()
                .desc("Ver curso por id")
                .required(false)
                .build());

        options.addOption(Option.builder("cu")
                .longOpt("curso-actualizar")
                .numberOfArgs(4)
                .desc("Actualizar curso: id, nombre, departamento, creditos son requeridos")
                .required(false)
                .build());

        options.addOption(Option.builder("cd")
                .longOpt("curso-borrar")
                .hasArg(true)
                .desc("Borrar curso: el id es requerido")
                .required(false)
                .build());

        options.addOption(Option.builder("crd")
                .longOpt("curso-ver-por-departamento")
                .hasArg(true)
                .desc("Ver curso por departamento")
                .required(false)
                .build());

        //------------------------------------------------------------------------
        // Opciones para profesor

        options.addOption(Option.builder("pc")
                .longOpt("profesor-nuevo")
                .hasArg(true)
                .numberOfArgs(4)
                .desc("Agregar Profesor: id, nombre, apellido, ciudad son requeridos.")
                .required(false)
                .build());

        options.addOption(Option.builder("pr")
                .longOpt("profesor-ver-todos")
                .desc("Ver todos los profesores.")
                .required(false)
                .build());

        options.addOption(Option.builder("pid")
                .longOpt("profesor-por-id")
                .hasArg()
                .desc("Ver profesor por id")
                .required(false)
                .build());

        options.addOption(Option.builder("pu")
                .longOpt("profesor-actualizar")
                .numberOfArgs(4)
                .desc("Actualizar profesor: id, nombre, apellido, ciudad son requeridos")
                .required(false)
                .build());

        options.addOption(Option.builder("pd")
                .longOpt("profesor-borrar")
                .hasArg(true)
                .desc("Borrar profesor: el id es requerido")
                .required(false)
                .build());

        options.addOption(Option.builder("prc")
                .longOpt("profesor-por-ciudad")
                .hasArg(true)
                .desc("Ver profesores por ciudad")
                .required(false)
                .build());

        CommandLineParser parser = new DefaultParser();

        try {
            var cmd = parser.parse(options, args);

            //------------------------------------------------------------------------
            // Opciones para estudiante

            if(cmd.hasOption("er")) {
                // Mostrar todos los estudiantes
                showAllStudents(estudianteService);
            } else if(cmd.hasOption("eid")) {
                // Mostrar un estudiante por carne
                var carne = cmd.getOptionValue("eid");
                showStudentInfo(estudianteService, Integer.parseInt(carne));
            } else if(cmd.hasOption("ec")) {
                // Crear/Agregar un nuevo estudiante
                var newStudentValues = cmd.getOptionValues("ec");
                addNewStudent(estudianteService,
                        Integer.parseInt(newStudentValues[0]),
                        newStudentValues[1],
                        newStudentValues[2],
                        Integer.parseInt(newStudentValues[3]));
                showAllStudents(estudianteService);
            } else if(cmd.hasOption("ed")) {
                // Borrar/remover un estudiante
                var carne = cmd.getOptionValue("ed");
                deleteStudent(estudianteService, Integer.parseInt(carne));
                showAllStudents(estudianteService);
            } else if(cmd.hasOption("eu")) {
                // Actualizar datos de un estudiante
                var newStudentValues = cmd.getOptionValues("eu");
                updateStudent(estudianteService,
                        Integer.parseInt(newStudentValues[0]),
                        newStudentValues[1],
                        newStudentValues[2],
                        Integer.parseInt(newStudentValues[3]));
                showAllStudents(estudianteService);

            } else if(cmd.hasOption("erln")) {
                // Ver todos los estudiantes ordenados por apellido
                showAllStudentsSortByLastName(estudianteService);


            } else if(cmd.hasOption("eln")) {
                // Ejemplo: -eln Rojas
                // Ver todos los estudiantes con un apellido en particular
                var apellido = cmd.getOptionValue("eln");
                showAllStudentsWithLastname(estudianteService,apellido);


                //------------------------------------------------------------------------
                // Opciones para curso

            } else if(cmd.hasOption("cr")) {
                // Mostrar todos los cursos
                showAllCursos(cursoService);

            } else if(cmd.hasOption("cid")) {
                // Mostrar un curso por id
                var id = cmd.getOptionValue("cid");
                showCursoWithId(cursoService, Integer.parseInt(id));

            } else if(cmd.hasOption("cc")) {
                // Crear/Agregar un nuevo curso
                var newCursoValues = cmd.getOptionValues("cc");
                addNewCurso(cursoService,
                        Integer.parseInt(newCursoValues[0]),
                        newCursoValues[1],
                        newCursoValues[2],
                        Integer.parseInt(newCursoValues[3]));
                showAllCursos(cursoService);


            } else if(cmd.hasOption("cd")) {
                // Borrar/remover un curso

                var id = cmd.getOptionValue("cd");
                deleteCurso(cursoService, Integer.parseInt(id));
                showAllCursos(cursoService);


            } else if(cmd.hasOption("cu")) {
                // Actualizar datos de un curso

                var newCursoValues = cmd.getOptionValues("cu");
                updateCurso(cursoService,
                        Integer.parseInt(newCursoValues[0]),
                        newCursoValues[1],
                        newCursoValues[2],
                        Integer.parseInt(newCursoValues[3]));
                showAllCursos(cursoService);


            } else if(cmd.hasOption("crd")) {
                // Ver cursos por departamento

                var departamento = cmd.getOptionValue("crd");
                showAllCursosWithDepartment(cursoService,departamento);


                //------------------------------------------------------------------------
                // Opciones para profesor

            } else if(cmd.hasOption("pr")) {
                // Mostrar todos los profesores

                showAllProfesores(profesorService);

            } else if(cmd.hasOption("pid")) {
                // Mostrar un profesor por id
                var id = cmd.getOptionValue("pid");
                showProfesorWithId(profesorService,Integer.parseInt(id));

            } else if(cmd.hasOption("pc")) {
                // Crear/Agregar un nuevo profesor
                var newProfesorValues = cmd.getOptionValues("pc");
                addNewProfesor(profesorService,
                        Integer.parseInt(newProfesorValues[0]),
                        newProfesorValues[1],
                        newProfesorValues[2],
                        newProfesorValues[3]);
                showAllProfesores(profesorService);

            } else if(cmd.hasOption("pd")) {
                // Borrar/remover un profesor
                var id = cmd.getOptionValue("pd");
                deleteProfesor(profesorService, Integer.parseInt(id));
                showAllProfesores(profesorService);

            } else if(cmd.hasOption("pu")) {
                // Actualizar datos de un profesor
                var newProfesorValues = cmd.getOptionValues("pu");
                updateProfesor(profesorService,
                        Integer.parseInt(newProfesorValues[0]),
                        newProfesorValues[1],
                        newProfesorValues[2],
                        newProfesorValues[3]);
                showAllProfesores(profesorService);

            } else if(cmd.hasOption("prc")) {
                // Ver profesores por ciudad
                var ciudad = cmd.getOptionValue("prc");
                showAllProfesoresWithCiudad(profesorService,ciudad);


                //------------------------------------------------------------------------


            } else if(cmd.hasOption("h")) {
                var formatter = new HelpFormatter();
                formatter.printHelp( "Estos son los commandos soportados", options );
            } else {
                var formatter = new HelpFormatter();
                formatter.printHelp( "Estos son los comandos soportados", options );
            }
        } catch (ParseException pe) {
            System.out.println("Error al parsear los argumentos de linea de comandos!");
            System.out.println("Por favor, seguir las siguientes instrucciones:");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Los siguientes son los comandos soportados", options );
            System.exit(1);
        }
    }

    public static void showAllStudentsWithLastname(EstudianteService estudianteService, String lastname){
        System.out.println("\n\n");
        System.out.println("Lista de Estudiantes con apellido " + lastname);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Carne\t\tNombre\t\tApellido\tEdad");
        System.out.println("-----------------------------------------------------------------------");
        for (Estudiante estudiante : estudianteService.getStudentsByLastName(lastname)){
            System.out.println(estudiante.getCarne() + "\t\t" + estudiante.getNombre() + "\t\t" +estudiante.getApellido() + "\t\t"+ estudiante.getEdad());
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }

    public static void showAllStudents(EstudianteService estudianteService) {

        System.out.println("\n\n");
        System.out.println("Lista de Estudiantes");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Carne\t\tNombre\t\tApellido\tEdad");
        System.out.println("-----------------------------------------------------------------------");
        for(Estudiante estudiante : estudianteService.getAll()) {
            System.out.println(estudiante.getCarne() + "\t\t" + estudiante.getNombre() + "\t\t" +estudiante.getApellido() + "\t\t"+ estudiante.getEdad());
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }


    public static void showAllStudentsSortByLastName(EstudianteService estudianteService){
        System.out.println("\n\n");
        System.out.println("Lista de Estudiantes ordenada por apellido");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Carne\t\tNombre\t\tApellido\tEdad");
        System.out.println("-----------------------------------------------------------------------");
        for(Estudiante estudiante : estudianteService.getStudentsSortedByLastName()) {
            System.out.println(estudiante.getCarne() + "\t\t" + estudiante.getNombre() + "\t\t" +estudiante.getApellido() + "\t\t"+ estudiante.getEdad());
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }

    public static void showStudentInfo(EstudianteService estudianteService, int carne) {
        Optional<Estudiante> estudiante = estudianteService.getById(carne);
        if(estudiante.isPresent()) {
            System.out.println("Estudiante: " + estudiante.get().getNombre() + " " + estudiante.get().getApellido());
            System.out.println("Carne: " + estudiante.get().getCarne());
        } else {
            System.out.println("Estudiante con carne: " + carne + " no existe");
        }
    }

    public static void addNewStudent(EstudianteService estudianteService, int carne, String nombre, String apellido, int edad) {
        var nuevoEstudiante = new Estudiante(carne,nombre, apellido, edad);
        estudianteService.addNew(nuevoEstudiante);
    }

    public static void deleteStudent(EstudianteService estudianteService, int carne) {
        estudianteService.deleteStudent(carne);
    }

    public static void updateStudent(EstudianteService estudianteService, int carne, String nombre, String apellido, int edad) {
        var nuevoEstudiante = new Estudiante(carne,nombre, apellido, edad);
        estudianteService.updateStudent(nuevoEstudiante);
    }


    // Metodos para Profesores

    public static void showAllProfesores(ProfesorService profesorService){
        System.out.println("\n\n");
        System.out.println("Lista de Profesores");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Id\t\tNombre\t\tApellido\tCiudad");
        System.out.println("-----------------------------------------------------------------------");
        for(Profesor profesor : profesorService.getAll()) {
            System.out.println(profesor.getId() + "\t\t" + profesor.getNombre() + "\t\t" +profesor.getApellido() + "\t\t"+ profesor.getCiudad());
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }

    public static void showProfesorWithId(ProfesorService profesorService, int id){
        Optional<Profesor> profesor = profesorService.getById(id);
        if(profesor.isPresent()) {
            System.out.println("Profesor: " + profesor.get().getNombre() + " " + profesor.get().getApellido());
            System.out.println("Id: " + profesor.get().getId());
        } else {
            System.out.println("Profesor con id: " + id + " no existe");
        }
    }

    public static void addNewProfesor(ProfesorService profesorService, int id, String nombre, String apellido, String ciudad) {
        var nuevoProfesor = new Profesor(id,nombre, apellido, ciudad);
        profesorService.addNew(nuevoProfesor);
    }

    public static void deleteProfesor(ProfesorService profesorService, int id) {
        profesorService.deleteProfesor(id);
    }

    public static void updateProfesor(ProfesorService profesorService, int id, String nombre, String apellido, String ciudad) {
        var nuevoProfesor = new Profesor(id,nombre, apellido, ciudad);
        profesorService.updateProfesor(nuevoProfesor);
    }

    public static void showAllProfesoresWithCiudad(ProfesorService profesorService, String ciudad){
        System.out.println("\n\n");
        System.out.println("Lista de Profesores con ciudad " + ciudad);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Id\t\tNombre\t\tApellido\tCiudad");
        System.out.println("-----------------------------------------------------------------------");
        for (Profesor profesor : profesorService.getProfesorByCity(ciudad)){
            System.out.println(profesor.getId() + "\t\t" + profesor.getNombre() + "\t\t" +profesor.getApellido() + "\t\t"+ profesor.getCiudad());
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }


    // Metodos para Cursos

    public static void showAllCursos(CursoService cursoService){
        System.out.println("\n\n");
        System.out.println("Lista de Cursos");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Id\t\tNombre\t\tDepartamento\tCreditos");
        System.out.println("-----------------------------------------------------------------------");
        for(Curso curso : cursoService.getAll()) {
            System.out.println(curso.getId() + "\t\t" + curso.getNombre() + "\t\t" +curso.getDepartamento() + "\t\t"+ curso.getCreditos());
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }


    public static void showCursoWithId(CursoService cursoService, int id){
        Optional<Curso> curso = cursoService.getById(id);
        if(curso.isPresent()) {
            System.out.println("Curso: " + curso.get().getNombre() + " " + curso.get().getDepartamento());
            System.out.println("Id: " + curso.get().getId());
        } else {
            System.out.println("Curso con id: " + id + " no existe");
        }
    }

    public static void addNewCurso(CursoService cursoService, int id, String nombre, String departamento, int creditos) {
        var nuevoCurso = new Curso(id,nombre, departamento, creditos);
        cursoService.addNew(nuevoCurso);
    }

    public static void deleteCurso(CursoService cursoService, int id) {
        cursoService.deleteCurso(id);
    }

    public static void updateCurso(CursoService cursoService, int id, String nombre, String departamento, int creditos) {
        var nuevoCurso = new Curso(id,nombre, departamento, creditos);
        cursoService.updateCurso(nuevoCurso);
    }

    public static void showAllCursosWithDepartment(CursoService cursoService, String departamento){
        System.out.println("\n\n");
        System.out.println("Lista de Cursos con departamento " + departamento);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Id\t\tNombre\t\tDepartamento\tCreditos");
        System.out.println("-----------------------------------------------------------------------");
        for (Curso curso : cursoService.getByDepartment(departamento)){
            System.out.println(curso.getId() + "\t\t" + curso.getNombre() + "\t\t" +curso.getDepartamento() + "\t\t"+ curso.getCreditos());
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }
}