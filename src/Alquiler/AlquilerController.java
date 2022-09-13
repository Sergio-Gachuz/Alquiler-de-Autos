package Alquiler;

import Clases.Alquiler;
import Clases.Empleado;
import Database.DAO.AlquilerDAO;
import Database.DAO.EmpleadoDAO;
import Database.MySQL;
import Empleados.InformacionController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlquilerController implements Initializable{
    @FXML TableView<Alquiler> tvlAlquiler;
    @FXML
    Button btnBorrar;
    AlquilerDAO alquilerDAO;
    Alquiler alquiler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn idClave = new TableColumn("Clave");
        TableColumn claveRenta = new TableColumn("Renta");
        TableColumn claveDev = new TableColumn("Devolucion");
        TableColumn Matricula = new TableColumn("Matricula");
        TableColumn RFC = new TableColumn("RFC");
        TableColumn tipoPago = new TableColumn("Pago");
        TableColumn fechaR = new TableColumn("Renta");
        TableColumn fechaD = new TableColumn("Devolucion");
        TableColumn HoraR = new TableColumn("Hora R");
        TableColumn HoraD = new TableColumn("Hora D");
        TableColumn costo = new TableColumn("Costo");


        idClave.setCellValueFactory(new PropertyValueFactory<>("CveAlquiler"));
        claveRenta.setCellValueFactory(new PropertyValueFactory<>("CveAgenciaRenta"));
        claveDev.setCellValueFactory(new PropertyValueFactory<>("CveAgenciaDev"));
        Matricula.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
        RFC.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        tipoPago.setCellValueFactory(new PropertyValueFactory<>("TipoPago"));
        fechaR.setCellValueFactory(new PropertyValueFactory<>("FechaRenta"));
        fechaD.setCellValueFactory(new PropertyValueFactory<>("FechaDev"));
        HoraR.setCellValueFactory(new PropertyValueFactory<>("HoraRenta"));
        HoraD.setCellValueFactory(new PropertyValueFactory<>("HoraDev"));
        costo.setCellValueFactory(new PropertyValueFactory<>("CostoTotal"));

        tvlAlquiler.getColumns().addAll(idClave, claveRenta, claveDev, Matricula, RFC, tipoPago, fechaR, fechaD, HoraR, HoraD, costo);
        alquilerDAO = new AlquilerDAO(MySQL.getConnection());
        tvlAlquiler.setItems(alquilerDAO.fetchAll());
        tvlAlquiler.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnBorrar.setDisable(false);
            }
        });

        btnBorrar.setOnAction(eventHandler);

        btnBorrar.setDisable(true);
    }

    EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnBorrar){
                ConfirmEliminar();
            }
        }
    };

    private void recargarAgencias() {
        tvlAlquiler.setItems(alquilerDAO.fetchAll());
    }

    private void ConfirmEliminar(){
        Alquiler alquiler = tvlAlquiler.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setContentText("Desea eliminar este registro?");
        Optional<ButtonType> respuesta = alert.showAndWait();
        if (respuesta.get() == ButtonType.OK){
            alquilerDAO.delete(alquiler.getCveAlquiler());
            recargarAgencias();
        }
    }
}
