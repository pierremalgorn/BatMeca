package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.ITestDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	@Autowired
	private ITestDao testDao;

	@Override
	@Transactional(readOnly = true)
	public List<Test> findAll() {
		return testDao.findAll();
	}

	@Override
	@Transactional
	public void add(Test test) {
		testDao.add(test);
	}

	@Override
	@Transactional(readOnly = true)
	public Test find(int id) {
		return testDao.find(id);
	}

	@Override
	@Transactional
	public void remove(Test test) {
		testDao.remove(test);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Test> findByMaterial(Material mat) {
		return testDao.findByMaterial(mat);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Test> findByUser(User user) {
		return testDao.findByUser(user);
	}
}
