package com.pp.service;

import com.pp.bo.User;

public interface IUserService {
	public String userRegistration(User user);
	public User findUser(Integer userId);
	
}
