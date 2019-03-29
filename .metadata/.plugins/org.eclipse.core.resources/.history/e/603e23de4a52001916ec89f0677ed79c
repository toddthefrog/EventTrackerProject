package com.skilldistillery.event.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.event.entities.User;
import com.skilldistillery.event.services.UserService;

@RestController
@RequestMapping("api")
public class UserController {

	// Post Service
	@Autowired
	private UserService us;
	
	@GetMapping(path = "/users/")
	public List<User> index() {
		return us.getAll();
	}
	
	@GetMapping(path = "users/{id}")
	public Optional<User> select(@PathVariable int id) {
		return us.getById(id);
	}
	
	@PostMapping(path = "users/")
	public User create(@RequestBody User user) {
		us.create(user);
		return user;
	}
	
	@PutMapping(path = "users/{id}")
	public User update(@RequestBody User user, @PathVariable int id) {
		return us.update(id, user);
	}
	
	@DeleteMapping(path = "users/{id}")
	public Boolean delete(@PathVariable int id) {
		return us.delete(id);
	}
	
}
