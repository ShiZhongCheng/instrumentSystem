package api.quartz.inte;

public interface TimeReminderService {
    /* Ϊĳ����λ�������Ѽ��޹���
     * ��Ҫ���ݴ˵�λ��groupId�źͲ����ߵ�userID��
     * ��Ȩ�����ã�ֻ���ڲ������ǵ�λ�ĸ�����ʱ���ܲ����ɹ�
     * */
	public String startQuartz( String groupId,String userId );
	
	/* Ϊĳ����λ�ر����Ѽ��޹���
      * ��Ҫ���ݴ˵�λ��groupId�źͲ����ߵ�userID��
     * ��Ȩ�����ã�ֻ���ڲ������ǵ�λ�ĸ�����ʱ���ܲ����ɹ�
     * */
	public String endQuartz( String groupId,String userId );
	
	/* ��ȡĳ��λ���޹���״̬
	 * ��Ҫ���ݴ˵�λ��ID��
	 * ��Ȩ������
	 * */
	public String quartzStatus( String groupId );
}
