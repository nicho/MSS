package com.aus.authority.control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.aus.authority.annotation.AccreditAnnotation;
import com.aus.authority.model.LanguageDto;
import com.aus.authority.model.ResponsibilityDto;
import com.aus.authority.model.SubinvDto;
import com.aus.authority.model.TreeDTO;
import com.aus.authority.model.UserDto;
import com.aus.authority.service.ResponsibilityService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.service.UserService;
import com.aus.authority.util.AuthorityUtils;
import com.aus.authority.util.Constant;
import com.aus.authority.util.SecurityUtil;
import com.aus.common.model.BaseGuestLimit;
import com.aus.common.model.BaseGuestLimitObj;
import com.aus.common.model.OperateLogDto;
import com.aus.common.model.OperateLogLineDto;
import com.aus.common.model.PersonDto;
import com.aus.common.model.RankData;
import com.aus.common.service.BaseGuestLimitService;
import com.aus.common.service.DictionaryService;
import com.aus.common.service.OperateLogService;
import com.aus.common.service.PersonService;
import com.aus.common.service.RankDataService;
import com.aus.common.util.DateUtil;
import com.aus.common.util.MD5Util;
import com.aus.common.util.OAUtils;
import com.aus.common.util.PageHelperUtil;
import com.aus.common.util.ReflectUtil;
import com.aus.common.util.SessionUtil;
import com.aus.common.util.TimerUtil;
import com.aus.common.util.mail.MailSenderInfo;
import com.aus.common.util.mail.SimpleMailSender;
import com.github.pagehelper.PageInfo;

/**
 * 用户管理
 * 
 * @author duzh
 * 
 */
@Controller
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private ResponsibilityService responsibilityService;

	@Autowired
	private PersonService personService;
	@Autowired
	private RankDataService rankDataService;
	@Autowired
	private OperateLogService operateLogService;
	@Autowired
	private BaseGuestLimitService baseGuestLimitService;
	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 * @throws DecoderException
	 * @throws ParseException
	 */
	@RequestMapping("index")
	public String indexPage(HttpServletRequest request, RedirectAttributes redirectAttributes) throws DecoderException, ParseException {

		Subject currentUser = SecurityUtils.getSubject();

		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();

		AuthorityUtils.userLogin(request.getSession().getId(), shiroUser.getUserId());

		String sessionId = request.getSession().getId();

		// LanguageDto lang =
		// userService.getLanguageByISO(RequestContextUtils.getLocale(request).getLanguage());

		UserDto user = userService.findUserById(shiroUser.getUserId());

		if ("30".equals(user.getUserType())) {
			// 经销商用户
			SubinvDto subinvDto = userService.getSubinvDto(shiroUser.getUserName(), OAUtils.getOrgId(request));
			if (subinvDto != null) {
				if ("20".equals(subinvDto.getSubinv_type())) {
					shiroUser.setName(subinvDto.getAccount_name());
				} else {
					shiroUser.setName(subinvDto.getSubinv_name());
				}

				shiroUser.setSubinvType(subinvDto.getSubinv_type());
				request.setAttribute("subinvType", subinvDto.getSubinv_type());
			} else {
				shiroUser.setName("");
			}

		} else if ("20".equals(user.getUserType())) {
			// 销售区域季度动销达成率排名
			List<RankData> territoryList = null;
			String personId = shiroUser.getPersonId();// 人员ID
			PersonDto personDto = new PersonDto();
			personDto.setPersonId(personId);
			// 获得主职位人员信息
			List<PersonDto> personList = personService.getPersonOrganization(personDto);
			if (personList != null && personList.size() > 0) {
				PersonDto personBo = personList.get(0);
				String orgId = personBo.getOrgId();
				territoryList = rankDataService.selectTerritoryForIndex(orgId);
			}
			request.setAttribute("territoryList", territoryList);
			if (territoryList != null) {
				request.setAttribute("size", territoryList.size());
			} else {
				request.setAttribute("size", 0);
			}
		}

		request.setAttribute("userType", user.getUserType());

		SessionUtil.initDomainCSSSession(request);

		if (StringUtils.equals(user.getPassword(), SecurityUtil.entryptPassword(Constant.INTIPASS, user.getSalt()))) {
			redirectAttributes.addFlashAttribute("InfoMessage", "为了您的账户安全，系统每半年将需要您更改密码。" + "	密码需包含数字、字母。");

			return "redirect:/GlobalPwdModify.do";
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (DateUtil.getIntervalDays(df.parse(user.getPwdModDate()), new Date()) > 180) {
			redirectAttributes.addFlashAttribute("InfoMessage", "为了您的账户安全，系统每半年将需要您更改密码。" + "	密码需包含数字、字母。");

			return "redirect:/GlobalPwdModify.do";
		}

		// shiroUser.setLanguage(StringUtils.upperCase(lang.getLanguageCode()));

		// try {
		// userService.alterSessionLang(ReadProperties.getDomainMap().get("oracle_language"));
		// } catch (Exception e) {
		// logger.error(e);
		// }
		if (request.getSession().getAttribute("v_navigationList") == null) {
			List<TreeDTO> v_navigationList = AuthorityUtils.findLogonNavigationTrees(sessionId, shiroUser.getLanguage());

			request.getSession().setAttribute("v_navigationList", v_navigationList);
		}

		SessionUtil.cleanNavigationSession(request);

		Map<String, String> urlResponsibilityMap = new HashMap<String, String>();

		request.getSession().setAttribute("urlResponsibilityMap", urlResponsibilityMap);

		request.setAttribute("orgId", OAUtils.getOrgId(request));

		return "jsp/index";
	}

	/**
	 * 手机用户登录
	 * 
	 * @param request
	 * @return
	 * @throws DecoderException
	 * @throws ParseException
	 */
	@RequestMapping("mobileIndex")
	public String mobileIndexPage(HttpServletRequest request, RedirectAttributes redirectAttributes) throws DecoderException, ParseException {

		Subject currentUser = SecurityUtils.getSubject();

		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();

		AuthorityUtils.userLogin(request.getSession().getId(), shiroUser.getUserId());

		LanguageDto lang = userService.getLanguageByISO(RequestContextUtils.getLocale(request).getLanguage());

		UserDto user = userService.findUserById(shiroUser.getUserId());

		if (StringUtils.equals(user.getPassword(), SecurityUtil.entryptPassword(Constant.INTIPASS, user.getSalt()))) {
			redirectAttributes.addFlashAttribute("InfoMessage", "为了您的账户安全，系统每半年将需要您更改密码。" + "	密码需包含数字、字母。");

			return "redirect:/GlobalPwdModify.do";
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (DateUtil.getIntervalDays(df.parse(user.getPwdModDate()), new Date()) > 180) {
			redirectAttributes.addFlashAttribute("InfoMessage", "为了您的账户安全，系统每半年将需要您更改密码。" + "	密码需包含数字、字母。");

			return "redirect:/GlobalPwdModify.do";
		}

		shiroUser.setLanguage(StringUtils.upperCase(lang.getLanguageCode()));

		Map<String, String> urlResponsibilityMap = new HashMap<String, String>();

		request.getSession().setAttribute("urlResponsibilityMap", urlResponsibilityMap);

		return "jsp/index";
	}

	/*
	 * 全局密码修改
	 */
	@RequestMapping(value = "/GlobalPwdModify")
	public String GlobalPwdModify(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();

		ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();

		UserDto user = userService.findUserById(shiroUser.getUserId());

		request.setAttribute("user", user);

		return "jsp/authority/user/user_pwd_update";
	}

	/**
	 * 
	 * 跳转到密码找回页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoPwdByEmail")
	public String gotoPwdByEmail(HttpServletRequest request) {
		return "jsp/authority/user/user_pwd_email";
	}

	/**
	 * 
	 * 密码找回
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPwdByEmail")
	public String findPwdByEmail(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {

		String userName = request.getParameter("userName");

		if (StringUtils.isBlank(userName)) {
			request.setAttribute("InfoMessage", "请输入用户名");
			return gotoPwdByEmail(request);
		}

		userName = userName.toUpperCase();

		UserDto user = userService.findUserByUserName(userName);

		if (user == null) {
			request.setAttribute("InfoMessage", "用户不存在");
			return gotoPwdByEmail(request);
		}

		if (!StringUtils.equals("20", user.getUserType())) {
			request.setAttribute("InfoMessage", "系统暂只支持员工找回密码");
			return gotoPwdByEmail(request);
		}

		if (StringUtils.isBlank(user.getPersonId()) || StringUtils.isBlank(userService.getPersonEmail(user.getPersonId()))) {
			request.setAttribute("InfoMessage", "未配置邮箱地址");
			return gotoPwdByEmail(request);
		}

		String title = "找回您的ESS账户密码";

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

		String secretKey = UUID.randomUUID().toString(); // 密钥

		String key = userName + "$" + secretKey;

		String digitalSignature = MD5Util.string2MD5(key);

		user.setValidataCode(secretKey);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		user.setOutDate(df.format(new Date()));

		userService.updateUserById(user, null);

		String resetPassHref = basePath + "checkPwdLink.do?sid=" + digitalSignature + "&userName=" + userName;

		String emailContent = "尊敬的用户：\r\n" + "\r\n" + "   您好！您提交了ESS系统找回密码请求，请点击下面的链接，系统将重置您的密码为123456。\r\n" + "<a href=" + "\"" + resetPassHref + "\"" + ">" + resetPassHref
				+ " \r\n" + "</a>" + "  为了保证您账号的安全性，该链接本日内有效，并且点击一次后将失效！\r\n" + " 如果您误收到此电子邮件，则可能是其他用户在尝试帐号设置时的误操作。" + "如果您并未发起该请求，则无需再进行任何操作，并可以放心地忽略此电子邮件。";

		// SendEmail.sendEmail(title, emailContent,
		// userService.getPersonEmail(user.getPersonId()));

		SimpleMailSender sender = new SimpleMailSender();
		MailSenderInfo mailInfo = new MailSenderInfo();

		// 这个类主要是设置邮件
		mailInfo.setMailServerHost("smtp.exmail.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("it@Ausnutria.com");
		mailInfo.setPassword("au123456789");// 您的邮箱密码
		mailInfo.setFromAddress("it@Ausnutria.com");

		// mailInfo.setUserName("800@Ausnutria.com");
		// mailInfo.setPassword("au8786122");// 您的邮箱密码
		// mailInfo.setFromAddress("800@Ausnutria.com");
		mailInfo.setToAddress(userService.getPersonEmail(user.getPersonId()));
		mailInfo.setSubject(title);
		mailInfo.setContent(emailContent);
		sender.sendHtmlMail(mailInfo);

		// sender.sendHtmlMail(mailInfo)

		redirectAttributes.addFlashAttribute("InfoMessage", "密码找回地址已发送到你的邮箱,请点击邮箱中的链接完成重置");

		return "redirect:/gotoPwdByEmail.do";
	}

	/**
	 * 
	 * 检测密码重置链接
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkPwdLink")
	public String checkPwdLink(HttpServletRequest request) throws Exception {

		String sid = request.getParameter("sid");

		String userName = request.getParameter("userName");

		if (StringUtils.isBlank(sid) || StringUtils.isBlank(userName)) {
			request.setAttribute("InfoMessage", "链接已过期,请重新申请吧.");
			return gotoPwdByEmail(request);
		}

		UserDto user = userService.findUserByUserName(userName);

		String key = userName + "$" + user.getValidataCode();

		if (DateUtil.compareDate(user.getOutDate()) > 0) {
			request.setAttribute("InfoMessage", "链接已过期,请重新申请吧.");

			return gotoPwdByEmail(request);
		}

		if (!StringUtils.equals(MD5Util.string2MD5(key), sid)) {

			request.setAttribute("InfoMessage", "链接不正确,可能已过期，请重新申请吧.");

			return gotoPwdByEmail(request);
		}

		user.setPassword(Constant.INTIPASS);

		user.setOutDate(null);

		user.setValidataCode(UUID.randomUUID().toString());

		SecurityUtil.entryptPassword(user);

		userService.updateUserById(user, Constant.INTIPASS);

		request.setAttribute("InfoMessage", "密码重置成功,请尽快修改密码.");

		return "redirect:/login.do";
	}

	/**
	 * 
	 * 修改用户密码
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateBasePwd")
	public String updateBasePwd(HttpServletRequest request, UserDto userDto, RedirectAttributes redirectAttributes) {

		try {
			UserDto user = userService.findUserById(userDto.getUserId());

			String newPwd = request.getParameter("newPwd");

			if (!StringUtils.equals(newPwd, request.getParameter("confirmpwd"))) {
				redirectAttributes.addFlashAttribute("InfoMessage", "两次密码不一致");
				return "redirect:/GlobalPwdModify.do";
			}

			if (!StringUtils.equals(user.getPassword(), SecurityUtil.entryptPassword(userDto.getPassword(), user.getSalt()))) {
				redirectAttributes.addFlashAttribute("InfoMessage", "密码错误");
				return "redirect:/GlobalPwdModify.do";
			}

			if (newPwd.length() < 6 || newPwd.length() > 18) {
				redirectAttributes.addFlashAttribute("InfoMessage", "密码长度必须6-18位之间");
				return "redirect:/GlobalPwdModify.do";
			}

			if (!newPwd.matches(".*?[a-zA-Z]+.*?") || !newPwd.matches(".*?\\d+.*?")) {
				redirectAttributes.addFlashAttribute("InfoMessage", "密码必须包含数字,字母");
				return "redirect:/GlobalPwdModify.do";
			}

			user.setPassword(request.getParameter("newPwd"));

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			user.setPwdModDate(df.format(new Date()));

			SecurityUtil.entryptPassword(user);

			String str = userService.updateUserById(user, newPwd);

			// 获取当前登录的用户
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			OperateLogDto dto = new OperateLogDto();
			dto.setOperateUserId(shiroUser.getUserId());
			dto.setLogType("3");
			dto.setOperateType("UPDATE");
			if ("20".equals(user.getUserType())) {
				dto.setOperateMsg("修改员工用户密码:用户ID为" + user.getUserId());
			} else if ("30".equals(user.getUserType())) {
				dto.setOperateMsg("修改经销商用户密码:经销商用户ID为:" + user.getUserId());
			} else if ("40".equals(user.getUserType())) {
				dto.setOperateMsg("修改门店用户密码:门店用户ID为:" + user.getUserId());
			}
			dto.setBeOperateObjId(user.getUserId());
			dto.setCreationBy(shiroUser.getUserId());
			dto.setLastUpdateBy(shiroUser.getUserId());
			operateLogService.addOperateLog(dto);

			if (Constant.SAVE_SUCCESS.equals(str)) {
				RequestContext requestContext = new RequestContext(request);
				redirectAttributes.addFlashAttribute("InfoMessage", requestContext.getMessage(str));
				return "redirect:/index.do";
			}

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "更新信息失败！具体异常信息：" + e.getMessage());
			return "result";
		}

		return null;
	}

	@RequestMapping(value = "/getUserResponsibilityList")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String getUserResponsibilityList(HttpServletRequest request, ResponsibilityDto responsibilityDto) {

		String orgId = AuthorityUtils.findPrifileValByResponsibility(request, "ORG_ID", responsibilityDto.getUrl());

		responsibilityDto.setOrgId(orgId);
		// 分页控件
		try {
			PageHelperUtil.PageHelperStartPage(request, responsibilityDto);
			List<ResponsibilityDto> responsibilitys = responsibilityService.getAllResponsibility(responsibilityDto);
			request.setAttribute("responsibilitys", responsibilitys);
			PageInfo<ResponsibilityDto> page = new PageInfo<ResponsibilityDto>(responsibilitys);
			// 页码
			request.setAttribute("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "jsp/authority/user/reponsibilty_select_list";

	}

	/**
	 * 跳转到用户管理界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoUserList")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String gotoUserList(HttpServletRequest request) {
		return "jsp/authority/user/user_list";
	}

	/**
	 * 查询用户列表
	 * 
	 * @param request
	 * @param userDto
	 * @return
	 */

	@RequestMapping(value = "/viewUserIndex")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String viewUserIndex(HttpServletRequest request, UserDto userDto) {

		try {
			userDto.setOrgId(OAUtils.getOrg(request));

			userDto.setUserType(OAUtils.getUserType(request));

			userDto.setResponsibilityId(OAUtils.getResponsebilityId(request));

			PageHelperUtil.PageHelperStartPage(request, userDto);

			List<UserDto> v_userList = userService.getAllUser(userDto);

			PageInfo<UserDto> page = new PageInfo<UserDto>(v_userList);

			request.setAttribute("page", page);
			request.setAttribute("responsibilityId", userDto.getResponsibilityId());

			request.setAttribute("v_userList", v_userList);

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}

		return "jsp/authority/user/user_list";
	}

	/**
	 * 跳转到用户新增页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoAddUser")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "user:add")
	public String gotoAddUser(HttpServletRequest request) {
		String beginTime = TimerUtil.getMonthNowDay();
		request.setAttribute("beginTime", beginTime);
		String endTime = "2099-12-31";
		request.setAttribute("endTime", endTime);

		return "jsp/authority/user/user_add";
	}

	/**
	 * 保存新增用户
	 * 
	 * @param request
	 * @param userDto
	 * @return
	 */
	@RequestMapping("saveUser")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String saveUser(HttpServletRequest request, UserDto userDto, RedirectAttributes redirectAttributes) {
		// 获取当前登录的用户
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

		try {
			if (StringUtils.isNotBlank(userDto.getUserName()) && StringUtils.equals(userDto.getUserName().toUpperCase(), "SYSADMIN")) {
				request.setAttribute("InfoMessage", "不允许创建名为SYSADMIN的帐号");
				return this.gotoAddUser(request);
			}

			if (userDto.getUserType() == "20" && StringUtils.isBlank(userDto.getPersonId())) {
				request.setAttribute("InfoMessage", "员工用户请关联人员");
				return this.gotoAddUser(request);
			}

			if (userDto.getUserType() == "30" && StringUtils.isBlank(userDto.getCustAccountId())) {
				request.setAttribute("InfoMessage", "经销商用户请关联经销商子库");
				return this.gotoAddUser(request);
			}

			if (userService.findUserByPersonId(userDto.getPersonId(), userDto.getUserId()) > 0) {
				request.setAttribute("InfoMessage", "人员已关联");
				return this.gotoAddUser(request);
			}

			userDto.setPassword(Constant.INTIPASS);

			userDto.setUserName(StringUtils.trim(userDto.getUserName()));

			SecurityUtil.entryptPassword(userDto);

			userDto.setUserName(StringUtils.upperCase(userDto.getUserName()));

			UserDto checkUser = userService.findUserByUserName(userDto.getUserName());

			if (checkUser != null) {
				request.setAttribute("InfoMessage", "用户名已存在");
				return this.gotoAddUser(request);
			}

			String str = userService.registerUser(userDto);
			String weixinRs = "";
			if (Constant.SAVE_SUCCESS.equals(str)) {
				OperateLogDto dto = new OperateLogDto();
				dto.setOperateUserId(shiroUser.getUserId());
				dto.setLogType("1");
				dto.setOperateType("ADD");
				if ("20".equals(userDto.getUserType())) {
					dto.setOperateMsg("新增员工用户:用户ID为" + userDto.getUserId());
					// 新增员工用户时,同时同步微信账号
					weixinRs = userService.addWeiXinUser(userDto.getUserId(), userDto.getOrgId());
				} else if ("30".equals(userDto.getUserType())) {
					dto.setOperateMsg("新增经销商用户:经销商用户ID为:" + userDto.getUserId());
				} else if ("40".equals(userDto.getUserType())) {
					dto.setOperateMsg("新增门店用户:门店用户ID为:" + userDto.getUserId());
				}
				dto.setBeOperateObjId(userDto.getUserId());
				dto.setCreationBy(shiroUser.getUserId());
				dto.setLastUpdateBy(shiroUser.getUserId());
				operateLogService.addOperateLog(dto);

				RequestContext requestContext = new RequestContext(request);
				redirectAttributes.addFlashAttribute("InfoMessage", requestContext.getMessage(str) + weixinRs);
				return "redirect:/gotoAddUser.do";
			}

		} catch (Exception e) {
			logger.error(e);

			redirectAttributes.addFlashAttribute("InfoMessage", "保存用户失败");

		}

		return "redirect:/gotoAddUser.do";
	}

	/**
	 * 跳转到用户修改页面
	 * 
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoUpdateUser")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "user:edit")
	public String gotoUpdateUser(String userId, HttpServletRequest request) {
		try {
			UserDto user = userService.findUserById(userId);

			if (StringUtils.isNotBlank(user.getPersonId()) && !StringUtils.equals(user.getPersonId().trim(), "0")) {
				request.setAttribute("showRealaseFlag", "Y");
			}

			request.setAttribute("user", user);

			return "jsp/authority/user/user_update";
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "reSetPwd")
	@ResponseBody
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "user:resetpwd")
	public String reSetPwd(String userId, HttpServletRequest request) {
		try {

			UserDto user = userService.findUserById(userId);
			user.setPassword(Constant.INTIPASS);

			SecurityUtil.entryptPassword(user);

			userService.updateUserById(user, Constant.INTIPASS);
			// 获取当前登录的用户
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			OperateLogDto dto = new OperateLogDto();
			dto.setOperateUserId(shiroUser.getUserId());
			dto.setLogType("3");
			dto.setOperateType("UPDATE");
			if ("20".equals(user.getUserType())) {
				dto.setOperateMsg("重置员工用户密码:用户ID为" + user.getUserId());
			} else if ("30".equals(user.getUserType())) {
				dto.setOperateMsg("重置经销商用户密码:经销商用户ID为:" + user.getUserId());
			} else if ("40".equals(user.getUserType())) {
				dto.setOperateMsg("重置门店用户密码:门店用户ID为:" + user.getUserId());
			}
			dto.setBeOperateObjId(user.getUserId());
			dto.setCreationBy(shiroUser.getUserId());
			dto.setLastUpdateBy(shiroUser.getUserId());
			operateLogService.addOperateLog(dto);

			return "true";

		} catch (Exception e) {
			logger.error(e);
			return "false";
		}
	}

	/**
	 * 解除绑定
	 * 
	 * @param user
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("releaseBoundUser")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String releaseBoundUser(UserDto user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String orgId = AuthorityUtils.findPrifileValByResponsibility(request, "ORG_ID", "gotoUserList.do");
		try {
			PersonDto personDto = new PersonDto();
			personDto.setOrgId(orgId);
			personDto.setPersonId(user.getPersonId());
			personDto.setConditionSql(" AND V.ORG_ID=" + orgId);

			List<PersonDto> list = personService.getPersonInBudget(personDto);
			String responsebilityId = OAUtils.getResponsebilityId(request);

			if (list != null && list.size() > 0) {
				PersonDto dto = list.get(0);
				String flag = dto.getPrimaryFlag();
				if (!"1017".equals(responsebilityId)) {
					if ("N".equals(flag)) {
						request.setAttribute("InfoMessage", "非主职位所在组织，无法解除绑定");
						return this.gotoUpdateUser(user.getUserId(), request);
					}
				}

			} else {
				if (!"1017".equals(responsebilityId)) {
					request.setAttribute("InfoMessage", "非主职位所在组织，无法解除绑定");
					return this.gotoUpdateUser(user.getUserId(), request);
				}
			}

			user.setPersonId("");

			Calendar cal = Calendar.getInstance();

			cal.add(Calendar.DATE, -1);

			String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());

			user.setEndDate(yesterday);// 帐号失效

			userService.updateUserById(user, null);
			UserDto oriUser = userService.findUserById(user.getUserId());
			// 获取当前登录的用户
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			OperateLogDto dto = new OperateLogDto();
			dto.setOperateUserId(shiroUser.getUserId());
			dto.setLogType("1");
			dto.setOperateType("UPDATE");
			if ("20".equals(oriUser.getUserType())) {

				// 检查员工微信账号,进行失效操作
				String weixinMsg = userService.deleteWeiXinUser(oriUser.getUserName());

				dto.setOperateMsg("员工用户解除绑定:用户ID为" + oriUser.getUserId() + weixinMsg);
			} else if ("30".equals(oriUser.getUserType())) {
				dto.setOperateMsg("经销商用户解除绑定:经销商用户ID为:" + oriUser.getUserId());
			} else if ("40".equals(oriUser.getUserType())) {
				dto.setOperateMsg("门店用户解除绑定:门店用户ID为:" + oriUser.getUserId());
			}
			dto.setBeOperateObjId(oriUser.getUserId());
			dto.setCreationBy(shiroUser.getUserId());
			dto.setLastUpdateBy(shiroUser.getUserId());
			operateLogService.addOperateLog(dto);

			redirectAttributes.addFlashAttribute("InfoMessage", "解除人员绑定成功,帐号已失效");

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "更新信息失败！具体异常信息：" + e.getMessage());
			return "result";
		} finally {

		}

		return "redirect:/gotoUpdateUser.do?userId=" + user.getUserId();
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateUser")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String updateUser(UserDto user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			UserDto userDto1 = new UserDto();
			userDto1.setOrgId(OAUtils.getOrg(request));
			userDto1.setPersonId(user.getPersonId());

			userDto1.setUserType(OAUtils.getUserType(request));

			userDto1.setResponsibilityId(OAUtils.getResponsebilityId(request));
			List<UserDto> v_userList = userService.getAllUser(userDto1);
			if (v_userList != null && v_userList.size() > 0) {
				UserDto dto = v_userList.get(0);
				String status = dto.getOrg_status();
				String responsebilityId = OAUtils.getResponsebilityId(request);
				if (!"1017".equals(responsebilityId)) {
					if ("20".equals(user.getUserType())) {
						if ("N".equals(status)) {
							request.setAttribute("InfoMessage", "非主职位所在组织，无法修改用户");
							return this.gotoUpdateUser(user.getUserId(), request);
						}
					}
				}

			}
			if (user.getUserType() == "20" && StringUtils.isBlank(user.getPersonId())) {
				request.setAttribute("InfoMessage", "员工用户请关联人员");
				return this.gotoUpdateUser(user.getUserId(), request);
			}

			if (user.getUserType() == "30" && StringUtils.isBlank(user.getCustAccountId())) {
				request.setAttribute("InfoMessage", "经销商用户请关联经销商子库");
				return this.gotoUpdateUser(user.getUserId(), request);
			}

			if (userService.findUserByPersonId(user.getPersonId(), user.getUserId()) > 0) {
				request.setAttribute("InfoMessage", "人员已关联");
				return this.gotoUpdateUser(user.getUserId(), request);
			}
			UserDto oriUser = userService.findUserById(user.getUserId());
			HashMap<String, Object> orimap = ReflectUtil.reflect(oriUser);
			// 获取当前登录的用户
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			String str = userService.updateUserById(user, null);
			HashMap<String, Object> updateMap = ReflectUtil.reflect(user);
			if (Constant.SAVE_SUCCESS.equals(str)) {
				OperateLogDto dto = new OperateLogDto();
				dto.setOperateUserId(shiroUser.getUserId());
				dto.setLogType("1");
				dto.setOperateType("UPDATE");
				if ("20".equals(user.getUserType())) {
					String weixinMsg = "";
					Date nowDate = new Date();
					if (!StringUtils.isEmpty(user.getEndDate()) && nowDate.getTime() > DateUtils.parseDate(user.getEndDate(), "yyyy-MM-dd").getTime()) {
						// 检查员工微信账号,进行失效操作
						weixinMsg = userService.deleteWeiXinUser(oriUser.getUserName());
					}

					dto.setOperateMsg("修改员工用户:用户ID为" + user.getUserId() + weixinMsg);
				} else if ("30".equals(user.getUserType())) {
					dto.setOperateMsg("修改经销商用户:经销商用户ID为:" + user.getUserId());
				} else if ("40".equals(user.getUserType())) {
					dto.setOperateMsg("修改门店用户:门店用户ID为:" + user.getUserId());
				} else {
					dto.setOperateMsg("修改用户:" + user.getUserId());
				}
				dto.setBeOperateObjId(user.getUserId());
				dto.setCreationBy(shiroUser.getUserId());
				dto.setLastUpdateBy(shiroUser.getUserId());
				operateLogService.addOperateLog(dto);
				for (String key : updateMap.keySet()) {
					if (updateMap.get(key) == null) {
						updateMap.put(key, "");
					}
					if (orimap.get(key) == null) {
						orimap.put(key, "");
					}
					if ("userType".equals(key) || "personId".equals(key) || "custAccountId".equals(key) || "storeCode".equals(key) || "beginDate".equals(key)
							|| "endDate".equals(key) || "internalUser".equals(key)) {
						if (!updateMap.get(key).equals(orimap.get(key))) {
							OperateLogLineDto dto1 = new OperateLogLineDto();
							dto1.setOperateId(dto.getOperateId());
							dto1.setOriValue(String.valueOf(orimap.get(key)));
							dto1.setUpdatedValue(String.valueOf(updateMap.get(key)));
							dto1.setVariableName(String.valueOf(key));
							dto1.setCreationBy(shiroUser.getUserId());
							dto1.setLastUpdateBy(shiroUser.getUserId());
							operateLogService.addOperateLogLine(dto1);
						}
					}
				}

			}
			RequestContext requestContext = new RequestContext(request);
			redirectAttributes.addFlashAttribute("InfoMessage", requestContext.getMessage(str));

			return "redirect:/gotoUpdateUser.do?userId=" + user.getUserId();

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "更新信息失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoViewUser")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String gotoViewUser(String userId, HttpServletRequest request) {
		try {
			UserDto user = userService.findUserById(userId);

			request.setAttribute("user", user);

			return "jsp/authority/user/user_view";
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}

	/**
	 * 
	 * 跳转到用户日志权限分配
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoUserDiaryAuthority")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "user:diaryAuthority")
	public String gotoUserDiaryAuthority(String userId, HttpServletRequest request) {
		try {
			UserDto user = userService.findUserById(userId);

			// 获取当前登录的用户
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
			BaseGuestLimit limit = new BaseGuestLimit();
			limit.setUser_id(userId);
			limit.setFun_id("1413");
			List<BaseGuestLimit> limitList = baseGuestLimitService.getBaseGuestLimitByUserId(limit);
			List<BaseGuestLimitObj> objList = new ArrayList<BaseGuestLimitObj>();
			if (limitList != null && limitList.size() > 0) {
				BaseGuestLimit lm = limitList.get(0);
				BaseGuestLimitObj obj = new BaseGuestLimitObj();
				obj.setGuser_limit_id(lm.getGuser_limit_id());
				objList = baseGuestLimitService.getBaseGuestLimitObjByLimitId(obj);
			}
			request.setAttribute("myDiaryList", objList);
			// 部门列表
			String orgId = AuthorityUtils.findPrifileValByResponsibility(request, "ORG_ID", "gotoUserList.do");

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("hrOrganizationId", orgId);
			List<BaseGuestLimit> departmemtList = baseGuestLimitService.getDeparmentList(map);
			request.setAttribute("departmemtList", departmemtList);
			request.setAttribute("user", user);

			return "jsp/authority/user/user_diaryAuthority";

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}

	/**
	 * 
	 * 跳转到用户职责分配
	 * 
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoUserResponsibility")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "user:responsibility")
	public String gotoUserResponsibility(String userId, HttpServletRequest request) {
		try {
			UserDto user = userService.findUserById(userId);

			String orgId = AuthorityUtils.findPrifileValByResponsibility(request, "ORG_ID", "gotoUserList.do");

			List<ResponsibilityDto> responsList = responsibilityService.getSelfAndChildOrgId(orgId);

			request.setAttribute("v_orgId", orgId);//

			ResponsibilityDto responsibilityDto = new ResponsibilityDto();

			responsibilityDto.setOrgId(orgId);

			List<ResponsibilityDto> responsibilitys = responsibilityService.getAllResponsibility(responsibilityDto);

			List<ResponsibilityDto> userResponsibilitys = responsibilityService.findResponsibilityByUserId(userId);
			if (responsList != null && responsList.size() > 0) {
				for (ResponsibilityDto dto : responsList) {
					for (ResponsibilityDto dto1 : userResponsibilitys) {
						if (dto.getOrgId().equals(dto1.getOrgId())) {
							dto1.setIsDisplay("Y");
						}
					}
				}
			}
			request.setAttribute("user", user);

			request.setAttribute("responsibilitys", responsibilitys);

			request.setAttribute("userResponsibilitys", userResponsibilitys);

			return "jsp/authority/user/user_responsibility";

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息：" + e.getMessage());
			return "result";
		}
	}

	/**
	 * 
	 * 保存用户职责
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("saveUserResponsibility")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String saveUserResponsibility(UserDto user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String orgId = AuthorityUtils.findPrifileValByResponsibility(request, "ORG_ID", "gotoUserList.do");
		PersonDto personDto = new PersonDto();
		personDto.setOrgId(orgId);
		personDto.setPersonId(user.getPersonId());
		personDto.setConditionSql(" AND V.ORG_ID=" + orgId);

		/*
		 * List<PersonDto> list = personService.getPersonInBudget(personDto);
		 * if(list !=null && list.size()>0){ PersonDto dto = list.get(0); String
		 * flag = dto.getPrimaryFlag(); if("N".equals(flag)){
		 * request.setAttribute("InfoMessage", "非主职位所在组织，无法修改员工用户职责"); return
		 * "redirect:/gotoUserResponsibility.do?userId=" + user.getUserId(); }
		 * }else{ request.setAttribute("InfoMessage", "非主职位所在组织，无法修改员工用户职责");
		 * return "redirect:/gotoUserResponsibility.do?userId=" +
		 * user.getUserId(); }
		 */

		List<ResponsibilityDto> userResponsibilitys = responsibilityService.findResponsibilityByUserId(user.getUserId());
		String oriResponsibilityId = "";
		for (ResponsibilityDto dto : userResponsibilitys) {
			oriResponsibilityId += dto.getResponsibilityId() + ",";
		}
		Map<String, String> retMap = userService.saveUserResponsibility(user);

		userResponsibilitys = responsibilityService.findResponsibilityByUserId(user.getUserId());
		String updatedResponsibilityId = "";
		for (ResponsibilityDto dto : userResponsibilitys) {
			updatedResponsibilityId += dto.getResponsibilityId() + ",";
		}
		UserDto oriUser = userService.findUserById(user.getUserId());
		// 获取当前登录的用户
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		OperateLogDto dto = new OperateLogDto();
		dto.setOperateUserId(shiroUser.getUserId());
		dto.setLogType("2");
		dto.setOperateType("UPDATE");
		if ("20".equals(oriUser.getUserType())) {
			dto.setOperateMsg("修改员工用户职责:用户ID为" + oriUser.getUserId());
		} else if ("30".equals(oriUser.getUserType())) {
			dto.setOperateMsg("修改经销商用户职责:经销商用户ID为:" + oriUser.getUserId());
		} else if ("40".equals(oriUser.getUserType())) {
			dto.setOperateMsg("修改门店用户职责:门店用户ID为:" + oriUser.getUserId());
		}
		dto.setBeOperateObjId(user.getUserId());
		dto.setCreationBy(shiroUser.getUserId());
		dto.setLastUpdateBy(shiroUser.getUserId());
		operateLogService.addOperateLog(dto);

		OperateLogLineDto dto1 = new OperateLogLineDto();
		dto1.setOperateId(dto.getOperateId());
		dto1.setOriValue(oriResponsibilityId);
		dto1.setUpdatedValue(updatedResponsibilityId);
		dto1.setVariableName("responsibilityIds");
		dto1.setCreationBy(shiroUser.getUserId());
		dto1.setLastUpdateBy(shiroUser.getUserId());
		operateLogService.addOperateLogLine(dto1);

		if (StringUtils.equals(retMap.get("success"), "true")) {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存成功");
		} else {
			redirectAttributes.addFlashAttribute("InfoMessage", "保存失败");
		}

		AuthorityUtils.accreditFlushUserResponsibility(user.getUserId());

		return "redirect:/gotoUserResponsibility.do?userId=" + user.getUserId();

	}

	/**
	 * 
	 * 保存用户职责
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("saveUserAuthority")
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String saveUserAuthority(UserDto user, BaseGuestLimit limit, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		RequestContext requestContext = new RequestContext(request);
		// 获取当前登录的用户
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		// limit.setUser_id(shiroUser.getUserId());
		limit.setFun_id("1413");
		limit.setLast_update_by(shiroUser.getUserId());
		limit.setCreation_by(shiroUser.getUserId());
		String str = baseGuestLimitService.addBaseGuestLimitList(limit);
		redirectAttributes.addFlashAttribute("InfoMessage", requestContext.getMessage(str));
		return "redirect:/gotoUserDiaryAuthority.do?userId=" + limit.getUser_id();

	}

	/**
	 * Ajax请求校验checkUserName是否唯一
	 */
	@RequestMapping(value = "checkUserName")
	@ResponseBody
	@AccreditAnnotation(url = "gotoUserList.do", resourceCode = "")
	public String checkUserName(@RequestParam("userName") String userName) {

		if (userService.findUserByUserName(userName) == null) {
			return "true";
		} else {
			return "false";
		}

	}

}
