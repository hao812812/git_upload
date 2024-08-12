package com.cathaybk.practice.nt50331.b;

public class Sales extends Employee {

	private int bouns; // 業績獎金
	public int peformance;// 業績
	private int payment;// 薪水

	// 建構子
	public Sales(String name, String department, int payment, int performance) {
		super.setName(name);
		super.setDepartment(department);
		super.setSalary(payment);
		this.bouns = (int) Math.round(performance * 0.05);
		this.payment = payment + bouns;
	}

	@Override
	public void printInfo() {
		System.out.println("薪資單");
		System.out.println("姓名: " + super.getName() + " " + "工作部門: " + super.getDepartment());
		System.out.println("月薪:" + super.getSalary());
		System.out.println("業績獎金: " + bouns);
		System.out.println("總計: " + payment);
	}

	// getter & setter
	public int getBouns() {
		return bouns;
	}

	public void setBouns(int bouns) {
		this.bouns = bouns;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

}
