package com.skilldistillery.event.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@GetMapping(path = "/users")
	public List<User> index() {
		return us.getAll();
	}
	
	@GetMapping(path = "users/{id}")
	public Optional<User> select(@PathVariable int id) {
		return us.getById(id);
	}
	
	@PostMapping(path = "users")
	public User create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		User createdUser = us.create(user);
		if (createdUser != null) {
			response.setStatus(201);
		} else {
			response.setStatus(400);
		}
		return user;
	}
	
	@PutMapping(path = "users/{id}")
	public User update(@RequestBody User user, @PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		User updatedUser = us.update(id, user);
		if(updatedUser != null) {
			response.setStatus(202);
		} else {
			updatedUser = null;
			response.setStatus(400);
		}
		return updatedUser;
	}
	
	@DeleteMapping(path = "users/{id}")
	public Boolean delete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		Boolean b = us.delete(id);
		if (b == true) {
			response.setStatus(202);
		} else {
			response.setStatus(400);
		}
		return b;
	}
	
}
