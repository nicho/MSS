package com.aus.common.service;

import java.util.List;

import com.aus.common.model.RankData;

public interface RankDataService {
	List<RankData> selectTerritoryForIndex(String ouId);
	
	List<RankData> selectSelfForIndex(RankData rankData);
	
	List<RankData> selectSubLevelForIndex(RankData rankData);
	/**
	 * 获取用户主职位
	 * @param personId
	 * @return
	 */
	String getPrimaryPosition(String personId);
}
