package com.aus.authority.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;

import com.aus.authority.model.UserDto;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.service.UserService;
import com.aus.authority.util.Constant;
import com.aus.authority.util.SecurityUtil;
import com.aus.common.model.OperateLogDto;
import com.aus.common.service.OperateLogService;

/**
 * 
 * 用户中心
 * 
 * @author duzh
 * 
 */
@Controller
public class UserCenterController {
	private static Logger logger = Logger.getLogger(UserCenterController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private OperateLogService operateLogService;
	/*
	 * 跳转到密码修改页面
	 */
	@RequestMapping(value = "/gotoPwdModify")
	public String gotoPwdModify(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();

		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals()
				.getPrimaryPrincipal();

		UserDto user = userService.findUserById(shiroUser.getUserId());

		request.setAttribute("user", user);

		return "jsp/authority/userCenter/userCenter_pwd_update";
	}

	/**
	 * 
	 * 修改用户密码
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateUserPwd")
	public String updateUserPwd(HttpServletRequest request, UserDto userDto,RedirectAttributes redirectAttributes) {

		try {
			UserDto user = userService.findUserById(userDto.getUserId());
			// 获取当前登录的用户
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			String newPwd = request.getParameter("newPwd");
			
			if (!StringUtils.equals(newPwd, request
					.getParameter("confirmpwd"))) {
				request.setAttribute("InfoMessage", "两次密码不一致");
				return this.gotoPwdModify(request);
			}

			if (!StringUtils.equals(user.getPassword(), SecurityUtil
					.entryptPassword(userDto.getPassword(), user.getSalt()))) {
				request.setAttribute("InfoMessage", "密码错误");
				return this.gotoPwdModify(request);
			}

			if (newPwd.length() < 6 || newPwd.length() > 18) {
				request.setAttribute("InfoMessage", "密码长度必须6-18位之间");
				return this.gotoPwdModify(request);
			}

			if ( !newPwd.matches(".*?[a-zA-Z]+.*?") || !newPwd.matches(".*?\\d+.*?")) {
				request.setAttribute("InfoMessage", "密码必须包含数字,字母");
				return this.gotoPwdModify(request);
			}

			user.setPassword(request.getParameter("newPwd"));	

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			user.setPwdModDate(df.format(new Date()));

			SecurityUtil.entryptPassword(user);

			String str = userService.updateUserById(user, newPwd);

			if (Constant.SAVE_SUCCESS.equals(str)) {
				OperateLogDto dto = new OperateLogDto();
				dto.setOperateUserId(shiroUser.getUserId());
				dto.setLogType("3");
				dto.setOperateType("UPDATE");
				if ("20".equals(user.getUserType())){
				dto.setOperateMsg("重置员工用户密码:用户ID为"+user.getUserId());
				}else if("30".equals(user.getUserType())){
				dto.setOperateMsg("重置经销商用户密码:经销商用户ID为:"+user.getUserId());	
				}else if("40".equals(user.getUserType())){
				dto.setOperateMsg("重置门店用户密码:门店用户ID为:"+user.getUserId());	
				}
				dto.setBeOperateObjId(user.getUserId());
				dto.setCreationBy(shiroUser.getUserId());
				dto.setLastUpdateBy(shiroUser.getUserId());
				operateLogService.addOperateLog(dto);
				
				RequestContext requestContext = new RequestContext(request);
				redirectAttributes.addFlashAttribute("InfoMessage", requestContext
						.getMessage(str));
				return "redirect:/gotoPwdModify.do";
			}

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "更新信息失败！具体异常信息："
					+ e.getMessage());
			return "result";
		}

		return null;
	}
	
}
