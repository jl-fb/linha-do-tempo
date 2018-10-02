package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller extends AnchorPane {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Button botaoGrandeInserir;

    public Controller(){

    }


    public void mudarTelaInserir() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/telaInserir.fxml"));
        ControllerInsertSQL inserir = new ControllerInsertSQL();
        fxmlLoader.setController(inserir);
        fxmlLoader.setRoot(inserir);
        Parent telaInserir = fxmlLoader.load();
        Scene scene = new Scene(telaInserir);
        Stage window = (Stage) menuBar.getScene().getWindow();
        window.setTitle("INSERIR DADOS HISTÓRICOS");
        window.setScene(scene);
        window.centerOnScreen();

    }
    public void mudarTelaConsultar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/telaConsulta.fxml"));
        ControllerCosulta consultar = new ControllerCosulta();
        fxmlLoader.setController(consultar);
        fxmlLoader.setRoot(consultar);
        Parent telaInserir = fxmlLoader.load();
        Scene scene = new Scene(telaInserir);
        Stage window = (Stage) menuBar.getScene().getWindow();
        window.setTitle("CONSULTAR DADOS HISTÓRICOS");
        window.setScene(scene);
        window.centerOnScreen();

    }

}