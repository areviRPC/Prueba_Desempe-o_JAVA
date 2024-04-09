package Controller;

import Model.EmpresaModel;
import database.DBcontrato;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class EmpresaController {
    public static void getAll(){
        // definimos el tipo de objeto que vamos a mostrar
        EmpresaModel objEmpresaModel = new EmpresaModel();

        // definimos lista empresa como texto
        String listaEmpresa = "lista de empresas\n";

        // hacemos un ciclo que ejecuta el findAll
        for (Object i : objEmpresaModel.findAll()){
            // contatena todas las empresas listadas en toString
            Empresa objEmpresa = (Empresa) i;

            listaEmpresa += objEmpresa.toString()+"\n";
        }
        // imprime las empresas en JOption
        JOptionPane.showMessageDialog(null,listaEmpresa);
    }

    public static String getAllString(){
        // definimos el objeto empresa
        EmpresaModel objPasajeroModel = new EmpresaModel();
        // definimos la lista empresas como texto
        String listaEmpresa = "lista de empresas actuales \n";

        // creamos un ciclo que lea todas las empresas y ejecute el findAll
        for (Object recorrido : objPasajeroModel.findAll()){
            // lista todas las empresas
            Empresa objEmpresa = (Empresa) recorrido;
            listaEmpresa += objEmpresa.toString()+"\n";
        }
        // retornamos todas las empresas
        return listaEmpresa;
    }

    public static void create(){
        // definimos el tipo de objeto a ingresar
        EmpresaModel objEmpresaModel = new EmpresaModel();

        // ingresamos los datos para dar valor al objeto
        String nombre = JOptionPane.showInputDialog(null,"ingrese el nombre de la empresa");
        String sector = JOptionPane.showInputDialog(null,"ingrese el sector al que corresponde la empresa");
        String contacto = JOptionPane.showInputDialog(null,"ingrese el contacto de la empresa");

        // definimos el tipo de objeto para guardar los datos
        Empresa objEmpresa = new Empresa();

        objEmpresa.setNombre(nombre);
        objEmpresa.setSector(sector);
        objEmpresa.setContacto(contacto);

        // insertamos el objeto empresa en el modelo empresa
        objEmpresa = (Empresa) objEmpresaModel.insert(objEmpresa);

        // imprimimos el resultado
        JOptionPane.showMessageDialog(null,objEmpresa.toString());
    }

    public static void delete(){
        // definimos el objeto a eliminar
        EmpresaModel objEmpresaModel = new EmpresaModel();

        // invocamos el all string
        String listaEmpresas = getAllString();

        // creamos un entero para guardar el id ingresado por teclado
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listaEmpresas,"ingresa el ID de la empresa a borrar"));

        // aplicamos la busqueda del ID al id ingresado
        Empresa objEmpresa = (Empresa) objEmpresaModel.buscar_por_id(idDelete);


        int eliminados = 1;
        // validamos que el id exista
        if (objEmpresa == null){
            JOptionPane.showMessageDialog(null,"no se borro ningun elemento");
        }else {
            eliminados = JOptionPane.showConfirmDialog(null,"Estas seguro que quieres eliminar esta empresa?\n" + objEmpresa.toString());
            if (eliminados == 0){
                // borramos el objeto con el Id correspondiente
                objEmpresaModel.delete(objEmpresa);
            }
        }

    }

    public static void update(){
        // definimos el objeto a actualizar
        EmpresaModel objEmpresaModel = new EmpresaModel();

        // invocamos el get all string
        String listaEmpresas = getAllString();

        // creamos un entero para ingresar el id a actualizar
        int id = Integer.parseInt(JOptionPane.showInputDialog(listaEmpresas,"ingrese el id a actualizar"));

        // aplicamos la busqueda por ID
        Empresa objEmpresa = (Empresa) objEmpresaModel.buscar_por_id(id);

        // validar si el Id ingresado si existe
        if(objEmpresa == null){
            JOptionPane.showMessageDialog(null,"no se encontraron resultados");
        }else {
            // asignamos por teclado los nuevos valores a las variables
            String nombre = JOptionPane.showInputDialog(null,"ingrese el nuevo nombre la empresa",objEmpresa.getNombre());
            String sector = JOptionPane.showInputDialog(null,"ingrese el nuevo sector de la empresa",objEmpresa.getSector());
            String contacto = JOptionPane.showInputDialog(null,"ingrese el nuevo contacto de la empresa",objEmpresa.getContacto());

            // guardamos los valores en el objeto empresa
            objEmpresa.setNombre(nombre);
            objEmpresa.setSector(sector);
            objEmpresa.setContacto(contacto);

            // actualizamos el objeto
            objEmpresaModel.update(objEmpresa);
        }


    }

    public static void buscar_nombre(){
        // se abre conexion
        Connection objConnection = DBcontrato.openConnection();
        // se define el objeto a buscar
        EmpresaModel objEmpresaModel = new EmpresaModel();

        // ingreso de datos abuscar
        String nombreBuscar = JOptionPane.showInputDialog("Ingresa el nombre que deseas buscar");
        String Result = "Resultados de la busqueda \n";

        // se define la lista en el que se van a guardar los resultados
        List<Empresa> listaEmpresa = objEmpresaModel.Buscar_nombre(nombreBuscar);

        // se valida que si existan resultados
        if(listaEmpresa.size() == 0){
            JOptionPane.showMessageDialog(null,"No se encontraron resultados");
        }else{
            // se imprime el resultado completo
            for (Empresa count : listaEmpresa){
                Result += count.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,Result);
        }

    }

}
