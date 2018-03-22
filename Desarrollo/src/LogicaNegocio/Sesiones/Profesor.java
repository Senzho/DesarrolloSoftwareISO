package LogicaNegocio.Sesiones;

import java.util.Date;

public class Profesor {
    private String correo;
    private String direccion;
    private boolean estado;
    private Date fecha;
    private String nombre;
    private String telefono;
    private boolean tipoPago;

    public Profesor(String correo, String direccion, boolean estado, Date fecha, String nombre, String telefono, boolean tipoPago) {
        this.correo = correo;
        this.direccion = direccion;
        this.estado = estado;
        this.fecha = fecha;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoPago = tipoPago;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(boolean tipoPago) {
        this.tipoPago = tipoPago;
    }
    
    
}
