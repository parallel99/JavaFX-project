package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {
    double xOffset, yOffset;

    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/resources/view/SignIn.fxml"));
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            File f = new File("src/sample/resources/css/style.css");
            scene.getStylesheets().add(f.toURI().toURL().toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            MoveScene(root, primaryStage, scene);
        } catch (IllegalAccessError | IOException e){
            System.out.println("Message: " + e.getMessage());
            System.out.println("ToString: " + e.toString());
            System.out.println("StackTrace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        } catch(Exception e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("ToString: " + e.toString());
            System.out.println("StackTrace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        }
    }

    public void MoveScene(Parent root, Stage primaryStage, Scene scene){
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = primaryStage.getX() - mouseEvent.getScreenX();
                yOffset = primaryStage.getY() - mouseEvent.getScreenY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() + xOffset);
                primaryStage.setY(mouseEvent.getScreenY() + yOffset);
                scene.setCursor(Cursor.CLOSED_HAND);
            }
        });
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
    }

    public void CloseWindow(MouseEvent actionEvent) {
        Stage s = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        s.close();
    }

    public void WindowMinimize(MouseEvent actionEvent) {
        Stage s = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    public String SHA_Encrypt(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
