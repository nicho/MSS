package com.aus.common.dao;

import com.aus.common.model.CuxOaLoginLogDto;
import com.aus.common.model.OperateLogDto;
import com.aus.common.model.OperateLogLineDto;

public interface OperateLogDao {
/**
 * 新增操作日志
 * */
int addOperateLog(OperateLogDto dto);
/**
 * 新增操作日志条目
 * */
int addOperateLogLine(OperateLogLineDto dto);
/**
 * 新增登录日志
 * */
int addLoginLog(CuxOaLoginLogDto dto);
}
