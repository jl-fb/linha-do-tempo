package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ConnectionConfiguration;
import model.Evento;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;


public class ControllerCosulta extends AnchorPane implements Initializable {
    @FXML
    private Button botaoVoltar, botaoDelete;
    @FXML
    private TextField txtConsultaNome, txtConsultaData;
    @FXML
    private TextArea txtEditar;
    @FXML
    private TableView<Evento> tabelaConsulta;
    @FXML
    private TableColumn<Evento, Integer> col_id;
    @FXML
    private TableColumn<Evento, String> col_evento;
    @FXML
    private TableColumn<Evento, String> col_data;
    @FXML
    private TableColumn<Evento, String> col_descricao;

    // private ObservableList<Evento> row = FXCollections.observableArrayList();
    //private List<Evento> rowList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;

        try {
            Statement stmt = ConnectionConfiguration.getConnection().createStatement();
            String sql = "SELECT rowid, nome, data, descricao FROM eventos ORDER BY data;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                ev = new Evento(rs.getInt("rowid"),
                        rs.getString("nome"),
                        rs.getString("data"),
                        rs.getString("descricao"));
                eventos.add(ev);
               /* Integer id = rs.getInt("rowid");
                String nome = rs.getString("nome");
                String data = rs.getString("data");

                row.add(new Evento(id, nome, data));
                rowList.add(new Evento(id, nome, data));*/
                //ConnectionConfiguration.getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println("Erro: "+e);
        }


        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_evento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        tabelaConsulta.setItems(FXCollections.observableArrayList(eventos));

    }

    public void mostrarTabela() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;
        try {
            Statement stmt = ConnectionConfiguration.getConnection().createStatement();
            String sql = "SELECT rowid, nome, data, descricao FROM eventos ORDER BY data;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                ev = new Evento(rs.getInt("rowid"),
                        rs.getString("nome"),
                        rs.getString("data"),
                        rs.getString("descricao"));
                eventos.add(ev);
                //ConnectionConfiguration.getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println("Erro: "+e);
        }
        tabelaConsulta.setItems(FXCollections.observableArrayList(eventos));

    }

    public void voltarTelaInserir() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/telaInserir.fxml"));
        ControllerInsertSQL inserir = new ControllerInsertSQL();
        fxmlLoader.setController(inserir);
        fxmlLoader.setRoot(inserir);
        Parent telaInserir = fxmlLoader.load();
        Scene scene = new Scene(telaInserir);
        Stage window = (Stage) botaoVoltar.getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    public void botaoConsultaNome() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;
        try {
            PreparedStatement stmt = ConnectionConfiguration.getConnection().prepareStatement("SELECT rowid, nome, " +
                    "data, descricao FROM eventos WHERE nome LIKE ? ORDER BY data;");
            stmt.setString(1, '%' + txtConsultaNome.getText() + '%');
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                ev = new Evento(rs.getInt("rowid"),
                        rs.getString("nome"),
                        rs.getString("data"),
                        rs.getString("descricao"));
                eventos.add(ev);
                //ConnectionConfiguration.getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println("Erro: "+e);
        }

        /*col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_evento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_evento.setCellFactory(TextFieldTableCell.forTableColumn());*/

        tabelaConsulta.setItems(FXCollections.observableArrayList(eventos));

    }

    public void botaoConsultaData() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;
        try {
            PreparedStatement stmt = ConnectionConfiguration.getConnection().prepareStatement("SELECT rowid, nome, " +
                    "data, descricao FROM eventos WHERE data LIKE ? ORDER BY data;");
            stmt.setString(1, '%' + txtConsultaData.getText() + '%');
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                ev = new Evento(rs.getInt("rowid"),
                        rs.getString("nome"),
                        rs.getString("data"),
                        rs.getString("descricao"));
                eventos.add(ev);

            }
        } catch (SQLException e) {
            System.out.println("Erro: "+e);
        }

        /*col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_evento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_evento.setCellFactory(TextFieldTableCell.forTableColumn());*/
        tabelaConsulta.setItems(FXCollections.observableArrayList(eventos));

    }
    public void botaoConsultaDesc() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;
        Connection con;
        con = ConnectionConfiguration.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT rowid, nome, " +
                    "data, descricao FROM eventos WHERE descricao LIKE ? ORDER BY data;");
            stmt.setString(1, '%' + txtEditar.getText() + '%');
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                ev = new Evento(rs.getInt("rowid"),
                        rs.getString("nome"),
                        rs.getString("data"),
                        rs.getString("descricao"));
                eventos.add(ev);

            }
        } catch (SQLException e) {
            System.out.println("Erro: "+e);
        }

        /*col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_evento.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_evento.setCellFactory(TextFieldTableCell.forTableColumn());*/
        tabelaConsulta.setItems(FXCollections.observableArrayList(eventos));

    }


    public void setItens() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev = null;
        Connection con;
        con = ConnectionConfiguration.getConnection();
        String sql = "SELECT rowid, nome, data, descricao FROM eventos WHERE rowid =?;";

       if (tabelaConsulta.getSelectionModel().getSelectedItem().getId()!=null){
           try {
               PreparedStatement statement = con.prepareStatement(sql);
               statement.setInt(1, tabelaConsulta.getSelectionModel().getSelectedItem().getId());
               ResultSet rs = statement.executeQuery();

               while (rs.next()) {
                   ev = new Evento(rs.getInt("rowid"),
                           rs.getString("nome"),
                           rs.getString("data"),
                           rs.getString("descricao"));
                   eventos.add(ev);

               }

               txtConsultaData.setText(ev.getData());
               txtConsultaNome.setText(ev.getNome());
               txtEditar.setText(ev.getDescricao());
           } catch (SQLException e) {
               System.out.println("Erro: "+e);
           }
       }

    }

    public void updateItens() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;
        Connection con;
        con = ConnectionConfiguration.getConnection();
        String sql = "UPDATE eventos SET nome =?, data =?, descricao=? WHERE rowid = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, txtConsultaNome.getText());
            stmt.setString(2, txtConsultaData.getText());
            stmt.setString(3, txtEditar.getText());
            stmt.setInt(4, tabelaConsulta.getSelectionModel().getSelectedItem().getId());
            stmt.executeUpdate();
            txtEditar.setText("");
            txtConsultaNome.setText("");
            txtConsultaData.setText("");

        } catch (SQLException e) {
            System.out.println("Erro: "+e);
        }
        mostrarTabela();

    }
    public void deleteItem(){
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev=null;
        Connection con;
        con = ConnectionConfiguration.getConnection();
        String sql = "DELETE FROM eventos WHERE rowid = ?;";
        String sql2 = "SELECT rowid,nome,data,descricao FROM eventos WHERE rowid =?;";

            botaoDelete.setOnAction(e -> {
                Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType btnSim = new ButtonType("Sim");
                ButtonType btnNao = new ButtonType("Não");

                dialogoExe.setTitle("Confirmação de ação");
                dialogoExe.setHeaderText("Você deseja excluir este item?");
                dialogoExe.getButtonTypes().setAll(btnNao, btnSim);
                dialogoExe.showAndWait().ifPresent(b -> {

                    if (b == btnSim) {
                        try {
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, tabelaConsulta.getSelectionModel().getSelectedItem().getId());
                            stmt.executeUpdate();
                            txtConsultaData.setText("");
                            txtConsultaNome.setText("");
                            txtEditar.setText("");

                        } catch (SQLException ex) {

                            System.out.println("Erro: " + ex);
                        }
                        mostrarTabela();
                    } else {
                        dialogoExe.close();
                    }
                });
            });

        mostrarTabela();
    }
}
/*
    public void updateData() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;
        Connection con;
        con = ConnectionConfiguration.getConnection();
        String sql = "UPDATE eventos SET data = ? WHERE rowid = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, txtConsultaData.getText());
            stmt.setInt(2, tabelaConsulta.getSelectionModel().getSelectedItem().getId());
            stmt.executeUpdate();
            txtConsultaData.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        mostrarTabela();

    }

    public void updateNome() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento ev;
        Connection con;
        con = ConnectionConfiguration.getConnection();
        String sql = "UPDATE eventos SET nome = ? WHERE rowid = ?;";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, txtConsultaNome.getText());
            stmt.setInt(2, tabelaConsulta.getSelectionModel().getSelectedItem().getId());
            stmt.executeUpdate();
            txtConsultaNome.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        mostrarTabela();

    }
}
*/


