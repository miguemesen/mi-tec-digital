package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Profesor;

import java.util.List;
import java.util.stream.Collectors;

public class ProfesorDAOImpl extends GenericSetDAOImpl<Profesor, Integer> implements ProfesorDAO {

    public ProfesorDAOImpl(SetDB setDB, Class<Profesor> clazz) {
        super(setDB, clazz);
    }

    @Override
    protected Profesor rowToEntity(Row row) {
        var id = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var apellido = row.stringAttributeValue("apellido");
        var ciudad = row.stringAttributeValue("ciudad");
        return new Profesor(id,nombre,apellido,ciudad);
    }

    @Override
    protected Row entityToRow(Profesor p) {
        return new Row(new RowAttribute[]{
           new RowAttribute("id", p.getId()),
           new RowAttribute("nombre", p.getNombre()),
           new RowAttribute("apellido", p.getApellido()),
           new RowAttribute("ciudad", p.getCiudad())
        });
    }

    @Override
    public List<Profesor> findByCity(String department) {
        return this.findAll().stream().filter(p -> p.getCiudad().equals(department)).collect(Collectors.toList());
    }
}
