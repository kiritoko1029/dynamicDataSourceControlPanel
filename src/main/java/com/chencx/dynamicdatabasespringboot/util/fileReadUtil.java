package com.chencx.dynamicdatabasespringboot.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Slf4j
public class fileReadUtil {
    /**
     * json读取
     * @param path
     * @return
     */
    public static JSONObject JsonRead( String path) throws IOException {
        File file = new File(path);
        JSONArray btnArray = null;
        //读取文件
        String input = FileUtils.readFileToString(file, "UTF-8");
        JSONObject jsonObject = JSONObject.fromObject(input);
        return jsonObject;
    }
    /**
     * json写入
     * @param path
     * @return
     */
    public static void JsonWrite( String path,String jsonText) throws IOException {
        File file = new File(path);
        FileUtils.writeStringToFile(file,jsonText,"UTF-8",false); //最后一个参数 true：追加，false：覆盖
    }
    public static void JsonCopy( String pathOriginal,String filePath) throws IOException {
        File fileOrigin = new File(pathOriginal);
        File file = new File(filePath);
        FileUtils.copyFile(fileOrigin,file);
    }
}
