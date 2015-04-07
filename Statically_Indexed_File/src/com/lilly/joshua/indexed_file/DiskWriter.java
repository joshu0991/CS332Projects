package com.lilly.joshua.indexed_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class DiskWriter {

	public static void main(String[] args) {

		BufferedReader reader = null;
		 try {
			reader = new BufferedReader(new FileReader("/Users/sputnik-110/Documents/workspace/Statically_Indexed_File/src/com/lilly/joshua/indexed_file/mountainofdata.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 String a = reader.readLine();
			System.out.println("test " + a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//IndexedFile if = new IndexedFile(new Disk(), 60, 27, 1000, )
		Scanner scanner = new Scanner(System.in);
		boolean finished = false;
		while(!finished){
			System.out.println("Enter operation: ");
			System.out.println("Insert Record [ir] ");
			System.out.println("Find Record [fr] ");
			System.out.println("Quit [q] ");
			String input = scanner.next();
			switch(input){
			case "ir":
				//insert record
				break;
			case "fr":
				//find record
				break;
			case "q":
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				finished = true;
				scanner.close();
				break;
			default:
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				};
				scanner.close();
				break;
			}
		}
		
	}

}
