package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import api.comment.Hash_Json;
import api.comment.JsonEncode;
import api.mysql.ConnectionDb;
import api.quartz.impl.GroupQuartz;
import model.CommentModel;

@Controller
public class IndexController {
	/* 首页 */
	@RequestMapping("/")
	public String index1(Model model, HttpServletRequest request) {
		CommentModel commentModel = new CommentModel();
		// 检查是否登录,已登录则获取session信息
		String[] basic_info = commentModel.getSession(request);
		if (basic_info == null || basic_info.length < 3)
			return "redirect:/login";
		// 获取用户个人信息
		HashMap<String, HashMap<String, String>> user_info = commentModel.getUserInfo(basic_info[0], basic_info[1],
				basic_info[2]);
		// 传递name
		model.addAttribute("name", user_info.get("0").get("name"));
		
		return "index";
	}

	/* 首页 */
	@RequestMapping("/index")
	public String index2(Model model, HttpServletRequest request) {
		CommentModel commentModel = new CommentModel();
		// 检查是否登录,已登录则获取session信息
		String[] basic_info = commentModel.getSession(request);
		if (basic_info == null || basic_info.length < 3)
			return "redirect:/login";
		// 获取用户个人信息
		HashMap<String, HashMap<String, String>> user_info = commentModel.getUserInfo(basic_info[0], basic_info[1],
				basic_info[2]);
		// 传递name
		model.addAttribute("name", user_info.get("0").get("name"));
		
		return "index";
	}

	/* 登录页面 */
	@RequestMapping("/login")
	public String login(Model model) {
		// 页面title
		model.addAttribute("title", "检修系统登录");
		// 当前日期
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		model.addAttribute("date", sdf.format(date));
		return "login";
	}
	
	/* 账号管理 */
	@RequestMapping("/userInfo")
	public String userInfo( Model model, HttpServletRequest request ) {
		CommentModel commentModel = new CommentModel();
		// 检查是否登录,已登录则获取session信息
		String[] basic_info = commentModel.getSession(request);
		if (basic_info == null || basic_info.length < 3)
			return "redirect:/login";
		// 获取用户个人信息
		HashMap<String, HashMap<String, String>> user_info = commentModel.getUserInfo(basic_info[0], basic_info[1],
				basic_info[2]);
		// 传递name
		model.addAttribute("name", user_info.get("0").get("name"));
		
		// 计算账号信息完善程度
		String[] completion_degree = {"no","no","no","no","no","0","#ff9900","中"};
		if(!user_info.get("0").get("nick_name").equals("null") && !user_info.get("0").get("nick_name").isEmpty()) completion_degree[0] = "yes";
		if(!user_info.get("0").get("email").equals("null") && !user_info.get("0").get("email").isEmpty()) completion_degree[1] = "yes";
		if(!user_info.get("0").get("telephone").equals("null") && !user_info.get("0").get("telephone").isEmpty()) completion_degree[2] = "yes";
		if(!user_info.get("0").get("academic_number").equals("null") && !user_info.get("0").get("academic_number").isEmpty()) completion_degree[3] = "yes";
		if(!user_info.get("0").get("openid").equals("null") && !user_info.get("0").get("openid").isEmpty()) completion_degree[4] = "yes";
		int percent = 0;
		for(int i=0;i<completion_degree.length;i++) {
			if(completion_degree[i].equals("yes"))
				percent = percent + 20;
		}
		completion_degree[5] = String.valueOf( percent );
		switch(percent) {
			case 0:
				completion_degree[6] = "red";
				completion_degree[7] = "极低";
				break;
			case 20:
				completion_degree[6] = "#e92525";
				completion_degree[7] = "低";
				break;
			case 40:
				completion_degree[6] = "#fe5326";
				completion_degree[7] = "低";
				break;
			case 60:
				completion_degree[6] = "#ff9900";
				completion_degree[7] = "中";
				break;
			case 80:
				completion_degree[6] = "#cddc39";
				completion_degree[7] = "高";
				break;
			case 100:
				completion_degree[6] = "#44b333";
				completion_degree[7] = "极高";
				break;
		}
	    model.addAttribute("completion_degree",completion_degree);
		
		// 传递昵称
		String[] nick_name = new String[4];
		if(user_info.get("0").get("nick_name").isEmpty() || user_info.get("0").get("nick_name").equals("null")) {
			nick_name[0] = "您还未设置昵称!";
			nick_name[1] = "#f90";
			nick_name[2] = "设置";
			nick_name[3] = "#f90";
		}else {
			nick_name[0] = user_info.get("0").get("nick_name");
			nick_name[1] = "#000";
			nick_name[2] = "修改";
			nick_name[3] = "#40b2da";
		}
		model.addAttribute("nick_name",nick_name);
		
		// 传递user_id
		model.addAttribute("user_id",user_info.get("0").get("user_id"));
		// 传递role
		model.addAttribute("role",user_info.get("0").get("role"));
		
		return "userInfo";
	}
	
	/* 检修结构 */
	@RequestMapping("/framework")
	public String framework(Model model, HttpServletRequest request) throws SQLException {
		CommentModel commentModel = new CommentModel();
		// 检查是否登录,已登录则获取session信息
		String[] basic_info = commentModel.getSession(request);
		if (basic_info == null || basic_info.length < 3)
			return "redirect:/login";
		// 获取用户个人信息
		HashMap<String, HashMap<String, String>> user_info = commentModel.getUserInfo(basic_info[0], basic_info[1],
				basic_info[2]);
		// 传递name
		model.addAttribute("name", user_info.get("0").get("name"));
		
		// 获取json字符串
		ConnectionDb con_db = new ConnectionDb(basic_info[1],basic_info[2]);
		con_db.connection();
		String result = con_db.quary_json("SELECT `block_id`, `block_name`, `last_node`, `next_node`, `type`, `keeper` FROM `block` WHERE 1");
		con_db.close();
		JsonEncode jsonEncode = new JsonEncode();
		HashMap<String, HashMap<String, String>> resultHash = jsonEncode.encode(result);
		String rs = new Hash_Json(resultHash).go();
		model.addAttribute("json", rs);
		
		return "framework";
	}
}
