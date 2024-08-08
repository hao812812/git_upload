package com.cathaybk.practice.nt50331.b;

public class Practice1 {
	public static void main(String[] args) {

		for (int i = 1; i < 10; i++) {

			for (int j = 2; j < 10; j++) {

				int count = j * i;

				System.out.print(j + "*" + i + "= " + count + "\t");
			}
			System.out.println();
		}
	}

}
