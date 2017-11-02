package api.quartz.impl;

import api.quartz.createQuartz.MainScheduler;
import api.quartz.inte.TimeReminderService;

public class GroupQuartz implements TimeReminderService {
	
	@Override
	public String startQuartz(String groupId,String userId){
		// 获取单位检修功能状态
		/*switch (quartzStatus(groupId)) {
		case "running":
			break;
		case "stoped":
			break;
		default:
			break;
		}*/
		
		MainScheduler sceduler = new MainScheduler("jobName", "jobGroup", "triggerName", "triggerGroup", "* * 11 ? * *");

		return sceduler.create();
	}

	@Override
	public String endQuartz(String groupId,String userId) {
		return null;
	}

	@Override
	public String quartzStatus(String groupId) {
		return null;
	}

}
