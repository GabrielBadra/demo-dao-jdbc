package application;

import java.util.List;

import modal.dao.DaoFactory;
import modal.dao.SellerDao;
import model.entiteis.Seller;

public class Program {

	public static void main(String[] args) {
	
		SellerDao sellerdao = DaoFactory.createSellerDao();
		Seller seller = sellerdao.findById(15);
		System.out.println(seller);
		List<Seller> list = sellerdao.findAll();
		
		for(Seller obj: list) {
			System.out.println(obj);
		}
	}

}
