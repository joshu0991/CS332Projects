package com.lilly.joshua.indexed_file;

import java.util.Scanner;

public class DiskWriter {

	public static void main(String[] args) {
		
		 Disk disk = new Disk();
		 Loader loader = new Loader(disk);
		//IndexedFile if = new IndexedFile(disk, 60, 27, 1000, )
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
				break;
			case "fr":
				//find record
				break;
			case "q":
				finished = true;
				scanner.close();
				break;
			}
		}
		
	}

}
