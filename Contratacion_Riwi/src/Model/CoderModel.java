package Model;

import database.CRUD;
import database.DBcontrato;
import entity.Coder;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {

    // crear
    @Override
    public Object insert(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el objeto tipo empresa
        Coder objCoder = (Coder) obj;

        // try catch para introducir la sentencia SQL
        try {
            // Sentencia SQL
            String sql ="INSERT INTO coder (nombre,apellidos,documento,cohorte,cv,clan) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // Se le asigna valores a los ???
            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellido());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());

            // Se ejecuta la sentencia (prepara el statement)
            objPrepare.execute();

            // Se asignan los valores a las llaves generadas
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // Se rellenan las columnas
            if (objRest.next()){
                objCoder.setId_coder(objRest.getInt(1));
            }
            // Se imprime un mensaje confirmando la creacion de la empresa
            JOptionPane.showMessageDialog(null, "Coder ingresado con exito");

            // Se imprime el error en caso de existir
        }catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR Insert: " + error.getMessage());
        }
        // se cierra la conexion
        DBcontrato.closeConnection();
        // se retorna el nuevo objeto
        return objCoder;
    }

    // leer todo
    @Override
    public List<Object> findAll() {

        // Se define el tipo de objeto
        List<Object> listaCoders = new ArrayList<>();

        // Se abre la conexion
        Connection objConnection = DBcontrato.openConnection();

        // try catch para la sentencia SQL
        try{
            String sql ="SELECT * FROM coder;";
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            // Se abre un while para el ingreso de datos
            while (objResult.next()){
                // Se define el tipo de objeto
                Coder objCoder = new Coder();


                // Se asignan las columnas que se van a leer
                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                // Se añaden los datos al objeto
                listaCoders.add(objCoder);
            }

            // Se imprimen los errores en caso de haber
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
        }
        // Se cierra la conexion
        DBcontrato.closeConnection();

        // Se retorna la lista
        return listaCoders;
    }

    // actualizar
    @Override
    public boolean update(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el tipo de objeto a retornar
        Coder objCoder = (Coder) obj;

        // se define un booleano para confirmar la actualizacion
        boolean isUpdated= false;

        // Se abre try catch para actualizar los datos
        try{
            // se ingresa la sentenci SQL con todos los atributos y la coincidencia del ID
            String slq = "UPDATE coder SET nombre = ?, apellidos = ?, documento = ?, cohorte = ? , cv = ? , clan = ? WHERE id_coder = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            // Se asigna valor a los interrogantes incluynedo el id
            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellido());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());

            objPrepare.setInt(7,objCoder.getId_coder());

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
        Coder objCoder = (Coder) obj;

        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se inicia la confimacion en falso
        boolean isDeleted = false;

        // Se abre un try catch para la sentencia SQL
        try{
            // Sentencia SQL para borrar
            String sql ="DELETE  FROM coder WHERE id_coder = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Se agrega valor al ? correspondiente al ID
            objPrepare.setInt(1,objCoder.getId_coder());

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
        Coder objCoder = null;

        // try catch para ejecutar la sentencia SQL
        try{
            // buscamos por id
            String sql ="SELECT * FROM coder WHERE id_coder = ?;";

            // preparamos el tatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1 ,id);

            ResultSet objResult = objPrepare.executeQuery();

            // mientras se ingresan los datos vamos asignado valor a los atributos
            while (objResult.next()){
                objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
            }

            // imprmimmos error en caso de fallar
        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        // cerramos conexion
        DBcontrato.closeConnection();
        // retornamos el objeto encontrado
        return objCoder;

    }

    // buscar por numero de cohorte
    public List<Coder> Buscar_cohorte(int cohorte) {

        Connection objConnection = DBcontrato.openConnection();

        Coder objCoder = null;

        List<Coder> listaCoders = new ArrayList<>();

        try {

            String sql = "SELECT * FROM coder WHERE cohorte like ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+cohorte+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listaCoders.add(objCoder);
            }


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR"+error.getMessage());
        }
        DBcontrato.closeConnection();
        return listaCoders;
    }

    // buscar por clan
    public List<Coder> Buscar_clan(String clan) {

        Connection objConnection = DBcontrato.openConnection();

        Coder objCoder = null;

        List<Coder> listaCoders = new ArrayList<>();

        try {

            String sql = "SELECT * FROM coder WHERE clan like ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+clan+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listaCoders.add(objCoder);
            }


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR"+error.getMessage());
        }
        DBcontrato.closeConnection();
        return listaCoders;
    }

    public List<Coder> Buscar_tecno(String tecno) {

        Connection objConnection = DBcontrato.openConnection();

        Coder objCoder = null;

        List<Coder> listaCoders = new ArrayList<>();

        try {

            String sql = "SELECT * FROM coder WHERE cv like ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+tecno+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listaCoders.add(objCoder);
            }


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR"+error.getMessage());
        }
        DBcontrato.closeConnection();
        return listaCoders;
    }


}
