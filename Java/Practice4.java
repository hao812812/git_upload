package com.cathaybk.practice.nt50331.b;

import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Practice4 {

	public static void main(String[] args) {

		List<Employee> emloyeeList = new ArrayList<>();

		emloyeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		emloyeeList.add(new Supervisor("李中白", "資訊部", 65000));
		emloyeeList.add(new Supervisor("林小中", "理財部", 80000));
		emloyeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));

		for (Employee employee : emloyeeList) {
			employee.printInfo();
		}

		try (

				// 創建 BufferedWriter
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("C:\\Users\\Admin\\Desktop\\output.csv", true), "UTF-8"));

		) {

			bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));

			for (Employee employee : emloyeeList) {
				if (employee instanceof Supervisor) {
					Supervisor employee1 = (Supervisor) employee;
					String name = employee1.getName();
					int payment = employee1.getPayment();

					bw.write(name + "," + payment);
					bw.newLine();

				} else {
					Sales employee1 = (Sales) employee;
					String name = employee1.getName();
					int payment = employee1.getPayment();

					bw.write(name + "," + payment);
					bw.newLine();
				}
			}
			// 將緩衝資料寫入文件，同時清空緩衝區
			bw.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
