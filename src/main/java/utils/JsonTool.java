package utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 权兴权意 on 2016/8/25.
 */
public class JsonTool
{
    /**
     * 得到一个json类型的字符串对象
     * @param key
     * @param value
     * @return
     */
    public static String getJsonString(String key, Object value)
    {
        JSONObject jsonObject = new JSONObject();
        //put和element都是往JSONObject对象中放入 key/value 对
        try {
            jsonObject.put(key, value);
            //jsonObject.element(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //jsonObject.element(key, value);
        return jsonObject.toString();
    }

    /**
     * 得到一个json对象
     * @param key
     * @param value
     * @return
     */
    public static JSONObject getJsonObject(String key, Object value)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
