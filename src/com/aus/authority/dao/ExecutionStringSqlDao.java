package com.aus.authority.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aus.common.util.SpringContextHolder;

public class ExecutionStringSqlDao extends SqlSessionDaoSupport{
	
	//日志管理器
	private static final Logger log=Logger.getLogger(ExecutionStringSqlDao.class);	
	
	public void executionSql(String sql) throws Exception{
		   		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory )SpringContextHolder.getBean("sqlSessionFactory");        
		
		SqlSession sqlSession = sqlSessionFactory.openSession();        
		
		Connection conn = sqlSession.getConnection();
		
		PreparedStatement ps=null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
			conn.commit();
            conn.setAutoCommit(true);
		} catch (Exception e) {
			throw e;
		} finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (Exception e) {
					log.error("预编译SQL语句对象PreparedStatement关闭异常！"+e.getMessage(), e);
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					log.error("关闭连接对象Connection异常！"+e.getMessage(), e);
				}
			}
		}
	}
	
	public void checkProSql(String sql) throws Exception{
   		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory )SpringContextHolder.getBean("sqlSessionFactory");        
		
		SqlSession sqlSession = sqlSessionFactory.openSession();        
		
		Connection conn = sqlSession.getConnection();
		
		PreparedStatement ps=null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
			conn.commit();
            conn.setAutoCommit(true);
            ps.executeQuery("SELECT CHECK_SOURCE_SQL() FROM DUAL");
		} catch (Exception e) {
			throw e;
		} finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (Exception e) {
					log.error("预编译SQL语句对象PreparedStatement关闭异常！"+e.getMessage(), e);
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					log.error("关闭连接对象Connection异常！"+e.getMessage(), e);
				}
			}
		}
	}
	
	public List<Map<String,String>> getProMap(String sql) throws Exception{
   		
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory )SpringContextHolder.getBean("sqlSessionFactory");        
		
		SqlSession sqlSession = sqlSessionFactory.openSession();

		Connection conn = sqlSession.getConnection();
		
		PreparedStatement ps=null;
		
		List<Map<String,String>> v_list = new ArrayList<Map<String,String>>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {	
				Map<String,String> map = new HashMap<String,String>();
				
				map.put("code", rs.getString(1));
				
				map.put("text",rs.getString(2));
				
				v_list.add(map);
			}
			
			return v_list;
	          
		} catch (Exception e) {
			throw e;
		} finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (Exception e) {
					log.error("预编译SQL语句对象PreparedStatement关闭异常！"+e.getMessage(), e);
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					log.error("关闭连接对象Connection异常！"+e.getMessage(), e);
				}
			}
		}
		
	}
	

}
