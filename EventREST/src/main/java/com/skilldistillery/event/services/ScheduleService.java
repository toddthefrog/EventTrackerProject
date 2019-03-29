package com.skilldistillery.event.services;

import com.skilldistillery.event.entities.Schedule;


public interface ScheduleService {
	
	Schedule getScheduleById(int id);
	
	Schedule create(Schedule schedule);
	
	Boolean delete(int id);
	
	Schedule update(int uid, int sid, Schedule schedule);
	
}
