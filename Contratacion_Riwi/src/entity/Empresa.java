package entity;

public class Empresa {

    private int id_empresa;
    private String nombre;

    private String apellido;

    private String sector;

    private String contacto;

    // constructor vacio


    public Empresa() {
    }

    // setters
    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    //getter
    public int getId_empresa() {
        return id_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSector() {
        return sector;
    }

    public String getContacto() {
        return contacto;
    }

    // constructor

    public Empresa(int id_empresa, String nombre, String apellido, String sector, String contacto) {
        this.id_empresa = id_empresa;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sector = sector;
        this.contacto = contacto;
    }

    // toString

    @Override
    public String toString() {
        return "empresa{" +
                "id_empresa=" + id_empresa +
                ", nombre='" + nombre + '\'' +
                ", sector='" + sector + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
