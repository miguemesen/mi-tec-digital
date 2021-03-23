package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Estudiante;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EstudianteDAOImpl extends GenericSetDAOImpl<Estudiante, Integer> implements EstudianteDAO {

    public EstudianteDAOImpl(SetDB setDB, Class<Estudiante> clazz) {
        super(setDB, clazz);
    }

    @Override
    public List<Estudiante> findByLastName(String lastName) {
        return this.findAll().stream().filter(e -> e.getApellido().equals(lastName)).collect(Collectors.toList());
    }


    @Override
    public List<Estudiante> findAllSortByLastName() {
        var estudiantes = this.findAll();
        List<String> apellidoEstudiante = new ArrayList<>();
        for (Estudiante e : estudiantes){
            apellidoEstudiante.add(e.getApellido());
        }
        apellidoEstudiante.sort(Comparator.comparing(String::toString));

        List<Estudiante> finalList = new ArrayList<>();
        for (int i = 0; i < estudiantes.size(); i++){
            finalList.add(this.findByLastName(apellidoEstudiante.get(i)).get(0));
            this.delete(this.findByLastName(apellidoEstudiante.get(i)).get(0).getCarne());
        }
        return finalList;
    }

    @Override
    protected Estudiante rowToEntity(Row row) {
        // conversiones de Row a Estudiante
        var carne = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var apellido = row.stringAttributeValue("apellido");
        var edad = row.intAttributeValue("edad");
        return new Estudiante(carne, nombre, apellido, edad);
    }

    @Override
    protected Row entityToRow(Estudiante e) {
        // conversiones de Estudiante a Row
        return new Row(new RowAttribute[] {
                new RowAttribute("id", e.getCarne()),
                new RowAttribute("nombre", e.getNombre()),
                new RowAttribute("apellido", e.getApellido()),
                new RowAttribute("edad", e.getEdad())
        });
    }

}