package com.lilly.joshua.indexed_file;

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
				boolean ins = true;
				String temp = null;
				//insert record
				String record = new String();
				System.out.println("Enter the mountain name (must be 27 chars or less) ");
				record += scanner.nextLine() + "#";
				
				//validate that the key is the appropriate size. will always be 27 or less
				//Plus one because we add a # to divide the string up.
				if(record.length() > loader.getKeySize() + 1){
					ins = false;
				}
				
				System.out.println("Enter the mountain's country (must be 27 chars or less) ");
				temp = scanner.nextLine() + "#";
				
				//the country field can't be changed so we expect the string will be key size + 29
				//since the extra #
				if(temp.length() > 28){
					ins = false;
				} else {
					record += temp;
					temp = null;
				}

				System.out.println("Enter the mountain's height in feet (must be 6 chars or less) ");
				temp = scanner.nextLine();
				
				//We will only except integers here. Make sure the input is a valid int
				try{
					Integer.parseInt(temp);
				} catch(Exception e){
					ins = false;
				}
				if(ins != false){
					record += ins;
				}
				//records are always 60 chars or less. The extra two are for the added hash symbols.
				//which we will use to differentiate inputs.
				if(record.length() <= 62 && ins != false){
						boolean ret = indexedFile.insertRecord(record.toCharArray());
					if(ret == true){
						System.out.println("Successfully inserted the record ");
					} else {
						System.out.println("Failed to insert the data. Are you sure the record doesn't already contain the data?");
					}
				} else {
					System.out.println("The input was not the valid try again.");
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
