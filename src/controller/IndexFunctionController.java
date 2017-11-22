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
	/* 登录处理 */
	@RequestMapping("/function/loginDeal")
	public void loginDeal(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// 获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remberPassword = request.getParameter("remberPassword");

		// 判断参数是否为空
		String result = null;
		if (username == null || password == null) {
			result = "{\"result\":\"not\"}";
		} else {
			// 用于json解码的对象
			JsonEncode jsonEncode = new JsonEncode();

			/*
			 * 连接主数据库administrators，获得信息后转接到组数据库 1.连接到转接表user_switch根据user_id获取到用户的group_id
			 * 2.连接到groups表，通过group_id获取此group的数据库地址和数据库名 3.转接到组数据库
			 */
			ConnectionDb cAdminDb = new ConnectionDb("administrators");
			cAdminDb.connection();
			// user_switch表
			String switch_data_json = cAdminDb
					.quary_json("SELECT `user_id`, `openid`, `group_id` FROM `user_switch` WHERE `user_id`='" + username
							+ "\'" + "or `nick_name`='" + username + "\'");
			HashMap<String, HashMap<String, String>> switch_data = jsonEncode.encode(switch_data_json);
			// user_switch表没有用户的数据，获取用户没有所属组时
			if (switch_data == null || switch_data.size() == 0 || switch_data.get("0").get("group_id").equals("null") || switch_data.get("0").get("group_id").isEmpty()) {
				result = "{\"result\":\"not\"}";
			} else {
				// groups表
				String groups_data_json = cAdminDb.quary_json(
						"SELECT * FROM `groups` WHERE `group_id`='" + switch_data.get("0").get("group_id") + "\'");
				HashMap<String, HashMap<String, String>> groups_data = jsonEncode.encode(groups_data_json);
				cAdminDb.close();

				/*
				 * 连接组数据库 1.连接组数据库users表，通过user_id获取用户基本信息
				 */
				String dbAddress = groups_data.get("0").get("database_ip");
				String dataBase = groups_data.get("0").get("database_name");
				ConnectionDb cGroupDb = new ConnectionDb(dbAddress, dataBase);
				cGroupDb.connection();
				// users表
				String users_data_json = cGroupDb.quary_json(
						"SELECT * FROM `users` WHERE `user_id`='" + switch_data.get("0").get("user_id") + "\'");
				HashMap<String, HashMap<String, String>> users_data = jsonEncode.encode(users_data_json);
				/*
				 * 将输入密码就行MD5编码 判断输入密码是否正确
				 */
				MD5 getMD5 = new MD5();
				String password_md5 = getMD5.GetMD5Code(password);
				if (users_data.get("0").get("password").equals(password_md5)) {
					// 将用户id保存到session
					request.getSession().setAttribute("user_id", switch_data.get("0").get("user_id"));
					// 将数据库地址保存到session中
					request.getSession().setAttribute("database_ip", dbAddress);
					// 将数据库名保存到session中
					request.getSession().setAttribute("database_name", dataBase);

					// 清除原先cookie
					Cookie deleteNewCookie = new Cookie("user_id", null);
					deleteNewCookie.setMaxAge(0); // 删除该Cookie
					deleteNewCookie.setPath("/");
					response.addCookie(deleteNewCookie);
					Cookie deleteNewCookie2 = new Cookie("password", null);
					deleteNewCookie2.setMaxAge(0); // 删除该Cookie
					deleteNewCookie2.setPath("/");
					response.addCookie(deleteNewCookie2);

					// 将user_id和password保存到cookie
					if (remberPassword.equals("true")) {
						// user_id 添加到cookie
						Cookie cookie = new Cookie("user_id", username);
						cookie.setPath("/");
						cookie.setMaxAge(7 * 24 * 60 * 60);
						response.addCookie(cookie);
						// password 添加到cookie
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

		// 将result返回给前台
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
