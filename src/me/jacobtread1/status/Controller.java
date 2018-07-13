package me.jacobtread1.status;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private double xOffset, yOffset;

    @FXML
    private Rectangle header;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField state;

    @FXML
    private JFXTextField details;

    @FXML
    private JFXTextField imgKeySmall;

    @FXML
    private JFXTextField imgKeyBig;

    @FXML
    private void onExitClicked(MouseEvent e) {
        System.exit(0);
    }

    @FXML
    private void onSetClicked(MouseEvent e) {
        if (id.getText().isEmpty())
            return;

        DiscordEventHandlers handlers = new DiscordEventHandlers();

        DiscordRPC.discordInitialize(id.getText(), handlers, true, "");

        DiscordRichPresence presence = new DiscordRichPresence();

        presence.details = details.getText().isEmpty() ? null : details.getText();

        presence.state = state.getText().isEmpty() ? null : state.getText();

        presence.smallImageKey = imgKeySmall.getText().isEmpty() ? null : imgKeySmall.getText();

        presence.largeImageKey = imgKeyBig.getText().isEmpty() ? null : imgKeyBig.getText();

        DiscordRPC.discordUpdatePresence(presence);

        Main.discordRunning = true;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        header.setOnMousePressed((MouseEvent event) -> {
            this.xOffset = Main.stage.getX() - event.getScreenX();
            this.yOffset = Main.stage.getY() - event.getScreenY();
        });

        header.setOnMouseDragged(event -> {
            Main.stage.setX(event.getScreenX() + this.xOffset);
            Main.stage.setY(event.getScreenY() + this.yOffset);
        });
    }
}
