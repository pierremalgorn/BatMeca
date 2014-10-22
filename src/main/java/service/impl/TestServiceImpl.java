package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TestService;
import dao.TestDao;
import entity.Material;
import entity.Test;
import entity.User;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	
	public TestServiceImpl(){
	}
	
	@Override
	public List<Test> findAll() {
		return testDao.findAll();
	}

	@Override
	public void add(Test test) {
		testDao.add(test);
		
	}

	@Override
	public Test find(int id) {
		return testDao.find(id);
	}

	@Override
	public void remove(Test test) {
		testDao.remove(test);
		
	}

	@Override
	public List<Test> findByMaterial(Material mat) {
		return testDao.findByMaterial(mat);
	}

	@Override
	public List<Test> findByUser(User user) {
		
		return testDao.findByUser(user);
	}

	

}
