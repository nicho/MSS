package com.aus.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ReflectUtil {
    public static HashMap<String,Object> reflect(Object obj) {  
    	 HashMap<String,Object> map = new HashMap<String,Object>();
        if (obj == null) return map;  
        Field[] fields = obj.getClass().getDeclaredFields();  
      
        for (int j = 0; j < fields.length; j++) {  
            fields[j].setAccessible(true);  
            // 字段名  
            //System.out.print(fields[j].getName() + ",");  
            // 字段值  
            if (fields[j].getType().getName().equals(  
                    java.lang.String.class.getName())) {  
                // String type  
                try {
                	map.put(fields[j].getName(), fields[j].get(obj));
                } catch (IllegalArgumentException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            } else if (fields[j].getType().getName().equals(  
                    java.lang.Integer.class.getName())  
                    || fields[j].getType().getName().equals("int")) {  
                // Integer type  
                try {  
                	map.put(fields[j].getName(), fields[j].get(obj));
                } catch (IllegalArgumentException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
            // 其他类型。。。  
        }
        return map;
    }  
    
    public  HashMap<String,Object> reflectNoStatic(Object obj) {  
   	 HashMap<String,Object> map = new HashMap<String,Object>();
       if (obj == null) return map;  
       Field[] fields = obj.getClass().getDeclaredFields();  
     
       for (int j = 0; j < fields.length; j++) {  
           fields[j].setAccessible(true);  
           // 字段名  
           //System.out.print(fields[j].getName() + ",");  
           // 字段值  
           if (fields[j].getType().getName().equals(  
                   java.lang.String.class.getName())) {  
               // String type  
               try {
               	map.put(fields[j].getName(), fields[j].get(obj));
               } catch (IllegalArgumentException e) {  
                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
               } catch (IllegalAccessException e) {  
                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
               }  
           } else if (fields[j].getType().getName().equals(  
                   java.lang.Integer.class.getName())  
                   || fields[j].getType().getName().equals("int")) {  
               // Integer type  
               try {  
               	map.put(fields[j].getName(), fields[j].get(obj));
               } catch (IllegalArgumentException e) {  
                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
               } catch (IllegalAccessException e) {  
                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
               }  
           }  
           // 其他类型。。。  
       }
       return map;
   }  
}
