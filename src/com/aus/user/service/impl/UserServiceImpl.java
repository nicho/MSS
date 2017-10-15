package com.aus.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.cache.memcached.SpyMemcachedClient;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

import com.alibaba.druid.util.StringUtils;
import com.aus.user.dao.UserMapper;
import com.aus.user.model.User;
import com.aus.user.service.UserService;
import com.aus.user.util.Constant;
import com.aus.user.util.MemcachedObjectType;

public class UserServiceImpl implements UserService {
	private Clock clock = Clock.DEFAULT;
	private UserMapper userMapper;

	@Autowired(required = false)
	private SpyMemcachedClient memcachedClient;

	private final JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User findUserByLoginName(String loginName) {
		return userMapper.findUserByLoginName(loginName);
	}

	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	@Override
	public String registerUser(User user) {
		entryptPassword(user);
		user.setName(user.getLogin_Name());
		user.setRoles("user");
		user.setRegister_Date(clock.getCurrentDate());
		if (userMapper.registerUser(user) == 1) {
			return "添加成功";
		}
		return "添加失败";
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(Constant.SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, Constant.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Override
	public User findUserById(String id) {
		if (memcachedClient != null) {
			return getUserWithMemcached(id);
		} else {
			return userMapper.findUserById(id);
		}
	}

	@Override
	public String updateUserByid(User user) {
		if (!StringUtils.isEmpty(user.getPassword())) {
			entryptPassword(user);
		} else {
			user.setPassword(null);
		}
		if (userMapper.updateUserByid(user) == 1) {
			memcachedClient.delete(MemcachedObjectType.USER.getPrefix() + user.getId());
			return "修改成功";
		}
		return "修改失败";
	}

	/**
	 * 先访问Memcached, 使用JSON字符串存放对象以节约空间.
	 */

	private User getUserWithMemcached(String id) {
		String key = MemcachedObjectType.USER.getPrefix() + id;

		String jsonString = memcachedClient.get(key);

		if (jsonString != null) {
			return jsonMapper.fromJson(jsonString, User.class);
		} else {
			User user = userMapper.findUserById(id);
			if (user != null) {
				jsonString = jsonMapper.toJson(user);
				memcachedClient.set(key, MemcachedObjectType.USER.getExpiredTime(), jsonString);
			}
			return user;
		}
	}

}
