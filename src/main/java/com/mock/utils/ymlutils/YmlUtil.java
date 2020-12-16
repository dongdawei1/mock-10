package com.mock.utils.ymlutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;



public class YmlUtil {

	//存储配置属性的Map集合
    private static Map<String, Object> confMap = new HashMap<String, Object>();
    public static void init()throws Exception{
        String filepath="/application-"+ GetEnvYmlUtil.getEnv("spring.profiles.active")+".yml";
        //从classpath下获取配置文件路径
        URL url = YmlUtil.class.getResource(filepath);
        Yaml yaml = new Yaml();
        //通过yaml对象将配置文件的输入流转换成map原始map对象
        Map map = yaml.loadAs(new FileInputStream(url.getPath()), Map.class);
        //递归map对象将配置加载到conf对象中
        loadRecursion(map, "");
        //{spring={profiles={active=dev}}}
      
    }    
    public static Object get(String key)throws Exception{
        if (confMap.size() == 0){
            throw new Exception("未初始化,未找到配置数据项!");
        }
        return confMap.get(key);
    }


    //递归解析map对象
    public static void loadRecursion(Map<String, Object> map, String key){
        map.forEach((k,v) -> {
            if(isParent(v)){
                Map<String, Object> nextValue = (Map<String, Object>) v;
                loadRecursion(nextValue, (("".equals(key) ? "" : key + ".")+ k));
            }else{
                confMap.put(key+"."+k,v);
            }
        });
    }
	
	//判断是否还有子节点
    public static boolean isParent(Object o){
        if (!(o instanceof String || o instanceof Character || o instanceof Byte)) {
            try {
                Number n = (Number) o;
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    
//    spring:
//    	  profiles:
//    	    active: dev
    
	public static String getYml(String key) {
		try {
			init();
			// 可以愉快的读取了
			return get(key).toString().trim();
		} catch (Exception e) {
			return null;
		}

    }
	
	
	
}
