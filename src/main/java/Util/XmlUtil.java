package Util;

import Entity.GameRecord;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
@Slf4j
public class XmlUtil {
    public static void BeanXml(GameRecord gameRecord) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //字段为null，自动忽略，不再序列化
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        //设置转换模式
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);

        String result = xmlMapper.writeValueAsString(gameRecord);
        log.info("Game Data:"+result.toString());
        String filePath = XmlUtil.class.getClassLoader().getResource("xml/GameRecord.xml").getFile();
        BufferedWriter bw = null;
        try {
            filePath = URLDecoder.decode(filePath,"utf-8");
            log.debug("FilePath:" + filePath);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true)));
            bw.write(result+"?"+"\r\n");
        } catch (IOException e){
            log.error(e.getMessage());
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public  static List<GameRecord> GetGameRecord() throws IOException {
        InputStream is = XmlUtil.class.getClassLoader().getResourceAsStream("xml/GameRecord.xml");
        Properties prop = new Properties();
        prop.load(is);
        String xml = prop.toString();
        String regExp = ",";
        if (xml.length()>0) {
            xml = xml.substring(1);
            xml = xml.substring(0,xml.length()-1);
        }
        xml = xml.replaceAll(regExp,"");
        xml = xml.replaceAll("=",":");
        String[] xmls = xml.split("\\?");
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        /* 字段为null，自动忽略，不再序列化 */
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        /* 设置转换模式 */
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        List<GameRecord> g = new ArrayList<>();
        for (int i = 0; i < xmls.length; i++) {
            g.add(xmlMapper.readValue(xmls[i], GameRecord.class));
            xmlMapper.readValue(xmls[i],GameRecord.class);
        }
        for (int i = 0; i <g.size() ; i++) {
            for (int j = i; j <g.size(); j++) {
                if (g.get(i).getStep() > g.get(j).getStep()) {
                    GameRecord m = g.get(i);
                    g.set(i , g.get(j));
                    g.set(j , m);
                }
            }
        }

        List<GameRecord> gameRecords = new ArrayList<>();
        int count = 0;
        if (g.size() >= 5) {
            count = 5;
        }else {
            count = g.size();
        }
        for (int i = 0; i < count; i++) {
            gameRecords.add(g.get(i));
        }

        return gameRecords;
    }
}
