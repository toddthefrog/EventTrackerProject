package com.skilldistillery.event.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.event.entities.User;


public interface UserService {
	
	List<User> getAll();
	
	Optional<User> getById(int id);
	
	User create(User user);
	
	Boolean delete(int id);
	
	User update(int id, User user);
	
}
