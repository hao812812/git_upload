package com.cathaybk.practice.nt50331.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Practice6 {

	public static void main(String[] args) {
		try {
			// 第6-1
			InputStreamReader isr = new InputStreamReader(new FileInputStream("C:\\Users\\Admin\\Desktop\\cars.csv"));// 檔案讀取路徑
			BufferedReader reader = new BufferedReader(isr);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("C:\\Users\\Admin\\Desktop\\cars2.csv", true), "UTF-8"));// 檔案匯出

			String line = null;

			List<Map<String, String>> list = new ArrayList<>();
			line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");

				/** 讀取 **/
				String data1 = item[0].trim();// trim()把資料中的空格修掉
				String data2 = item[1].trim();
				String data3 = item[2].trim();
				String data4 = item[3].trim();

				// 存入Map
				Map<String, String> multiMap = new HashMap<>();
				multiMap.put("Manufacturer", data1);
				multiMap.put("Type", data2);

				multiMap.put("Min.Price", data3.toString());
				multiMap.put("Price", data4.toString());

				// 存入Map資料存入list
				list.add(multiMap);

			}

			// 進行price大小的排序
			Collections.sort(list, new Comparator<Map<String, String>>() {// 只用一次
				@Override
				public int compare(Map<String, String> map1, Map<String, String> map2) {

					BigDecimal priceBig = new BigDecimal(map1.get("Price"));
					BigDecimal priceSmall = new BigDecimal(map2.get("Price"));
					return priceSmall.compareTo(priceBig);
				}
			});

			// 因沒有欄位名稱，需寫入欄位名稱到list
			Map<String, String> multiMap = new HashMap<>();
			multiMap.put("Manufacturer", "Manufacturer");
			multiMap.put("Type", "Type");

			multiMap.put("Min.Price", "Min.Price");
			multiMap.put("Price", "Price");
			list.add(0, multiMap);

			// 寫入到cars2
			for (Map<String, String> listCars : list) {
				bw.write(listCars.get("Manufacturer") + "," + listCars.get("Type") + "," + listCars.get("Min.Price")
						+ "," + listCars.get("Price"));
				bw.newLine();
			}

			// 第6-2
			// 製作set，
			System.out.printf("%-14s%-8s%8s%7s", "Manufacturer", "Type", "Min.Price", "Price");
			System.out.println();
			Set<String> ManufacturerSet = new TreeSet<>();
			ManufacturerSet.add("Cadillac");
			ManufacturerSet.add("Chevrolet");
			ManufacturerSet.add("Audi");
			ManufacturerSet.add("BMW");
			ManufacturerSet.add("Acura");
			ManufacturerSet.add("Buick");

			BigDecimal sumPrice = new BigDecimal("0");
			BigDecimal sumMinPrice = new BigDecimal("0");

			for (String Manufacturer : ManufacturerSet) {// 抓set的廠商名

				BigDecimal totalPrice = new BigDecimal("0");
				BigDecimal totalMinPrice = new BigDecimal("0");

				for (Map<String, String> listMan : list) {// 抓list的廠商名

					if (listMan.get("Manufacturer").equals(Manufacturer)) {

						System.out.printf("%-14s%-8s%8s%7s", Manufacturer, listMan.get("Type"),
								listMan.get("Min.Price"), listMan.get("Price"));
						System.out.println();

						totalMinPrice = totalMinPrice.add(new BigDecimal(listMan.get("Min.Price")));
						totalPrice = totalPrice.add(new BigDecimal(listMan.get("Price")));
						sumPrice = sumPrice.add(new BigDecimal(listMan.get("Price")));
						sumMinPrice = sumMinPrice.add(new BigDecimal(listMan.get("Min.Price")));

					}

				}

				System.out.printf("小計:" + "%26s%7s", totalMinPrice.toPlainString(), totalPrice.toPlainString());
				System.out.println();
			}
			System.out.printf("合計" + "%27s%7s", sumMinPrice, sumPrice);

			// 將緩衝資料寫入文件，同時清空緩衝區
			bw.flush();
			bw.close();
			reader.close();

		} catch (Exception e) {// try結束

			e.printStackTrace();
		}
	}

}
