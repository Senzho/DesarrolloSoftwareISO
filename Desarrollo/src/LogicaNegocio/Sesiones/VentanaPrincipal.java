package LogicaNegocio.Sesiones;

import LogicaNegocio.Asistencia.PanelAsistenciaController;
import LogicaNegocio.Asistencia.PanelRegistroAsistenciasController;
import LogicaNegocio.Catalogos.PanelCatalogoAlumnosController;
import LogicaNegocio.Catalogos.PanelCatalogoClientesController;
import LogicaNegocio.Catalogos.PanelCatalogoProfesoresController;
import LogicaNegocio.Egresos.PanelConsultarPromocionesEgresosController;
import LogicaNegocio.Egresos.PanelIngresosEgresosController;
import LogicaNegocio.Grupos.PanelAlumnosGrupoDirectorController;
import LogicaNegocio.Grupos.PanelAlumnosGrupoProfesorController;
import LogicaNegocio.Grupos.PanelGruposProfesorController;
import LogicaNegocio.Grupos.PanelSemanaController;
import LogicaNegocio.Pagos.PanelConsultarRentasController;
import LogicaNegocio.Pagos.PanelHistorialPagoProfesoresController;
import LogicaNegocio.Pagos.PanelHistorialPagosAlumnoController;
import LogicaNegocio.Pagos.PanelHistorialPagosClienteController;
import LogicaNegocio.Pagos.PanelPagosTemporalesController;
import LogicaNegocio.Pagos.PanelProximosPagosAlumnoController;
import LogicaNegocio.Pagos.PanelProximosPagosProfesorController;

public class VentanaPrincipal {
    private PanelAsistenciaController registrarAsistencia;
    private PanelRegistroAsistenciasController historialAsistencia;
    private PanelProximosPagosProfesorController proximosPagosProfesor;
    private PanelProximosPagosAlumnoController proximosPagosAlumno;
    private PanelGruposProfesorController misGrupos;
    private PanelAlumnosGrupoProfesorController alumnosGrupoProfesor;
    private PanelAlumnosGrupoDirectorController alumnosGrupoDirector;
    private PanelHistorialPagosAlumnoController pagosAlumno;
    private PanelHistorialPagoProfesoresController pagosProfesor;
    private PanelHistorialPagosClienteController pagosCliente;
    private PanelPagosTemporalesController pagosTemporales;
    private PanelSemanaController gruposRentas;
    private PanelConsultarRentasController rentas;
    private PanelCatalogoAlumnosController catalogoAlumnos;
    private PanelCatalogoProfesoresController catalogoProfesores;
    private PanelCatalogoClientesController catalogoClientes;
    private PanelConsultarPromocionesEgresosController egresos;
    private PanelIngresosEgresosController reporte;
    
    public VentanaPrincipal(){
        
    }
    
    public void establecerControlador(Object controlador){
        if (controlador instanceof PanelAsistenciaController){
            this.registrarAsistencia = (PanelAsistenciaController) controlador;
        }else if(controlador instanceof PanelRegistroAsistenciasController){
            this.historialAsistencia = (PanelRegistroAsistenciasController) controlador;
        }else if(controlador instanceof PanelProximosPagosProfesorController){
            this.proximosPagosProfesor = (PanelProximosPagosProfesorController) controlador;
        }else if(controlador instanceof PanelProximosPagosAlumnoController){
            this.proximosPagosAlumno = (PanelProximosPagosAlumnoController) controlador;
        }else if(controlador instanceof PanelGruposProfesorController){
            this.misGrupos = (PanelGruposProfesorController) controlador;
        }else if(controlador instanceof PanelAlumnosGrupoProfesorController){
            this.alumnosGrupoProfesor = (PanelAlumnosGrupoProfesorController) controlador;
        }else if(controlador instanceof PanelAlumnosGrupoDirectorController){
            this.alumnosGrupoDirector = (PanelAlumnosGrupoDirectorController) controlador;
        }else if(controlador instanceof PanelHistorialPagosAlumnoController){
            this.pagosAlumno = (PanelHistorialPagosAlumnoController) controlador;
        }else if(controlador instanceof PanelHistorialPagoProfesoresController){
            this.pagosProfesor = (PanelHistorialPagoProfesoresController) controlador;
        }else if(controlador instanceof PanelHistorialPagosClienteController){
            this.pagosCliente = (PanelHistorialPagosClienteController) controlador;
        }else if(controlador instanceof PanelPagosTemporalesController){
            this.pagosTemporales = (PanelPagosTemporalesController) controlador;
        }else if(controlador instanceof PanelSemanaController){
            this.gruposRentas = (PanelSemanaController) controlador;
        }else if(controlador instanceof PanelConsultarRentasController){
            this.rentas = (PanelConsultarRentasController) controlador;
        }else if(controlador instanceof PanelCatalogoAlumnosController){
            this.catalogoAlumnos = (PanelCatalogoAlumnosController) controlador;
        }else if(controlador instanceof PanelCatalogoProfesoresController){
            this.catalogoProfesores = (PanelCatalogoProfesoresController) controlador;
        }else if(controlador instanceof PanelCatalogoClientesController){
            this.catalogoClientes = (PanelCatalogoClientesController) controlador;
        }else if(controlador instanceof PanelConsultarPromocionesEgresosController){
            this.egresos = (PanelConsultarPromocionesEgresosController) controlador;
        }else if(controlador instanceof PanelIngresosEgresosController){
            this.reporte = (PanelIngresosEgresosController) controlador;
        }
    }

    public PanelAsistenciaController getRegistrarAsistencia() {
        return registrarAsistencia;
    }
    public void setRegistrarAsistencia(PanelAsistenciaController registrarAsistencia) {
        this.registrarAsistencia = registrarAsistencia;
    }
    public PanelRegistroAsistenciasController getHistorialAsistencia() {
        return historialAsistencia;
    }
    public void setHistorialAsistencia(PanelRegistroAsistenciasController historialAsistencia) {
        this.historialAsistencia = historialAsistencia;
    }
    public PanelProximosPagosProfesorController getProximosPagosProfesor() {
        return proximosPagosProfesor;
    }
    public void setProximosPagosProfesor(PanelProximosPagosProfesorController proximosPagosProfesor) {
        this.proximosPagosProfesor = proximosPagosProfesor;
    }
    public PanelProximosPagosAlumnoController getProximosPagosAlumno() {
        return proximosPagosAlumno;
    }
    public void setProximosPagosAlumno(PanelProximosPagosAlumnoController proximosPagosAlumno) {
        this.proximosPagosAlumno = proximosPagosAlumno;
    }
    public PanelGruposProfesorController getMisGrupos() {
        return misGrupos;
    }
    public void setMisGrupos(PanelGruposProfesorController misGrupos) {
        this.misGrupos = misGrupos;
    }
    public PanelAlumnosGrupoProfesorController getAlumnosGrupoProfesor() {
        return alumnosGrupoProfesor;
    }
    public void setAlumnosGrupoProfesor(PanelAlumnosGrupoProfesorController alumnosGrupoProfesor) {
        this.alumnosGrupoProfesor = alumnosGrupoProfesor;
    }
    public PanelAlumnosGrupoDirectorController getAlumnosGrupoDirector() {
        return alumnosGrupoDirector;
    }
    public void setAlumnosGrupoDirector(PanelAlumnosGrupoDirectorController alumnosGrupoDirector) {
        this.alumnosGrupoDirector = alumnosGrupoDirector;
    }
    public PanelHistorialPagosAlumnoController getPagosAlumno() {
        return pagosAlumno;
    }
    public void setPagosAlumno(PanelHistorialPagosAlumnoController pagosAlumno) {
        this.pagosAlumno = pagosAlumno;
    }
    public PanelHistorialPagoProfesoresController getPagosProfesor() {
        return pagosProfesor;
    }
    public void setPagosProfesor(PanelHistorialPagoProfesoresController pagosProfesor) {
        this.pagosProfesor = pagosProfesor;
    }
    public PanelHistorialPagosClienteController getPagosCliente() {
        return pagosCliente;
    }
    public void setPagosCliente(PanelHistorialPagosClienteController pagosCliente) {
        this.pagosCliente = pagosCliente;
    }
    public PanelPagosTemporalesController getPagosTemporales() {
        return pagosTemporales;
    }
    public void setPagosTemporales(PanelPagosTemporalesController pagosTemporales) {
        this.pagosTemporales = pagosTemporales;
    }
    public PanelSemanaController getGruposRentas() {
        return gruposRentas;
    }
    public void setGruposRentas(PanelSemanaController gruposRentas) {
        this.gruposRentas = gruposRentas;
    }
    public PanelConsultarRentasController getRentas() {
        return rentas;
    }
    public void setRentas(PanelConsultarRentasController rentas) {
        this.rentas = rentas;
    }
    public PanelCatalogoAlumnosController getCatalogoAlumnos() {
        return catalogoAlumnos;
    }
    public void setCatalogoAlumnos(PanelCatalogoAlumnosController catalogoAlumnos) {
        this.catalogoAlumnos = catalogoAlumnos;
    }
    public PanelCatalogoProfesoresController getCatalogoProfesores() {
        return catalogoProfesores;
    }
    public void setCatalogoProfesores(PanelCatalogoProfesoresController catalogoProfesores) {
        this.catalogoProfesores = catalogoProfesores;
    }
    public PanelCatalogoClientesController getCatalogoClientes() {
        return catalogoClientes;
    }
    public void setCatalogoClientes(PanelCatalogoClientesController catalogoClientes) {
        this.catalogoClientes = catalogoClientes;
    }
    public PanelConsultarPromocionesEgresosController getEgresos() {
        return egresos;
    }
    public void setEgresos(PanelConsultarPromocionesEgresosController egresos) {
        this.egresos = egresos;
    }
    public PanelIngresosEgresosController getReporte() {
        return reporte;
    }
    public void setReporte(PanelIngresosEgresosController reporte) {
        this.reporte = reporte;
    }  
}
