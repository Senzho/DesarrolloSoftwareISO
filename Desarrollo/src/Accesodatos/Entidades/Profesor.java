/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Desktop
 */
@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p")
    , @NamedQuery(name = "Profesor.findByIdProfesor", query = "SELECT p FROM Profesor p WHERE p.idProfesor = :idProfesor")
    , @NamedQuery(name = "Profesor.findByEstado", query = "SELECT p FROM Profesor p WHERE p.estado = :estado")
    , @NamedQuery(name = "Profesor.findByNombre", query = "SELECT p FROM Profesor p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Profesor.findByTelefono", query = "SELECT p FROM Profesor p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Profesor.findByTipoPago", query = "SELECT p FROM Profesor p WHERE p.tipoPago = :tipoPago")
    , @NamedQuery(name = "Profesor.findByCorreo", query = "SELECT p FROM Profesor p WHERE p.correo = :correo")
    , @NamedQuery(name = "Profesor.findByFecha", query = "SELECT p FROM Profesor p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Profesor.findByMonto", query = "SELECT p FROM Profesor p WHERE p.monto = :monto")
    , @NamedQuery(name = "Profesor.findByFechaInicio", query = "SELECT p FROM Profesor p WHERE p.fechaInicio = :fechaInicio")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProfesor")
    private Integer idProfesor;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "tipoPago")
    private Integer tipoPago;
    @Column(name = "correo")
    private String correo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "monto")
    private String monto;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @OneToMany(mappedBy = "idProfesor")
    private Collection<Promocion> promocionCollection;
    @OneToMany(mappedBy = "idProfesor")
    private Collection<Gastopromocional> gastopromocionalCollection;
    @OneToMany(mappedBy = "idProfesor")
    private Collection<Grupo> grupoCollection;
    @OneToMany(mappedBy = "idProfesor")
    private Collection<Pagotemporal> pagotemporalCollection;
    @OneToMany(mappedBy = "idProfesor")
    private Collection<Pagoalumno> pagoalumnoCollection;
    @OneToMany(mappedBy = "idProfesor")
    private Collection<Pagoprofesor> pagoprofesorCollection;

    public Profesor() {
    }

    public Profesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    public Integer getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @XmlTransient
    public Collection<Promocion> getPromocionCollection() {
        return promocionCollection;
    }

    public void setPromocionCollection(Collection<Promocion> promocionCollection) {
        this.promocionCollection = promocionCollection;
    }

    @XmlTransient
    public Collection<Gastopromocional> getGastopromocionalCollection() {
        return gastopromocionalCollection;
    }

    public void setGastopromocionalCollection(Collection<Gastopromocional> gastopromocionalCollection) {
        this.gastopromocionalCollection = gastopromocionalCollection;
    }

    @XmlTransient
    public Collection<Grupo> getGrupoCollection() {
        return grupoCollection;
    }

    public void setGrupoCollection(Collection<Grupo> grupoCollection) {
        this.grupoCollection = grupoCollection;
    }

    @XmlTransient
    public Collection<Pagotemporal> getPagotemporalCollection() {
        return pagotemporalCollection;
    }

    public void setPagotemporalCollection(Collection<Pagotemporal> pagotemporalCollection) {
        this.pagotemporalCollection = pagotemporalCollection;
    }

    @XmlTransient
    public Collection<Pagoalumno> getPagoalumnoCollection() {
        return pagoalumnoCollection;
    }

    public void setPagoalumnoCollection(Collection<Pagoalumno> pagoalumnoCollection) {
        this.pagoalumnoCollection = pagoalumnoCollection;
    }

    @XmlTransient
    public Collection<Pagoprofesor> getPagoprofesorCollection() {
        return pagoprofesorCollection;
    }

    public void setPagoprofesorCollection(Collection<Pagoprofesor> pagoprofesorCollection) {
        this.pagoprofesorCollection = pagoprofesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accesodatos.Entidades.Profesor[ idProfesor=" + idProfesor + " ]";
    }
    
}
