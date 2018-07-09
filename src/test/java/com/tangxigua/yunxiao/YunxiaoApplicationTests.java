package com.tangxigua.yunxiao;

import com.google.common.base.CaseFormat;
import com.tangxigua.yunxiao.dao.CategoryDao;
import com.tangxigua.yunxiao.dao.UserDao;
import com.tangxigua.yunxiao.model.*;
import com.tangxigua.yunxiao.dao.util.SqlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YunxiaoApplicationTests {

	@Autowired
	private SqlUtil sqlUtil;

	@Autowired
	private UserDao userDao;

	@Autowired
	CategoryDao categoryDao;


	@Test
	public void getInsertValues() {

		System.out.println("String SELECT_FIELDS = \"" + sqlUtil.getSelectFields(OrderItem.class) + "\";");
		System.out.println("String INSERT_FIELDS = \"" + sqlUtil.getInsertFields(OrderItem.class) + "\";");
		System.out.println("String INSERT_VALUES = \"" + sqlUtil.getInsertValues(OrderItem.class) + "\";");
	}

	@Test
	public void test1(){
		System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testData"));//test_data
	}

	@Test
	public void getInsertFields() {
	}

	@Test
	//@Transactional
	public void mybaitsTest(){
		User user = new User();
		user.setUserName("1");
		user.setPassword("3");
		user.setSalt("3");
		//System.out.println(userDao.insert(user));
		//System.out.println(user.getId());
	}

	@Test
	public void baseDaoTest(){
		Category category = new Category();
		category.setName("test");
		category.setDescription("test");
		categoryDao.insert(category);
	}

	@Test
	public void  test(){
		Long test = userDao.test();
		Class c = test.getClass();
		try {
			Field field = c.getField("name");
		}catch (Exception e){

		}
		System.out.println(test);
	}



}
