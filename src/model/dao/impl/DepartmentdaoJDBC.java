package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentdaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentdaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("""
					INSERT INTO department
					(Name)
					VALUES
					(?)""", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("""
					UPDATE department
					SET Name = ?
							WHERE Id = ? """);

			st.setString(1, obj.getName()); // Primeiro parâmetro é o Name
			st.setInt(2, obj.getId()); // Segundo parâmetro é o Id
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("""
									SELECT *
					FROM department
					WHERE id = ? """);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("""
									SELECT *
					FROM department
					WHERE id = ? """);

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);

				return dep;
			}

			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		int departmentId = rs.getInt("Id");
		String departmentName = rs.getString("Name");

		Department dep = new Department(departmentId, departmentName);
		return dep;

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("""
										SELECT *
					FROM department
					ORDER BY Name""");
			
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<Department>();
			while (rs.next()) {
			    Department dep = new Department(
			        rs.getInt("Id"),
			        rs.getString("Name")
			    );
			    list.add(dep);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

}
