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
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Admin\\Desktop\\cars2.csv", true), "UTF-8"));// 檔案匯出
			InputStreamReader isr = new InputStreamReader(new FileInputStream("C:\\Users\\Admin\\Desktop\\cars.csv"));// 檔案讀取路徑
			BufferedReader reader = new BufferedReader(isr);
			){
			
			
			// 第6-1
			String line = null;

			List<Map<String, String>> carsList = new ArrayList<>();
			line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");

				
				String manufacturer = item[0].trim();// trim()把資料中的空格修掉
				String type = item[1].trim();
				String minPrice = item[2].trim();
				String price = item[3].trim();

				// 存入Map
				Map<String, String> carsMap = new HashMap<>();
				carsMap.put("Manufacturer", manufacturer);
				carsMap.put("Type", type);

				carsMap.put("Min.Price", minPrice);
				carsMap.put("Price", price);

				// 存入Map資料存入list
				carsList.add(carsMap);

			}

			// 進行price大小的排序
			Collections.sort(carsList, new Comparator<Map<String, String>>() {// 只用一次
				@Override
				public int compare(Map<String, String> carsMap, Map<String, String> compareCarsMap) {

					BigDecimal priceBig = new BigDecimal(carsMap.get("Price"));
					BigDecimal priceSmall = new BigDecimal(compareCarsMap.get("Price"));
					return priceSmall.compareTo(priceBig);
				}
			});
			
			
			// 製作ManufacturerSet，並把carsList中所有製造商名丟入set
			Set<String> ManufacturerSet = new TreeSet<>();
			for (Map<String,String> carsSet : carsList) {
				ManufacturerSet.add(carsSet.get("Manufacturer"));
			}

			// 寫入到cars2
			Map<String, String> multiMap = new HashMap<>();
			multiMap.put("Manufacturer", "Manufacturer");
			multiMap.put("Type", "Type");
			multiMap.put("Min.Price", "Min.Price");
			multiMap.put("Price", "Price");
			carsList.add(0, multiMap);
			
			for (Map<String, String> listCars : carsList) {
				bw.write(listCars.get("Manufacturer") + "," + listCars.get("Type") + "," + listCars.get("Min.Price")
						+ "," + listCars.get("Price"));
				bw.newLine();
			}
			
			// 第6-2
			System.out.printf("%-14s%-8s%8s%7s", "Manufacturer", "Type", "Min.Price", "Price");
			System.out.println();
			BigDecimal sumPrice = new BigDecimal("0");
			BigDecimal sumMinPrice = new BigDecimal("0");

			for (String Manufacturer : ManufacturerSet) {// 抓set的廠商名

				BigDecimal totalPrice = new BigDecimal("0");
				BigDecimal totalMinPrice = new BigDecimal("0");

				for (Map<String, String> listMan : carsList) {// 抓list的廠商名

					if (listMan.get("Manufacturer").equals(Manufacturer)) {

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
