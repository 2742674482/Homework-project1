package util;

import pojo.GameList;
import pojo.GameRecord;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class JaxbUtil {

    /**
     * Convert data format and write data to file,
     * @param gameRecord
     *@throws JAXBException,IOException
     */
    public void BeanXml(GameRecord gameRecord) throws JAXBException, IOException {
        GameList gameList = new GameList();
        JAXBContext jaxbContext =JAXBContext.newInstance(gameList.getClass());
        createfile();
        FileInputStream fi = new FileInputStream("gamerecord.xml");
        //先读取文件中的数据
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        gameList = (GameList) unmarshaller.unmarshal(fi);
        //加入到集合
        List<GameRecord> gameRecords = gameList.getGameRecords();
        //将新获取的数据加入集合
        gameRecords.add(gameRecord);
        gameList.setGameRecords(gameRecords);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //再次将所有的数据写入到文件中
        marshaller.marshal(gameList, new FileOutputStream("gamerecord.xml"));
        log.info("Write data to file");
    }
    /**
     * Get game record collection,
     * @return Game record collection
     * @throws IOException,JAXBException
     */
    public static List<GameRecord> GetGameRecord() throws IOException, JAXBException {
        GameList gameList = new GameList();
        JAXBContext jaxbContext = JAXBContext.newInstance(gameList.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        createfile();
        FileInputStream fi = new FileInputStream("gamerecord.xml");
        gameList = (GameList) unmarshaller.unmarshal(fi);
        log.info("Gets the data set in the file");
        fi.close();
        List<GameRecord> g = gameList.getGameRecords();
        for (int i = 0; i < g.size(); i++) {
            for (int j = i; j < g.size(); j++) {
                if (g.get(i).getStep() > g.get(j).getStep()) {
                    GameRecord m = g.get(i);
                    g.set(i, g.get(j));
                    g.set(j, m);
                }
            }
        }
        List<GameRecord> gameRecords = new ArrayList<>();
        int count = 0;
        if (g.size() >= 5) {
            count = 5;
        } else {
            count = g.size();
        }
        for (int i = 0; i < count; i++) {
            gameRecords.add(g.get(i));
        }
        return gameRecords;
    }
    public static void createfile() throws IOException {
        File file=new File("gamerecord.xml");
        if(!file.exists())
            file.createNewFile();
    }
}
