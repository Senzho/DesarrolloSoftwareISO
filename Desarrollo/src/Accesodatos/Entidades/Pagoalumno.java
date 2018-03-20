/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desktop
 */
@Entity
@Table(name = "pagoalumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagoalumno.findAll", query = "SELECT p FROM Pagoalumno p")
    , @NamedQuery(name = "Pagoalumno.findByIdPago", query = "SELECT p FROM Pagoalumno p WHERE p.idPago = :idPago")
    , @NamedQuery(name = "Pagoalumno.findByTipoPago", query = "SELECT p FROM Pagoalumno p WHERE p.tipoPago = :tipoPago")
    , @NamedQuery(name = "Pagoalumno.findByFecha", query = "SELECT p FROM Pagoalumno p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pagoalumno.findByMonto", query = "SELECT p FROM Pagoalumno p WHERE p.monto = :monto")})
public class Pagoalumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPago")
    private Integer idPago;
    @Column(name = "tipoPago")
    private Integer tipoPago;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "monto")
    private String monto;
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno")
    @ManyToOne
    private Alumno idAlumno;
    @JoinColumn(name = "idPromocion", referencedColumnName = "idPromocion")
    @ManyToOne
    private Promocion idPromocion;

    public Pagoalumno() {
    }

    public Pagoalumno(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Promocion getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Promocion idPromocion) {
        this.idPromocion = idPromocion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagoalumno)) {
            return false;
        }
        Pagoalumno other = (Pagoalumno) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accesodatos.Entidades.Pagoalumno[ idPago=" + idPago + " ]";
    }
    
}
