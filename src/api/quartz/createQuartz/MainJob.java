package api.quartz.createQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class MainJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current Exec Time is:" + sdf.format(date));
		// 编写具体业务逻辑
		System.out.println("Hello World!");
	}

}
