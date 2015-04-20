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
				indexedFile.insertRecord(input.toCharArray());
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
