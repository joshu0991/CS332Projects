package com.lilly.joshua.indexed_file;

import java.io.IOException;
import java.util.Scanner;

public class DiskWriter {

	public static void main(String[] args) {
		 Loader loader = null;
		 Disk disk = new Disk();
		 try {
			loader = new Loader(disk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 //----------------------------testCODE--------------------------
		 try {
			disk.printEverything();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//----------------------------endtestCODE--------------------------
		 
		 //create a new indexed file. 
		IndexedFile indexedFile = new IndexedFile(disk, loader.getRecordSize(),
				loader.getKeySize(), loader.getFirstAllocated(), loader.getIndexStart(),
				loader.getIndexSectors(), loader.getIndexRoot(), loader.getIndexLevels());
		
		Scanner scanner = new Scanner(System.in);
		boolean finished = false;
		while(!finished){
			System.out.println("Enter operation: ");
			System.out.println("Insert Record [ir] ");
			System.out.println("Find Record [fr] ");
			System.out.println("Quit [q] ");
			System.out.print("-> ");
			String input = scanner.nextLine();
			switch(input){
			case "ir":
				//insert record
				String record = new String();
				System.out.println("Enter the mountain name (must be 27 chars or less) ");
				record += scanner.nextLine() + "#";
				System.out.println("Enter the mountain's country (must be 27 chars or less) ");
				record += scanner.nextLine() + "#";
				System.out.println("Enter the mountain's height (must be 6 chars or less) ");
				record += scanner.nextLine();
				//records are always 60 chars or less. The extra two are for the added hash symbols.
				//which we will use to differentiate inputs.
				System.out.println("Record is " + record);
				if(record.length() <= 62){
					boolean ret = indexedFile.insertRecord(record.toCharArray());
					if(ret == true){
						System.out.println("Successfully insert the record ");
					} else {
						System.out.println("Failed to insert the data. Are you sure the record doesn't already contain the data?");
					}
				} else {
					System.out.println("The input was not the appropriate length try again.");
				}
				break;
			case "fr":
				//find record
				System.out.println("Enter the key you would like to search for");
				String search = scanner.nextLine();
				if(search.length() < loader.getKeySize()){
					indexedFile.findRecord(search.toCharArray());
				} else {
					System.out.println("The key you input is larger than the max expected key for this file. Try"
							+ "checking the input for null characters.");
				}
				break;
			case "q":
				finished = true;
				scanner.close();
				break;
			}
		}
		
	}
}
