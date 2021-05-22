package Controller;

import Dao.GameRecordDao;
import Entity.Checkerboard;
import Entity.GameRecord;
import Entity.Position;
import Entity.operation.PawnDirection;
import Util.Victory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * class of gam controller.
 */
@Slf4j
@Data
public class GameController {


    private enum SelectionPhase {
        SELECT_FROM,
        SELECT_TO;

        public SelectionPhase alter() {
            return switch (this) {
                case SELECT_FROM -> SELECT_TO;
                case SELECT_TO -> SELECT_FROM;
            };
        }
    }
    private SelectionPhase selectionPhase = SelectionPhase.SELECT_FROM;

    private List<Position> selectablePositions = new ArrayList<>();



    private String player1Name;
    private String player2Name;

    private Position selected;

    private Instant startTime;

    private int status = 0;

    private Checkerboard model;

    @FXML
    private Label messageLabel;

    @FXML
    private GridPane board;

    @FXML
    private Label stopWatchLabel;

    private Timeline stopWatchTimeline;

    @FXML
    private Button resetButton;

    @FXML
    private Button giveUpButton;

    @FXML
    private Label stepsLabel;

    private int steps = 0;

    @FXML
    private Label playerLabel;

    private int round = 2;

    @FXML
    private void initialize() {
        model = new Checkerboard();
        init();

    }
    private  void init(){
        Platform.runLater(() -> messageLabel.setText(player1Name+"is red, go first!"));
        playerLabel.setText(player1Name);
        startTime = Instant.now();
        createBoard();
        createPieces();
        setSelectablePositions();
        showSelectablePositions();
        createStopWatch();
    }
    private void createBoard() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare();
                board.add(square, j, i);
            }
        }
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    private void createPieces() {
        for (int i = 0; i < model.getPieceCount(); i++) {
            model.positionProperty(i).addListener(this::piecePositionChange);
            var piece = createPiece(Color.valueOf(model.getPieceType(i).name()));
            getSquare(model.getPiecePosition(i)).getChildren().add(piece);
        }
    }

    private Circle createPiece(Color color) {
        var piece = new Circle(50);
        piece.setFill(color);
        return piece;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        String color = "";
        for (int i = 0; i <model.getPieceCount() ; i++) {
            if (model.getPiecePosition(i).getCol() == col && model.getPiecePosition(i).getRow() == row) {
                color = String.valueOf(model.getPieceType(i));
            }
        }

        var position = new Position(row, col);
        log.info("Click on square {}", position);
        handleClickOnSquare(position);
    }

    private void handleClickOnSquare(Position position) {
        switch (selectionPhase) {
            case SELECT_FROM -> {
                if (selectablePositions.contains(position)) {
                    selectPosition(position);
                    alterSelectionPhase();
                }
            }
            case SELECT_TO -> {
                if (selectablePositions.contains(position)) {
                    var pieceNumber = model.getPieceNumber(selected).getAsInt();
                    var direction = PawnDirection.of(position.getRow() - selected.getRow(), position.getCol() - selected.getCol());
                    log.info("Moving piece {} {}", pieceNumber, direction);
                    model.move(pieceNumber, direction);
                    deselectSelectedPosition();
                    alterSelectionPhase();
                }
            }
        }
    }

    private void alterSelectionPhase() {
        selectionPhase = selectionPhase.alter();
        hideSelectablePositions();
        setSelectablePositions();
        showSelectablePositions();
    }

    private void selectPosition(Position position) {
        selected = position;
        showSelectedPosition();
    }

    private void showSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().add("selected");
    }

    private void deselectSelectedPosition() {
        hideSelectedPosition();
        selected = null;
    }

    private void hideSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().remove("selected");
    }

    private void setSelectablePositions() {
        selectablePositions.clear();
        switch (selectionPhase) {
            case SELECT_FROM -> selectablePositions.addAll(model.getPiecePositions());
            case SELECT_TO -> {
                var pieceNumber = model.getPieceNumber(selected).getAsInt();
                for (var direction : model.getValidMoves(pieceNumber)) {
                    selectablePositions.add(selected.moveTo(direction));
                }
            }
        }
    }

    private void showSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().add("selectable");
        }
    }

    private void hideSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().remove("selectable");
        }
    }

    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.getRow() && GridPane.getColumnIndex(child) == position.getCol()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }

    private void piecePositionChange(ObservableValue<? extends Position> observable,
                                     Position oldPosition, Position newPosition) {
        log.info("Move: {} -> {}", oldPosition, newPosition);
        StackPane oldSquare = getSquare(oldPosition);
        StackPane newSquare = getSquare(newPosition);
        newSquare.getChildren().addAll(oldSquare.getChildren());
        oldSquare.getChildren().clear();
        steps++;
        stepsLabel.setText(String.valueOf(steps));

        String color = "";
        for (int i = 0; i <model.getPieceCount() ; i++) {
            if (model.getPiecePosition(i).getCol() == newPosition.getCol() && model.getPiecePosition(i).getRow() == newPosition.getRow()) {
                color = String.valueOf(model.getPieceType(i));
            }
        }
        boolean result = new  Victory().victory(model,color);

        if (result == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Win");
            alert.headerTextProperty().set("Congratulations on "+playerLabel.getText()+" victory");
            alert.showAndWait();
            board.setFocusTraversable(false);
            stopWatchTimeline.stop();
            try {
                GameRecord g = createGameResult();
                GameRecordDao ff = new  GameRecordDao();
                ff.InputGameRecord(g);
            } catch (Exception e){
                log.info(String.valueOf(e));
            }

        }

        if (color.equals("RED")) {
            playerLabel.setText(player2Name);
        }else if (color.equals("BLUE")){
            playerLabel.setText(player1Name);
        }
    }

    private void createStopWatch() {
        stopWatchTimeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            long millisElapsed = startTime.until(Instant.now(), ChronoUnit.MILLIS);
            stopWatchLabel.setText(DurationFormatUtils.formatDuration(millisElapsed, "HH:mm:ss"));
        }), new KeyFrame(javafx.util.Duration.seconds(1)));
        stopWatchTimeline.setCycleCount(Animation.INDEFINITE);
        stopWatchTimeline.play();
    }

    /**
     *
     * @param actionEvent
     */
    public void handleResetButton(ActionEvent actionEvent) {
        log.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        log.info("Resetting game...");
        stopWatchTimeline.stop();
        board.getChildren().clear();
        initialize();
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleGiveUpButton(ActionEvent actionEvent) throws IOException {
        String buttonText = ((Button) actionEvent.getSource()).getText();
        log.debug("{} is pressed", buttonText);
        if (buttonText.equals("Quit")) {
            log.info("The game has been stopped");
        }
        log.info("Loading high scores scene...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/settlement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public GameRecord createGameResult() throws Exception {
        GameRecord result = new GameRecord();
        result.setCreatetime(String.valueOf(Instant.now()));
        result.setPlayer(playerLabel.getText());
        result.setStep(steps);
        result.setPlaygame(stopWatchLabel.getText());
        log.info("Game information" + result.toString());
        return result;
    }
}
