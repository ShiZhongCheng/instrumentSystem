package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import api.comment.Hash_Json;
import api.comment.JsonEncode;
import api.comment.MD5;
import api.comment.Stack;
import api.comment.UUIDGenerator;
import api.mail.Email;
import api.mysql.ConnectionDb;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) throws SQLException {
		/*
		 * ���ݿ����Ӳ��� ConnectionDb myConnectionDb = new ConnectionDb("newphysical");
		 * myConnectionDb.connection(); ResultSet rs =
		 * myConnectionDb.quary("select * from physical_student"); while (rs.next()) {
		 * // ������ System.out.println(rs.getString("id") + "\t" + rs.getString("name"));
		 * } myConnectionDb.close();
		 */

		/*
		 * ���ʼ����� Email email = new Email(); String content =
		 * "<h1 style='color:red;'>�������أ�ʲô��û�У�</h1>"; String res = email.sendMail("������",
		 * content, "2454715873@qq.com"); System.out.println(res);
		 */

		/* MD5�㷨���� 
		 MD5 getMD5 = new MD5();
		 System.out.println(getMD5.GetMD5Code("hdu_admin"));
		 */
		
		/* �����������ѹ���
		GroupQuartz quartz = new GroupQuartz();
		String returnMesg = quartz.startQuartz("textGroupId", "textUserId");
		*/
		
		/* UUID���� 
		String[] ss = UUIDGenerator.getUUID_MORE(10,6); 
        for(int i=0;i<ss.length;i++){ 
            System.out.println(ss[i]); 
        }
        */
		
        /* ѹջ����ջ���� 
		Stack stack = new Stack();
		for(int i=0;i<10;i++)
		   stack.put(""+i);
	    for(int i=10;i>0;i--) {
	    	System.out.println(stack.get());
	    }
	    */
		
		/* �����ݿ�dabase_hdu�������Ӳ������� 
		String sql = "INSERT INTO `block`(`block_id`, `block_name`, `last_node`, `next_node`, `type`, `keeper`, `block_sp`, `block_dp`, `block_dp_check_crong`, `block_dp_check_flag`) VALUES "
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','���ڵ�','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','һ���ڵ�1','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','һ���ڵ�2','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','�����ڵ�1','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','�����ڵ�2','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','�����ڵ�1','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','�����ڵ�2','0','','0','userid','','','',0),"
		        + "('"+ UUIDGenerator.getUUID_ONE(6) +"','�ļ��ڵ�1','0','','0','userid','','','',0)";
		ConnectionDb con_db = new ConnectionDb("123.207.11.102","database_hdu");
		con_db.connection();
		int res = con_db.quary_pre(sql);
		System.out.println(res);
		con_db.close();
		*/
		
		/* ��ȡblock���� */
		ConnectionDb con_db = new ConnectionDb("123.207.11.102","database_hdu");
		con_db.connection();
		String result = con_db.quary_json("SELECT `block_id`, `block_name`, `last_node`, `next_node`, `type`, `keeper` FROM `block` WHERE 1");
		con_db.close();
		JsonEncode jsonEncode = new JsonEncode();
		HashMap<String, HashMap<String, String>> resultHash = jsonEncode.encode(result);
		String rs = new Hash_Json(resultHash).go();
		System.out.println(rs);
		
		/*
		HashMap<String, HashMap<String, HashMap<String, String>>> threeHash = new HashMap<String, HashMap<String, HashMap<String, String>>> ();
		HashMap<String, HashMap<String, String>> transfer2 = new HashMap<String, HashMap<String, String>> ();
		for(int i =0;i<resultHash.size();i++) {
			// block_id
			HashMap<String, String> transfer1 = new HashMap<String, String> ();
			transfer1.put("0", ""+resultHash.get(""+i).get("block_id"));
			transfer2.put("0", transfer1);
			// block_name
			HashMap<String, String> transfer1_1 = new HashMap<String, String> ();
			transfer1_1.put("0", ""+resultHash.get(""+i).get("block_name"));
			transfer2.put("1", transfer1_1);
			// last_node
			HashMap<String, String> transfer1_1_1 = new HashMap<String, String> ();
			transfer1_1_1.put("0", ""+resultHash.get(""+i).get("last_node"));
			transfer2.put("2", transfer1_1_1);
			// keeper
			HashMap<String, String> transfer1_1_1_1 = new HashMap<String, String> ();
			transfer1_1_1_1.put("0", ""+resultHash.get(""+i).get("keeper"));
			transfer2.put("3", transfer1_1_1_1);
			// type 
			HashMap<String, String> transfer1_1_1_1_1 = new HashMap<String, String> ();
			transfer1_1_1_1_1.put("0", ""+resultHash.get(""+i).get("type"));
			transfer2.put("3", transfer1_1_1_1_1);
			// next_node
			String next_node = resultHash.get(""+i).get("next_node");
			if(!next_node.equals("") && next_node != null) 
			{
				String[] strs = next_node.split(",");
				HashMap<String, String> transfer1_1_1_1_1_1 = new HashMap<String, String> ();
				for(int j=0,len=strs.length;j<len;j++){
					transfer1_1_1_1_1_1.put(""+j, strs[j].toString());
				}
				transfer2.put("4", transfer1_1_1_1_1_1);
			}else 
			{
				HashMap<String, String> transfer1_1_1_1_1_1 = new HashMap<String, String> ();
				transfer2.put("4", transfer1_1_1_1_1_1);
			}
			
			threeHash.put(""+i,transfer2);
		}
		System.out.println(threeHash);
		*/
		
	}
}




