package dao;

import pojo.GameRecord;
import util.JaxbUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;

/**
 * The data layer is used for data acquisition and processing.
 */
public class GameRecordDao {
    /**
     * Write data.
     * @param gameRecord  Generated entity class object
     * @throws JsonProcessingException The exception of Jackson data conversion
     * @throws IOException Data exception thrown
     */
    public void InputGameRecord(GameRecord gameRecord) throws JsonProcessingException, IOException, JAXBException {
        new  JaxbUtil().BeanXml(gameRecord);
    }

    /**
     * Get all the game data return the{@code g}.
     * @return the {@code g} to game data list
     * @throws IOException Data exception thrown
     */
    public List<GameRecord> OutputGamaRecord() throws IOException, JAXBException {
        List<GameRecord> g = JaxbUtil.GetGameRecord();
        return g;
    }
}
