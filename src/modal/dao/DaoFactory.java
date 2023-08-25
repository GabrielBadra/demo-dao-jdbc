package modal.dao;

import db.DB;
import modal.dao.impl.DepartmentDaoJDBC;
import modal.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	public static DepartmentDaoJDBC createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
