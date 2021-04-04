package br.com.bmo.taskmanager.repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bmo.taskmanager.model.Status;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ActiveProfiles("test")
public class StatusRepositoryTest {

	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void shouldFindStatusByName() {
		String statusName = "Done";
		Status status = statusRepository.findByName(statusName);
		assertNotNull(status);
		assertEquals(statusName, status.getName());
	}
	
	@Test
	public void shouldNotFindUnknownStatusByName() {
		String statusName = "Reopen";
		Status status = statusRepository.findByName(statusName);
		assertNull(status);
	}
}
