package sample.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.RollIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.model.ToDoList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.model.UserSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDoController extends Main implements Initializable{

    @FXML
    private Label username;

    @FXML
    private Circle userImg;

    @FXML
    private TableView<ToDoList> ToDoTable;

    @FXML
    private TableColumn<ToDoList, String> col_todo;

    @FXML
    private TableColumn<ToDoList, String> col_date;

    @FXML
    private TableColumn<ToDoList, String> col_more;

    @FXML
    private TableColumn<ToDoList, String> col_status;

    @Override
    public void initialize(URL location,ResourceBundle resource){
        initTable();
    }

    private void initTable(){
        col_todo.resizableProperty();
        initCols();
    }

    private void initCols(){
        try {
            Image img = new Image(getClass().getResource("/sample/resources/image/man.jpg").toString());
            userImg.setFill(new ImagePattern(img));
        } catch (Exception e){
            System.out.println("Message: " + e.getMessage());
            System.out.println("ToString: " + e.toString());
            System.out.println("StackTrace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        }


        int id = UserSession.getInstace().getID();
        String name = UserSession.getInstace().getUserName();
        String email = UserSession.getInstace().getEmail();

        username.setText(name);
        col_todo.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_more.setCellValueFactory(new PropertyValueFactory<>("information"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<ToDoList> data = FXCollections.observableArrayList();
        col_todo.prefWidthProperty().bind(ToDoTable.widthProperty().divide(3));
        col_date.prefWidthProperty().bind(ToDoTable.widthProperty().divide(8));
        col_more.prefWidthProperty().bind(ToDoTable.widthProperty().divide(4));
        col_status.prefWidthProperty().bind(ToDoTable.widthProperty().divide(8));
        data.add(new ToDoList("Megcsinálni valamit","2021-02-10","Egy kis szöveg ide!!!","Aktív"));
        data.add(new ToDoList("Megcsinálni valakit","2022-12-28","Egy kis szöveg ide!!!","Aktív"));
        data.add(new ToDoList("Eladni a lekem","2021-05-15","Egy kis szöveg ide!!!","Aktív"));
        data.add(new ToDoList("Ellponi más lelkét","2021-04-05","Egy kis szöveg ide!!!","Aktív"));
        data.add(new ToDoList("JavaFX fejlesztőit megfenyegetni","2021-11-12","Egy kis szöveg ide!!!","Aktív"));
        data.add(new ToDoList("Valami biztos jó ide","2021-05-24","Egy kis szöveg ide!!!","Aktív"));
        //data.add(new ToDoList(String.valueOf(id), name, email,"asd"));

        ToDoTable.setItems(data);
    }

    private void editableCols(){
        col_todo.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    void SignOut(ActionEvent actionEvent) {
        try {
            //Close app stage
            Stage loginSystemStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            loginSystemStage.close();

            //delete user session
            UserSession.getInstace().cleanUserSession();

            //open sign in page
            Parent root = FXMLLoader.load(getClass().getResource("/sample/resources/view/SignIn.fxml"));
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            window.setScene(scene);
            window.show();
            MoveScene(root, window, scene);

        } catch (IOException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("ToString: " + e.toString());
            System.out.println("StackTrace: " + e.getStackTrace());
            System.out.println("Cause: " + e.getCause());
        }
    }
}
