package modal.dao;

import java.util.List;

import model.entiteis.Department;
import model.entiteis.Seller;

public interface SellerDao {

	void insert(Seller obj);
	void uptade(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department department);
}
