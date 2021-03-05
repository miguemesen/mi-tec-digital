package tec.bd.app.bd;

import tec.bd.app.service.Estudiante;

import java.util.Set;

public class SetDB {

    private Set<Estudiante> estudianteTable;

    public SetDB(Set<Estudiante> estudianteTable){
        this.estudianteTable = estudianteTable;
    }

    public Set<Estudiante> getEstudianteTable() {
        return estudianteTable;
    }

    public void setEstudianteTable(Set<Estudiante> estudianteTable) {
        this.estudianteTable = estudianteTable;
    }
}
