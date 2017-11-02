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
			// ����һ��JobDetailʵ��������ʱ����HelloJob Class��
			JobDetail jobDetail = JobBuilder.newJob(MainJob.class).withIdentity(jobName, jobGroupName).build();
			// ����һ��Triggerʱ��
			CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(cronTime))
					/*
					 * cron���ʽ 0 15 10 ? * * ÿ���10��15��0�봥�� 0 0/5 14 * * �� ÿ�������2�㵽2��59�֣����㿪ʼ��ÿ��5�ִ�����
					 * 0 15 10 ? * MON-FRI ����һ������ÿ�������10��15�ִ��� 0 15 10 ? * 6#3 ÿ�ܵĵ����ܵ������忪ʼ���� 0 15
					 * 10 ? * 6L 2016-2017 ��2016��2017ÿ�����һ�ܵ��������10��15�ִ���
					 */
					.build();
			// ����Schedulerʵ��
			SchedulerFactory sfact = new StdSchedulerFactory();
			Scheduler scheduler = sfact.getScheduler();
			scheduler.start();

			scheduler.scheduleJob(jobDetail, trigger);
			return "succes";
			
		}catch(SchedulerException e) {  
			System.out.println("getScheduler �쳣��");
			return "error";
		}
	}
}
