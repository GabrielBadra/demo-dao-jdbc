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
			System.out.println("[8] Deletar por ID  '' / ");
			System.out.print("[9] Inserir um Department / ");
			System.out.print("[10] Uptade um Department' / ");
			System.out.println("[11] para sair!");
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
				List<Seller>sellerDepartment = sellerdao.findByDepartment(new Department(id, null));
				System.out.print("DepartmentId: ");
				List<Department> department = departmentDao.findAll();
				for(Department obj: department) {
					System.out.print(obj.getId() +", "+ obj.getName() + " / ");
				}
				System.out.println();
				System.out.print("Digite o ID: ");
				id = sc.nextInt();
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
				department = departmentDao.findAll();
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
			case 6:
				List<Department> departmentCadastrado = departmentDao.findAll();
				System.out.println("Departmentos Cadastrados: ");
				for(Department obj: departmentCadastrado) {
					if(obj.getId() != 0)
					System.out.println(obj.getName() + ", ");
				}
				break;
			case 7:
				System.out.print("Escolha o ID: ");
				id = sc.nextInt();
				Department departmentAux = departmentDao.findById(id);
				System.out.println(departmentAux);
				break;
			case 8:
				department = departmentDao.findAll();
				for(Department obj: department) {
					System.out.print(obj.getId() +", "+ obj.getName() + " / ");
				}
				System.out.println(" ");
				System.out.print("Digite o id do department que quer deletar: ");
				id = sc.nextInt();
				if(id != 0)
				departmentDao.deleteById(id);
				break;
			case 9: 
				department = departmentDao.findAll();
				for(Department obj: department) {
					System.out.print(obj.getId() +", "+ obj.getName() + " / ");
				}
				System.out.print("Escolha o numero do departmento: ");
				id = sc.nextInt();
				
				departmentDao.insert(new Department(id, null));
				break;
			case 10:
				System.out.print("UPDATE seller escolha um id: ");
				id = sc.nextInt();
				departmentAux = departmentDao.findById(id);
				
				System.out.print("New name: ");
				name = sc.next();
				departmentAux.setName(name);
				
				departmentDao.uptade(departmentAux);
			}
			
		} while (res != 11);

		System.out.println("ACABOU!");
		System.out.println(res);
		sc.close();
	}

}
