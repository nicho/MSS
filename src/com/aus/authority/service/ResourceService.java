package com.aus.authority.service;

import java.util.List;
import java.util.Map;

import com.aus.authority.model.ResourceDto;

/**
 * 资源服务
 * 
 * @author duzh
 *
 */
public interface ResourceService {

	ResourceDto findResourceById(String resourceId);

	List<ResourceDto> findAllResource(ResourceDto resourceDto);

	Map<String, String> saveResource(ResourceDto resourceDto);

	Map<String, String> updateResource(ResourceDto resourceDto,String lang);

	void deleteResourceById(String resourceId);


}
