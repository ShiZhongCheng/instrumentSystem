package api.comment;

import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonEncode {
	public HashMap<String, HashMap<String, String>> encode( String rs ){
		// 第一次将json字符串转成JSONArray对象
		JSONArray json = new JSONArray().fromObject(rs);
		// 定义要返回的变量
		HashMap<String, HashMap<String, String>> result = new HashMap<String, HashMap<String, String>>();
		if (json.size() > 0) {
			// // 遍历 jsonarray 数组
			for (int i = 0; i < json.size(); i++) {
				HashMap<String, String> result_second = new HashMap<String, String>();
				// 把每一个对象转成 JSONObject 对象
				JSONObject job = json.getJSONObject(i); 
				Iterator iterator = job.keys();
				// 遍历JSONObject，并且拼接返回数据
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					String value = job.getString(key);
					result_second.put(key, value);
				}
				result.put(""+i, result_second);
			}
		}
		return result;
	}
}
