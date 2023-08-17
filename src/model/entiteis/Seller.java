package model.entiteis;

import java.util.Date;
import java.util.Objects;

public class Seller {
	private Integer id;
	private String name;
	private String email;
	private Date birthDay;
	private double baseSalary;
	
	private Department department;

	public Seller(Integer id, String name, String email, Date birthDay, double baseSalary, Department department) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDay = birthDay;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		return Objects.hash(baseSalary, birthDay, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Double.doubleToLongBits(baseSalary) == Double.doubleToLongBits(other.baseSalary)
				&& Objects.equals(birthDay, other.birthDay) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", birthDay=" + birthDay + ", baseSalary="
				+ baseSalary + "]";
	}
	
	
}
