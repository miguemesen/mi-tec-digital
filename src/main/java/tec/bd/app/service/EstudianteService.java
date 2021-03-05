package tec.bd.app.service;

import tec.bd.app.bd.SetDB;

import java.util.List;
import java.util.stream.Collectors;

public class EstudianteService {

    private SetDB database;

    public EstudianteService(SetDB database){
        this.database = database;
    }

    public List<Estudiante> getAll(){
        return this.database.getEstudianteTable().stream().collect(Collectors.toList());
    }

    public Estudiante getById(long carne){
        try {
            return this.database.getEstudianteTable().stream().filter(e -> e.getCarne() == carne).findFirst().get();
        } catch (Exception e) {
            System.out.println("Error: There's no student registered with the id: "+carne);
            return null;
        }

    }

    public void addNew(Estudiante e){
        try{
            this.database.getEstudianteTable().stream().filter(ne -> ne.getCarne() == e.getCarne()).findFirst().get();
            System.out.println("Error: already existent student with the id: "+e.getCarne());
        } catch (Exception exception) {
            this.database.getEstudianteTable().add(e);
            System.out.println("Student added successfully");
        }
    }

    public void updateEstudiante(Estudiante e){
        try{
            this.database.getEstudianteTable().stream().filter(ne -> ne.getCarne() == e.getCarne()).findFirst().get().setNombre(e.getNombre());
            this.database.getEstudianteTable().stream().filter(ne -> ne.getCarne() == e.getCarne()).findFirst().get().setApellido(e.getApellido());
            this.database.getEstudianteTable().stream().filter(ne -> ne.getCarne() == e.getCarne()).findFirst().get().setEdad(e.getEdad());
            System.out.println("Student updated successfully");
        } catch (Exception exception) {
            System.out.println("Error: There's no student registered with the id: " + e.getCarne());
        }
    }

    public void deleteEstudiante(long carne){
        
    }

}
