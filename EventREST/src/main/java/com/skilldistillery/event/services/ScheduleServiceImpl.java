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
	public Schedule update(int id, Schedule schedule) {
		Schedule managed = repo.getScheduleById(id);
		managed.setId(repo.getScheduleById(id).getId());
		managed.setDoseInUg(schedule.getDoseInUg());
		managed.setIsActive(schedule.getIsActive());
		managed.setPillSizeInUg(schedule.getPillSizeInUg());
		managed.setPrescriptionName(schedule.getPrescriptionName());
		managed.setTimesDaily(schedule.getTimesDaily());
		repo.saveAndFlush(managed);
		return managed;
	}
	
	@Override
	public Boolean delete(int id) {
		Boolean result = false;
		if (repo.existsById(id)) {
			Schedule schedule = repo.getScheduleById(id);
			schedule.setIsActive(false);
			repo.saveAndFlush(schedule);
			result = true;
			return result;
		} else {
			return result;
		}
	}
	
}
