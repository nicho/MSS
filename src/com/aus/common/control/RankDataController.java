package com.aus.common.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.common.model.PersonDto;
import com.aus.common.service.PersonService;
import com.aus.common.model.RankData;
import com.aus.common.service.RankDataService;
/**
 * 四类业务数据排名管理
 * @author haowenxin
 */
@Controller
public class RankDataController {
	@Autowired
	private PersonService personService;
	@Autowired
	private RankDataService rankDataService;
	
	/**
	 * 首页初始化人员的季度任务数据
	 * @return
	 */
	@RequestMapping(value = "initPersonRankData")
	@ResponseBody
	public Map<String, String> initPersonRankData() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		//获得当前登录用户信息
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		String personId = shiroUser.getPersonId();//人员ID
		PersonDto personDto = new PersonDto();
		personDto.setPersonId(personId);
		//获得人员信息
		List<PersonDto> personList = personService.getPersonOrganization(personDto);
		if (personList != null && personList.size() > 0) {
			PersonDto personBo = personList.get(0);
			String orgId = personBo.getOrgId();
			String positionId = personBo.getPositionId();//职位ID
			//直接下属任务进度汇总
			RankData subLevel = new RankData();
			subLevel.setOuId(orgId);
			subLevel.setPositionId(positionId);
			List<RankData> subLevelList = rankDataService.selectSubLevelForIndex(subLevel);
			if(subLevelList != null && subLevelList.size() > 0){
				for(RankData sub : subLevelList){
					if("SELL".equals(sub.getBizType())){//动销
						map.put("SUBLEVEL_ACHIEVEVALUE_SELL", sub.getSysAchieveValue());
						map.put("SUBLEVEL_ACIHEVERATE_SELL", sub.getAciheveRate());
						map.put("SUBLEVEL_TASKVALUE_SELL", sub.getTaskValue());
						map.put("SUBLEVEL_TASKUNIT_SELL", sub.getTaskUnit());
					}else if("NEW_MENBER".equals(sub.getBizType())){//新客
						map.put("SUBLEVEL_ACHIEVEVALUE_NEWMENBER", sub.getSysAchieveValue());
						map.put("SUBLEVEL_ACIHEVERATE_NEWMENBER", sub.getAciheveRate());
						map.put("SUBLEVEL_TASKVALUE_NEWMENBER", sub.getTaskValue());
						map.put("SUBLEVEL_TASKUNIT_NEWMENBER", sub.getTaskUnit());
					}else if("SELL_ORDER".equals(sub.getBizType())){//调货
						map.put("SUBLEVEL_ACHIEVEVALUE_SELLORDER", sub.getSysAchieveValue());
						map.put("SUBLEVEL_ACIHEVERATE_SELLORDER", sub.getAciheveRate());
						map.put("SUBLEVEL_TASKVALUE_SELLORDER", sub.getTaskValue());
						map.put("SUBLEVEL_TASKUNIT_SELLORDER", sub.getTaskUnit());
					}else if("COLLECTION".equals(sub.getBizType())){//回款
						map.put("SUBLEVEL_ACHIEVEVALUE_COLLECTION", sub.getSysAchieveValue());
						map.put("SUBLEVEL_ACIHEVERATE_COLLECTION", sub.getAciheveRate());
						map.put("SUBLEVEL_TASKVALUE_COLLECTION", sub.getTaskValue());
						map.put("SUBLEVEL_TASKUNIT_COLLECTION", sub.getTaskUnit());
					}else{
						
					}
				}
			}
			//个人任务进度汇总
			RankData self = new RankData();
			self.setOuId(orgId);
			self.setPositionId(positionId);
			self.setObject(personId);
			List<RankData> selfList = rankDataService.selectSelfForIndex(self);
			if(selfList != null && selfList.size() > 0){
				for(RankData sub : selfList){
					if("SELL".equals(sub.getBizType())){//动销
						map.put("SELF_ACHIEVEVALUE_SELL", sub.getSysAchieveValue());
						map.put("SELF_ACIHEVERATE_SELL", sub.getAciheveRate());
						map.put("SELF_TASKVALUE_SELL", sub.getTaskValue());
						map.put("SELF_TASKUNIT_SELL", sub.getTaskUnit());
					}else if("NEW_MENBER".equals(sub.getBizType())){//新客
						map.put("SELF_ACHIEVEVALUE_NEWMENBER", sub.getSysAchieveValue());
						map.put("SELF_ACIHEVERATE_NEWMENBER", sub.getAciheveRate());
						map.put("SELF_TASKVALUE_NEWMENBER", sub.getTaskValue());
						map.put("SELF_TASKUNIT_NEWMENBER", sub.getTaskUnit());
					}else if("SELL_ORDER".equals(sub.getBizType())){//调货
						map.put("SELF_ACHIEVEVALUE_SELLORDER", sub.getSysAchieveValue());
						map.put("SELF_ACIHEVERATE_SELLORDER", sub.getAciheveRate());
						map.put("SELF_TASKVALUE_SELLORDER", sub.getTaskValue());
						map.put("SELF_TASKUNIT_SELLORDER", sub.getTaskUnit());
					}else if("COLLECTION".equals(sub.getBizType())){//回款
						map.put("SELF_ACHIEVEVALUE_COLLECTION", sub.getSysAchieveValue());
						map.put("SELF_ACIHEVERATE_COLLECTION", sub.getAciheveRate());
						map.put("SELF_TASKVALUE_COLLECTION", sub.getTaskValue());
						map.put("SELF_TASKUNIT_COLLECTION", sub.getTaskUnit());
					}else{
						
					}
				}
			}
			//销售区域季度动销达成率排名
			List<RankData> territoryList = rankDataService.selectTerritoryForIndex(orgId);
			if(territoryList != null && territoryList.size() > 0){
				for(RankData rd : territoryList){
					map.put("TERRITORY_NAME", rd.getTerritoryName());
					map.put("TERRITORY_ACIHEVE_RATE", rd.getAciheveRate());
					map.put("TERRITORY_GLOBAL_RANK", rd.getGlobalRank());
				}
			}
		}
		return map;
	}
}
