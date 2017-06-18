package com.oddle.app.repository;

import com.oddle.app.config.AppInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tanqu on 6/18/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppInitializer.class)
public class AbstractRepositoryImplTest {

	@Test
	public void get() throws Exception {
	}

	@Test
	public void getAll() throws Exception {
	}

	@Test
	public void saveOrUpdate() throws Exception {
	}

	@Test
	public void delete() throws Exception {
	}

	@Test
	public void deleteByKey() throws Exception {
	}

}