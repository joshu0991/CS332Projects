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
		 System.out.println("first alloced " + loader.getFirstAllocated());
		 System.out.println("Number of levels " + loader.getIndexLevels());
		 System.out.println("Index sectos " + loader.getIndexSectors());
		 System.out.println("Total used " + loader.getTotalSectorsUsed());
		 System.out.println("Index start " + loader.getIndexStart());
		 System.out.println("Root "  + loader.getIndexRoot());
		 
		 //----------------end test code ----------------------------------------------
		 
		 
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
			String input = scanner.next();
			switch(input){
			case "ir":
				//insert record
				indexedFile.insertRecord(input.toCharArray());
				break;
			case "fr":
				//find record
				System.out.println("Enter the key you would like to search for");
				String search = scanner.next();
				indexedFile.findRecord(search.toCharArray());
				break;
			case "q":
				finished = true;
				scanner.close();
				break;
			}
		}
		
	}
}
