package com.aus.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aus.common.dao.RankDataDao;
import com.aus.common.model.RankData;
import com.aus.common.service.RankDataService;
@Service("RankDataService")
public class RankDataServiceImpl implements RankDataService {
	@Autowired
	private RankDataDao rankDataDao;
	public List<RankData> selectTerritoryForIndex(String ouId) {
		return rankDataDao.selectTerritoryForIndex(ouId);
	}

	public List<RankData> selectSelfForIndex(RankData rankData) {
		return rankDataDao.selectSelfForIndex(rankData);
	}

	public List<RankData> selectSubLevelForIndex(RankData rankData) {
		return rankDataDao.selectSubLevelForIndex(rankData);
	}

	public String getPrimaryPosition(String personId) {
		return rankDataDao.getPrimaryPosition(personId);
	}

}
