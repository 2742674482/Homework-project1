package model;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


/**
 record the data by game.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GameRecord {
    /**
     * create time.
     */
    private String dateStarted;
    /**
     * player.
     */
    private String winner;//玩家
    /**
     * step.
     */
    private int step;//步数
    /**
     * the time of gameplay.
     */
    private String playTime;//游戏所用时长

    /**
     * Play1.
     */
    private String playerOne;//玩家/**
    /**
     * Play2.
     */
    private String playerTwo;//玩家


}
