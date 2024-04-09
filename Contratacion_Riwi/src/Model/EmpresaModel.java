package Model;

import database.CRUD;
import database.DBcontrato;
import entity.Empresa;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel implements CRUD {

    // crear
    @Override
    public Object insert(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el objeto tipo empresa
        Empresa objEmpresa = (Empresa) obj;

        // try catch para introducir la sentencia SQL
        try {
            // Sentencia SQL
            String sql ="INSERT INTO empresa (nombre,sector,contacto) VALUES (?,?,?); ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // Se le asigna valores a los ???
            objPrepare.setString(1,objEmpresa.getNombre());
            objPrepare.setString(2,objEmpresa.getSector());
            objPrepare.setString(3,objEmpresa.getContacto());

            // Se ejecuta la sentencia (prepara el statement)
            objPrepare.execute();

            // Se asignan los valores a las llaves generadas
            ResultSet objRest = objPrepare.getGeneratedKeys();

            // Se rellenan las columnas
            if (objRest.next()){
                objEmpresa.setId_empresa(objRest.getInt(1));
            }
            // Se imprime un mensaje confirmando la creacion de la empresa
            JOptionPane.showMessageDialog(null, "Empresa creada");

            // Se imprime el error en caso de existir
        }catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR Insert: " + error.getMessage());
        }
        // se cierra la conexion
        DBcontrato.closeConnection();
        // se retorna el nuevo objeto
        return objEmpresa;
    }

    // leer todo
    @Override
    public List<Object> findAll() {

        // Se define el tipo de objeto
        List<Object> listEmpresas = new ArrayList<>();

        // Se abre la conexion
        Connection objConnection = DBcontrato.openConnection();

        // try catch para la sentencia SQL
        try{
            String sql ="SELECT * FROM empresa;";
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            // Se abre un while para el ingreso de datos
            while (objResult.next()){
                // Se define el tipo de objeto
                Empresa objEmpresa = new Empresa();

                // Se asignan las columnas que se van a leer
                objEmpresa.setId_empresa(objResult.getInt("id_empresa"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                // Se añaden los datos al objeto
                listEmpresas.add(objEmpresa);
            }

            // Se imprimen los errores en caso de haber
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
        }
        // Se cierra la conexion
        DBcontrato.closeConnection();

        // Se retorna la lista
        return listEmpresas;
    }

    // actualizar
    @Override
    public boolean update(Object obj) {
        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se define el tipo de objeto a retornar
        Empresa objEmpresa = (Empresa) obj;

        // se define un booleano para confirmar la actualizacion
        boolean isUpdated= false;

        // Se abre try catch para actualizar los datos
        try{
            // se ingresa la sentenci SQL con todos los atributos y la coincidencia del ID
            String slq = "UPDATE empresa SET nombre = ?, sector = ?, contacto = ? WHERE id_empresa = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            // Se asigna valor a los interrogantes incluynedo el id
            objPrepare.setString(1,objEmpresa.getNombre());
            objPrepare.setString(2,objEmpresa.getSector());
            objPrepare.setString(3,objEmpresa.getContacto());

            objPrepare.setInt(4,objEmpresa.getId_empresa());

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
        Empresa objEmpresa = (Empresa) obj;

        // Se abre conexion
        Connection objConnection = DBcontrato.openConnection();

        // Se inicia la confimacion en falso
        boolean isDeleted = false;

        // Se abre un try catch para la sentencia SQL
        try{
            // Sentencia SQL para borrar
            String sql ="DELETE  FROM empresa WHERE id_empresa = ?;";

            // Se prepara el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Se agrega valor al ? correspondiente al ID
            objPrepare.setInt(1,objEmpresa.getId_empresa());

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
        Connection objConnection = DBcontrato.openConnection();
        Empresa objEmpresa = null;

        try{
            String sql ="SELECT * FROM empresa WHERE id_empresa = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1 ,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objEmpresa = new Empresa();

                objEmpresa.setId_empresa(objResult.getInt("id_empresa"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setContacto(objResult.getString("contacto"));

            }

        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        DBcontrato.closeConnection();
        return objEmpresa;

    }

    public List<Empresa> Buscar_nombre(String nombre) {

        Connection objConnection = DBcontrato.openConnection();

        Empresa objEmpresa = null;

        List<Empresa> listaEmpresas = new ArrayList<>();

        try {

            String sql = "SELECT * FROM empresa WHERE nombre like ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+nombre+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objEmpresa = new Empresa();
                objEmpresa.setId_empresa(objResult.getInt("id_empresa"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                objEmpresa = new Empresa();

                listaEmpresas.add(objEmpresa);
            }


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR"+error.getMessage());
        }
        DBcontrato.closeConnection();
        return listaEmpresas;
    }


}
