package com.cathaybk.practice.nt50331.b;

/**
 * java評量第五題之主管資料
 */
public class Supervisor extends Employee {

	private int payment;// 主管 薪水=月薪

	// 建構子
	public Supervisor(String name, String department, int salary) {
		super.setName(name);
		super.setDepartment(department);
		super.setSalary(salary);
		this.payment = super.getSalary();

	}

	@Override
	public void printInfo() {
		System.out.println("薪資單");
		System.out.println("姓名: " + super.getName() + " " + "工作部門: " + super.getDepartment());
		System.out.println("月薪:" + payment);
		System.out.println("總計: " + payment);
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

}
