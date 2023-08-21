package modal.dao;

import java.util.List;

import model.entiteis.Department;

public interface DepartmentDao {

	void insert(Department obj);
	void uptade(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();
}
