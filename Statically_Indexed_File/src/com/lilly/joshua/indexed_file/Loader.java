package com.lilly.joshua.indexed_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader {

	Disk disk;
	private int totalSectorsUsed = 0;
	private int recordSize;
	private int keySize;
	private int firstAllocated;
	private int indexStart;
	private int indexSectors;
	int indexRoot; 
	int indexLevels;
	public Loader(){}

	
	public Loader(Disk disk, int recordSize, 
			int firstAllocated) throws DiskOverFlowError{
		this.disk = disk;
		this.recordSize = recordSize;
		this.firstAllocated = firstAllocated;
		loadData();
		buildIndex();
	}
	
	public void loadData() throws DiskOverFlowError{
		BufferedReader reader = null;
		 try {
			reader = new BufferedReader(new FileReader("/Users/sputnik-110/Documents/workspace/Statically_Indexed_File/src/com/lilly/joshua/indexed_file/mountainofdata.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String data;
		try {
			//Track the number of files per sector
			int i = 1;
			while((data = reader.readLine()) != null){
				//build a record with recordSize character size.
				char[] record = buildRecord(data);
				//write the records to the next open sector.......
				disk.writeSector(firstAllocated, record);
				totalSectorsUsed++;
				//only write 5 records to a file
				if(i == 5){
					i = 1;
					firstAllocated++;
				} else {
					i++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//loadData
	
	public int getTotalSectorsUsed() {
		return totalSectorsUsed;
	}

	//construct a record by removing unwanted characters and then inserting
	//the data into a recordSize character long record. Truncates the data if it is larger
	//than sizes in the character array.
	private char[] buildRecord(String dataLine){
		//records are always recordSize chars long.
		char[] buf = new char[recordSize];
		int l = 0;
		String[] brokenRecord = breakUpRecord(dataLine);
		//write the key/city to a record file.
		String name = brokenRecord[0];
		for(int i = 0; i < 26; i++){
			if(i < name.length()){
				buf[i] = name.charAt(i);
			} else {
				buf[i] = '\0';
			}
		}
		//write the country
		String country = brokenRecord[1];
		for(int j = 27; j < 55; j++, l++){
			if((l) < country.length()){
				buf[j] = country.charAt(l);
			} else {
				buf[j] = '\0';
			}
		}
		l = 0;
		//write the size
		String size = brokenRecord[2];
		for(int k = 55; k < recordSize; k++, l++){
			if((l) < size.length()){
				buf[k] = size.charAt(l);
			} else {
				buf[k] = '\0';
			}
		}
		return buf;
	}//buildRecord
	
	private String[] breakUpRecord(String line){
		int i = 0, j = 0;
		// array with a location for each of the three data pieces.
		String[] buf = new String[3];

		// find the first two has marks.
		while (j < 2) {
			while (line.charAt(i) != '#') {
				i++;// find the index of the hash
			}
			String temp = line.substring(0, i);// substring up to the hash
			buf[j] = temp;
			line = line.substring(i + 1);// get rid of hash and processed
										 // string.
			j++;
			i = 0;
		}
		// found both hashes, get what ever remains in the
		// string it it the height.
		buf[j] = line.substring(0, line.length());
		return buf;
	}//breakUpRecord
	
	private void buildIndex(){
		
		
		
	}//build index.
	
}//loader.java
