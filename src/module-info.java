module ModernMenu {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.swt;
    requires fontawesomefx;
    requires AnimateFX;
    requires mysql.connector.java;
    requires java.sql;
    requires java.security.jgss;
    requires commons.validator;
    requires com.jfoenix;

    opens sample;
    opens sample.model;
    opens sample.Controllers;
}