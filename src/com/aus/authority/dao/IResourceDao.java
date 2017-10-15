package com.aus.authority.dao;

import java.util.List;

import com.aus.authority.model.ResourceDto;
import com.aus.authority.model.ResourceTlDto;

public interface IResourceDao {

	List<ResourceDto> findAllResource(ResourceDto resourceDto);

	ResourceDto findResourceById(String resourceId);

	void saveResource(ResourceDto resourceDto);

	void saveResourceTl(ResourceTlDto tl);

	void updateResource(ResourceDto resourceDto);

	void updateResourceT1(ResourceTlDto tl);

	void deleteResourceById(String resourceId);

	void deleteResourceTlById(String resourceId);

	List<ResourceDto> getResourceByMeunId(String menuNodeId);

}
