package service;

import java.util.List;

import entity.Material;
import entity.Test;
import entity.User;

public interface TestService {
	abstract List<Test> findAll();

	abstract void add(Test test);

	abstract Test find(int id);

	abstract void remove(Test test);

	abstract List<Test> findByMaterial(Material mat);

	abstract List<Test> findByUser(User user);
}
