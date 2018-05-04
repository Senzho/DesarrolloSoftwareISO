/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Accesodatos.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "dia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dia.findAll", query = "SELECT d FROM Dia d")
    , @NamedQuery(name = "Dia.findByIdDia", query = "SELECT d FROM Dia d WHERE d.idDia = :idDia")
    , @NamedQuery(name = "Dia.findBySalon", query = "SELECT d FROM Dia d WHERE d.salon = :salon")
    , @NamedQuery(name = "Dia.findByTipo", query = "SELECT d FROM Dia d WHERE d.tipo = :tipo")
    , @NamedQuery(name = "Dia.findByIdTipo", query = "SELECT d FROM Dia d WHERE d.idTipo = :idTipo")
    , @NamedQuery(name = "Dia.findByDia", query = "SELECT d FROM Dia d WHERE d.dia = :dia")
    , @NamedQuery(name = "Dia.findByHoraInicio", query = "SELECT d FROM Dia d WHERE d.horaInicio = :horaInicio")
    , @NamedQuery(name = "Dia.findByHoraFin", query = "SELECT d FROM Dia d WHERE d.horaFin = :horaFin")
    , @NamedQuery(name = "Dia.findByGrupo", query = "SELECT d FROM Dia d WHERE d.tipo = 1 and d.idTipo = :idGrupo")
    , @NamedQuery(name = "Dia.findByRenta", query = "SELECT d FROM Dia d WHERE d.tipo = 0 and d.idTipo = :idRenta")
})
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDia")
    private Integer idDia;
    @Column(name = "salon")
    private String salon;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "idTipo")
    private Integer idTipo;
    @Column(name = "dia")
    private String dia;
    @Column(name = "horaInicio")
    private String horaInicio;
    @Column(name = "horaFin")
    private String horaFin;

    public Dia() {
    }

    public Dia(Integer idDia) {
        this.idDia = idDia;
    }

    public Integer getIdDia() {
        return idDia;
    }

    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDia != null ? idDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dia)) {
            return false;
        }
        Dia other = (Dia) object;
        if ((this.idDia == null && other.idDia != null) || (this.idDia != null && !this.idDia.equals(other.idDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accesodatos.Entidades.Dia[ idDia=" + idDia + " ]";
    }
    
}
