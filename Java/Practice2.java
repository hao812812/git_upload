package com.cathaybk.practice.nt50331.b;

import java.util.ArrayList;
import java.util.Collections;

public class Practice2 {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<>();

		// 隨機數放入list
		for (int i = 0; i < 6; i++) {
			int number = (int) (Math.random() * 49) + 1;
			//若有相同數字則跳出for
			if (list.contains(number)) {
				continue;
			}
			list.add(number);
		}

		// 印出排序前

		System.out.print("排序前:");

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println();

		// 排序後並印出
		Collections.sort(list);

		System.out.print("排序後:");

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}

	}
}
