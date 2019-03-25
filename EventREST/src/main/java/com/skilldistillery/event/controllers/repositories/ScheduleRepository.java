package com.skilldistillery.event.controllers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.event.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
	
	Schedule getScheduleById(int id);

}
