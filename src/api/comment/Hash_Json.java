package api.comment;

import java.util.HashMap;

public class Hash_Json {
	HashMap<String, HashMap<String, String>> resultHash;
	private HashMap<String, HashMap<String, HashMap<String, String>>> hash3;
	private String json;
	
	public Hash_Json(HashMap<String, HashMap<String, String>> resu) {
		resultHash = resu;
	}
	
	// 将二维哈希处理成三维哈希（将区块的下一节点拆开）
	private void hash2_hash3 () {
		HashMap<String, HashMap<String, HashMap<String, String>>> threeHash = new HashMap<String, HashMap<String, HashMap<String, String>>> ();
		for(int i =0;i<resultHash.size();i++) {
			HashMap<String, HashMap<String, String>> transfer2 = new HashMap<String, HashMap<String, String>> ();
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
			transfer2.put("4", transfer1_1_1_1_1);
			// next_node
			String next_node = resultHash.get(""+i).get("next_node");
			if(!next_node.equals("") && next_node != null) 
			{
				String[] strs = next_node.split(",");
				HashMap<String, String> transfer1_1_1_1_1_1 = new HashMap<String, String> ();
				for(int j=0,len=strs.length;j<len;j++){
					transfer1_1_1_1_1_1.put(""+j, strs[j].toString());
				}
				transfer2.put("5", transfer1_1_1_1_1_1);
			}else 
			{
				HashMap<String, String> transfer1_1_1_1_1_1 = new HashMap<String, String> ();
				transfer2.put("5", transfer1_1_1_1_1_1);
			}
			
			threeHash.put(""+i,transfer2);
			// System.out.println(transfer2);
		}
		this.hash3 = threeHash;
		
		//System.out.println(threeHash);	
	}
	
	// 将处理好的三维哈希进行拼接成json格式的字符串
	private String hash3_json(String par,String cond) {
		String ret = null; 
		String addres = search(par,cond);  // 找到结点位置
		String type = hash3.get(addres).get("4").get("0");  // 得到区块类型
		switch (type) 
		{
			case "entity":
				ret = "{\"name\": \""+ hash3.get(addres).get("1").get("0") +"\",\"value\": \""+ hash3.get(addres).get("0").get("0") +"\"}";
				break;
			default:
				ret = "{\"name\": \""+ hash3.get(addres).get("1").get("0") +"\",\"value\": \""+ hash3.get(addres).get("0").get("0") +"\"";
				// 查看下一节点
				for(int i = 0;i< hash3.get(addres).get("5").size();i++) {
					if(i==0 && i == hash3.get(addres).get("5").size() - 1){
						ret += ",\"children\": [";
						ret += hash3_json("0",hash3.get(addres).get("5").get(""+i));  // 递归
						ret += "]";
					}else if( i==0 ) {
						ret += ",\"children\": [";
						ret += hash3_json("0",hash3.get(addres).get("5").get(""+i));  // 递归
						ret += ",";
					}else if(i == hash3.get(addres).get("5").size() - 1){
						ret += hash3_json("0",hash3.get(addres).get("5").get(""+i));  // 递归
						ret += "]";
					}else {
						ret += hash3_json("0",hash3.get(addres).get("5").get(""+i));  // 递归
						ret += ",";
					}
				}
				ret += "}";
				break;
		}
		return ret;
	} 
	
	// 条件查询hash3 (参数，条件)
	private String search(String parameter,String condition){
		int k = -1;
		for(int i=0;i<hash3.size();i++) {
			if(hash3.get(""+i).get(parameter).get("0").equals(condition)) {
				k = i;
				break;
			}
		}
		return ""+k;
	}
	
	public String go() {
		hash2_hash3 ();
		return hash3_json("4","root");
	}
}
