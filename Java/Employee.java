package com.cathaybk.practice.nt50331.b;

public class Employee implements IWork {

	private String name;//員工名字
	private String department;//員工部門
	private int salary;//員工薪水

	// 建構子
	public Employee() {

	}
	// getter & setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	// PrintInfo
	@Override
	public void printInfo() {

	}

}
