package application;

import java.util.Date;

import model.entiteis.Department;
import model.entiteis.Seller;

public class Program {

	public static void main(String[] args) {
	
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(),300.0,new Department(2,"Carlos"));
		System.out.println(seller);
	}

}
