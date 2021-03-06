package com.skilldistillery.event.entitytests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.event.entities.User;

public class UserTest {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	User user;
	
	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("EventJPA");
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	@Test
	public void test_prescription_get_data_from_prescription_table() {
		User user = em.find(User.class, 1);
		assertNotNull(user);
		assertEquals(1, user.getId());
		assertEquals("todd", user.getFirstName());
		assertEquals("trowbridge", user.getLastName());
	}
	
	@Test
	public void test_prescription_schedule_map_onetomany_assosiation() {
		User user = em.find(User.class, 1);
		assertNotNull(user);
		assertEquals("pill 1", user.getSchedule().get(0).getPrescriptionName());
		
	}

}
