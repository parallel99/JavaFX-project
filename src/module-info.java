module ModernMenu {
    requires javafx.fxml;
    requires javafx.controls;
    requires fontawesomefx;
    requires AnimateFX;
    requires mysql.connector.java;
    requires java.sql;
    requires java.security.jgss;
    requires commons.validator;
    requires com.jfoenix;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.swt;

    opens sample;
    opens sample.Controllers;
}