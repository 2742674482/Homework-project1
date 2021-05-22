package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 *
 */
@Slf4j
public class StartController {
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField playone;
    @FXML
    private TextField playtwo;
    /**
     *
     */
    @FXML
    private Label errorLabel;

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void start(ActionEvent actionEvent) throws IOException {
        if (playone.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
        }
        if (playtwo.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        Parent root = loader.load();
        GameController target = loader.getController();
        target.setPlayer1Name(playone.getText());
        target.setPlayer2Name(playtwo.getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("The player1 name is set to {"+ playone.getText() +"}, loading game scene");
        log.info("The player2 name is set to {"+ playtwo.getText() +"}, loading game scene");
    }
}
