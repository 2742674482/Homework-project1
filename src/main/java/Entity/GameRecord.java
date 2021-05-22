package Entity;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;



/**
 record the data by game.
 */
@Data

@JacksonXmlRootElement(localName = "Record")
public class GameRecord {
    /**
     * create time.
     */
    public String Createtime;
    /**
     * player.
     */
    public String Player;//玩家
    /**
     * step.
     */
    public int Step;//步数
    /**
     * the time of gameplay.
     */
    public String Playgame;//游戏所用时长


}
