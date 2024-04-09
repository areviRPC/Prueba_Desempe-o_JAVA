package database;

import java.util.List;


// Usamos todos los metodos vacios del crud para sobreescribirlos luego
public interface CRUD {
    // Crear
    public Object insert(Object obj);
    // Leer
    public List<Object> findAll();
    // Actualizar
    public  boolean update(Object obj);
    // Eliminar
    public boolean delete(Object obj);
}
