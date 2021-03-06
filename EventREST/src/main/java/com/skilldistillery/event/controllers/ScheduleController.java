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

import com.skilldistillery.event.entities.Schedule;
import com.skilldistillery.event.entities.User;
import com.skilldistillery.event.services.ScheduleService;
import com.skilldistillery.event.services.UserService;

@RestController
@RequestMapping("api")
public class ScheduleController {

	// Post Service
	@Autowired
	private ScheduleService ss;

	@Autowired
	private UserService us;

	@GetMapping(path = "users/{uid}/schedules")
	public List<Schedule> index(@PathVariable("uid") int uid) {
		Optional<User> optional = us.getById(uid);
		User user;
		List<Schedule> schedules = null;
		if (optional.isPresent()) {
			user = optional.get();
			schedules = user.getSchedule();
			return schedules;
		} else {
			return schedules;
		}
	}

	@GetMapping(path = "users/{uid}/schedules/{sid}")
	public Schedule select(@PathVariable("uid") int uid, @PathVariable("sid") int sid) {
		Optional<User> optional = us.getById(uid);
		User user;
		Schedule schedule = null;
		if (optional.isPresent()) {
			user = optional.get();
			if (user.getSchedule().contains(ss.getScheduleById(sid))) {
				schedule = ss.getScheduleById(sid);
			} else {
				schedule = null;
			}
		}
		return schedule;
	}
	
	@PostMapping(path = "users/{uid}/schedules")
	public Schedule create(@PathVariable("uid") int id, @RequestBody Schedule schedule, HttpServletRequest request, HttpServletResponse response) {
		Optional<User> optional = us.getById(id);
		if (optional.isPresent()) {
			schedule.setUser(optional.get());
			response.setStatus(201);
			ss.create(schedule);
			return schedule;
		} else {
			response.setStatus(400);
			return null;
		}
	}

	@PutMapping(path = "users/{uid}/schedules/{sid}")
	public Schedule update(@PathVariable("uid") int uid, @PathVariable("sid") int sid, @RequestBody Schedule schedule, HttpServletRequest request, HttpServletResponse response) {
		Schedule updatedSchedule = ss.update(uid, sid, schedule);
		if (updatedSchedule != null) {
			response.setStatus(202);
		} else {
			response.setStatus(400);
		}
		return schedule;
	}
	
	@DeleteMapping(path = "users/{uid}/schedules/{sid}")
	public Boolean delete(@PathVariable("sid") int sid, HttpServletRequest request, HttpServletResponse response) {
		Boolean b = ss.delete(sid);
		if (b == true) {
			response.setStatus(202);
		} else {
			response.setStatus(400);
		}
		return b;
	}
	
	

}