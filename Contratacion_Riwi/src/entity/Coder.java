package entity;

public class Coder {
    private int id_coder;

    private  String nombre;

    private String apellido;

    private String documento;

    private int cohorte;

    private String cv;

    private String clan;

    // constructor vacio


    public Coder() {
    }

    // Setters

    public void setClan(String clan) {
        this.clan = clan;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setCohorte(int cohorte) {
        this.cohorte = cohorte;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    // getter


    public int getId_coder() {
        return id_coder;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public int getCohorte() {
        return cohorte;
    }

    public String getCv() {
        return cv;
    }

    public String getClan() {
        return clan;
    }

    // constructor

    public Coder(int id_coder, String nombre, String apellido, String documento, int cohorte, String cv, String clan) {
        this.id_coder = id_coder;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.cohorte = cohorte;
        this.cv = cv;
        this.clan = clan;
    }

    // toString


    @Override
    public String toString() {
        return "Coder{" +
                "id_coder=" + id_coder +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento='" + documento + '\'' +
                ", cohorte=" + cohorte +
                ", cv='" + cv + '\'' +
                ", clan='" + clan + '\'' +
                '}';
    }
}
