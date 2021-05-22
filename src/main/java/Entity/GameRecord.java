package Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;



/*
游戏记录类，用于记录游戏产生的数据
/
 */
@Data

@JacksonXmlRootElement(localName = "Record")
public class GameRecord {

    public String Createtime;//创建时间

    public String Player;//玩家\

    public int Step;//步数

    public String Playgame;//游戏所用时长


}
