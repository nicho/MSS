package com.aus.common.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.BaseGuestLimitDao;
import com.aus.common.model.BaseGuestLimit;
import com.aus.common.model.BaseGuestLimitObj;
import com.aus.common.service.BaseGuestLimitService;
@Service("BaseGuestLimitService")
public class BaseGuestLimitServiceImpl implements BaseGuestLimitService{
	@Autowired
	private BaseGuestLimitDao baseGuestLimitDao;
	@Override
	public int addBaseGuestLimit(BaseGuestLimit baseGuestLimit) {
		// TODO Auto-generated method stub
		return baseGuestLimitDao.addBaseGuestLimit(baseGuestLimit);
	}

	@Override
	public int addBaseGuestLimitObj(BaseGuestLimitObj baseGuestLimitObj) {
		// TODO Auto-generated method stub
		return baseGuestLimitDao.addBaseGuestLimitObj(baseGuestLimitObj);
	}

	@Override
	public int deleteBaseGuestLimitById(BaseGuestLimit baseGuestLimit) {
		// TODO Auto-generated method stub
		return baseGuestLimitDao.deleteBaseGuestLimitById(baseGuestLimit);
	}

	@Override
	public int deleteBaseGuestLimitObjById(BaseGuestLimit baseGuestLimit) {
		return baseGuestLimitDao.deleteBaseGuestLimitObjById(baseGuestLimit);
	}

	@Override
	public List<BaseGuestLimit> getBaseGuestLimitByUserId(
			BaseGuestLimit baseGuestLimit) {
		// TODO Auto-generated method stub
		return baseGuestLimitDao.getBaseGuestLimitByUserId(baseGuestLimit);
	}

	@Override
	public List<BaseGuestLimitObj> getBaseGuestLimitObjByLimitId(
			BaseGuestLimitObj baseGuestLimitObj) {
		// TODO Auto-generated method stub
		return baseGuestLimitDao.getBaseGuestLimitObjByLimitId(baseGuestLimitObj);
	}

	@Override
	public String addBaseGuestLimitList(BaseGuestLimit baseGuestLimit) {
		
		List<BaseGuestLimit> limitlist = baseGuestLimitDao.getBaseGuestLimitByUserId(baseGuestLimit);
		if(limitlist !=null && limitlist.size()>0){
		baseGuestLimitDao.deleteBaseGuestLimitById(baseGuestLimit);
		BaseGuestLimit limit = new BaseGuestLimit();
		limit.setGuser_limit_id(baseGuestLimit.getGuser_limit_id());
		baseGuestLimitDao.deleteBaseGuestLimitObjById(limit);
		}
		String []departemntIds = baseGuestLimit.getDepartmemtIds();
		departemntIds = array_unique(departemntIds);
		if(departemntIds !=null && departemntIds.length>0){
		 if(baseGuestLimitDao.addBaseGuestLimit(baseGuestLimit)==1){
			String guest_limit_id = baseGuestLimit.getGuser_limit_id();
			
			if(departemntIds !=null && departemntIds.length>0){
			for(int i=0;i<departemntIds.length;i++){
				BaseGuestLimitObj obj = new BaseGuestLimitObj();
				obj.setCreation_by(baseGuestLimit.getCreation_by());
				obj.setLast_update_by(baseGuestLimit.getCreation_by());
				obj.setObj_type("1");
				obj.setObj_value(departemntIds[i]);
				obj.setGuser_limit_id(guest_limit_id);
				if(baseGuestLimitDao.addBaseGuestLimitObj(obj)<=0){
				 return "save_failure";
				}
			}
			}else{
				 return "save_failure";
			}
			}
		 }
		return "save_success";
	}
	 //去除数组中重复的记录  
    public static String[] array_unique(String[] a) {  
        // array_unique  
        List<String> list = new LinkedList<String>(); 
        if(a==null || a.length==0){
        	return null;
        }
        
        for(int i = 0; i < a.length; i++) {  
            if(!list.contains(a[i])) {  
                list.add(a[i]);  
            }  
        }  
        return (String[])list.toArray(new String[list.size()]);  
    }

	@Override
	public List<BaseGuestLimit> getDeparmentList(HashMap<String, Object> map) {
		
		return baseGuestLimitDao.getDeparmentList(map);
	}

	@Override
	public List<BaseGuestLimitObj> getBaseGuestLimitJobObjByLimitId(
			BaseGuestLimitObj baseGuestLimitObj) {
		// TODO Auto-generated method stub
		return baseGuestLimitDao.getBaseGuestLimitJobObjByLimitId(baseGuestLimitObj);
	}  

}
