/**
  * 软件架构教学应用程序
  **/
module cn.onism.architecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens cn.onism.architecture to javafx.fxml;
    exports cn.onism.architecture;
}