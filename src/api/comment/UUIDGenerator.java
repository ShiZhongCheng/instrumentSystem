package api.comment;

import java.util.UUID;

public class UUIDGenerator { 
    public UUIDGenerator() { 
    } 
    /** 
     * ���һ��UUID 
     * @return String UUID 
     */ 
    public static String getUUID_ONE(int length){ 
        String s = UUID.randomUUID().toString(); 
        //ȥ����-������ 
        int minNum =(int)(Math.random() * 27);
        int maxNum = minNum +6;
        return (s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24)).substring(minNum,maxNum); 
    } 
    /** 
     * ���ָ����Ŀ��UUID 
     * @param number int ��Ҫ��õ�UUID���� 
     * @return String[] UUID���� 
     */ 
    public static String[] getUUID_MORE(int number,int length){ 
        if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++){ 
            ss[i] = getUUID_ONE(length); 
        } 
        return ss; 
    } 
}   
