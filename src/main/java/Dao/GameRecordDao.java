package Dao;

import Entity.GameRecord;
import Util.XmlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;


public class GameRecordDao {
    public void InputGameRecord(GameRecord gameRecord) throws JsonProcessingException ,IOException {
        XmlUtil.BeanXml(gameRecord);
        OutputGamaRecord();
    }

    public List<GameRecord> OutputGamaRecord() throws IOException {
        List<GameRecord> g = XmlUtil.GetGameRecord();
        return g;
    }
}
