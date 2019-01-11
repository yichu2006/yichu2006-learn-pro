package com.james.cache;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.james.cache.entity.TCountDetail;
import com.james.cache.services.VisitService;

/*
 * AUTHOR james
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SiteVisitNumTest {
	
	@Resource(name="visitServiceImpl")
	private VisitService vs;
	
	private String visitId = "123";
	
	private String key = "redis:count:visit"; 
	//@Test
	public void testAdd(){
		TCountDetail tcd = new TCountDetail();
		tcd.setId(visitId);
		tcd.setIp("127.0.0.1");
		tcd.setOptime(new Date());
		tcd.setUsername("test");
		vs.addTCountDetail(tcd,key);		
	}
	
	@Test
	public void testVisitCount(){
		int count = vs.getVisitNum(key);
		System.out.println("====visit num ========="+count);
	}
}
