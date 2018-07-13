package me.jacobtread1.status;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.arikia.dev.drpc.DiscordRPC;

public class Main extends Application {

    static boolean discordRunning;

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setScene(new Scene(root, 376.0, 593.0));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setOnCloseRequest(event -> {
            if (discordRunning) {
                DiscordRPC.discordClearPresence();
                DiscordRPC.discordShutdown();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
