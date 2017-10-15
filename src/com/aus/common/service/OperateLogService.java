package com.aus.common.service;

import com.aus.common.model.CuxOaLoginLogDto;
import com.aus.common.model.OperateLogDto;
import com.aus.common.model.OperateLogLineDto;

public interface OperateLogService {
/**
* 新增操作日志
* */
public String addOperateLog(OperateLogDto dto);
/**
 * 新增操作日志条目
 * */
public String addOperateLogLine(OperateLogLineDto dto);

/**
 * 新增登录日志
 * */
public String addLoginLog(CuxOaLoginLogDto dto);
}
