/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxloginregcrubform1;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafxloginregcrubform1.database;

/**
 *
 * @author Jean_Nico .T
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane login_form;

    @FXML
    private Button si_creatAccountBtn;

    @FXML
    private Button si_loginBtn;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private BorderPane signup_form;

    @FXML
    private Button su_loginAccountBtn;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signupBtn;

    @FXML
    private TextField su_username;

    //DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void loginAccount() {
        String sql = "SELECT username, password FROM admin WHERE username = ? and password = ?";
        connect = database.coonect();

        try {

            Alert alert;
            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blan field");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, si_username.getText());
                prepare.setString(2, si_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    //IF CORRECT USERNAME AND PASSWORD
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();
                    
                    // TO HIDE THE LOGIN FORM
                    si_loginBtn.getScene().getWindow().hide();
                    
                    //TO SHOW CRUD form
                    Parent root = FXMLLoader.load(getClass().getResource("crudForm.fxml"));
                    
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.show();
                    

                } else {
                    //IF INCORRECT USERNAME AND PASSWORD
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("incorrect Username/Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    
    //NOW, LETS CREATE OUR CRUD FORM
    public void registerAccount() {

        String sql = "INSERT INTO admin (username, password) VALUES(?,?)";

        connect = database.coonect();

        try {
            Alert alert;
            if (su_username.getText().isEmpty() || su_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blan field");
                alert.showAndWait();
            } else {

                //CHECK IF USERNAME IS ALREADY TAKEN
                String checkData = "SELECT username FROM admin WHERE username = '"
                        + su_username.getText() + "'";

                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " Is already taken");
                    alert.showAndWait();
                } else {

                    if (su_password.getText().length() < 8) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalide password, atleast 8 characters needed");
                        alert.showAndWait();

                    } else {
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, su_username.getText());
                        prepare.setString(2, su_password.getText());

                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully creat a new Account!");
                        alert.showAndWait();
                        
                        login_form.setVisible(true);
                        signup_form.setVisible(false);
                        
                        su_username.setText("");
                        su_password.setText("");
                    }
                }
            }

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == su_loginAccountBtn) {
            login_form.setVisible(true);
            signup_form.setVisible(false);
        } else if (event.getSource() == si_creatAccountBtn) {
            login_form.setVisible(false);
            signup_form.setVisible(true);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
