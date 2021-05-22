import Entity.GameRecord;
import Util.XmlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
@Slf4j
public class xmlutilTest {
    @Test
    public  void BeanXmltest() throws JsonProcessingException {
        GameRecord g = new GameRecord();
        g.setPlayer("qwe");
        g.setCreatetime("2021-05-22T15:37:15.336119200Z");
        g.setStep(0);
        g.setPlaygame("00:00:01");
        XmlUtil.BeanXml(g);
    }


    @Test
    public  static void GetGameRecordtest() throws IOException {
        List<GameRecord> g = XmlUtil.GetGameRecord();
        for (int i = 0; i < g.size(); i++) {
            log.info(g.get(i).toString());
        }
    }
}
