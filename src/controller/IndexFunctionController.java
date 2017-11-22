package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import api.comment.JsonEncode;
import api.comment.MD5;
import api.mysql.ConnectionDb;

@Controller
public class IndexFunctionController {
	/* ��¼���� */
	@RequestMapping("/function/loginDeal")
	public void loginDeal(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// ��ȡ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remberPassword = request.getParameter("remberPassword");

		// �жϲ����Ƿ�Ϊ��
		String result = null;
		if (username == null || password == null) {
			result = "{\"result\":\"not\"}";
		} else {
			// ����json����Ķ���
			JsonEncode jsonEncode = new JsonEncode();

			/*
			 * ���������ݿ�administrators�������Ϣ��ת�ӵ������ݿ� 1.���ӵ�ת�ӱ�user_switch����user_id��ȡ���û���group_id
			 * 2.���ӵ�groups��ͨ��group_id��ȡ��group�����ݿ��ַ�����ݿ��� 3.ת�ӵ������ݿ�
			 */
			ConnectionDb cAdminDb = new ConnectionDb("administrators");
			cAdminDb.connection();
			// user_switch��
			String switch_data_json = cAdminDb
					.quary_json("SELECT `user_id`, `openid`, `group_id` FROM `user_switch` WHERE `user_id`='" + username
							+ "\'" + "or `nick_name`='" + username + "\'");
			HashMap<String, HashMap<String, String>> switch_data = jsonEncode.encode(switch_data_json);
			// user_switch��û���û������ݣ���ȡ�û�û��������ʱ
			if (switch_data == null || switch_data.size() == 0 || switch_data.get("0").get("group_id").equals("null") || switch_data.get("0").get("group_id").isEmpty()) {
				result = "{\"result\":\"not\"}";
			} else {
				// groups��
				String groups_data_json = cAdminDb.quary_json(
						"SELECT * FROM `groups` WHERE `group_id`='" + switch_data.get("0").get("group_id") + "\'");
				HashMap<String, HashMap<String, String>> groups_data = jsonEncode.encode(groups_data_json);
				cAdminDb.close();

				/*
				 * ���������ݿ� 1.���������ݿ�users��ͨ��user_id��ȡ�û�������Ϣ
				 */
				String dbAddress = groups_data.get("0").get("database_ip");
				String dataBase = groups_data.get("0").get("database_name");
				ConnectionDb cGroupDb = new ConnectionDb(dbAddress, dataBase);
				cGroupDb.connection();
				// users��
				String users_data_json = cGroupDb.quary_json(
						"SELECT * FROM `users` WHERE `user_id`='" + switch_data.get("0").get("user_id") + "\'");
				HashMap<String, HashMap<String, String>> users_data = jsonEncode.encode(users_data_json);
				/*
				 * �������������MD5���� �ж����������Ƿ���ȷ
				 */
				MD5 getMD5 = new MD5();
				String password_md5 = getMD5.GetMD5Code(password);
				if (users_data.get("0").get("password").equals(password_md5)) {
					// ���û�id���浽session
					request.getSession().setAttribute("user_id", switch_data.get("0").get("user_id"));
					// �����ݿ��ַ���浽session��
					request.getSession().setAttribute("database_ip", dbAddress);
					// �����ݿ������浽session��
					request.getSession().setAttribute("database_name", dataBase);

					// ���ԭ��cookie
					Cookie deleteNewCookie = new Cookie("user_id", null);
					deleteNewCookie.setMaxAge(0); // ɾ����Cookie
					deleteNewCookie.setPath("/");
					response.addCookie(deleteNewCookie);
					Cookie deleteNewCookie2 = new Cookie("password", null);
					deleteNewCookie2.setMaxAge(0); // ɾ����Cookie
					deleteNewCookie2.setPath("/");
					response.addCookie(deleteNewCookie2);

					// ��user_id��password���浽cookie
					if (remberPassword.equals("true")) {
						// user_id ��ӵ�cookie
						Cookie cookie = new Cookie("user_id", username);
						cookie.setPath("/");
						cookie.setMaxAge(7 * 24 * 60 * 60);
						response.addCookie(cookie);
						// password ��ӵ�cookie
						Cookie cookie2 = new Cookie("password", password);
						cookie2.setPath("/");
						cookie2.setMaxAge(7 * 24 * 60 * 60);
						response.addCookie(cookie2);
					}
					result = "{\"result\":\"success\"}";
				} else {
					result = "{\"result\":\"not\"}";
				}
			}
		}

		// ��result���ظ�ǰ̨
		PrintWriter out = null;
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
