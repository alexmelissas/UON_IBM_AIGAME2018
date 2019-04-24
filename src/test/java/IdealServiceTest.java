import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.Application;
import springboot.domain.Ideal;
import springboot.service.IdealService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class IdealServiceTest {
	@Autowired
	private IdealService idealService;
	private String id = "ideal";
	
	@Before
	public void testAddIdeal() {
		Ideal ideal = new Ideal();
		ideal.setId(id);
		idealService.addIdeal(ideal);
	}
	
	@After
	public void testDeleteIdealById() {
		idealService.deleteIdealById(id);
	}

	@Test
	public void testGetIdeals() {
		List<Ideal> ideals = (List<Ideal>) idealService.getIdeals();
		assertTrue(ideals != null);
	}

	@Test
	public void testGetIdealById() {
		Ideal ideal = idealService.getIdealById(id);
		assertTrue(ideal != null);
	}
}
