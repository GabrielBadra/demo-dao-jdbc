package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import modal.dao.DaoFactory;
import modal.dao.DepartmentDao;
import modal.dao.SellerDao;
import model.entiteis.Department;
import model.entiteis.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SellerDao sellerdao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		int res;
		
		do {
			System.out.println("");
			System.out.println("OPÇÕES DE SELLERS");
			System.out.print("[0] todos cadastrados / ");
			System.out.print("[1] filtrar por id / ");
			System.out.println("[2] filtrar por department / ");
			System.out.print("[3] Deletar por ID  'seller' / ");
			System.out.print("[4] Inserir um Seller / ");
			System.out.println("[5] Uptade um Seller' / ");
			System.out.println("OPÇÕES DO DEPARTMENT");
			System.out.print("[6] Departmentos cadastrados / ");
			System.out.print("[7] filtrar por id / ");
			System.out.print("[8] filtrar por department / ");
			System.out.println("[9] Deletar por ID  'seller' / ");
			System.out.print("[10] Inserir um Seller / ");
			System.out.print("[11] Uptade um Seller' / ");
			System.out.println("[12] para sair!");
			System.out.print("Deseja executar algo? (Digite os numeros):  ");
			res = sc.nextInt();
			int id =0;
			switch (res) {

			case 0:
				List<Seller> list = sellerdao.findAll();

				for (Seller obj : list) {
					System.out.println(obj);
				}
				
				break;
			case 1:
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				Seller sellerAux = sellerdao.findById(id);
				System.out.println(sellerAux);

				break;
			case 2:
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				List<Seller>sellerDepartment = sellerdao.findByDepartment(new Department(id, null));
				
				for (Seller obj : sellerDepartment) {
					System.out.println(obj);
				}
				break;
			case 3:
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
				sellerdao.deleteById(id);
				break;
			case 4:
				System.out.println("Inserir: ");
				System.out.print("ID: ");
				id = sc.nextInt();
				System.out.print("Name: ");
				String name = sc.next();
				System.out.print("Email: ");
				String email = sc.next();
				System.out.println("BirthDay: ");
				Date birthDay = new Date();
				System.out.print("BaseSalary: ");
				double baseSalary = sc.nextDouble();
				System.out.println("Department: ");
				List<Department> department = departmentDao.findAll();
				for(Department obj: department) {
					System.out.print(obj.getId() +", "+ obj.getName() + " / ");
				}
				System.out.print("Escolha o id: ");
				int idAux = sc.nextInt();
				
				sellerdao.insert(new Seller(id, name, email, birthDay, baseSalary, new Department(idAux, null)));
				break;
			case 5:
				System.out.print("Escolha o ID: ");
				id = sc.nextInt();
				Seller seller = sellerdao.findById(id);
				System.out.println("[1]Nome, [2]Email, [3]BaseSalary, [4]DepartmentId");
				System.out.print("Escolha um numero: ");
				id = sc.nextInt();
				
				switch(id) {
				case 1:
					System.out.print("Nome: ");
					name = sc.next();
					seller.setName(name);
					sellerdao.uptade(seller);
					break;
				case 2: 
					System.out.print("Email: ");
					email = sc.next();
					seller.setEmail(email);
					sellerdao.uptade(seller);
					break;
				case 3:
					System.out.print("BaseSalary: ");
					baseSalary = sc.nextDouble();
					seller.setBaseSalary(baseSalary);
					sellerdao.uptade(seller);
					break;
				case 4:
					System.out.print("DepartmentId: ");
					department = departmentDao.findAll();
					for(Department obj: department) {
						System.out.print(obj.getId() +", "+ obj.getName() + " / ");
					}
					id = sc.nextInt();
					seller.setDepartment(new Department(id, null));
					sellerdao.uptade(seller);
					break;
				}
			}
			
		} while (res != 12);

		System.out.println("ACABOU!");
		System.out.println(res);
		sc.close();
	}

}
