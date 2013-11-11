package dao;

import java.util.List;

import entity.SubMaterial;
import entity.Test;

public interface TestDao {
	public List<Test> findAll();
	public void add(Test test);
	public Test find(int id);
	public void remove(int id);
	public List<Test> findBySub(SubMaterial sub);
}
