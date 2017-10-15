package com.aus.common.service;

import java.util.List;

import com.aus.common.model.ItemDto;

public interface ItemService {

	List<ItemDto> getItems(ItemDto itemDto);

}
