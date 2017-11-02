package api.quartz.createQuartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MainScheduler {
	private String jobName;
	private String jobGroupName;
	private String triggerName;
	private String triggerGroupName;
	private String cronTime;

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	public void setCronTime(String cronTime) {
		this.cronTime = cronTime;
	}

	public MainScheduler(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cronTime) {
		this.jobName = jobName;
		this.jobGroupName = jobGroupName;
		this.triggerName = triggerName;
		this.triggerGroupName = triggerGroupName;
		this.cronTime = cronTime;
	}

	public String create(){
		try {
			// 创建一个JobDetail实例，将该时例与HelloJob Class绑定
			JobDetail jobDetail = JobBuilder.newJob(MainJob.class).withIdentity(jobName, jobGroupName).build();
			// 创建一个Trigger时例
			CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(cronTime))
					/*
					 * cron表达式 0 15 10 ? * * 每天的10点15分0秒触发 0 0/5 14 * * ？ 每天下午的2点到2点59分（整点开始，每隔5分触发）
					 * 0 15 10 ? * MON-FRI 从周一到周五每天上午的10点15分触发 0 15 10 ? * 6#3 每周的第三周的星期五开始触发 0 15
					 * 10 ? * 6L 2016-2017 从2016到2017每月最后一周的星期五的10点15分触发
					 */
					.build();
			// 创建Scheduler实例
			SchedulerFactory sfact = new StdSchedulerFactory();
			Scheduler scheduler = sfact.getScheduler();
			scheduler.start();

			scheduler.scheduleJob(jobDetail, trigger);
			return "succes";
			
		}catch(SchedulerException e) {  
			System.out.println("getScheduler 异常！");
			return "error";
		}
	}
}
