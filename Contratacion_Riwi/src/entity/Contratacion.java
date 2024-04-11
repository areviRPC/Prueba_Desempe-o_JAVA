package entity;

public class Contratacion {
    private int id_contratacion;

    private String fecha_aplicacion;

    private String estado;

    private double salario;

    private int coder_id_fk;

    private int vacante_id_fk;

    // constructor vacio

    public Contratacion() {
    }


    // setter

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setCoder_id_fk(int coder_id_fk) {
        this.coder_id_fk = coder_id_fk;
    }

    public void setVacante_id_fk(int vacante_id_fk) {
        this.vacante_id_fk = vacante_id_fk;
    }

    // getter

    public int getId_contratacion() {
        return id_contratacion;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public double getSalario() {
        return salario;
    }

    public int getCoder_id_fk() {
        return coder_id_fk;
    }

    public int getVacante_id_fk() {
        return vacante_id_fk;
    }

    // constructor

    public Contratacion(int id_contratacion, String fecha_aplicacion, String estado, double salario, int coder_id_fk, int vacante_id_fk) {
        this.id_contratacion = id_contratacion;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
        this.coder_id_fk = coder_id_fk;
        this.vacante_id_fk = vacante_id_fk;
    }

    // string

    @Override
    public String toString() {
        return "Contratacion{" +
                "id_contratacion=" + id_contratacion +
                ", fecha_aplicacion='" + fecha_aplicacion + '\'' +
                ", estado='" + estado + '\'' +
                ", salario=" + salario +
                ", coder_id_fk=" + coder_id_fk +
                ", vacante_id_fk=" + vacante_id_fk +
                '}';
    }

}
