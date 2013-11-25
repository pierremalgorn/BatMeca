package service.impl;

import java.util.List;

import dao.TestDao;
import dao.manager.DaoManager;
import entity.Material;
import entity.Test;
import service.TestService;

public class TestServiceImpl implements TestService {

	private TestDao testDao;
	
	public TestServiceImpl(){
		testDao = DaoManager.INSTANCE.getTestDao();
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
	public void remove(int id) {
		testDao.remove(id);
		
	}

	@Override
	public List<Test> findByMaterial(Material mat) {
		return testDao.findByMaterial(mat);
	}

	

}
