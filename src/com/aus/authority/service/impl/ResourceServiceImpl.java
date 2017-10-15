package com.aus.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.authority.dao.IResourceDao;
import com.aus.authority.model.ResourceDto;
import com.aus.authority.model.ResourceTlDto;
import com.aus.authority.service.ResourceService;
import com.aus.authority.util.AuthorityUtils;

/**
 * 
 * 资源服务
 * 
 * @author duzh
 *
 */
@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService{
	private static Logger logger = Logger.getLogger(ResourceServiceImpl.class);
	@Autowired
	private IResourceDao resourceDao;

	public List<ResourceDto> findAllResource(ResourceDto resourceDto) {
		return resourceDao.findAllResource(resourceDto);
	}

	public ResourceDto findResourceById(String resourceId) {
		return resourceDao.findResourceById(resourceId);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveResource(ResourceDto resourceDto) {
		Map returnMap = new HashMap<String,String>();
		
		try {
			resourceDao.saveResource(resourceDto);
			
			List<String> v_language_lit = AuthorityUtils.findSysLanguage();
			
			for( String v_lang : v_language_lit ) {				
				ResourceTlDto tl = new ResourceTlDto();
				BeanUtils.copyProperties(resourceDto, tl);
				tl.setLanguage(v_lang);
				resourceDao.saveResourceTl(tl);				
			}
			
			returnMap.put("success","true");
			
		} catch (Exception e) {
			returnMap.put("success","false");
			
			returnMap.put("message",e.getMessage());
			
			logger.error(e);
		}
		
		return returnMap;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> updateResource(ResourceDto resourceDto,String lang) {
		
		Map returnMap = new HashMap<String,String>();
		
		try {
			resourceDao.updateResource(resourceDto);
							
			ResourceTlDto tl = new ResourceTlDto();
			BeanUtils.copyProperties(resourceDto, tl);
			tl.setLanguage(lang);
			resourceDao.updateResourceT1(tl);				
			returnMap.put("success","true");
			
		} catch (Exception e) {
			returnMap.put("success","false");
			
			returnMap.put("message",e.getMessage());
			
			logger.error(e);
		}
		
		return returnMap;
	}

	public void deleteResourceById(String resourceId) {

		try {
			resourceDao.deleteResourceById(resourceId);
			resourceDao.deleteResourceTlById(resourceId);
			
		} catch (Exception e) {
			
			logger.error(e);
		}
		
		
	}

}
