package controller;

import dao.GameRecordDao;
import model.GameRecord;
import jakarta.xml.bind.JAXBException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 *Results show the controller of the page.
 */
@Slf4j

public class SettlementController {
    @FXML
    private TableView<GameRecord> recordTable;

    @FXML
    private TableColumn<GameRecord, String> winner;

    @FXML
    private TableColumn<GameRecord, Integer> step;

    @FXML
    private TableColumn<GameRecord, String> playTime;

    @FXML
    private TableColumn<GameRecord, String> createTime;

    @FXML
    private TableColumn<GameRecord, String> playerOne;
    @FXML
    private TableColumn<GameRecord, String> playerTwo;

    @FXML
    private void initialize() throws IOException, JAXBException {
        log.debug("Loading Play record...");
        List<GameRecord> gameRecords = new  GameRecordDao().OutputGamaRecord();


        winner.setCellValueFactory(new PropertyValueFactory<>("winner"));
        step.setCellValueFactory(new PropertyValueFactory<>("step"));
        playTime.setCellValueFactory(new PropertyValueFactory<>("playTime"));
        createTime.setCellValueFactory(new PropertyValueFactory<>("dateStarted"));
        playerOne.setCellValueFactory(new PropertyValueFactory<>("playerOne"));
        playerTwo.setCellValueFactory(new PropertyValueFactory<>("playerTwo"));

        ObservableList<GameRecord> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(gameRecords);

        recordTable.setItems(observableResult);
    }

    /**
     * Page Jump.
     * @param actionEvent Mouse click event source
     * @throws IOException When the data is empty, or data overflow is an exception
     */
    public void RestartButton(ActionEvent actionEvent) throws IOException {
        log.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        log.info("Loading launch scene...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
