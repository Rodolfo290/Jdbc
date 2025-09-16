package aplication;

import java.time.LocalDate;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
//
//		Department obj = new Department(1, "Books");
//		System.out.println(obj);
//		
//		Seller seller = new Seller(21, "Bob", "Bob@gmai.com", LocalDate.of(1990, 1, 01), 3000.00, obj);
//		System.out.println(seller);
		
		SellerDao sellerDao = DaoFactory.creatSellerDao();
		
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println();
		
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for (Seller seller2 : list) {
			System.out.println(seller2);
		}
		
		System.out.println();
		
		System.out.println("Test 4: seller insert=====");
		
		Seller newSeller = new Seller(null, "Greg", "Greg@email", LocalDate.of(1995, 1, 01), 4000.00, department);
		sellerDao.insert(newSeller);
		System.out.println("Insert! New id = " + newSeller.getId());
		
		System.out.println();
		
		System.out.println("Test 5 == seller update");
		
		seller  = sellerDao.findById(1);
		seller.setName("Martha");
		sellerDao.update(seller);
		System.out.println("Update completed");
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
}
