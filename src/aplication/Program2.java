package aplication;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("1 - Insert.");
		Department department = new Department(null, "T.I");
		departmentDao.insert(department);
		System.out.println("Insert! Novo id: " + department.getId());
		
		System.out.println();
		
		department = departmentDao.findById(1);
		department.setName("Computers/Notebooks");
		departmentDao.update(department);
		System.out.println("Update completado");
		
		System.out.println();
		
		Department departmentDa = departmentDao.findById(4);
		System.out.println(departmentDa);
	}

}
