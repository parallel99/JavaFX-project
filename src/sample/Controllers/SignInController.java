package sample.Controllers;


import javafx.stage.Modality;
import javafx.stage.StageStyle;
import sample.model.UserSession;
import sample.ConnectDatabase.MySqlConnect;
import sample.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SignInController extends Main {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    public void SignIn(ActionEvent actionEvent) {

        MySqlConnect connection = new MySqlConnect();
        Connection conn = connection.getConnection();

        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) as ROW, `id`, `username`, `email` FROM `users` WHERE `email` = ? AND `password` = ?");
            stmt.setString(1, email.getText());
            stmt.setString(2, SHA_Encrypt(password.getText()));
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if(rs.getInt("ROW") == 1){
                SaveUser(rs);

                //Close SignIn Stage
                Stage loginSystemStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                loginSystemStage.close();

                OpenToDoApp();
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("ToString: " + ex.toString());
            System.out.println("Message: " + ex.getMessage());
        }
    }

    public void ChangeSignUp(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/resources/view/SignUp.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            window.setScene(scene);
            window.show();
            MoveScene(root, window, scene);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void OpenToDoApp(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/resources/view/ToDo.fxml"));
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.setMinWidth(815);
            window.setMinHeight(615);
            window.setScene(scene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.initStyle(StageStyle.DECORATED);
            window.show();
            MoveScene(root, window, scene);
        } catch (IOException ex){
            System.out.println("Message: " + ex.getMessage());
            System.out.println("ToString: " + ex.toString());
            System.out.println("StackTrace: " + ex.getStackTrace());
            System.out.println("Cause: " + ex.getCause());
        }
    }

    private void SaveUser(ResultSet rs){
        try {
            UserSession.getInstace(rs.getInt("id"), rs.getString("username"), rs.getString("email"));
        }catch (SQLException ex){
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("ToString: " + ex.toString());
            System.out.println("Message: " + ex.getMessage());
        }
    }
}
