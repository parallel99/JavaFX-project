package sample.Controllers;

import sample.ConnectDatabase.MySqlConnect;
import sample.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.*;

public class SignUpController extends Main {

    @FXML
    private Label username_label, email_label, password1_label, password2_label, success_signup;

    @FXML
    private TextField username, email;

    @FXML
    private PasswordField password1, password2;

    public void SignUp(ActionEvent actionEvent) {

        username_label.setText("");
        email_label.setText("");
        password1_label.setText("");
        password2_label.setText("");
        success_signup.setText("");

        boolean valid = true;

        valid = Username_Check();

        if(!valid){
            Email_Check();
        } else {
            valid = Email_Check();
        }

        if(!valid){
            Password_Check();
        } else {
            valid = Password_Check();
        }



        if(valid){
            try {
                MySqlConnect connection = new MySqlConnect();
                Connection conn = connection.getConnection();

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (`username`, `email`, `password`, `joinTime`) VAlUES(?, ?, ?, ?)");
                stmt.setString(1, username.getText());
                stmt.setString(2, email.getText());
                stmt.setString(3, SHA_Encrypt(password1.getText()));
                stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                stmt.executeUpdate();
                success_signup.setText("Successful signed up");
            }catch (SQLException e){
                System.out.println("Hiba: " + e.getMessage());
            }
        }
    }

    public void ChangeSignIn(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/resources/view/SignIn.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            scene.setFill(Color.TRANSPARENT);
            window.setResizable(true);
            window.setScene(scene);
            window.show();
            MoveScene(root, window, scene);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private boolean Username_Check(){
        boolean valid = true;

        if(username.getText().length() < 4){
            username_label.setText("Username is too short");
            valid = false;
        }

        if(username.getText().length() > 25){
            username_label.setText("Username is too long");
            valid = false;
        }

        if(username.getText().isEmpty()){
            username_label.setText("Username is required");
            valid = false;
        }

        return valid;
    }

    private boolean Email_Check(){
        EmailValidator validator = EmailValidator.getInstance();
        boolean valid = true;

        if(!validator.isValid(email.getText())){
            email_label.setText("Email address is invalid");
            valid = false;
        }

        if(email.getText().isEmpty()){
            email_label.setText("Email address is required");
            valid = false;
        }

        try {
            MySqlConnect connection = new MySqlConnect();
            Connection conn = connection.getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(`email`) as ROW FROM users WHERE `email` = ?");
            stmt.setString(1, email.getText());
            ResultSet rs = stmt.executeQuery();
            rs.next();

            if(rs.getInt("ROW") > 0) {
                email_label.setText("You have already registered this email");
                valid = false;
            }

            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ToString: " + e.toString());
            System.out.println("StackTrace: " + e.getStackTrace());
        }

        return valid;
    }

    private boolean Password_Check(){
        boolean valid = true;

        if(!password1.getText().equals(password2.getText())){
            password2_label.setText("Password and confirmation password do not match");
            valid = false;
        }

        if(password1.getText().length() < 4){
            password1_label.setText("Password is too short");
            valid = false;
        }

        if(password1.getText().length() > 25){
            password1_label.setText("Password is too long");
            valid = false;
        }

        if(password1.getText().isEmpty()){
            password1_label.setText("Password is required");
            valid = false;
        }
        if(password2.getText().isEmpty()){
            password2_label.setText("Password confirm is required");
            valid = false;
        }

        return valid;
    }
}
