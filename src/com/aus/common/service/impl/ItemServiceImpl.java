package com.aus.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.ItemDao;
import com.aus.common.model.ItemDto;
import com.aus.common.service.ItemService;

/**
 * 
 * @author duzh
 *
 */
@Service("ItemService")
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao itemDao;
	
	public List<ItemDto> getItems(ItemDto itemDto) {
		return itemDao.getItems(itemDto);
	}

}
