package com.aus.common.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aus.authority.util.AuthorityUtils;
import com.aus.common.model.ItemDto;
import com.aus.common.service.ItemService;
import com.aus.common.util.Constant;
import com.aus.common.util.PageHelperUtil;
import com.github.pagehelper.PageInfo;



/**
 * 
 * 产品选择
 * @author duzh
 *
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService ;
	
	@RequestMapping(value = "/gotoItemView")
	public String gotoItemView(HttpServletRequest request) {		
		return "jsp/common/item_list";		
	}
	
	@RequestMapping(value = "/getItemList")
	public String getItemList(HttpServletRequest request, ItemDto itemDto) {
		
		try {
			String orgId = AuthorityUtils.findPrifileValByResponsibility(request, Constant.ORG_ID, itemDto.getUrl() );
			
	    	itemDto.setOrgId(orgId);
		
			PageHelperUtil.PageHelperStartPage(request,itemDto);
			
			List<ItemDto> itemList = itemService.getItems(itemDto);
			
			PageInfo<ItemDto> page = new PageInfo<ItemDto>(itemList);
			
			request.setAttribute("page", page);
			
			request.setAttribute("itemList", itemList);			
			
			return "jsp/common/item_list";	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		} finally {
			
		}
		
	}
	
}
