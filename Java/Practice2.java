package com.cathaybk.practice.nt50331.b;

import java.util.*;

/**
 * java評量第二題
 */
public class Practice2 {

	public static void main(String[] args) {
		// 將1-49的數字放入numberlist中
		ArrayList<Integer> numberList = new ArrayList<>();
		for (int i = 1; i <= 49; i++) {
			Integer allNumber = i;
			numberList.add(allNumber);
		}
		// 將numberlist的數字隨機排列
		Collections.shuffle(numberList);

		// 取前6個尚未排序的隨機數字，並加入lottoSet中進行排序
		System.out.print("排序前: ");
		Set<Integer> lottoSet = new TreeSet<>();
		for (int i = 1; i <= 6; i++) {
			System.out.print(numberList.get(i) + " ");
			lottoSet.add(numberList.get(i));
		}
		System.out.println();
		System.out.print("排序後: ");
		for (Integer lottoSets : lottoSet) {
			System.out.print(lottoSets + " ");
		}
	}
}
