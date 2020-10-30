package sample.Controllers;

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

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

public class ToDoController implements Initializable{

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
        initCols();
    }

    private void initCols(){
        Iterator<String> userdata = UserSession.getInstace().getPrivileges().iterator();
        String email = userdata.next();
        String id = userdata.next();
        String username = UserSession.getInstace().getUserName();

        col_todo.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_more.setCellValueFactory(new PropertyValueFactory<>("information"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<ToDoList> data = FXCollections.observableArrayList();

        data.add(new ToDoList("id","name","email","asd"));
        data.add(new ToDoList(id, username, email,"asd"));

        ToDoTable.setItems(data);
    }

    private void editableCols(){
        col_todo.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
