package com.lilly.joshua.indexed_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for loading files and building an index on a disk.
 * @author Joshua Lilly
 */
public class Loader {

	/**
	 * Data members inited with constructor
	 */
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
	private int sectorSize;
	private int countrySize;
	
	/**
	 * Default constructor call use this to set up a default configuration.
	 * @param disk - A disk object with with a given number of sectors and sector size.
	 */
	public Loader(Disk disk){
		this.disk = disk;
		this.recordSize = 60;
		this.firstAllocated = 999;
		this.keySize = 27;
		this.sectorSize = 7;
		this.countrySize = 27;
		this.indexFileSize = (keySize + sectorSize);
		bufferDisk();
	}//loader
	
	/**
	 * Non-default constructor for a custom configuration.
	 * @param disk -  A disk object to represent an arbitrarily size hard drive
	 * @param recordSize - The number of chars in a given data record
	 * @param firstAllocated - The first sector in the disk allocated to hold files.
	 * @param keySize - The size of the key which is used to uniquely identify a record.
	 */
	public Loader(Disk disk, int recordSize, 
			int firstAllocated, int keySize) {
		this.disk = disk;
		this.recordSize = recordSize;
		this.firstAllocated = firstAllocated;
		this.keySize = keySize;
		calculateSize();
		this.indexFileSize = (keySize + sectorSize);
		bufferDisk();
	}//loader
	
	/**
	 * Load the data from a file onto the disk using the input metrics.
	 * @throws KeyOutOfRangeException - Exception if the configuration is not logical
	 */
	private void loadData() throws KeyOutOfRangeException {
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
			int i = 0;
			int location = firstAllocated;
			
			//buffer for storing data.
			char[] buffer = new char[disk.getSectorSize()];
			
			while((data = reader.readLine()) != null){
				//build a record with recordSize character size.
				char[] record = buildRecord(data);
				
				//we will ensure this value is greater than three since it would be pointless
				//to only be able to store 3 records. And we will ensure that i > 1. This 
				//Since storing a single record in a sector is... stupid.
				int totalPossibleRecordsPerSector = (disk.getSectorSize() / recordSize);
				if(totalPossibleRecordsPerSector - 3 <= 0){
					throw new KeyOutOfRangeException("The configuration is not logical. Cannot store less than 3 records");
				}
				if(i == (totalPossibleRecordsPerSector - 3) && i > 1){
					i = 1;
					//write the records to the next open sector when the buffer is full.
					disk.writeSector(location, buffer);
					location++;
					
					//could get this though subtraction but this var makes it clearer.
					totalSectorsUsed++;
					
					//throw the old array on the ground!!!
					buffer = new char[disk.getSectorSize()];
					buffer = buildBuffer(buffer, record, "data");
				//if this isn't the fifth file we will just append the record to the buffer.
				} else {
					buffer = buildBuffer(buffer, record, "data");
					i++;
				}
			}
			//with this design it is likely that the while loop will exit with data in
			//the buffer that still needs to be written. So write it.
			if(buffer[0] != 0){
				++totalSectorsUsed;
				disk.writeSector(location, buffer);
			}
			this.indexStart = firstAllocated + totalSectorsUsed + 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//loadData
	
	
	/**
	 * 
	 * @return countrySize for configuration.
	 */
	public int getCountrySize(){
		return countrySize;
	}
	
	/**
	 * 
	 * @return sectorSize for configuration.
	 */
	public int getSectorSize(){
		return sectorSize;
	}
	
	/**
	 * 
	 * @return total sectors used for configuration.
	 */
	public int getTotalSectorsUsed() {
		return totalSectorsUsed;
	}
	
	/**
	 * 
	 * @return recordSize for configuration.
	 */
	public int getRecordSize() {
		return recordSize;
	}

	/**
	 * 
	 * @return Key size for configuration.
	 */
	public int getKeySize() {
		return keySize;
	}

	/**
	 * 
	 * @return First alloced sector for configuration.
	 */
	public int getFirstAllocated() {
		return firstAllocated;
	}

	/**
	 * 
	 * @return Start of the index for configuration.
	 */
	public int getIndexStart() {
		return indexStart;
	}

	/**
	 * 
	 * @return Index sectors for configuration.
	 */
	public int getIndexSectors() {
		return indexSectors;
	}

	/**
	 * 
	 * @return Where the indexed root is located (last sector) for configuration.
	 */
	public int getIndexRoot() {
		return indexRoot;
	}

	/**
	 * 
	 * @return Number of levels in the tree for configuration.
	 */
	public int getIndexLevels() {
		return indexLevels;
	}
	
	/**
	 * 	construct a record by removing unwanted characters and then inserting
	 *	the data into a recordSize character long record. Truncates the data if it is larger
	 *	than sizes in the character array.
	 * @param dataLine - The line we want to format.
	 * @return A formatted buffer.
	 */
	private char[] buildRecord(String dataLine){
		//records are always recordSize chars long.
		char[] buf = new char[recordSize];
		int l = 0;
		String[] brokenRecord = breakUpRecord(dataLine);
		//write the key/city to a record file.
		String name = brokenRecord[0];
		for(int i = 0; i < keySize; i++){
			if(i < name.length()){
				buf[i] = name.charAt(i);
			} else {
				buf[i] = '\0';
			}
		}
		//write the country
		String country = brokenRecord[1];
		for(int j = keySize; j < countrySize + keySize; j++, l++){
			if((l) < country.length()){
				buf[j] = country.charAt(l);
			} else {
				buf[j] = '\0';
			}
		}
		l = 0;
		//write the size
		String size = brokenRecord[2];
		for(int k = (countrySize + keySize); k < recordSize; k++, l++){
			if((l) < size.length()){
				buf[k] = size.charAt(l);
			} else {
				buf[k] = '\0';
			}
		}
		return buf;
	}//buildRecord
	
	/**
	 * Break up a record into it's separate components.
	 * @param line - The line we want to break up.
	 * @return A string[] consisting of the input data.
	 */
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
	
	/**
	 * Builds the index system for the stored data. Simply loops until we only build
	 * a single node which is the root.
	 */
	private void buildIndex(){
		//inits to the size of the data sectors
		int levelSize = totalSectorsUsed;
		int start = firstAllocated - 1;
		
		//until there is only one "node" continue this code
		while(levelSize != 1){
			
			//build an entire level of "nodes" each level with
			//fewer than the previous.
			int end = start + levelSize;
			levelSize = buildLevel(start + 1, end);
			indexLevels++;
			//new start is end of last section.
			start = end;		
		}
	}//build index.
	
	/**
	 * Buffer disk is a safe way to load the data and build the index.
	 */
	private void bufferDisk(){
		try {
			loadData();
			buildIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//bufferDisk
	
	/**
	 * build a tree level given a begin and end index
	 * loop over all given sectors get the first keys and sectors
	 * store those in another sector.
	 * @param begin - The disk sector to star grabbing data and inserting into the next "node"
	 * @param end - The last sector in a level 
	 * @return The number of "nodes" that was constructed in the level.
	 */
	private int buildLevel(int begin, int end){
		//number of "nodes" this level contains.
		int levelSize = 0;
		//have to read the entire sector at a time.
		char[] readBuffer = new char[disk.getSectorSize()];
		char[] writeBuffer = new char[disk.getSectorSize()];
		int writeLocation = end + 1;
		//we initially have this many bits that we can fill up.
		int remainingBits = disk.getSectorSize();
		char[] indexListing = new char[indexFileSize];
		for(int i = begin; i <= end; i++){
			
			//fill up the "bucket" so we can get the key.
			disk.readSector(i, readBuffer);
			
			//build an indexed record
			indexListing = buildIndexFile(readBuffer, i);
			
			//only write the index listing to the buffer if it has data to be written.
			if(indexListing[0] != '\0'){
			//if remaining bits is greater than indexFileSize write index to the same sector.
			if(remainingBits >=  indexFileSize){
				
				//append the index to the buffer to be rewritten.
				writeBuffer = buildBuffer(writeBuffer, indexListing, "index");
				remainingBits -= indexListing.length;
			//Else we write so reset everything.
			} else {
				totalSectorsUsed++;
				indexSectors++;
				disk.writeSector(writeLocation, writeBuffer);
				writeLocation = indexSectors + indexStart - 1;
				writeBuffer = new char[disk.getSectorSize()];
				writeBuffer = buildBuffer(writeBuffer, indexListing, "index");
				indexListing = new char[indexFileSize];
				remainingBits = (disk.getSectorSize() - indexListing.length);
				levelSize++;
			}
			}
		}
		//Ensure the final buffer got written.
		if(writeBuffer[0] != '\0'){
			totalSectorsUsed++;
			indexSectors++;
			disk.writeSector(writeLocation, writeBuffer);
			levelSize++;
		}
		
		//we have the root and it is equal to where we are writing.
		if(levelSize == 1){
			this.indexRoot = writeLocation;
		}
		return levelSize;
		
	}//buildLevel
	
	/**
	 * Build a file in the format of an index record.
	 * from sector is the sector it was read from
	 * @param buffer - The data read from a given sector.
	 * @param fromSector - The sector where the data was read from.
	 * @return A new index record.
	 */
	private char[] buildIndexFile(char[] buffer, int fromSector){
		int m;
		char[] indexRecord = new char[indexFileSize];
		String sectorInt = String.valueOf(fromSector);
		char[] sectorNumber = new char[sectorSize];
		// Build a corresponding char[].
		for(m = 0; m < sectorSize; m++){
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
				if(m < sectorSize){
				indexRecord[i] = sectorNumber[m];
				m++;
				} else {
					indexRecord[i] = '\0';
				}
			}
		}
		return indexRecord;
	}//buildIndexFile
	
	/**
	 * Call this when reading a file and editing the contents. This can only be 
	 * called when we have previously checked that the buffer has the appropriate space.
	 * type can be data for data building or index for index files.
	 * @param currentBuffer - The buffer that has all of the files except the one we are adding.
	 * @param fileToAdd - to the buffer that will be written to the disk
	 * @param type - can be data for data building data records or index for building index files.
	 * @return A brand new buffer with the appropriate size and the new added file to be written.
	 */
	private char[] buildBuffer(char[] currentBuffer, char[] fileToAdd, String type){
		//start position for new Data.
		if(type.equals("data")){
			int position = calculateSectorSize(currentBuffer, type);
			for(int i = 0; i < fileToAdd.length; i++, position++){
				currentBuffer[position] = fileToAdd[i];
			}
		} else if(type.equals("index")){
			int position = calculateSectorSize(currentBuffer, type);
			for(int i = 0; i < fileToAdd.length; i++, position++){
				currentBuffer[position] = fileToAdd[i];
			}
		//didn't pass in a correct type.
		} else {
			return null;
		}
		return currentBuffer;
	}//buildBuffer
	
	/**
	 * find out how many spaces are occupied on a disk.
	 * @param sector - The sector to calculate how many records it contains.
	 * @param type - Can be data for calculating the size of a data record or index for size of an index file
	 * @return an integer which represents the number of records in a given sector.
	 */
	private int calculateSectorSize(char[] sector, String type){
		   int size = 0;
		   //loop over entire sector
		   if(type.equals("data")){
			   while(size < disk.getSectorSize()){
				   if(sector[size] != 0){
					   size+=recordSize;
				   } else {
					   break;
				   }
		   		}
		   } else if(type.equals("index")){
			   while(size < disk.getSectorSize()){
				   if(sector[size] != 0){
					   size+= indexFileSize;
				  	} else {
					  break;
				  	}
			   	}
		   }
		   return size;
	   }//calculateSectorSize
	
	/**
	 * Calculate the size of some stuff we may not know the size of at instance.
	 */
	private void calculateSize(){
		//number of bits we need to hold the sector. Plus one for null terminator.
		sectorSize = String.valueOf(disk.getSectorCount()).length() + 2;
		countrySize = recordSize - (keySize + sectorSize);
		
	}//calculateSize
	
}//loader.java
