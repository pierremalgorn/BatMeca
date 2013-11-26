package dao;

import java.util.List;

import entity.Material;
import entity.Test;
import entity.User;

public interface TestDao {
	public List<Test> findAll();
	public void add(Test test);
	public Test find(int id);
	public void remove(Test test);
	public List<Test> findByMaterial(Material mat);
	public List<Test> findByUser(User user);

}
