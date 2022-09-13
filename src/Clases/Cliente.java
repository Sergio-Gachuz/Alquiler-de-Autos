package Clases;

public class Cliente {
    String RFC, Nombre,Direccion, CveCiudad, CveEstado;
    int Telefono;

    public Cliente(String RFC, String nombre, int telefono, String direccion, String cveCiudad, String cveEstado) {
        this.RFC = RFC;
        Nombre = nombre;
        Telefono = telefono;
        Direccion = direccion;
        CveCiudad = cveCiudad;
        CveEstado = cveEstado;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCveCiudad() {
        return CveCiudad;
    }

    public void setCveCiudad(String cveCiudad) {
        CveCiudad = cveCiudad;
    }

    public String getCveEstado() {
        return CveEstado;
    }

    public void setCveEstado(String cveEstado) {
        CveEstado = cveEstado;
    }
}
