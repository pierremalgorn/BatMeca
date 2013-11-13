package service;

import java.util.List;

import entity.SubMaterial;
import entity.Test;

public interface TestService {
	abstract List<Test> findAll();
	abstract void add(Test test);
	abstract Test find(int id);
	abstract void remove(int id);
	abstract List<Test> findBySub(SubMaterial sub);
}