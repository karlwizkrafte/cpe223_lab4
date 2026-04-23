module cpe223.karlvince.lab4 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens cpe223.karlvince.lab4 to javafx.fxml;
    exports cpe223.karlvince.lab4;
}
