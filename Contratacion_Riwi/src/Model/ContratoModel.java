package Model;

import database.CRUD;
import database.DBcontrato;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el objeto tipo empresa
         Contratacion objContrato = (Contratacion) obj;

        // try catch para introducir la sentencia SQL
        try {
            // Sentencia SQL
            String sql ="INSERT INTO contratacion (estado,salario,coder_id_fk,vacante_id_fk) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // Se le asigna valores a los ???
            objPrepare.setString(1,"ACTIVO");
            objPrepare.setDouble(2,objContrato.getSalario());
            objPrepare.setInt(3,objContrato.getCoder_id_fk());
            objPrepare.setInt(4,objContrato.getVacante_id_fk());

            // Se ejecuta la sentencia (prepara el statement)
            objPrepare.execute();

            // Se asignan los valores a las llaves generadas
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // Se rellenan las columnas
            if (objRest.next()){
                objContrato.setId_contratacion(objRest.getInt(1));
            }
            // Se imprime un mensaje confirmando la creacion de la empresa
            JOptionPane.showMessageDialog(null, "Contratacion ingresada correctamente");

            // Se imprime el error en caso de existir
        }catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR Insert: " + error.getMessage());
        }
        // se cierra la conexion
        DBcontrato.closeConnection();
        // se retorna el nuevo objeto
        return objContrato;
    }

    // leer todo
    @Override
    public List<Object> findAll() {

        // Se define el tipo de objeto
        List<Object> listaContrataciones = new ArrayList<>();

        // Se abre la conexion
        Connection objConnection = DBcontrato.openConnection();

        // try catch para la sentencia SQL
        try{
            String sql ="SELECT * FROM contratacion;";
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            // Se abre un while para el ingreso de datos
            while (objResult.next()){
                // Se define el tipo de objeto
                Contratacion objContratacion;
                objContratacion = new Contratacion();


                // Se asignan las columnas que se van a leer
                objContratacion.setId_contratacion(objResult.getInt("id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("estado"));
                objContratacion.setSalario(objResult.getDouble("salario"));
                objContratacion.setCoder_id_fk(objResult.getInt("coder_id_fk"));
                objContratacion.setVacante_id_fk(objResult.getInt("vacante_id_fk"));



                // Se añaden los datos al objeto
                listaContrataciones.add(objContratacion);
            }

            // Se imprimen los errores en caso de haber
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
        }
        // Se cierra la conexion
        DBcontrato.closeConnection();

        // Se retorna la lista
        return listaContrataciones;
    }

    // actualizar
    @Override
    public boolean update(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el tipo de objeto a retornar
        Contratacion objContrato = (Contratacion) obj;

        // se define un booleano para confirmar la actualizacion
        boolean isUpdated= false;

        // Se abre try catch para actualizar los datos
        try{
            // se ingresa la sentenci SQL con todos los atributos y la coincidencia del ID
            String slq = "UPDATE vacante SET estado = ?, salario = ?, coder_id_fk = ? WHERE id_vacante = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            // Se asigna valor a los interrogantes incluynedo el id
            objPrepare.setString(1,objContrato.getEstado());
            objPrepare.setDouble(2,objContrato.getSalario());
            objPrepare.setInt(3,objContrato.getCoder_id_fk());

            objPrepare.setInt(4,objContrato.getId_contratacion());

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
        Contratacion objContratacion = (Contratacion) obj;

        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se inicia la confimacion en falso
        boolean isDeleted = false;

        // Se abre un try catch para la sentencia SQL
        try{
            // Sentencia SQL para borrar
            String sql ="DELETE  FROM contratacion WHERE id_contratacion = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Se agrega valor al ? correspondiente al ID
            objPrepare.setInt(1,objContratacion.getId_contratacion());

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

    public Object buscar_por_id(int id) {
        // se abre conexion
        Connection objConnection = DBcontrato.openConnection();
        // se define el objeto vacio
        Contratacion objContratacion = null;

        // try catch para ejecutar la sentencia SQL
        try{
            // buscamos por id
            String sql ="SELECT * FROM contratacion WHERE id_contratacion = ?;";

            // preparamos el tatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1 ,id);

            ResultSet objResult = objPrepare.executeQuery();

            // mientras se ingresan los datos vamos asignado valor a los atributos
            while (objResult.next()){
                objContratacion = new Contratacion();

                objContratacion.setId_contratacion(objResult.getInt("id_contratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("estado"));
                objContratacion.setSalario(objResult.getDouble("salario"));
                objContratacion.setCoder_id_fk(objResult.getInt("coder_id_fk"));
                objContratacion.setVacante_id_fk(objResult.getInt("vacante_id_fk"));

            }

            // imprmimmos error en caso de fallar
        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        // cerramos conexion
        DBcontrato.closeConnection();
        // retornamos el objeto encontrado
        return objContratacion;

    }

    public boolean updateActivo(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el tipo de objeto a retornar
        Vacante objContrato = (Vacante) obj;

        // se define un booleano para confirmar la actualizacion
        boolean isUpdated= false;

        // Se abre try catch para actualizar los datos
        try{
            // se ingresa la sentenci SQL con todos los atributos y la coincidencia del ID
            String slq = "UPDATE vacante SET estado = ? WHERE id_vacante = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            // Se asigna valor a los interrogantes incluynedo el id
            objPrepare.setString(1,"INACTIVO");

            objPrepare.setInt(2,objContrato.getId_vacante());

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

}
