package com.yugesh.healthcare;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.yugesh.healthcare.entity.Specilization;
import com.yugesh.healthcare.repo.SpecilizationRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SpecilizationTest {

	@Autowired
	private SpecilizationRepo repo;

	public void test() {
		Specilization spec = new Specilization(null, "dentist", "DT", "Tooth specilist");
		spec = repo.save(spec);
		assertNotNull(spec.getId(), "object not created");
	}

}
