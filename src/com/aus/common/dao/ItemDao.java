package com.aus.common.dao;

import java.util.List;

import com.aus.common.model.ItemDto;

/**
 * 
 * @author duzh
 *
 */
public interface ItemDao {

	List<ItemDto> getItems(ItemDto itemDto);

}
