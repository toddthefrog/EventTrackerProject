package com.skilldistillery.event.controllers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.event.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
