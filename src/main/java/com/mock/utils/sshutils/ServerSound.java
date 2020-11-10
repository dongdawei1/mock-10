package com.mock.utils.sshutils;

import static com.mock.constant.Constants.SOUND_SERVICE_NAME;
import static com.mock.utils.PropertiesUtil.getProperty;
import static com.mock.utils.PropertiesUtil.getPropertyInt;
import static com.mock.constant.Constants.SOUND_SERVICE_FUNC;
import static com.mock.constant.Constants.PROPERTY_INT_NULL_RETURN;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket; // 依赖的包
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.mock.constant.reponse.ServerResponse;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//<dependency>
//<groupId>net.sf.json-lib</groupId>
//<artifactId>json-lib</artifactId>
//<version>2.4</version>
//</dependency>

public class ServerSound {
	public static int serverSound_service_name_length = SOUND_SERVICE_NAME.length;
	public static int serverSound_func_length = SOUND_SERVICE_FUNC.length;
	/**
	 * 某一个环境或者全部环境的探活
	 */
	public static ServerResponse<Map<String, Map<String, Boolean>>> getConnecMap(String func) {
		Map<String, Map<String, Boolean>> funcConnecMap = new HashMap<String, Map<String, Boolean>>();
		if (func == null || func.equals("")) {
			// 如果传入环境为空则探活全部服务
			for (int i = 0; i < serverSound_func_length; i++) {
				func = SOUND_SERVICE_FUNC[i];
				funcConnecMap.put(func, getFuncConnecMap(func));
			}
		} else {
			funcConnecMap.put(func, getFuncConnecMap(func));
		}
		return ServerResponse.createBySuccess(funcConnecMap);
	}

	/**
	 * 某一个的探活
	 */
	public static Map<String, Boolean> getFuncConnecMap(String func) {
		Map<String, Boolean> connecMap = new HashMap<String, Boolean>();
		String service_name_ip_name;
		String service_name_ip;
		int service_name_port;
		for (int a = 0; a < serverSound_service_name_length; a++) {
			service_name_ip_name = func + "_service_" + SOUND_SERVICE_NAME[a] + "_ip";
			service_name_ip = getProperty(service_name_ip_name);
			service_name_port = getPropertyInt(func + "_service_" + SOUND_SERVICE_NAME[a] + "_ssh_port");
			if (service_name_ip != null && !service_name_ip.equals("")
					&& service_name_port != PROPERTY_INT_NULL_RETURN) {
				boolean result = isHostConnectable(service_name_ip, service_name_port);
				if (result) {
					connecMap.put(service_name_ip_name + "_" + service_name_ip + ":" + service_name_port, result);
				} else {
					connecMap.put(service_name_ip_name + "_" + service_name_ip + ":" + service_name_port, result);
				}
			} else {
				// 有异常或者null值
				connecMap.put(service_name_ip_name + "_" + service_name_ip + ":" + service_name_port, false);
			}
		}
		return connecMap;
	}

	/**
	 * test socket
	 */
	public static boolean isHostConnectable(String host, int port) {
		Socket socket = null;
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(host, port)); // 不抛异常说明服务再
			try {
				socket.close();
			} catch (IOException e) {
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		ServerResponse<Map<String, Map<String, Boolean>>> t = getConnecMap(null);
		if (t.getStatus() == 0) {
			Map<String, Map<String, Boolean>> map = t.getData();

			Set<String> key_set = map.keySet();
			for (String func : key_set) {
				// String func = a;
				Map<String, Boolean> service_map = map.get(func);
				Set<String> service_map_key_set = service_map.keySet();
				for (String service_name : service_map_key_set) {
					boolean result = service_map.get(service_name);
					System.out.println("环境：" + func + "  服务名  " + service_name + " 结果  " + result);
				}
			}

		}

	}

//写入文件
//public static void isHostConnectable() throws IOException {
//
//    String str = "["
//                 + "{name:'192.168.172.28',value:'1611'}"
//                 + ",{name:'192.168.172.28',value:'1615'}"
//                 + ",{name:'172.20.34.152',value:'1602'}"
//+ ",{name:'192.168.153.99',value:'1977'}" 
//                 + "]";
//
//    Socket socket = null;
//    BufferedWriter bw = null;  
//    JSONArray json = JSONArray.fromObject(str);        
//    try {
//        bw = new BufferedWriter(new FileWriter("C:\\isHostConnectables.txt", true));    
//        for (int i=0;i<json.size();i++) {
//        	 socket = new Socket();
//        	JSONObject job = json.getJSONObject(i);            	
//            String host = job.getString("name");
//            int port = job.getInt("value");                
//          System.out.println(host+"   "+port);         
//            try {
//                socket.connect(new InetSocketAddress(host, port));   // 能new  成功就说明
//                bw.write(host + "   " + port + "               true");
//                bw.newLine();
//                bw.flush();
//
//            } catch (IOException e) {
//
//                bw.write(host + "   " + port + "               false");
//                bw.newLine();
//                bw.flush();
//            }
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                }              
//        }
//
//    } finally {
//        try {
//            socket.close();
//            bw.close();
//        } catch (IOException e) {
//        }
//    }
//}
}
