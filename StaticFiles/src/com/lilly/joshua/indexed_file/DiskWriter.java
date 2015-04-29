package com.lilly.joshua.indexed_file;

import java.util.Scanner;

public class DiskWriter {

	public static void main(String[] args) throws KeyOutOfRangeException {
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
		boolean success = false;
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
				System.out.println("Enter the mountain name ");
				record += scanner.nextLine() + "#";
				
				//validate that the key is the appropriate size. will always be 27 or less
				//Plus one because we add a # to divide the string up.
				if(record.length() > loader.getKeySize() + 1){
					ins = false;
				}
				
				System.out.println("Enter the mountain's country ");
				temp = scanner.nextLine() + "#";
				
				//the country field can't be changed so we expect the string will be key size + 29
				//since the extra #
				if(temp.length() > loader.getCountrySize()){
					ins = false;
				} else {
					record += temp;
					temp = null;
				}

				System.out.println("Enter the mountain's height in feet ");
				temp = scanner.nextLine();
				
				//We will only except integers here. Make sure the input is a valid int
				try{
					Integer.parseInt(temp);
				} catch(Exception e){
					ins = false;
				}
				if(ins != false){
					record += temp;
				}
			    //The extra two are for the added hash symbols.
				//which we will use to differentiate inputs.
				if(record.length() <= loader.getRecordSize() + 2 && ins != false){
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
					success = indexedFile.findRecord(search.toCharArray());
				} else {
					System.out.println("The key you input is larger than the max expected key for this file. Try"
							+ "checking the input for null characters.");
				}
				if(!success){
					System.out.println("Could not find the record ");
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
