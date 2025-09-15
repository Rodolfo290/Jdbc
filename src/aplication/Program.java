package aplication;

import java.time.LocalDate;

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
	}

}
