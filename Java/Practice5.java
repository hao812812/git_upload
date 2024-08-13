package com.cathaybk.practice.nt50331.b;

import java.time.LocalDate;
import java.util.*;

public class Practice5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入介於1-12間的整數m:");
		int month = sc.nextInt();

		// 使用當前電腦年份
		int year = LocalDate.now().getYear();

		System.out.println("          " + year + "年" + month + "月");
		System.out.println("   --------------------------");
		System.out.println("   日  一   二   三  四   五   六");
		System.out.println("   ==========================");
		sc.close();

		// 如果月份輸入錯誤，拋出異常1
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Error month");
		}

		// 每個月的第一天禮拜幾
		LocalDate firstDayOfMonth = LocalDate.of(year, month, 1); // 或者自定義一個日期
		int firstDays = firstDayOfMonth.getDayOfWeek().getValue();// .getValue()轉換為數字

		// 追蹤月份天數
		int monthDays = firstDayOfMonth.lengthOfMonth();

		// 每個月開頭的空格數,若firstDays==7代表沒有空格
		if (firstDays == 7) {
			firstDays = 0;
		} else {

			for (int i = 0; i < firstDays; i++) {
				System.out.print("    ");
			}
		}

		// 列印整個月份的日期
		for (int i = 1; i <= monthDays; i++) {
			System.out.printf("%4d", i);

			// 按照規律firstdays隨著i加1，當firstdays=6需換行，代表位置為周六，並將firsdays重設為0
			if (firstDays == 6) {
				System.out.println();
				firstDays = 0;

			} else {
				firstDays += 1;
			}
		}

	}

}
