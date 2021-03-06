package com.skilldistillery.event.entitytests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.event.entities.Schedule;

public class ScheduleTest {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	Schedule schedule;
	
	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("EventJPA");
		em = emf.createEntityManager();
		schedule = em.find(Schedule.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	@Test
	public void test_prescription_get_data_from_prescription_table() {
		Schedule schedule = em.find(Schedule.class, 1);
		assertNotNull(schedule);
		assertEquals(1, schedule.getId());
		assertEquals("pill 1", schedule.getPrescriptionName());
		assertEquals(20000, schedule.getPillSizeInUg());
		assertEquals(20000, schedule.getDoseInUg());
		assertEquals(2, schedule.getTimesDaily());
	}

}
