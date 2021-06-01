package util;

import model.GameList;
import model.GameRecord;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class of jaxb.
 */
@Slf4j
public class JaxbUtil {
    /**
     * Convert data format and write data to file.
     *
     * @param gameRecord
     * @throws JAXBException,IOException
     */
    public void BeanXml(GameRecord gameRecord) throws JAXBException, IOException {
        GameList gameList = new GameList();
        JAXBContext jaxbContext = JAXBContext.newInstance(gameList.getClass());
        createfile();
        System.out.println(gameRecord.toString());
        List<GameRecord> gameRecords = new ArrayList<>();
        FileInputStream fi = new FileInputStream("gamerecord.xml");
        //先读取文件中的数据
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        gameList = (GameList) unmarshaller.unmarshal(fi);
        //加入到集合
        if (gameList.getGameRecords() != null) {
            gameRecords = gameList.getGameRecords();
        }
        //将新获取的数据加入集合
        gameRecords.add(gameRecord);
        gameList.setGameRecords(gameRecords);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //再次将所有的数据写入到文件中
        marshaller.marshal(gameList, new FileOutputStream("gamerecord.xml"));
        log.info("Write data to file:" + gameList.getGameRecords().toString());
    }

    /**
     * Get game record collection.
     *
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
        List<GameRecord> gameRecords = new ArrayList<>();
        if (gameList.getGameRecords() != null) {
            log.info("Gets the data set in the file" + gameList.getGameRecords().toString());
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
        fi.close();

        return gameRecords;
    }

    /**
     * find the file is exit or not,if not,create a new file.
     *
     * @throws IOException
     */
    public static void createfile(){
        File file = new File("gamerecord.xml");
        if (!file.exists())
            try {
                // 创建解析器工厂
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = factory.newDocumentBuilder();
                Document document = db.newDocument();
                Element bookstore = document.createElement("Bean");
                // 将bookstore节点（已包含book）添加到dom树中
                document.appendChild(bookstore);
                // 创建TransformerFactory对象
                TransformerFactory tff = TransformerFactory.newInstance();
                // 创建 Transformer对象
                Transformer tf = tff.newTransformer();
                // 输出内容是否使用换行
                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                // 创建xml文件并写入内容
                tf.transform(new DOMSource(document), new StreamResult(new File("gamerecord.xml")));
                log.info("File created successfully");
            } catch (Exception e) {
                log.error(String.valueOf(e));
            }
    }
}
