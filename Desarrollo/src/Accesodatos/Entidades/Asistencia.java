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
 * @author Victor Javier
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
    , @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia")
    , @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Asistencia.findRegistrada", query = "SELECT a FROM Asistencia a WHERE a.idGrupo.idGrupo = :idGrupo and a.fecha = :fecha")
    , @NamedQuery(name = "Asistencia.findAllByIdCurso", query ="SELECT a FROM Asistencia a WHERE a.idGrupo.idGrupo =:idGrupo AND a.idAlumno.idAlumno=:idAlumno")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsistencia")
    private Integer idAsistencia;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idGrupo", referencedColumnName = "idGrupo")
    @ManyToOne
    private Grupo idGrupo;
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno")
    @ManyToOne
    private Alumno idAlumno;

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accesodatos.Entidades.Asistencia[ idAsistencia=" + idAsistencia + " ]";
    }
    
}
