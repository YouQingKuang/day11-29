package cn.com.example.service;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.com.example.SpringJdbcApplication;
import cn.com.example.domain.Actor;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringJdbcApplication.class)
public class ActorServiceTest {
	private static final Logger log = LoggerFactory.getLogger(ActorServiceTest.class);

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired 
	ActorService actorService;
	
	@Test
	public void testHello(){
		actorService.hello();
	}
		
	@Test
	public void testInsert() throws ParseException{
/*		actorService.insert("zhang", "san", sdf.parse("2010-1-1"));
		actorService.insert("li", "si", sdf.parse("2011-1-1"));
		actorService.insert("wang", "wu", sdf.parse("2012-1-1"));
		actorService.insert("zhou", "liu", sdf.parse("2011-1-1"));*/
		Integer id = actorService.insert("张华", "邱", new Date());
		log.info("id = {}", id);
		assertNotNull(id);
	}
	
	@Test
	public void testInsertBatch() throws ParseException{
/*		String[] firstNames = {"Yang","SU","LIN"};
		String[] lastNames = {"YI","ER","SAN"};
		Date[] birthes = {sdf.parse("2009-9-9"),
				sdf.parse("2008-8-8"),sdf.parse("2007-7-7")};
		actorService.insertBatch(firstNames, lastNames, birthes);*/
		
		String[] firstNames = {"三", "四"};
		String[] lastNames = {"张", "李"};
		Date[] birthes = {new Date(), new Date()};
		int[] result = actorService.insertBatch(firstNames, lastNames, birthes);
		log.info("insert batch result :");
		for (int i : result) {
			log.info("result = {}", i);
		}
 	}
	
	@Test
	public void testSelect(){
		List<Actor> actors = actorService.select();
		if(actors!=null){
			actors.forEach(actor->log.info(actor.toString()));
		}
	}
	
	@Test
	public void testSelectMore(){
		log.info("more actor = {}", actorService.selectMore());
	}

	@Test
  public void selectSome() {
		log.info("some actor = {}", actorService.selectSome());
	}
}
