package Model;

import database.CRUD;
import database.DBcontrato;
import entity.Coder;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el objeto tipo empresa
        Vacante objVacante = (Vacante) obj;

        // try catch para introducir la sentencia SQL
        try {
            // Sentencia SQL
            String sql ="INSERT INTO vacante (titulo,descripcion,duracion,estado,empresa_id_fk,tecnologia) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // Se le asigna valores a los ???
            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2,objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,"ACTIVO");
            objPrepare.setInt(5,objVacante.getId_empresa());
            objPrepare.setString(6,objVacante.getTecnologia());


            // Se ejecuta la sentencia (prepara el statement)
            objPrepare.execute();

            // Se asignan los valores a las llaves generadas
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // Se rellenan las columnas
            if (objRest.next()){
                objVacante.setId_vacante(objRest.getInt(1));
            }
            // Se imprime un mensaje confirmando la creacion de la empresa
            JOptionPane.showMessageDialog(null, "Vacante ingresada correctamente ingresado con exito");

            // Se imprime el error en caso de existir
        }catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR Insert: " + error.getMessage());
        }
        // se cierra la conexion
        DBcontrato.closeConnection();
        // se retorna el nuevo objeto
        return objVacante;
    }

    // leer todo
    @Override
    public List<Object> findAll() {

        // Se define el tipo de objeto
        List<Object> listaVacantes = new ArrayList<>();

        // Se abre la conexion
        Connection objConnection = DBcontrato.openConnection();

        // try catch para la sentencia SQL
        try{
            String sql ="SELECT * FROM vacante;";
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            // Se abre un while para el ingreso de datos
            while (objResult.next()){
                // Se define el tipo de objeto
                Vacante objVacante = new Vacante();


                // Se asignan las columnas que se van a leer
                objVacante.setId_vacante(objResult.getInt("id_vacante"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("empresa_id_fk"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                // Se añaden los datos al objeto
                listaVacantes.add(objVacante);
            }

            // Se imprimen los errores en caso de haber
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
        }
        // Se cierra la conexion
        DBcontrato.closeConnection();

        // Se retorna la lista
        return listaVacantes;
    }

    // actualizar
    @Override
    public boolean update(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el tipo de objeto a retornar
        Vacante objVacante = (Vacante) obj;

        // se define un booleano para confirmar la actualizacion
        boolean isUpdated= false;

        // Se abre try catch para actualizar los datos
        try{
            // se ingresa la sentenci SQL con todos los atributos y la coincidencia del ID
            String slq = "UPDATE vacante SET titulo = ?, descripcion = ?, duracion = ?, estado = ? , empresa_id_fk = ? , tecnologia = ? WHERE id_vacante = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            // Se asigna valor a los interrogantes incluynedo el id
            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2,objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,objVacante.getEstado());
            objPrepare.setInt(5, objVacante.getId_empresa());
            objPrepare.setString(6,objVacante.getTecnologia());

            objPrepare.setInt(7,objVacante.getId_vacante());

            // se define y se cuentan las lineas afectadas
            int totalRowAffec = objPrepare.executeUpdate();
            if (totalRowAffec>0){
                isUpdated =true;
                // Se imprime un mensaje confirmando la actualizacion
                JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
            }
            // Se imprime un mensaje en caso de se salir error
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Upddte: " + error.getMessage());
        }

        // Se cierra la conexion
        DBcontrato.closeConnection();

        // Se retorna la confirmacion de la actualizacion
        return isUpdated;
    }

    // borrar
    @Override
    public boolean delete(Object obj) {
        // Se define el objeto a borrar
        Vacante objVacante = (Vacante) obj;

        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se inicia la confimacion en falso
        boolean isDeleted = false;

        // Se abre un try catch para la sentencia SQL
        try{
            // Sentencia SQL para borrar
            String sql ="DELETE  FROM vacante WHERE id_vacante = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Se agrega valor al ? correspondiente al ID
            objPrepare.setInt(1,objVacante.getId_vacante());

            // Se leen y se confirman las lineas afectadas
            int totalAffet= objPrepare.executeUpdate();
            if(totalAffet >0){
                isDeleted = true;
                // Se imprime un mensaje confirmando el borrado
                JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
            }

            // Se imprimen errores en caso de ser necesario
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Delete: " + error.getMessage());
        }
        // Se cierra la conexion
        DBcontrato.closeConnection();
        // Se retorna la confirmacion del borrado
        return isDeleted;
    }

    // buscar por ID
    public Object buscar_por_id(int id) {
        // se abre conexion
        Connection objConnection = DBcontrato.openConnection();
        // se define el objeto vacio
        Vacante objVacante = null;

        // try catch para ejecutar la sentencia SQL
        try{
            // buscamos por id
            String sql ="SELECT * FROM vacante WHERE id_vacante = ?;";

            // preparamos el tatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1 ,id);

            ResultSet objResult = objPrepare.executeQuery();

            // mientras se ingresan los datos vamos asignado valor a los atributos
            while (objResult.next()){
                objVacante = new Vacante();

                objVacante.setId_vacante(objResult.getInt("id_vacante"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("empresa_id_fk"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
            }

            // imprmimmos error en caso de fallar
        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        // cerramos conexion
        DBcontrato.closeConnection();
        // retornamos el objeto encontrado
        return objVacante;

    }

    // buscar por nombre
    public List<Vacante> Buscar_titulo(String titulo) {

        Connection objConnection = DBcontrato.openConnection();

        Vacante objVacante = null;

        List<Vacante> listaVacante = new ArrayList<>();

        try {

            String sql = "SELECT * FROM vacante WHERE titulo like ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+titulo+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                objVacante = new Vacante();

                objVacante.setId_vacante(objResult.getInt("id_vacante"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("empresa_id_fk"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));


                listaVacante.add(objVacante);
            }


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR"+error.getMessage());
        }
        DBcontrato.closeConnection();
        return listaVacante;
    }

    public List<Vacante> Buscar_tecnologia(String tecnologia) {

        Connection objConnection = DBcontrato.openConnection();

        Vacante objVacante = null;

        List<Vacante> listaVacante = new ArrayList<>();

        try {

            String sql = "SELECT * FROM vacante WHERE tecnologia like ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+tecnologia+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                objVacante = new Vacante();

                objVacante.setId_vacante(objResult.getInt("id_vacante"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("empresa_id_fk"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));


                listaVacante.add(objVacante);
            }


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR"+error.getMessage());
        }
        DBcontrato.closeConnection();
        return listaVacante;
    }

}
