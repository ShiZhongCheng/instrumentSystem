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
		 * 数据库连接测试 ConnectionDb myConnectionDb = new ConnectionDb("newphysical");
		 * myConnectionDb.connection(); ResultSet rs =
		 * myConnectionDb.quary("select * from physical_student"); while (rs.next()) {
		 * // 输出结果 System.out.println(rs.getString("id") + "\t" + rs.getString("name"));
		 * } myConnectionDb.close();
		 */

		/*
		 * 发邮件测试 Email email = new Email(); String content =
		 * "<h1 style='color:red;'>逗你玩呢！什么都没有！</h1>"; String res = email.sendMail("周年庆",
		 * content, "2454715873@qq.com"); System.out.println(res);
		 */

		/* MD5算法测试 
		 MD5 getMD5 = new MD5();
		 System.out.println(getMD5.GetMD5Code("hdu_admin"));
		 */
		
		/* 开启检修提醒功能
		GroupQuartz quartz = new GroupQuartz();
		String returnMesg = quartz.startQuartz("textGroupId", "textUserId");
		*/
		
		/* UUID测试 
		String[] ss = UUIDGenerator.getUUID_MORE(10,6); 
        for(int i=0;i<ss.length;i++){ 
            System.out.println(ss[i]); 
        }
        */
		
        /* 压栈和入栈测试 
		Stack stack = new Stack();
		for(int i=0;i<10;i++)
		   stack.put(""+i);
	    for(int i=10;i>0;i--) {
	    	System.out.println(stack.get());
	    }
	    */
		
		/* 给数据库dabase_hdu区块表添加测试数据 
		String sql = "INSERT INTO `block`(`block_id`, `block_name`, `last_node`, `next_node`, `type`, `keeper`, `block_sp`, `block_dp`, `block_dp_check_crong`, `block_dp_check_flag`) VALUES "
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','根节点','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','一级节点1','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','一级节点2','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','二级节点1','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','二级节点2','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','三级节点1','0','','0','userid','','','',0),"
				+ "('"+ UUIDGenerator.getUUID_ONE(6) +"','三级节点2','0','','0','userid','','','',0),"
		        + "('"+ UUIDGenerator.getUUID_ONE(6) +"','四级节点1','0','','0','userid','','','',0)";
		ConnectionDb con_db = new ConnectionDb("123.207.11.102","database_hdu");
		con_db.connection();
		int res = con_db.quary_pre(sql);
		System.out.println(res);
		con_db.close();
		*/
		
		/* 读取block测试 */
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




