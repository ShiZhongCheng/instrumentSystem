package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import api.quartz.impl.GroupQuartz;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index1(Model model) {

		// 开启检修提醒功能
//		GroupQuartz quartz = new GroupQuartz();
//		String returnMesg = quartz.startQuartz("textGroupId", "textUserId");
//
//		model.addAttribute("returnMesg", returnMesg);
		model.addAttribute("message", "这是首页/!");
		return "index";
	}

	@RequestMapping("/index")
	public String index2(Model model) {
		model.addAttribute("message", "这是首页/index!");
		return "index";
	}
	
	@RequestMapping("/mail")
	public String mail(Model model) {
		model.addAttribute("message", "mail页面");
		return "mail";
	}
}
