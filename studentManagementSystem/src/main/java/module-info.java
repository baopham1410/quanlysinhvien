module com.example.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.studentmanagementsystem to javafx.fxml;
    exports com.example.studentmanagementsystem;
    opens com.example.studentmanagementsystem.Model;
    exports com.example.studentmanagementsystem.Controller;
    opens com.example.studentmanagementsystem.Controller to javafx.fxml;
}