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
    public String Createtime;
    /**
     * player.
     */
    public String Winer;//玩家
    /**
     * step.
     */
    public int Step;//步数
    /**
     * the time of gameplay.
     */
    public String Playgame;//游戏所用时长

    /**
     * Playone.
     */
    public String Playone;//玩家/**
    /**
     * Playtwo.
     */
    public String Playtwo;//玩家


}
