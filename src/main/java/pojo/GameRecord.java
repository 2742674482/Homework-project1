package pojo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String Createtime;
    /**
     * player.
     */
    private String Winer;//玩家
    /**
     * step.
     */
    private int Step;//步数
    /**
     * the time of gameplay.
     */
    private String Playgame;//游戏所用时长

    /**
     * Play1.
     */
    private String Playone;//玩家/**
    /**
     * Play2.
     */
    private String Playtwo;//玩家


}
