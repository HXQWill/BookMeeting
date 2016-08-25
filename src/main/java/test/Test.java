package test;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 权兴权意 on 2016/8/23.
 */
public class Test {
    public  static  void main(String args[])   {
        System.out.println("Hello");
//        Room room = new Room(R.drawable.meeting,"发起人：甲","时间：15:00-16:00","会议：神州专车需求讨论会");
//        System.out.println(room);
        //    将Person对象转换成一个json类型的字符串对象
//        JSONObject roomString = JsonTool.getJsonObject("room", room);
//        System.out.println(roomString.toString());
//        Person person = new Person(1, "xiaoluo", "广州");
//        //    将Person对象转换成一个json类型的字符串对象
//        String personString = JsonTool.getJsonString("person", person);
//        System.out.println(personString.toString());

// 假设现在要创建这样一个json文本
//  {
//      "name" : "yuanzhifei89", // 字符串
//      "id" : 100, // 数值
//      "address" : "china" // 字符串
//  }
        // 首先最外层是{}，是创建一个对象
        JSONObject person = new JSONObject();
        try {
            person.put("name", "A");
            person.put("id", 100);
            person.put("address", "china");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(person);

    }
}
