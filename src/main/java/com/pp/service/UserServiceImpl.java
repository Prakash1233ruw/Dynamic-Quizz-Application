package com.pp.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pp.bo.User;
import com.pp.dao.UserRepository;



@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repository;
	
	
@Override
public String userRegistration(User user) {
	repository.save(user);
	return "user Registered";
}

    @Override
	public User findUser(Integer userId)  {
		
		Optional<User> optional = repository.findById(userId);
		
		if(optional.isPresent()) {
		return	optional.get();
		 
		}
		return optional.orElse(null);
	}
	
	
}
