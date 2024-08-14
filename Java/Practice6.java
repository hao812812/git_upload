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

/**
 * java評量第六題
 */
public class Practice6 {

	public static void main(String[] args) {
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("C:\\Users\\Admin\\Desktop\\cars2.csv", true), "UTF-8")); // 檔案匯出
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream("C:\\Users\\Admin\\Desktop\\cars.csv")); // 檔案讀取路徑
				BufferedReader reader = new BufferedReader(isr);) {

			// 第6-1
			String line = null;

			List<Map<String, String>> carsList = new ArrayList<>();

			// 把cars.csv的 title放進titleArray中
			String[] titleArray = reader.readLine().split(",");

			// 把cars.csv檔案每列資料拉出
			while ((line = reader.readLine()) != null) {

				// 建立Map
				Map<String, String> carsMap = new HashMap<>();

				String item[] = line.split(",");
				// 存入carsMap中
				for (int i = 0; i < titleArray.length; i++) {

					carsMap.put(titleArray[i], item[i].trim());
				}

				// carsMap資料存入list
				carsList.add(carsMap);
			}

			// 進行price大小的排序
			Collections.sort(carsList, new Comparator<Map<String, String>>() {// 只用一次
				@Override
				public int compare(Map<String, String> carsMap, Map<String, String> compareCarsMap) {

					BigDecimal priceBig = new BigDecimal(carsMap.get("Price"));
					BigDecimal priceSmall = new BigDecimal(compareCarsMap.get("Price"));
					return priceSmall.compareTo(priceBig);// 回傳小於0
				}
			});

			// 製作ManufacturerSet，並把carsList中所有製造商名丟入set
			Set<String> ManufacturerSet = new TreeSet<>();
			for (Map<String, String> carsSet : carsList) {
				ManufacturerSet.add(carsSet.get(titleArray[0]));
			}

			// 寫入到cars2
			Map<String, String> multiMap = new HashMap<>();// 寫入title
			multiMap.put("Manufacturer", titleArray[0]);
			multiMap.put("Type", titleArray[1]);
			multiMap.put("Min.Price", titleArray[2]);
			multiMap.put("Price", titleArray[3]);
			carsList.add(0, multiMap);

			for (Map<String, String> listCars : carsList) {
				bw.write(listCars.get("Manufacturer") + "," + listCars.get("Type") + "," + listCars.get("Min.Price")
						+ "," + listCars.get("Price"));
				bw.newLine();
			}

			// 第6-2
			System.out.printf("%-14s%-8s%8s%7s", "Manufacturer", "Type", "Min.Price", "Price");
			System.out.println();
			BigDecimal sumPrice = BigDecimal.ZERO;
			BigDecimal sumMinPrice = BigDecimal.ZERO;

			for (String Manufacturer : ManufacturerSet) {// 抓set的廠商名

				BigDecimal totalPrice = BigDecimal.ZERO;
				BigDecimal totalMinPrice = BigDecimal.ZERO;

				for (Map<String, String> listMan : carsList) {// 抓list的廠商名

					if (listMan.get(titleArray[0]).equals(Manufacturer)) {

						System.out.printf("%-14s%-8s%8s%7s", Manufacturer, listMan.get("Type"),
								listMan.get("Min.Price"), listMan.get("Price"));
						System.out.println();
						// 每個製造商組別的小計
						totalMinPrice = totalMinPrice.add(new BigDecimal(listMan.get("Min.Price")));// totalMinPrice +=
																									// Min.Price(做BigDecimal)
						totalPrice = totalPrice.add(new BigDecimal(listMan.get("Price")));// totalPrice +=
																							// Price(做BigDecimal)
						// 合計
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
