package com.aus.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.aus.common.model.RankData;

public interface RankDataDao {

	List<RankData> selectTerritoryForIndex(@Param("ouId") String ouId);
	
	List<RankData> selectSelfForIndex(RankData rankData);
	
	List<RankData> selectSubLevelForIndex(RankData rankData);
	/**
	 * 获取用户主职位
	 * @param personId
	 * @return
	 */
	String getPrimaryPosition(@Param("personId") String personId);

}
