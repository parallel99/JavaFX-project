package sample.model;

public class ToDoList {

    String name, date, information, status;

    public ToDoList(String name, String date, String information, String status) {
        this.name = name;
        this.date = date;
        this.information = information;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getInformation() {
        return information;
    }

    public String getStatus() {
        return status;
    }
}
