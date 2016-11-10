package com.rrcp.common.service;

import com.hpw.ssm.entity.User;

import java.util.List;

public interface UserService {

	List<User> getUserList(int offset, int limit);
	 
}
