package com.lilly.joshua.indexed_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader {

	Disk disk;
	//pointer to the last used sector
	private int totalSectorsUsed = 0;
	private int recordSize;
	private int keySize;
	private int firstAllocated;
	private int indexStart;
	private int indexSectors;
	private int indexRoot; 
	private int indexLevels;
	private int indexFileSize;
	private int levelSize;
	
	//default for basic Loader class.
	public Loader(Disk disk) throws DiskOverFlowError{
		this.disk = disk;
		this.recordSize = 60;
		this.firstAllocated = 999;
		this.keySize = 27;
		this.indexFileSize = (keySize + 7);
		bufferDisk();
	}
	
	//Compliance with the spec that the disk should be customizable.
	public Loader(Disk disk, int recordSize, 
			int firstAllocated, int keySize) throws DiskOverFlowError{
		this.disk = disk;
		this.recordSize = recordSize;
		this.firstAllocated = firstAllocated;
		this.keySize = keySize;
		this.indexFileSize = (keySize + 7);
		bufferDisk();
	}
	
	private void loadData() throws DiskOverFlowError{
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
			int location = firstAllocated;
			while((data = reader.readLine()) != null){
				//build a record with recordSize character size.
				char[] record = buildRecord(data);
				//write the records to the next open sector.
				disk.writeSector(location, record);
				//could get this though subtraction but this var makes it clearer.
				totalSectorsUsed++;
				//only write 5 records to a file
				if(i == 5){
					i = 1;
					location++;
				} else {
					i++;
				}
			}
			this.indexStart = firstAllocated + totalSectorsUsed;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//loadData
	
	//getters
	
	public int getTotalSectorsUsed() {
		return totalSectorsUsed;
	}
	
	public int getRecordSize() {
		return recordSize;
	}

	public int getKeySize() {
		return keySize;
	}

	public int getFirstAllocated() {
		return firstAllocated;
	}

	public int getIndexStart() {
		return indexStart;
	}

	public int getIndexSectors() {
		return indexSectors;
	}

	public int getIndexRoot() {
		return indexRoot;
	}

	public int getIndexLevels() {
		return indexLevels;
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
	
	private void buildIndex() throws DiskOverFlowError{
		//inits to the size of the data sectors
		int levelSize = totalSectorsUsed;
		int start = firstAllocated;
		
		//until there is only one "node" continue this code
		while(levelSize != 1){
			
			//build an entire level of "nodes" each level with
			//fewer than the previous.
			levelSize = buildLevel(start, (start+levelSize));
			indexLevels++;
			start = totalSectorsUsed + levelSize; //----------------------need to check this------------------		
		}
		
		//get the first keySize chars and sector number
		//construct indexFile with key and sector number
		//check to see if current buffer is large enough
		//if large enough write the buffer
		//if not large enough increment sector and write.
		//track the number of indexs written.
		//track the levels of nodes in the "tree"
		
	}//build index.
	
	//Safe wrapper function.
	private void bufferDisk(){
		try {
			loadData();
			buildIndex();
		} catch (DiskOverFlowError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//bufferDisk
	
	//build a tree level given a begin and end index
	//loop over all given sectors get the first keys and sectors
	//store those in another sector.
	private int buildLevel(int begin, int end) throws DiskOverFlowError{
		//number of "nodes" this level contains.
		levelSize = 1;
		//have to read the entire sector at a time.
		char[] buffer = new char[disk.getSectorSize()];
		int writeLocation = indexStart;
		//initially we will automatically take up one sector.
		indexSectors++;
		//we initially have this many bits that we can fill up.
		int remainingBits = disk.getSectorSize();

		for(int i = begin; i <= end; i++){
			
			//fill up the "bucket" so we can get the key.
			disk.readSector(i, buffer);
			
			//build an indexed record
			buffer = buildIndexFile(buffer, i);
			
			//if remaining bits is greater than indexFileSize write index to the same sector.
			if(remainingBits >=  indexFileSize){
				disk.writeSector(writeLocation, buffer);
				remainingBits -= buffer.length;
			} else {
				totalSectorsUsed++;
				writeLocation = indexSectors + indexStart;
				disk.writeSector(writeLocation, buffer);
				//incremented at the beginning so need to use that position.
				indexSectors++;
				levelSize++;
			}
		}
		
		//we have the root and it is equal to where we are writing.
		if(levelSize == 1){
			this.indexRoot = writeLocation;
		}
		return levelSize;
		
	}//buildLevel
	
	//buffer is the data read from a sector
	//from sector is the sector it was read from
	private char[] buildIndexFile(char[] buffer, int fromSector){
		int m;
		char[] indexRecord = new char[indexFileSize];
		String sectorInt = String.valueOf(fromSector);
		char[] sectorNumber = new char[6];
		//sector will always have length 6. Build a corresponding char[].
		for(m = 0; m < 5; m++){
			if(m < sectorInt.length()){
				sectorNumber[m] = sectorInt.charAt(m);
			//out of numbers to write so pad with null
			} else {
				sectorNumber[m] = '\0';
			}
		}
		m = 0;
		//indexFileSize is guaranteed to be larger than the amount of
		//things we are storing in the index file. This essentially 
		//merges two arrays into a single one.
		for(int i = 0; i < indexFileSize; i++){
			//if we are less than the key size
			//we are either copying a key value or
			//a null terminator to the indexFile
			if(i < keySize){
				indexRecord[i] = buffer[i];
			//else we have the entire key filled in. So we need to write
			// the sector value to the last six indices.
			} else {
				indexRecord[i] = sectorNumber[m];
				m++;
			}
		}
		return indexRecord;
	}
	
}//loader.java
