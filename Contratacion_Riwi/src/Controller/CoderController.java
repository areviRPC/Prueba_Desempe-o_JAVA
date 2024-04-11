package Controller;

import Model.CoderModel;
import database.DBcontrato;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class CoderController {

    public static void getAll(){
        // definimos el tipo de objeto que vamos a mostrar
        CoderModel objCoderModel = new CoderModel();

        // definimos lista empresa como texto
        String listaCoder = "lista de coders\n";

        // hacemos un ciclo que ejecuta el findAll
        for (Object i : objCoderModel.findAll()){
            // contatena todas las empresas listadas en toString
            Coder objCoder = (Coder) i;

            listaCoder += objCoder.toString()+"\n";
        }
        // imprime las empresas en JOption
        JOptionPane.showMessageDialog(null,listaCoder);
    }

    public static String getAllString(){
        // definimos el objeto empresa
        CoderModel objCoderModel = new CoderModel();
        // definimos la lista empresas como texto
        String listaCoder = "lista de coders actuales \n";

        // creamos un ciclo que lea todas las empresas y ejecute el findAll
        for (Object recorrido : objCoderModel.findAll()){
            // lista todas las empresas
            Coder objCoder = (Coder) recorrido;
            listaCoder += objCoder.toString()+"\n";
        }
        // retornamos todas las empresas
        return listaCoder;
    }

    public static void create(){
        // definimos el tipo de objeto a ingresar
        CoderModel objCoderModel = new CoderModel();

        // ingresamos los datos para dar valor al objeto
        String nombre = JOptionPane.showInputDialog(null,"ingrese el nombre del nuevo coder");
        String apellidos = JOptionPane.showInputDialog(null,"ingrese los apellidos");
        String documento = JOptionPane.showInputDialog(null,"ingrese el documento del coder");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null,"ingrese el numero de cohorte del estudiante"));
        String cv = JOptionPane.showInputDialog(null,"ingrese la cv del coder");
        String clan = JOptionPane.showInputDialog(null,"ingrese el clan del coder");

        // definimos el tipo de objeto para guardar los datos
        Coder objCoder = new Coder();

        objCoder.setNombre(nombre);
        objCoder.setApellido(apellidos);
        objCoder.setDocumento(documento);
        objCoder.setCohorte(cohorte);
        objCoder.setCv(cv);
        objCoder.setClan(clan);

        // insertamos el objeto empresa en el modelo empresa
        objCoder = (Coder) objCoderModel.insert(objCoder);

        // imprimimos el resultado
        JOptionPane.showMessageDialog(null,objCoder.toString());
    }

    public static void delete(){
        // definimos el objeto a eliminar
        CoderModel objCoderModel = new CoderModel();

        // invocamos el all string
        String listaCoder = getAllString();

        // creamos un entero para guardar el id ingresado por teclado
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listaCoder,"ingresa el ID del coder a borrar"));

        // aplicamos la busqueda del ID al id ingresado
        Coder objCoder = (Coder) objCoderModel.buscar_por_id(idDelete);


        int eliminados = 1;
        // validamos que el id exista
        if (objCoder == null){
            JOptionPane.showMessageDialog(null,"no se borro ningun elemento");
        }else {
            eliminados = JOptionPane.showConfirmDialog(null,"Estas seguro que quieres eliminar este coder?\n" + objCoder.toString());
            if (eliminados == 0){
                // borramos el objeto con el Id correspondiente
                objCoderModel.delete(objCoder);
            }
        }

    }

    public static void update(){
        // definimos el objeto a actualizar
        CoderModel objCoderModel = new CoderModel();

        // invocamos el get all string
        String listaCoders = getAllString();

        // creamos un entero para ingresar el id a actualizar
        int id = Integer.parseInt(JOptionPane.showInputDialog(listaCoders,"ingrese el id a actualizar"));

        // aplicamos la busqueda por ID
        Coder objCoder = (Coder) objCoderModel.buscar_por_id(id);

        // validar si el Id ingresado si existe
        if(objCoder == null){
            JOptionPane.showMessageDialog(null,"no se encontraron resultados");
        }else {
            // asignamos por teclado los nuevos valores a las variables
            String nombre = JOptionPane.showInputDialog(null,"ingrese el nuevo nombre del coder",objCoder.getNombre());
            String apellidos = JOptionPane.showInputDialog(null,"ingrese los nuevo apellidos",objCoder.getApellido());
            String documento = JOptionPane.showInputDialog(null,"ingrese el nuevo documento del coder",objCoder.getDocumento());
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null,"ingrese el numero de cohorte del estudiante",objCoder.getCohorte()));
            String cv = JOptionPane.showInputDialog(null,"ingrese la nueva cv del coder",objCoder.getCv());
            String clan = JOptionPane.showInputDialog(null,"ingrese el nuevo clan del coder",objCoder.getClan());

            // guardamos los valores en el objeto empresa
            objCoder.setNombre(nombre);
            objCoder.setApellido(apellidos);
            objCoder.setDocumento(documento);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);
            // actualizamos el objeto
            objCoderModel.update(objCoder);
        }


    }

    public static void buscar_corte(){
        // se abre conexion
        Connection objConnection = DBcontrato.openConnection();
        // se define el objeto a buscar
        CoderModel objCoderModel = new CoderModel();

        // ingreso de datos abuscar
        int corteBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de cohorte que deseas buscar"));
        String Result = "Resultados de la busqueda \n";

        // se define la lista en el que se van a guardar los resultados
        List<Coder> listaCoder = objCoderModel.Buscar_cohorte(corteBuscar);

        // se valida que si existan resultados
        if(listaCoder.size() == 0){
            JOptionPane.showMessageDialog(null,"No se encontraron resultados");
        }else{
            // se imprime el resultado completo
            for (Coder count : listaCoder){
                Result += count.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,Result);
        }

    }

    public static void buscar_Clan(){
        // se abre conexion
        Connection objConnection = DBcontrato.openConnection();
        // se define el objeto a buscar
        CoderModel objCoderModel = new CoderModel();

        // ingreso de datos abuscar
        String clanBuscar = JOptionPane.showInputDialog("Ingresa el nombre del clan que quieres que deseas buscar");
        String Result = "Resultados de la busqueda \n";

        // se define la lista en el que se van a guardar los resultados
        List<Coder> listaCoder = objCoderModel.Buscar_clan(clanBuscar);

        // se valida que si existan resultados
        if(listaCoder.size() == 0){
            JOptionPane.showMessageDialog(null,"No se encontraron resultados");
        }else{
            // se imprime el resultado completo
            for (Coder count : listaCoder){
                Result += count.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,Result);
        }

    }

    public static void buscar_cv(){
        // se abre conexion
        Connection objConnection = DBcontrato.openConnection();
        // se define el objeto a buscar
        CoderModel objCoderModel = new CoderModel();

        // ingreso de datos abuscar
        String cvBuscar = JOptionPane.showInputDialog("Ingresa el nombre de la tecnologia (cv) que  deseas buscar");
        String Result = "Resultados de la busqueda \n";

        // se define la lista en el que se van a guardar los resultados
        List<Coder> listaCoder = objCoderModel.Buscar_tecno(cvBuscar);

        // se valida que si existan resultados
        if(listaCoder.size() == 0){
            JOptionPane.showMessageDialog(null,"No se encontraron resultados");
        }else{
            // se imprime el resultado completo
            for (Coder count : listaCoder){
                Result += count.toString()+"\n";
            }
            JOptionPane.showMessageDialog(null,Result);
        }

    }

    public void MenuCoder(){

        String aOption="";
        CoderModel objCoder = new CoderModel();
        do {
            aOption=JOptionPane.showInputDialog(null, """
                                Seleccione una opcion:
                                
                                1). Listar los Coders.
                                2). Crear nuevo Coder.
                                3). Actualizar datos de un Coder.
                                4). Borrar un Coder.
                                5). Buscar un coder por numero de cohorte.
                                6). Buscar un coder por clan.
                                7). Buscar un coder por tecnologia (cv).
                                8). Salir del menu de Coders.
    
                                
                               :
                                """);
            switch (aOption){
                case "1":
                    getAll();
                    break;
                case "2":
                    create();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    buscar_corte();
                    break;
                case "6":
                    buscar_Clan();
                    break;
                case "7":
                    buscar_cv();
                    break;
                case "8":
                    JOptionPane.showMessageDialog(null,"has salido del menu");
                    break;

            }
        }while (!aOption.equals("8"));
    }
}
