package com.cathaybk.practice.nt50331.b;

import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) {

		List<Employee> emloyeeList = new ArrayList<>();
		emloyeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		emloyeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		emloyeeList.add(new Supervisor("李中白", "資訊部", 65000));
		emloyeeList.add(new Supervisor("林小中", "理財部", 80000));

		for (Employee employee : emloyeeList) {
			employee.printInfo();
		}
	}
}
