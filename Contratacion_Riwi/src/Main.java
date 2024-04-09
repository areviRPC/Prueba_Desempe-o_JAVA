import Controller.CoderController;
import Controller.ContratoController;
import Controller.VacanteController;
import Model.VacanteModel;
import entity.Vacante;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        // Controlador de Coder
        CoderController objCoderController = new CoderController();

        // Controlador de Vacante
        VacanteController objVacanteController = new VacanteController();

        // controlador de reserva
        ContratoController objContratoocntroller = new ContratoController();



        String option = "";
        do {
            option = JOptionPane.showInputDialog(null, """
                    Seleccione la tabla de su preferencia:
                    1). Menu de Coders.
                    2). Menu de Vacantes.
                    3). Menu Contratos
                    4). -
                    5). Exit of the select
                    Enter the table to select:
                    """);
            switch (option) {
                case "1":
                    objCoderController.MenuCoder();
                    break;
                case "2":
                    objVacanteController.MenuVacante();
                case "3":
                    objContratoocntroller.MenuContrato();
                    break;
                case "4":


                case "5":
                    JOptionPane.showMessageDialog(null,"Saliendo del menu...");
                    JOptionPane.showMessageDialog(null, """
                            Menu Closed.
                            Good Bye...
                            """);
                    break;
            }
        }while (!option.equals("5"));

    }


}
