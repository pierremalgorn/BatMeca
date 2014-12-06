package fr.epf.batmeca.service;

import java.io.IOException;
import java.util.List;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.User;

public interface ITestService {
	List<Test> findAll();

	void add(Test test);

	Test find(int id);

	void remove(Test test) throws IOException;

	List<Test> findByMaterial(Material mat);

	List<Test> findByUser(User user);
}
