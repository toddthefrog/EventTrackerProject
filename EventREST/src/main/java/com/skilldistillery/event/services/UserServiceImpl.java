package com.skilldistillery.event.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.event.controllers.repositories.UserRepository;
import com.skilldistillery.event.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Override
	public List<User> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<User> getById(int id) {
		Optional<User> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional;
		} else {
			return null;
		}
	}

	@Override
	public User create(User user) {
		return repo.saveAndFlush(user);
	}

	@Override
	public Boolean delete(int id) {
		Boolean result = false;
		Optional<User> optional = repo.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			repo.delete(user);
			result = true;
			return result;
		} else {
			return result;
		}
	}

	@Override
	public User update(int id, User user) {
		Optional<User> optional = repo.findById(id);
		if (optional.isPresent()) {
			User managed = optional.get();
			user.setId(managed.getId());
			repo.saveAndFlush(user);
			return managed;
		} else {
			return null;
		}
		
	}
}
