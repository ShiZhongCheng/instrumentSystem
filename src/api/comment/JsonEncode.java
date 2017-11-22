package api.comment;

import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonEncode {
	public HashMap<String, HashMap<String, String>> encode( String rs ){
		// ��һ�ν�json�ַ���ת��JSONArray����
		JSONArray json = new JSONArray().fromObject(rs);
		// ����Ҫ���صı���
		HashMap<String, HashMap<String, String>> result = new HashMap<String, HashMap<String, String>>();
		if (json.size() > 0) {
			// // ���� jsonarray ����
			for (int i = 0; i < json.size(); i++) {
				HashMap<String, String> result_second = new HashMap<String, String>();
				// ��ÿһ������ת�� JSONObject ����
				JSONObject job = json.getJSONObject(i); 
				Iterator iterator = job.keys();
				// ����JSONObject������ƴ�ӷ�������
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
