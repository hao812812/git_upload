package com.cathaybk.practice.nt50331.b;

import java.time.LocalDate;
import java.util.*;

public class Practice5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入介於1-12間的整數m:");
		int month = sc.nextInt();

		System.out.println("          2024年" + month + "月");
		System.out.println("   --------------------------");
		System.out.println("   日  一   二   三   四   五   六");
		System.out.println("   ==========================");

		sc.close();

		// 如果月份輸入錯誤，拋出異常1
		if (month < 1 || month > 12)
			throw new IllegalArgumentException("Error month");

		// 每個月的第一天禮拜幾
		LocalDate d2 = LocalDate.of(2024, month, 1); // 或者自定義一個日期
		int firstDays = d2.getDayOfWeek().getValue();// .getValue()轉換為數字

		// 追蹤月份天數
		int monthDays = LocalDate.of(2024, month, 1).lengthOfMonth();

		// 將每個月的天數加入daylist中
		ArrayList<Integer> dayList = new ArrayList<>();
		for (int i = 1; i <= monthDays; i++) {
			dayList.add(i);
		}

		// 每個月開頭的空格數
		for (int i = 0; i < firstDays; i++) {
			System.out.print("  " + "  ");
		}
	
		for (int i = 1; i <= 6; i++) {

			for (int j = 1; j <= 7 - firstDays; j++) {
				System.out.printf("%4d", dayList.get(0));
				dayList.remove(0);
				if (dayList.size() == 0) {// break最後一列多餘天數
					break;
				}
			}
			if (dayList.size() == 0) {// break多餘的列數
				break;
			}
			System.out.println();
			firstDays = 0;
		}
		
	}

}
