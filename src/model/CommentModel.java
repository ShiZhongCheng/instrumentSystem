package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import api.comment.JsonEncode;
import api.mysql.ConnectionDb;

public class CommentModel {
	/* ��ȡ�û���¼ʱ�����session */
	public String[] getSession(HttpServletRequest request) {
		String user_id=null,database_ip=null,database_name=null;
		user_id = (String) request.getSession(true).getAttribute("user_id");
		database_ip = (String) request.getSession(true).getAttribute("database_ip");
		database_name = (String) request.getSession(true).getAttribute("database_name");
		if (user_id==null || database_ip==null || database_name==null) {
			return null;
		}
		// ƴ�ӷ�������
		String[] reslut = new String[3];
		reslut[0] = user_id;
		reslut[1] = database_ip;
		reslut[2] = database_name;
		return reslut;
	}
	
	/* ��ȡ�û���Ϣ  */
	public HashMap<String, HashMap<String, String>> getUserInfo(String user_id,String database_ip,String database_name){
		// �������ݿ�
		ConnectionDb cDb = new ConnectionDb(database_ip, database_name);
		cDb.connection();
		// ִ��sql����ȡjson�ַ���
		String user_info_json = cDb.quary_json(
				"SELECT * FROM `users` WHERE `user_id`='" + user_id + "\'");
		// ��json�ַ�������
		JsonEncode jsonEncode = new JsonEncode();
		HashMap<String, HashMap<String, String>> user_info = jsonEncode.encode(user_info_json);
		return user_info;
	}
	
}
