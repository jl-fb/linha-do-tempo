package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ConnectionConfiguration;

import java.io.IOException;
import java.sql.*;

public class ControllerInsertSQL extends AnchorPane {

    @FXML
    private TextField txtNomeTelaInserir, txtDataTelaInserir;
    @FXML
    private TextArea descTelaInserir;
    @FXML
    private Button botaoVoltar;

    public ControllerInsertSQL() {

    }

    public void addEvento() throws SQLException {

        Connection con;
        con = ConnectionConfiguration.getConnection();

        String sql = "INSERT INTO eventos(nome,data, descricao) VALUES(?,?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, txtNomeTelaInserir.getText());
        stmt.setString(2, txtDataTelaInserir.getText());
        stmt.setString(3, descTelaInserir.getText());
        stmt.executeUpdate();
        stmt.close();

        txtDataTelaInserir.setText("");
        txtNomeTelaInserir.setText("");
        descTelaInserir.setText("");
        stmt.close();
        con.close();
    }

        // Setando a controller da tela inserir, para voltar para tela principal

    public void voltarTelaPrincipal() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/telaPrincipal.fxml"));
        App inserir = new App();
        Parent telaPincipal = fxmlLoader.load();
        Scene scene = new Scene(telaPincipal);
        Stage window = (Stage) botaoVoltar.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();

    }

}


