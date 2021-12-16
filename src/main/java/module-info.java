module main.bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires java.sql;

    opens main.bomberman to javafx.fxml;
    exports main.bomberman;
    exports main.bomberman.gui;
    opens main.bomberman.gui to javafx.fxml;
}