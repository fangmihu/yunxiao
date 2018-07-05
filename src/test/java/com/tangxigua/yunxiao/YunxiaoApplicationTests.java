package com.tangxigua.yunxiao;

import com.tangxigua.yunxiao.dao.CategoryDao;
import com.tangxigua.yunxiao.dao.UserDao;
import com.tangxigua.yunxiao.model.Category;
import com.tangxigua.yunxiao.model.Commodity;
import com.tangxigua.yunxiao.model.User;
import com.tangxigua.yunxiao.dao.util.SqlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
		System.out.println(sqlUtil.getInsertValues(Commodity.class));
	}

	@Test
	public void getInsertFields() {
		System.out.println(sqlUtil.getInsertFields(Commodity.class));
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
		categoryDao.save(category);
	}



}
