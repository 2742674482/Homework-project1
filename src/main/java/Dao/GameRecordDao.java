package Dao;

import Entity.GameRecord;
import Util.XmlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

/**
 *The data layer is used for data acquisition and processing.
 */
public class GameRecordDao {
    /**
     * Write data.
     * @param gameRecord  Generated entity class object
     * @throws JsonProcessingException The exception of Jackson data conversion
     * @throws IOException Data exception thrown
     */
    public void InputGameRecord(GameRecord gameRecord) throws JsonProcessingException ,IOException {
        XmlUtil.BeanXml(gameRecord);
    }

    /**
     * Get all the game data return the{@code g}.
     * @return the {@code g} to game data list
     * @throws IOException Data exception thrown
     */
    public List<GameRecord> OutputGamaRecord() throws IOException {
        List<GameRecord> g = XmlUtil.GetGameRecord();
        return g;
    }
}
