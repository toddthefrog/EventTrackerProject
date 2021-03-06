package com.skilldistillery.event.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.event.controllers.repositories.ScheduleRepository;
import com.skilldistillery.event.entities.Schedule;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository repo;
	
	@Override
	public Schedule getScheduleById(int id) {
		return repo.getScheduleById(id);
	}
	
	@Override
	public Schedule create(Schedule schedule) {
		return repo.saveAndFlush(schedule);
	}

	@Override
	public Schedule update(int uid, int sid, Schedule schedule) {
		Schedule managed = repo.getScheduleById(sid);
		Schedule newSchedule;
		if (managed.getUser().getId() == uid) {
			schedule.setId(managed.getId());
			schedule.setUser(managed.getUser());
			newSchedule = repo.saveAndFlush(schedule);
		} else {
			newSchedule = null;
		}
		return newSchedule;
	}
	
	@Override
	public Boolean delete(int id) {
		Boolean result = false;
		Schedule schedule = repo.getScheduleById(id);
		if (repo.existsById(id)) {
			repo.delete(schedule);
			result = true;
			return result;
		} else {
			return result;
		}
	}
	
}
