package com.aus.authority.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aus.authority.annotation.AccreditAnnotation;
import com.aus.authority.model.ProfileDto;
import com.aus.authority.model.ProfileTlDto;
import com.aus.authority.service.ProfileService;
import com.aus.authority.service.ShiroDbRealm.ShiroUser;
import com.aus.authority.util.AuthorityUtils;
import com.aus.common.util.PageHelperUtil;
import com.github.pagehelper.PageInfo;

/**
 * Profile管理
 * 
 * @author duzh
 * 
 */
@Controller
public class ProfileController {
	private static Logger logger = Logger.getLogger(ProfileController.class);
	@Autowired
	private ProfileService profileService;

	/**
	 * 跳转到Profile管理界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoProfileList")
	@AccreditAnnotation(url = "gotoProfileList.do", resourceCode = "")
	public String gotoProfileList(HttpServletRequest request) {
		return "jsp/authority/profile/profile_list";
	}

	/**
	 * 查询profile列表
	 * 
	 * @param request
	 * @param userDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewProfileIndex")
	@AccreditAnnotation(url = "gotoProfileList.do", resourceCode = "")
	public String viewProfileIndex(HttpServletRequest request,
			ProfileDto profileDto) {

		try {
			PageHelperUtil.PageHelperStartPage(request, profileDto);
			
			List<ProfileDto> v_profileList = profileService.getAllProfile(profileDto);

			PageInfo<ProfileDto> page = new PageInfo(v_profileList);

			request.setAttribute("page", page);

			request.setAttribute("v_profileList", v_profileList);

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息："
					+ e.getMessage());
			return "result";
		}

		return "jsp/authority/profile/profile_list";
	}

	/**
	 * 跳转到profile新增页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoAddProfile")
	@AccreditAnnotation(url = "gotoProfileList.do", resourceCode = "")
	public String gotoAddProfile(HttpServletRequest request) {
		return "jsp/authority/profile/profile_add";
	}

	/**
	 * 保存新增profile
	 * 
	 * @param request
	 * @param userDto
	 * @return
	 */
	@RequestMapping("saveProfile")
	@AccreditAnnotation(url = "gotoProfileList.do", resourceCode = "")
	public String saveProfile(HttpServletRequest request, ProfileDto profileDto,RedirectAttributes redirectAttributes) {

		try {
			if ( StringUtils.isBlank(profileDto.getProfileCode())|| StringUtils.isBlank(profileDto.getProfileName()) ) {
				request.setAttribute("InfoMessage", "profile名称,profile编码不能为空");
				return this.gotoAddProfile(request);
			}

			if (Boolean.FALSE.equals(profileService.checkSourceSql(profileDto
					.getSourceSql()))) {
				request.setAttribute("InfoMessage", "SQL语句错误");
				return this.gotoAddProfile(request);
			}

			profileService.saveProfile(profileDto);

			List<String> v_language_lit = AuthorityUtils.findSysLanguage();

			for (String v_lang : v_language_lit) {

				ProfileTlDto t1 = new ProfileTlDto();

				BeanUtils.copyProperties(profileDto, t1);

				t1.setLanguage(v_lang);

				profileService.saveProfileInfoByCurLang(t1);

			}

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "保存Profile失败！具体异常信息："
					+ e.getMessage());
			return "result";
		}

		redirectAttributes.addFlashAttribute("InfoMessage", "保存成功");

		return "redirect:/gotoAddProfile.do";
	}

	/**
	 * 跳转到profile修改页面
	 * 
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoUpdateProfile")
	@AccreditAnnotation(url = "gotoProfileList.do", resourceCode = "")
	public String gotoUpdateProfile(String profileId, HttpServletRequest request) {
		try {
			ProfileDto profileDto = profileService.getProfileById(profileId);

			request.setAttribute("profile", profileDto);

			return "jsp/authority/profile/profile_update";
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息："
					+ e.getMessage());
			return "result";
		}
	}

	/**
	 * 修改profile
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateProfile")
	@AccreditAnnotation(url = "gotoProfileList.do", resourceCode = "")
	public String updateProfile(ProfileDto profileDto,
			HttpServletRequest request,RedirectAttributes redirectAttributes) {

		try {
			if ( StringUtils.isBlank(profileDto.getProfileCode())|| StringUtils.isBlank(profileDto.getProfileName()) ) {
				request.setAttribute("InfoMessage", "profile名称,profile编码不能为空");
				return this.gotoUpdateProfile(profileDto.getProfileId(),
						request);
			}

			if (Boolean.FALSE.equals(profileService.checkSourceSql(profileDto
					.getSourceSql()))) {
				request.setAttribute("InfoMessage", "SQL语句错误");
				return "result";
			}

			profileService.updateProfile(profileDto);// 保存Profile

			Subject currentUser = SecurityUtils.getSubject();

			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals()
					.getPrimaryPrincipal();

			ProfileTlDto t1 = new ProfileTlDto();

			BeanUtils.copyProperties(profileDto, t1);

			t1.setLanguage(shiroUser.getLanguage());

			profileService.updateProfileInfoByCurLang(t1);

			redirectAttributes.addFlashAttribute("InfoMessage", "保存成功");
			
			AuthorityUtils.accreditFlush();
			
			return "redirect:/gotoUpdateProfile.do?profileId="+profileDto.getProfileId();

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息："
					+ e.getMessage());
			return "result";
		}

	}

	/**
	 * 跳转到profile查看页面
	 * 
	 * @param tid
	 * @param request
	 * @return
	 */
	@RequestMapping("gotoViewProfile")
	@AccreditAnnotation(url = "gotoProfileList.do", resourceCode = "")
	public String gotoViewProfile(String profileId, HttpServletRequest request) {
		try {
			ProfileDto profileDto = profileService.getProfileById(profileId);

			request.setAttribute("profile", profileDto);

			return "jsp/authority/profile/profile_view";
		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("InfoMessage", "信息载入失败！具体异常信息："
					+ e.getMessage());
			return "result";
		}
	}

}
