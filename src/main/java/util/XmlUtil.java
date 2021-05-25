package util;

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

/**
 *Transformation tool class between XML and JavaB.
 */
@Slf4j
public class XmlUtil {
    /**
     *
     * @param gameRecord
     * @throws JsonProcessingException
     */
    public static void BeanXml(GameRecord gameRecord) throws JsonProcessingException {
        //构建一个map，实体类的装载容器
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //字段为null，自动忽略，不再序列化
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        //设置转换模式
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        //转译到数据 复制到结果
        String result = xmlMapper.writeValueAsString(gameRecord);
        log.info("Game Data:"+result.toString());
        //获取resourece的路劲
        String filePath = XmlUtil.class.getClassLoader().getResource("xml/GameRecord.xml").getFile();
        //流
        BufferedWriter bw = null;
        try {
            //转译为真实的url
            filePath = URLDecoder.decode(filePath,"utf-8");
            log.debug("FilePath:" + filePath);
            //创建文件流 重新构建一个新的outputstream
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true)));
            bw.write(result+"?");
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
    //xml转实体
    /**
     * Get game record collection.
     * @return Game record collection
     * @throws IOException
     */
    //构建一个输出流
    public static   List<GameRecord> GetGameRecord() throws IOException {
        InputStream is = XmlUtil.class.getClassLoader().getResourceAsStream("xml/GameRecord.xml");
        Properties prop = new Properties();
        prop.load(is);
        String xml = prop.toString();
        System.out.println(prop.toString());
        String regExp = ",";
        //此处if是消除可能会得到的符号 jackson无法吧xml转化为list，只能手动吧xml分割为一个个单独的xml（只对一个对象），再用jackson转译加入list
        if (xml.length()>0) {
            xml = xml.substring(1);
            xml = xml.substring(0,xml.length()-1);
        }
        xml = xml.replaceAll("<\\?xml=version=\"1.0\" encoding=\"UTF-8\" \\?>","");
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
            System.out.println(xmls[i]);
            g.add(xmlMapper.readValue(xmls[i], GameRecord.class));
            xmlMapper.readValue(xmls[i],GameRecord.class);
        }
        //排序，步数最少的最高
        for (int i = 0; i <g.size() ; i++) {
            for (int j = i; j <g.size(); j++) {
                if (g.get(i).getStep() > g.get(j).getStep()) {
                    GameRecord m = g.get(i);
                    g.set(i , g.get(j));
                    g.set(j , m);
                }
            }
        }

        //把最高的五个重新输入到list并输出
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
