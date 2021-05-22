import Dao.GameRecordDao;
import Entity.GameRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
@Slf4j
public class Daotest {
    @Test
    public void testinput() throws IOException {
        GameRecord g = new GameRecord();
        g.setPlaygame("00:00:01");
        g.setStep(1);
        g.setCreatetime("2021-05-20T20:58:06.444644900Z");
        g.setPlayer("Unit_test");
        new  GameRecordDao().InputGameRecord(g);
    }

    @Test
    public void testout() throws IOException {
        List<GameRecord> g = new  GameRecordDao().OutputGamaRecord();
        for (int i = 0; i < g.size(); i++) {
            log.info(g.get(i).toString());
        }
    }

}
