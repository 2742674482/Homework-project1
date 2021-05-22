package Controller;

import Dao.GameRecordDao;
import Entity.GameRecord;
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
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j

public class settlementController {
    @FXML
    private TableView<GameRecord> RecordTable;

    @FXML
    private TableColumn<GameRecord, String> Player;

    @FXML
    private TableColumn<GameRecord, Integer> Step;

    @FXML
    private TableColumn<GameRecord, String> Playgame;

    @FXML
    private TableColumn<GameRecord, String> Createtime;

    @FXML
    private void initialize() throws IOException {
        log.debug("Loading Play record...");
        List<GameRecord> gameRecords = new  GameRecordDao().OutputGamaRecord();


        Player.setCellValueFactory(new PropertyValueFactory<>("Player"));
        Step.setCellValueFactory(new PropertyValueFactory<>("Step"));
        Playgame.setCellValueFactory(new PropertyValueFactory<>("Playgame"));
        Createtime.setCellValueFactory(new PropertyValueFactory<>("Createtime"));

        ObservableList<GameRecord> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(gameRecords);

        RecordTable.setItems(observableResult);
    }


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
