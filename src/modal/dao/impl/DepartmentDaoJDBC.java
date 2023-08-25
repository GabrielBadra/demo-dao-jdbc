package modal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import modal.dao.DepartmentDao;
import model.entiteis.Department;
import model.entiteis.Seller;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO department "+
					"(Name) "+
					"VALUES "+
					"(?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResulteSet(rs);
			}else {
				throw new DbException("ERROR INESPERADO: ");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void uptade(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE department "
					+"SET Name = ?"
					+"WHERE Id = ?");
			
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
		
	}

	@Override
	public void deleteById(Integer id) {
			PreparedStatement st = null;
			SellerDaoJDBC sellerdao = new SellerDaoJDBC();
			
			try {
				st = conn.prepareStatement(
					"DELETE FROM department WHERE Id = ?");

				st.setInt(1, id);

				List<Seller> list = filterDepartmentId(id);
				
				for(Seller obj: list) {
					sellerdao.uptade(obj);
				}
				
				st.executeUpdate();
				
			}
			catch (SQLException e) {
				throw new DbIntegrityException(e.getMessage());
			} 
			finally {
				DB.closeStatement(st);
			}
		}
	

	public List<Seller> filterDepartmentId(Integer Id){
		List<Seller> list = new ArrayList<>();
		
		SellerDaoJDBC sellerdao = new SellerDaoJDBC();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*" +
					"FROM seller " +
					"WHERE seller.DepartmentId = ? ");
			st.setInt(1, Id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = sellerdao.instantiateDepartment(rs);
				dep.setId(0);
				dep.setName("Não encontrado");
				Seller obj = sellerdao.instantiateSeller(rs, dep);
				obj.setDepartment(dep);
				
				list.add(obj);
			}
			
			return list;
		}catch(SQLException e) {
			throw new DbException("Error" + e.getMessage());
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DB.closeResulteSet(rs);
			DB.closeStatement(st);
		}
	}
	
	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		
		try {
			st = conn.prepareStatement(
					"SELECT *" +
					"FROM department " 
					+"ORDER BY Name");
			rs = st.executeQuery();
			while (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				list.add(obj);
			}
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DB.closeResulteSet(rs);
			DB.closeStatement(st);
		}
		
	}
}
