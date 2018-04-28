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
import javax.persistence.Lob;
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
@Table(name = "gastopromocional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastopromocional.findAll", query = "SELECT g FROM Gastopromocional g")
    , @NamedQuery(name = "Gastopromocional.findByIdGasto", query = "SELECT g FROM Gastopromocional g WHERE g.idGasto = :idGasto")
    , @NamedQuery(name = "Gastopromocional.findByFechaInicio", query = "SELECT g FROM Gastopromocional g WHERE g.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Gastopromocional.findByFechaFin", query = "SELECT g FROM Gastopromocional g WHERE g.fechaFin = :fechaFin")
    , @NamedQuery(name = "Gastopromocional.findByMonto", query = "SELECT g FROM Gastopromocional g WHERE g.monto = :monto")
    , @NamedQuery(name = "Gastopromocional.findByUrl", query = "SELECT g FROM Gastopromocional g WHERE g.url = :url")})
public class Gastopromocional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGasto")
    private Integer idGasto;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "monto")
    private String monto;
    @Column(name = "url")
    private String url;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    public Gastopromocional() {
    }

    public Gastopromocional(Integer idGasto) {
        this.idGasto = idGasto;
    }

    public Integer getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Integer idGasto) {
        this.idGasto = idGasto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGasto != null ? idGasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastopromocional)) {
            return false;
        }
        Gastopromocional other = (Gastopromocional) object;
        if ((this.idGasto == null && other.idGasto != null) || (this.idGasto != null && !this.idGasto.equals(other.idGasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accesodatos.Entidades.Gastopromocional[ idGasto=" + idGasto + " ]";
    }
    
}
