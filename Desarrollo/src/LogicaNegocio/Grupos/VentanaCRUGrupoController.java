package LogicaNegocio.Grupos;

import Accesodatos.Catalogos.ProfesorDAOSql;
import Accesodatos.Grupos.GrupoDAOSql;
import InterfazGrafica.MessageFactory;
import LogicaNegocio.Catalogos.Profesor;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class VentanaCRUGrupoController implements Initializable {
    @FXML
    private TextField nombre;
    @FXML
    private TextField danza;
    @FXML
    private ComboBox profesores;
    @FXML
    private FlowPane panelHorario;
    @FXML
    private ImageView agregarDia;
    @FXML
    private Button guardar;
    
    private List<Dia> dias;
    private Grupo grupo;
    private List<Profesor> listaProfesores;
    private GrupoDAOSql grupoDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.grupoDAO = new GrupoDAOSql();
        this.dias = new ArrayList();
        this.agregarDia.setImage(new Image(this.getClass().getResourceAsStream("/RecursosGraficos/darkPlusIcon.png")));
        this.cargarProfesores();
    }
    
    public void cargarProfesores(){
        this.listaProfesores = new ArrayList();
        ProfesorDAOSql profesorDAO = new ProfesorDAOSql();
        this.listaProfesores = profesorDAO.obtenerProfesores();
        this.listaProfesores.forEach((profesor) -> {
            this.profesores.getItems().add(profesor.getNombre());
        });
        this.profesores.setValue(String.valueOf(this.listaProfesores.get(0).getNombre()));
    }
    public void setGrupo(Grupo grupo){
        this.grupo = grupo;
        this.cargarGrupo();
    }
    public void cargarGrupo(){
        this.nombre.setText(this.grupo.getNombre());
        this.danza.setText(this.grupo.getDanza());
        this.profesores.setValue(String.valueOf(this.grupo.getProfesor().getNombre()));
        this.grupo.getHorario().getDias().forEach((dia) -> {
            this.nuevoDia(dia);
        });
    }
    public void nuevoDia(Dia dia){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/InterfazGrafica/Grupos/PanelDiaGrupo.fxml"));
        AnchorPane panel;
        try {
            panel = loader.load();
            PanelDiaGrupoController controller = loader.getController();
            controller.setVentanaCRUGrupoController(this);
            controller.setPane(panel);
            if (dia != null){
                controller.setDia(dia);
            }
            this.dias.add(controller.getDia());
            this.panelHorario.getChildren().add(panel);
        } catch (IOException ex) {
            Logger.getLogger(VentanaCRUGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void borrarDia(AnchorPane diaPane, Dia dia){
        this.panelHorario.getChildren().remove(diaPane);
        this.dias.remove(dia);
    }
    public Profesor getProfesor(){
        Profesor profesor = null;
        for (Profesor profesorLista : this.listaProfesores){
            if (profesorLista.getNombre().equals(this.profesores.getValue())){
                profesor = profesorLista;
                break;
            }
        }
        return profesor;
    }
    public Dia validarHorario(){
        Dia diaValidacion = null;
        fors:
        for(Dia dia : this.dias){
            for (Dia dia2 : this.dias){
                if (!dia.equals(dia2)){
                    if (dia.getSalon().equals(dia2.getSalon()) && dia.getDia().equals(dia2.getDia())){
                        int miliIni2 = Horas.getSegundos(dia2.getHoraInicio());
                        int miliIni = Horas.getSegundos(dia.getHoraInicio());
                        int miliFin2 = Horas.getSegundos(dia2.getHoraFin());
                        int miliFin = Horas.getSegundos(dia.getHoraFin());;
                        if ((miliIni >= miliIni2 && miliIni <= miliFin2) || (miliFin >= miliIni2 && miliFin <= miliFin2)){
                            diaValidacion = dia;
                            break fors;
                        }
                    }
                }
            }
        }
        return diaValidacion;
    }
    public GrupoEnum validarDatos(){
        GrupoEnum grupoEnum = GrupoEnum.DATOS_VALIDOS;
        if (this.nombre.getText().trim().length() == 0){
            grupoEnum = GrupoEnum.NOMBRE_VACIO;
        }else if (this.nombre.getText().trim().length() > 50){
            grupoEnum = GrupoEnum.NOMBRE_LARGO;
        }else if (this.danza.getText().trim().length() == 0){
            grupoEnum = GrupoEnum.DANZA_VACIA;
        }else if (this.danza.getText().trim().length() > 50){
            grupoEnum = GrupoEnum.DANZA_LARGA;
        }else if (this.dias.isEmpty()){
            grupoEnum = GrupoEnum.HORARIO_VACIO;
        }else if(this.validarHorario() != null){
            grupoEnum = GrupoEnum.HORARIO_INVALIDO;
        }
        return grupoEnum;
    }
    public void mostrarMensajeError(GrupoEnum grupoEnum){
        String mensaje;
        switch(grupoEnum){
            case NOMBRE_VACIO:
                mensaje = "El campo nombre es requerido";
                break;
            case NOMBRE_LARGO:
                mensaje = "El campo nombre supera los 50 caractéres permitidos";
                break;
            case DANZA_VACIA:
                mensaje = "El campo danza es requerido";
                break;
            case DANZA_LARGA:
                mensaje = "El campo danza supera los 50 caractéres permitidos";
                break;
            case HORARIO_VACIO:
                mensaje = "Debes ingresar el horario";
                break;
            case HORARIO_INVALIDO:
                mensaje = "Los días ingresados tienen conflictos entre sí";
                break;
            default:
                mensaje = "null";
                break;
        }
        MessageFactory.showMessage("Error", "Registro", mensaje, Alert.AlertType.WARNING);
    }
    public void crearGrupo(){
        this.grupo = new Grupo();
        this.grupo.setDanza(this.danza.getText().trim());
        this.grupo.setNombre(this.nombre.getText().trim());
        this.grupo.setId(0);
        this.grupo.setProfesor(this.getProfesor());
        Horario horario = new Horario();
        horario.setIdGrupo(0);
        horario.setDias(this.dias);
        this.grupo.setHorario(horario);
        try {
            if (this.grupoDAO.registrarGrupo(this.grupo)){
                MessageFactory.showMessage("Éxito", "Registro", "El grupo fue registrado exitosamente", Alert.AlertType.INFORMATION);
            }else{
                this.grupo = null;
                MessageFactory.showMessage("Error", "Registro", "El grupo no pudo ser registrado", Alert.AlertType.ERROR);
            }
        } catch (HorarioException ex) {
            this.grupo = null;
            MessageFactory.showMessage("Error", "Horario", ex.getMessage(), Alert.AlertType.WARNING);
            Logger.getLogger(VentanaCRUGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarGrupo(){
        
    }
    
    public void agregarDia_onClick(){
        this.nuevoDia(null);
    }
    public void guardar_onClick(){
        GrupoEnum grupoEnum = this.validarDatos();
        if (grupoEnum.equals(GrupoEnum.DATOS_VALIDOS)){
            if (this.grupo == null){
                this.crearGrupo();
            }else{
                this.editarGrupo();
            }
        }else{
            this.mostrarMensajeError(grupoEnum);
        }
    }
}
