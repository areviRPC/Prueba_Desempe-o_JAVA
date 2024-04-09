package Controller;

import Model.VacanteModel;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class VacanteController {

    public static void getAll(){
        // definimos el tipo de objeto que vamos a mostrar
        VacanteModel objVacanteModel = new VacanteModel();

        String listaVacantes = "lista de vacantes\n";

        for (Object i : objVacanteModel.findAll()){
            Vacante objVacante = (Vacante) i;

            listaVacantes += objVacante.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listaVacantes);
    }

    public static String getAllString(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listaVacante = "lista de vacantes \n";

        for (Object recorrido : objVacanteModel.findAll()){
            Vacante objVacante = (Vacante) recorrido;
            listaVacante += objVacante.toString()+"\n";
        }
        return  listaVacante;
    }

    public static void create(){
        VacanteModel objVacanteModel = new VacanteModel();

        // invocamos el to String de la empresa
        EmpresaController objEmpresa = new EmpresaController();
        String listaEmpresas = objEmpresa.getAllString();

        // definimos los inputs para llenar datos

        String titulo = JOptionPane.showInputDialog(null,"ingrese el titulo de la vacante");
        String descripcion = JOptionPane.showInputDialog(null,"ingrese la descripcion de la vacante");
        String duracion = JOptionPane.showInputDialog(null,"ingrese la duracion de la vacante");
        String estado = JOptionPane.showInputDialog(null,"ingrese el estado de la vacante (solo se acepta 'activo' e 'inactivo' ");
        int empresa = Integer.parseInt( JOptionPane.showInputDialog(null, listaEmpresas + "Seleccione la empresa para asignar la vacante"));
        String tecnoloiga = JOptionPane.showInputDialog(null,"ingrese la tecnologia de la vacante");

        // definimos un objeto tipo vacante
        Vacante objVacante = new Vacante();

        // llenamos el objeto con los datos ingresados
        objVacante.setTitulo(titulo);
        objVacante.setDescripcion(descripcion);
        objVacante.setDuracion(duracion);
        objVacante.setEstado(estado);
        objVacante.setId_empresa(empresa);
        objVacante.setTecnologia(tecnoloiga);

        // guardamos el objeto vacante en el modelo
        objVacante = (Vacante) objVacanteModel.insert(objVacante);

        JOptionPane.showMessageDialog(null,objVacante.toString());
    }

    public static void delete(){
        VacanteModel objVacanteModel = new VacanteModel();

        // invocamos el get all string
        String listaVacante = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listaVacante,"ingresa el ID de la vacante a borrar"));

        Vacante objVacante = (Vacante) objVacanteModel.buscar_por_id(idDelete);

        int eliminados = 1;
        if (objVacante == null){
            JOptionPane.showMessageDialog(null,"no se borro ningun elemento");
        }else {
            eliminados = JOptionPane.showConfirmDialog(null,"Estas seguro que quieres eliminar este vuelo?\n" + objVacante.toString());
            if (eliminados == 0){
                objVacanteModel.delete(objVacante);
            }
        }

    }

    public static void update(){
        VacanteModel objVacanteModel = new VacanteModel();

        // invocamos el get all string
        String listaVacante = getAllString();

        // invocamos el all string de la empresa
        EmpresaController objEmpresa = new EmpresaController();
        String listaEmpresas = objEmpresa.getAllString();


        int id = Integer.parseInt(JOptionPane.showInputDialog(listaVacante,"ingrese el id a actualizar"));

        Vacante objVacante = (Vacante) objVacanteModel.buscar_por_id(id);

        if(objVacante == null){
            JOptionPane.showMessageDialog(null,"no se encontraron resultados");
        }else {

            String titulo = JOptionPane.showInputDialog(null,"ingrese el nuevo titulo de la vacante",objVacante.getTitulo());
            String descripcion = JOptionPane.showInputDialog(null,"ingrese la nueva descripcion de la vacante",objVacante.getDescripcion());
            String duracion = JOptionPane.showInputDialog(null,"ingrese la nueva duracion de la vacante",objVacante.getDuracion());
            String estado = JOptionPane.showInputDialog(null,"ingrese el nuevo estado de la vacante",objVacante.getEstado());
            int empresa = Integer.parseInt(JOptionPane.showInputDialog(null, listaEmpresas + "Seleccione una nueva empresa para asignar la vacante",objVacante.getId_empresa()));
            String tecnologia = JOptionPane.showInputDialog(null,"ingrese la nueva tecnologia",objVacante.getTecnologia());

            objVacante.setTitulo(titulo);
            objVacante.setDescripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setId_empresa(empresa);
            objVacante.setTecnologia(tecnologia);

            objVacanteModel.update(objVacante);

        }


    }


    public void MenuVacante(){

        String aOption="";
        VacanteModel objVacante = new VacanteModel();
        do {
            aOption=JOptionPane.showInputDialog(null, """
                                Seleccione una opcion:
                                
                                1). Listar las vacantes.
                                2). Generar una nueva vacante.
                                3). Actualizar datos de una vacante.
                                4). Borrar una vacante.
                                5). -
                                6). Salir del menu de Vacantes
    
                                
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
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null,"has salido del menu");
                    break;

            }
        }while (!aOption.equals("6"));
    }
}
