package com.aus.common.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.aus.common.model.WeiXinDepartment;
import com.aus.common.model.WeiXinUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class WinXinMessageUtil {
	private static Logger logger = Logger.getLogger(WinXinMessageUtil.class);
  
	 public static Map<String,String> sendWinXinMessage(String access_token,String touser,String msgContents,String agentid,String title,String url) throws Exception 
	 {
		  Map<String,String> map=new HashMap<String, String>();
		  CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {  
	            HttpPost httpPost = new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+access_token);
	            
	            JSONObject jsonObj = new JSONObject();
	            //System.out.println("touser-----------"+touser);
	            jsonObj.put("touser", touser); 
	            jsonObj.put("msgtype", "news");
	            jsonObj.put("agentid", agentid); 
	      
	            JSONObject body=new JSONObject();
	            body.put("title", title);
	            body.put("description", msgContents);
	            body.put("url", url); 
	             
	            jsonObj.put("news",new JSONObject().put("articles", new JSONArray().put(body)));
	            //System.out.println(jsonObj.toString());
	            StringEntity entity = new StringEntity(jsonObj.toString(),"UTF-8");
	            
	            httpPost.setEntity(entity); 
	             
	            CloseableHttpResponse response2 = httpclient.execute(httpPost);

	            try {
	                //System.out.println(response2.getStatusLine());
	                HttpEntity entity2 = response2.getEntity(); 
	                if (entity2 != null) {
	                    try {
	                        BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(entity2.getContent(),"UTF-8"), 8 * 1024);
	                        StringBuilder entityStringBuilder = new StringBuilder();
	                        String line = null;
	                        while ((line = bufferedReader.readLine()) != null) {
	                            entityStringBuilder.append(line);
	                        }
	                        // 利用从HttpEntity中得到的String生成JsonObject
	                        JSONObject resultJsonObject = new JSONObject(entityStringBuilder.toString());  
	                         
	                        map.put("errcode", resultJsonObject.get("errcode")+"");
	                        map.put("errmsg", resultJsonObject.get("errmsg")+"");
	                       
	                        String invaliduser="";
	                        try {
	                        	invaliduser=resultJsonObject.get("invaliduser")+"";
							} catch (Exception e) { 
							}
	                        map.put("invaliduser",invaliduser);
	                    } catch (Exception e) {
	                        logger.error(e);
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return map;
	 }
	 
	 public static String getAccessToken(String CorpID,String Secret) throws Exception
	 {
		 String access_token="";
		   CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+CorpID+"&corpsecret="+Secret);
	          
	            CloseableHttpResponse response1 = httpclient.execute(httpGet);
	            JSONObject resultJsonObject = null;
	            try {
	                //System.out.println(response1.getStatusLine());
	                HttpEntity httpEntity = response1.getEntity();
	                
	                
	                if (httpEntity != null) {
	                    try {
	                        BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
	                        StringBuilder entityStringBuilder = new StringBuilder();
	                        String line = null;
	                        while ((line = bufferedReader.readLine()) != null) {
	                            entityStringBuilder.append(line);
	                        }
	                        // 利用从HttpEntity中得到的String生成JsonObject
	                        //System.out.println(entityStringBuilder.toString().trim());
	                        resultJsonObject = new JSONObject(entityStringBuilder.toString().trim());
	                        access_token=resultJsonObject.get("access_token")+"";
	                    } catch (Exception e) {
	                        logger.error(e);
	                    }
	                }
	                
	            } finally {
	                response1.close();
	            } 
	        } finally {
	            httpclient.close();
	        }
	        return access_token;
	    
	 }
	 public static void main(String[] args) {
		 try {
			String access_token=getAccessToken("wxbaa80479dd64524f", "AfD0i_4VbaoYr2k1qpvtkqIN6yJR1LOheLCgCsShqu6OqyHYegT8pqqCBi20FlDp");
			//Map<String, String> rsMap = WinXinMessageUtil.sendWinXinMessage(access_token,"15618970660", "测试","62","title","http://www.iseeau.cn/ess/mobile/search_travel.do");

		//System.out.println("SendTo:"  + ",微信服务发送:" + rsMap.get("errcode") + "----" + rsMap.get("errmsg"));
	//		//System.out.println(access_token);
			//getWinXinUser(access_token, "1", "1", "0");
			//getWinXinDepartment(access_token,"17");
			
			Map map = WinXinMessageUtil.getWinXinUserById(access_token, "23332");
			System.out.println(map.get("errcode"));
			System.out.println(map.get("errmsg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	 
	 
	  
	public static Map<String,String> getWinXinUser(String access_token,String department_id,String fetch_child,String status) throws Exception 
	 {
		  Map<String,String> map=new HashMap<String, String>();
		  CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {  
	            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+access_token+"&department_id="+department_id+"&fetch_child="+fetch_child+"&status="+status);
	             
	            CloseableHttpResponse response2 = httpclient.execute(httpGet);

	            try {
	                //System.out.println(response2.getStatusLine());
	                HttpEntity entity2 = response2.getEntity(); 
	                if (entity2 != null) {
	                    try {
	                        BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(entity2.getContent(),"UTF-8"), 8 * 1024);
	                        StringBuilder entityStringBuilder = new StringBuilder();
	                        String line = null;
	                        while ((line = bufferedReader.readLine()) != null) {
	                            entityStringBuilder.append(line);
	                        }
	                        // 利用从HttpEntity中得到的String生成JsonObject
	                        JSONObject resultJsonObject = new JSONObject(entityStringBuilder.toString());  
	                        //System.out.println(entityStringBuilder.toString());
	                        map.put("errcode", resultJsonObject.get("errcode")+"");
	                        map.put("errmsg", resultJsonObject.get("errmsg")+"");
	                        if("0".equals(resultJsonObject.get("errcode")+""))
	                        { 
	                        	ObjectMapper mapper = new ObjectMapper();  
	                          	 List<WeiXinUser> beanList = mapper.readValue(resultJsonObject.get("userlist").toString(), new TypeReference<List<WeiXinUser>>() {}); 
	                        	  for (int i = 0; i < beanList.size(); i++) {
								//	//System.out.println(beanList.get(i).getUserid()+beanList.get(i).getName());
									map.put(beanList.get(i).getUserid(), beanList.get(i).getName());
								}
	                        }
	                        
	                       
	                      
	                        
	                    } catch (Exception e) {
	                        logger.error(e);
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return map;
	 }
	
	/**
	 * 获取部门列表
	 * @param access_token
	 * @param department_id
	 * @param fetch_child
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> getWinXinDepartment(String access_token,String id) throws Exception 
	 {
		  Map<String,String> map=new HashMap<String, String>();
		  CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {  
	            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+access_token+"&id="+id);
	             
	            CloseableHttpResponse response2 = httpclient.execute(httpGet);

	            try {
	                //System.out.println(response2.getStatusLine());
	                HttpEntity entity2 = response2.getEntity(); 
	                if (entity2 != null) {
	                    try {
	                        BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(entity2.getContent(),"UTF-8"), 8 * 1024);
	                        StringBuilder entityStringBuilder = new StringBuilder();
	                        String line = null;
	                        while ((line = bufferedReader.readLine()) != null) {
	                            entityStringBuilder.append(line);
	                        }
	                        // 利用从HttpEntity中得到的String生成JsonObject
	                        JSONObject resultJsonObject = new JSONObject(entityStringBuilder.toString());  
	                        //System.out.println(entityStringBuilder.toString());
	                        map.put("errcode", resultJsonObject.get("errcode")+"");
	                        map.put("errmsg", resultJsonObject.get("errmsg")+"");
	                        if("0".equals(resultJsonObject.get("errcode")+""))
	                        { 
	                        	ObjectMapper mapper = new ObjectMapper();  
	                          	 List<WeiXinDepartment> beanList = mapper.readValue(resultJsonObject.get("department").toString(), new TypeReference<List<WeiXinDepartment>>() {}); 
	                        	  for (int i = 0; i < beanList.size(); i++) {
							//		//System.out.println(beanList.get(i).getName()+"----"+beanList.get(i).getId());
									map.put(beanList.get(i).getName(), beanList.get(i).getId());
								}
	                        }
	                        
	                       
	                      
	                        
	                    } catch (Exception e) {
	                        logger.error(e);
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return map;
	 }
	
	/**
	 * 添加微信用户
	 * @param access_token
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> addWinXinUser(String access_token,WeiXinUser user) throws Exception 
	 {
		  Map<String,String> map=new HashMap<String, String>();
		  CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {  
	            HttpPost httpPost = new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+access_token);
	            
	            JSONObject jsonObj = new JSONObject(); 
	            jsonObj.put("userid", user.getUserid());
	            jsonObj.put("name", user.getName());
	            jsonObj.put("department", user.getDepartment());
	            jsonObj.put("position", user.getPosition());
	            jsonObj.put("mobile", user.getMobile());
	            jsonObj.put("gender", user.getGender());
	            jsonObj.put("email", user.getEmail());
	            jsonObj.put("weixinid", user.getWeixinid()); 
	            
	            StringEntity entity = new StringEntity(jsonObj.toString(),"UTF-8");
	            
	            httpPost.setEntity(entity); 
	             
	            CloseableHttpResponse response2 =httpclient.execute(httpPost);

	            try {
	                //System.out.println(response2.getStatusLine());
	                HttpEntity entity2 = response2.getEntity(); 
	                if (entity2 != null) {
	                    try {
	                        BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(entity2.getContent(),"UTF-8"), 8 * 1024);
	                        StringBuilder entityStringBuilder = new StringBuilder();
	                        String line = null;
	                        while ((line = bufferedReader.readLine()) != null) {
	                            entityStringBuilder.append(line);
	                        }
	                        // 利用从HttpEntity中得到的String生成JsonObject
	                        JSONObject resultJsonObject = new JSONObject(entityStringBuilder.toString());  
	                         
	                        map.put("errcode", resultJsonObject.get("errcode")+"");
	                        map.put("errmsg", resultJsonObject.get("errmsg")+"");
	                        
	                    } catch (Exception e) {
	                        logger.error(e);
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return map;
	 }
	
	/**
	 * 删除微信用户
	 * @param access_token
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> deleteWinXinUser(String access_token,String userAccount) throws Exception 
	 {
		  Map<String,String> map=new HashMap<String, String>();
		  CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {  
	            HttpGet httpPost = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token="+access_token+"&userid="+userAccount);
	              
	            CloseableHttpResponse response2 =httpclient.execute(httpPost);

	            try {
	                //System.out.println(response2.getStatusLine());
	                HttpEntity entity2 = response2.getEntity(); 
	                if (entity2 != null) {
	                    try {
	                        BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(entity2.getContent(),"UTF-8"), 8 * 1024);
	                        StringBuilder entityStringBuilder = new StringBuilder();
	                        String line = null;
	                        while ((line = bufferedReader.readLine()) != null) {
	                            entityStringBuilder.append(line);
	                        }
	                        // 利用从HttpEntity中得到的String生成JsonObject
	                        JSONObject resultJsonObject = new JSONObject(entityStringBuilder.toString());  
	                         
	                        map.put("errcode", resultJsonObject.get("errcode")+"");
	                        map.put("errmsg", resultJsonObject.get("errmsg")+"");
	                        
	                    } catch (Exception e) {
	                        logger.error(e);
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return map;
	 }
	
	/**
	 * 根据用户Id 获取微信单个用户
	 * @param access_token
	 * @param department_id
	 * @param fetch_child
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> getWinXinUserById(String access_token, String userId) throws Exception {
		  Map<String,String> map=new HashMap<String, String>();
		  CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {  
	        	System.out.println("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+access_token+"&userid="+userId);
	            HttpGet httpGet = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+access_token+"&userid="+userId);
	             
	            CloseableHttpResponse response2 = httpclient.execute(httpGet);

	            try {
	                HttpEntity entity2 = response2.getEntity(); 
	                if (entity2 != null) {
	                    try {
	                        BufferedReader bufferedReader = new BufferedReader(
	                        new InputStreamReader(entity2.getContent(),"UTF-8"), 8 * 1024);
	                        StringBuilder entityStringBuilder = new StringBuilder();
	                        String line = null;
	                        while ((line = bufferedReader.readLine()) != null) {
	                            entityStringBuilder.append(line);
	                        }
	                        // 利用从HttpEntity中得到的String生成JsonObject
	                        JSONObject resultJsonObject = new JSONObject(entityStringBuilder.toString());  
	                        map.put("errcode", resultJsonObject.get("errcode")+"");
	                        map.put("errmsg", resultJsonObject.get("errmsg")+"");
	                        
	                    } catch (Exception e) {
	                        logger.error(e);
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return map;
	 }
}
