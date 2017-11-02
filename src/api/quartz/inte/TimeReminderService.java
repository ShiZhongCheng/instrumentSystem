package api.quartz.inte;

public interface TimeReminderService {
    /* 为某个单位开启提醒检修功能
     * 需要传递此单位的groupId号和操作者的userID号
     * 有权限设置，只有在操作者是单位的负责人时才能操作成功
     * */
	public String startQuartz( String groupId,String userId );
	
	/* 为某个单位关闭提醒检修功能
      * 需要传递此单位的groupId号和操作者的userID号
     * 有权限设置，只有在操作者是单位的负责人时才能操作成功
     * */
	public String endQuartz( String groupId,String userId );
	
	/* 获取某单位检修功能状态
	 * 需要传递此单位的ID号
	 * 无权限设置
	 * */
	public String quartzStatus( String groupId );
}
