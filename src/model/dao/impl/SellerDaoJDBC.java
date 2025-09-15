package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? ");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				int departmentId = rs.getInt("DepartmentId");
				String name = rs.getString("DepName");
				Department dep = new Department(id, name);

//				Department dep = new Department();
//				dep.setId(rs.getInt("DepartmentId"));
//				dep.setName(rs.getString("DepName"));
				int id1 = rs.getInt("Id");
				String name1 = rs.getString("Name");
				String email = rs.getString("Email");
				LocalDate birthDate = rs.getDate("BirthDate").toLocalDate();
				double baseSalary = rs.getDouble("BaseSalary");
				Seller obj = new Seller(id, name, email, birthDate, baseSalary, dep);

//				Department dep = new Department();
//				dep.setId(rs.getInt("DepartmentId"));
//				dep.setName(rs.getString("DepName"));
//
//				Seller obj = new Seller();
//				obj.setId(rs.getInt("Id"));
//				obj.setName(rs.getString("Name"));
//				obj.setEmail(rs.getString("Email"));
//				obj.setBirthDate(rs.getDate("BirthDate").toLocalDate());
//				obj.setBaseSalary(rs.getDouble("BaseSalary"));
//				obj.setDepartment(dep);

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
