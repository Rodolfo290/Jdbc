package aplication;

import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("1 - Insert:");
		Department department = new Department(null, "T.I");
		departmentDao.insert(department);
		System.out.println("Insert! Novo id: " + department.getId());

		System.out.println();
		System.out.println("2 - update:");
		department = departmentDao.findById(1);
		department.setName("Computers/Notebooks");
		departmentDao.update(department);
		System.out.println("Update completado");

		System.out.println();

		Department departmentFind = departmentDao.findById(4);
		System.out.println(departmentFind);

		System.out.println();
		
		System.out.println("3 - delete:");
		try {
		    departmentDao.deleteById(13);
		    System.out.println("Delete completado!");
		} catch (DbException e) {
		    System.out.println("Erro: " + e.getMessage());
		}
//		
//		int delete = 10;
//		departmentDao.deleteById(delete);
//		System.out.println("Deletado:");

		System.out.println();
		System.out.println("Departamento findAll");
		List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}

	}

}
