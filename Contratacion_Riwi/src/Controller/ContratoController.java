package Controller;

import Model.CoderModel;
import Model.ContratoModel;
import Model.EmpresaModel;
import Model.VacanteModel;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;

public class ContratoController {


    public static void getAll(){
        // definimos el tipo de objeto que vamos a mostrar
        ContratoModel objContratoModel = new ContratoModel();

        String listaContratos = "lista de contratos\n";

        for (Object i : objContratoModel.findAll()){
            Contratacion objContrato = (Contratacion) i;

            listaContratos += objContrato.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listaContratos);
    }

    public static String getAllString(){
        ContratoModel objContratoModel = new ContratoModel();
        String listaContratos = "lista de contratos \n";

        for (Object recorrido : objContratoModel.findAll()){
            Contratacion objContrato = (Contratacion) recorrido;
            listaContratos += objContrato.toString()+"\n";
        }
        return  listaContratos;
    }

    public static void create(){
        ContratoModel objContratoModel = new ContratoModel();

        // invocamos el coder completo
        CoderController objCoder = new CoderController();
        String listaCoder = objCoder.getAllString();
        CoderModel objCoderModel = new CoderModel();

        Coder objCoderCompara = null;

        // invocamos las vacantes
        VacanteController objVacante = new VacanteController();
        String listaVacantes = objVacante.getAllString();
        VacanteModel objVacanteModel = new VacanteModel();

        Vacante objVacanteCompara = null;

        // invocamos las empresas
        EmpresaModel objEmpresaModel = new EmpresaModel();
        Empresa objEmpresaShow = null;


        int opcion  = 0;
        int coder = 0;
        int vacante = 0;
        // definimos los inputs para llenar datos

        String estado = "ACTIVO";
        double salario = Double.parseDouble(JOptionPane.showInputDialog(null,"ingrese el salario"));

        while (opcion != 1){
            coder = Integer.parseInt(JOptionPane.showInputDialog(null,listaCoder + "ingrese el id del coder a ingresar"));
            vacante = Integer.parseInt(JOptionPane.showInputDialog(null,listaVacantes + "ingrese el id del coder a ingresar"));
            objVacanteCompara = (Vacante) objVacanteModel.buscar_por_id(vacante);
            objCoderCompara = (Coder) objCoderModel.buscar_por_id(coder);
            objEmpresaShow = (Empresa) objEmpresaModel.buscar_por_id(objVacanteCompara.getId_empresa());



            if (objCoderCompara.getCv().equals(objVacanteCompara.getTecnologia())){
                opcion = 1;
            }else{
                JOptionPane.showMessageDialog(null,"La tecnologia del coder y la vacante no coinciden");
            }
        }

        // definimos un objeto tipo vacante
        Contratacion objContrato = new Contratacion();

        // llenamos el objeto con los datos ingresados
        objContrato.setEstado(estado);
        objContrato.setSalario(salario);
        objContrato.setCoder_id_fk(coder);
        objContrato.setVacante_id_fk(vacante);


        // guardamos el objeto vacante en el modelo
        objContrato = (Contratacion) objContratoModel.insert(objContrato);

        // instanciamos vacante
        VacanteModel ObjVacanteModel = new VacanteModel();
        Vacante objetoVacante = (Vacante) ObjVacanteModel.buscar_por_id(objContrato.getVacante_id_fk());
        objContratoModel.updateActivo(objetoVacante);

        JOptionPane.showMessageDialog(null, "titulo vacante: " + objVacanteCompara.getTitulo() +"\n" + "Descripcion: " + objVacanteCompara.getDescripcion() + "\n" + "Empresa: " + objEmpresaShow.getNombre() + "\n" + "ubicacion: " + objEmpresaShow.getSector() + "\n" + "Coder: " + objCoderCompara.getNombre() + " " + objCoderCompara.getApellido() + "\n" + "documento: " + objCoderCompara.getDocumento() + "\n" + "tecnologia: " + objCoderCompara.getCv() + "\n" + "salario: " + objContrato.getSalario());
    }

    public static void delete(){
        ContratoModel objContratoModel = new ContratoModel();

        // invocamos el get all string
        String listaContrato = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listaContrato,"ingresa el ID del contrato a borrar"));

        Contratacion objContrato = (Contratacion) objContratoModel.buscar_por_id(idDelete);

        int eliminados = 1;
        if (objContrato == null){
            JOptionPane.showMessageDialog(null,"no se borro ningun elemento");
        }else {
            eliminados = JOptionPane.showConfirmDialog(null,"Estas seguro que quieres eliminar este vuelo?\n" + objContrato.toString());
            if (eliminados == 0){
                objContratoModel.delete(objContrato);
            }
        }

    }

    public static void update(){
        ContratoModel objContratoModel = new ContratoModel();

        // invocamos el get all string
        String listaContrato = getAllString();

        // invocamos el to String del coder
        CoderController objCoder = new CoderController();
        String listaCoder = objCoder.getAllString();

        // invocamos el to String de las vacantes
        VacanteController objVacante = new VacanteController();
        String listaVacantes = objVacante.getAllString();


        int id = Integer.parseInt(JOptionPane.showInputDialog(listaContrato,"ingrese el id a actualizar"));

        Contratacion objContrato = (Contratacion) objContratoModel.buscar_por_id(id);

        if(objContrato == null){
            JOptionPane.showMessageDialog(null,"no se encontraron resultados");
        }else {

            String estado = JOptionPane.showInputDialog(null,"ingrese el nuevo estado (solo se acepta 'activo' e 'inactivo'",objContrato.getEstado());
            double salario = Double.parseDouble(JOptionPane.showInputDialog(null,"ingrese el nuevo salario",objContrato.getSalario()));
            int coder = Integer.parseInt(JOptionPane.showInputDialog(null,listaCoder + "ingrese el id del nuevo coder a ingresar",objContrato.getCoder_id_fk()));


            // llenamos el objeto con los datos ingresados
            objContrato.setEstado(estado);
            objContrato.setSalario(salario);
            objContrato.setCoder_id_fk(coder);


            objContratoModel.update(objVacante);

        }


    }


    public void MenuContrato(){

        String aOption="";
        VacanteModel objVacante = new VacanteModel();
        do {
            aOption=JOptionPane.showInputDialog(null, """
                                Seleccione una opcion:
                                
                                1). Listar los contratos.
                                2). Generar un nuevo contrato.
                                3). Actualizar datos de un contrato.
                                4). Borrar un contrato.
                                5). -
                                6). Salir del menu de Contratacion
    
                                
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
