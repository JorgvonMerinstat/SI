module cz.vse.si {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.si.main to javafx.fxml;
    exports cz.vse.si.main;
}