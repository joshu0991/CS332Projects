package com.lilly.joshua.indexed_file;

public class IndexedFile
{
   private Disk disk;             // disk on which the file will be written
   private char[] buffer;         // disk buffer
   private int recordSize;        // in characters
   private int keySize;           // in characters
   
   // fields describing data portion of file
   private int recordsPerSector;  // sectorSize/recordSize
   private int firstAllocated;    // sector number where data begins
   private int sectorsAllocated;  // sectors originally allocated for data
   private int overflowStart;     // sector number where overflow begins
   private int overflowSectors;   // count of overflow sectors in use
   
   // fields describing index portion of file
   private int indexStart;        // sector number where index begins
   private int indexSectors;      // number of sectors allocated for index
   private int indexRoot;         // sector number of root of index
   private int indexLevels;       // number of levels of index
   
   private int residingSector;    //if we find a sector with the appropriate data we will
   								  //store it here.
   private String data; 		  //This will store the data we were looking for if found.
   
   private int countrySize;
   private int sectorSize;
   private int indexRecordSize;
   
   public IndexedFile(Disk disk, int recordSize, int keySize,
                      int firstAllocated, int indexStart,
                      int indexSectors, int indexRoot, int indexLevels) throws KeyOutOfRangeException
   {
	   this.disk = disk;
	   this.recordSize = recordSize;
	   this.keySize = keySize;
	   this.firstAllocated = firstAllocated;
	   this.indexStart = indexStart;
	   this.indexRoot = indexRoot;
	   this.indexSectors = indexSectors;
	   this.indexLevels = indexLevels;
	   buffer = new char[disk.getSectorSize()];
	   this.overflowStart = indexRoot + 1;
	   this.residingSector = 0;
	   this.overflowSectors = 1;
	   //We will keep the sector size as the default assuming that size
	   //is large enough to reference all indices on the disk. Otherwise
	   //we will get the size and init to that.
	   if(this.disk.getSectorCount() <= 999999){
	   this.sectorSize = 7;
	   } else {
		   //plus one for the null terminator
		   this.sectorSize = String.valueOf(disk.getSectorCount()).length() + 1;
	   }
	   //since key size and disk size are given by the user the country size is the only
	   //constant. We will however assert that the country size in the record is at least 10
	   //since if we go smaller than that it would be impractical.
	   this.countrySize = recordSize - (sectorSize + keySize);
	   if(countrySize < 10){
		   throw new KeyOutOfRangeException("The configuration you entered wouldn't allow for "
		   		+ "a logically size country to be inserted. To fix either decrease the disk size"
		   		+ "or key size or increase the record size");
	   }
	   indexRecordSize = keySize + sectorSize;
	   
   }
   
   public boolean insertRecord(char[] record)
   {
	   String[] in = tokanizeInput(record);
	   int sector = getSector(in[0]);

	   //the sector will = -1 if the data exist. check to make sure this isn't the case.
	   if(sector == -1){
		   return false;
	   }
	   disk.readSector(sector, buffer);
	   int numRecords = numberOfRecords(buffer, "Data");
	   
	   char[] rec = buildRecord(in);
	   
	   //check to see if the sector is full. If disk is full if the number of records
	   //times the record size + the new record size is greater than the disk sector size.
	   //if this is the case we need to store the new record in an overflow sector.
	   if((numRecords * recordSize) + recordSize > disk.getSectorSize()){
		   disk.readSector(overflowStart + overflowSectors - 1, buffer);
		   numRecords = numberOfRecords(buffer, "Data");
		   //we need to check to see if the current overflow sector is full.
		   //If it is we will just increment the pointer and buffer the next sector.
		   if((numRecords * recordSize) + recordSize > disk.getSectorSize()){
			   ++overflowSectors;
			   disk.readSector(overflowStart + overflowSectors - 1, buffer);
		   }
		   addRecordToBuffer(rec);
		   disk.writeSector(overflowStart + overflowSectors - 1, buffer);
		  //else we will just store the record in the sector that was retrieved.
	   } else {
		   addRecordToBuffer(rec);
		   disk.writeSector(sector, buffer);
		   return true;
	   }
	   return true;
   }   
   
   public boolean findRecord(char[] record) throws UnsupportedOperationException
   {
	   boolean found = false;
	   found = checkTree(format(record));
	   //if not found in the tree check the overflow sectors.
	   if(found == false){
		   found = checkOverflow(format(record));
	   } 
	   if(found == true){
		   System.out.println("Record found: " + data);
	   } else {
		   System.out.println("Record not located.");
	   }
	   return found;
   }   
   
   // there is no delete operation
   private int getSector(String key)   // returns sector number indicated by key
   {
	   boolean exist = findRecord(key.toCharArray());
	   //if the data already exist in the disk we won't insert it again.
	   if(exist == true){
		   residingSector = -1;
	   }
	   return residingSector;
   }
   
   //append the given record to the next available spot in the buffer.
   //This is only invoked after we have guaranteed that there is space in the buffer.
   private void addRecordToBuffer(char[] rec){
	   int i = 0, j = 0;
	   //find next location
	   while(buffer[i] != '\0'){
		   i += recordSize;
	   }
	   //Using the location found above add the record to that location.
	   while(j < rec.length){
		   buffer[i] = rec[j];
		   ++i;
		   ++j;
	   }
   }
   
   private boolean checkTree(char[] key) throws UnsupportedOperationException{
	   int pointer = indexRoot;
	   int i = indexLevels;
	   int nextNode = 0;
	   while(i > 0){
		   nextNode = findNextNode(key, pointer);
		   pointer = nextNode;
		   i--;
	   }
	   boolean found = checkDataSector(key, nextNode);
	   if(found){
		   return true;
	   } else {
		   residingSector = nextNode;
		   return false;
	   }
   }
   
   private boolean checkOverflow(char[] key){
	   int i;
	   //loop over all of the sectors in overflow sectors.
	   for(i = 0; i < overflowSectors; i++){
		   disk.readSector(overflowStart + i, buffer);
		   char[][] tokens = tokanize(buffer, "Data");
		   for(int j = 0; j < tokens.length; j++){
			   char[] token = getKey(tokens[j]);
			   if(String.valueOf(token).equalsIgnoreCase(String.valueOf(key))){
				   this.residingSector = (overflowStart + i);
				   this.data = "Mountain Name: " + String.valueOf(key) + " Country: " + getCountry(tokens[j]) + " Height: "
						   + getHeight(tokens[j]);
				   return true;
			   }
		   }
	   }
	   return false;
   }
   
   //node - the sector to read. key - what were looking for.
   private int findNextNode(char[] key, int node) throws UnsupportedOperationException{
	   disk.readSector(node, buffer);
	   char[][] records = tokanize(buffer, "Index");
	   int recordPos = 0;
	   int i = 0;
	   char[] sectorNumber = getSectorNumber(records[recordPos]);
	   char[] rec = null;
	   char[] recKey = null;
	   
	   //if we get to the very last file in the list this will automatically return the 
	   //last sector if we run out of keys to check.
	   while(recordPos < records.length){
		   //if it is zero we have verified the previous record was too small.
		   //if this isn't the case we want to compare the next chars in the same string
		   if(i == 0){
			   rec = records[recordPos];
			   recKey = getKey(rec);
		   }
		   // if there is a character at the location we are still checking the key
		   
			if (recKey[i] != '\0' && key[i] != '\0') {
				// the key letter is less than the letter from the buffer.
				// we want to follow that path.
				if (convertToUpper(recKey[i]) < convertToUpper(key[i])) {
					// get the sector number from this portion of the buffer.
					sectorNumber = getSectorNumber(rec);
					i = 0;
					recordPos++;
				} else if (convertToUpper(recKey[i]) > convertToUpper(key[i])) {
					return buildInt((sectorNumber));
				} else {
					// characters are the same. 
					i++;
					continue;
				}
			// we have reached the end of a given key. Figure out 
			// which was shorter if neither move on to the next record.
			} else {
				if(key[i] == '\0' && recKey[i] != '\0'){
					return buildInt((sectorNumber));
			
				} else if(key[i] != '\0' && recKey[i] == '\0'){
					return buildInt((sectorNumber));
				} else if (String.valueOf(key).equalsIgnoreCase(String.valueOf(recKey))){
					return buildInt(getSectorNumber(rec));
				} else {
					throw new UnsupportedOperationException();
				} 
			}
	   }
	   return buildInt((sectorNumber));
   }
   
   //get the records and their associated sectors and store then in an array of string
   //for easy pickins.
	private char[][] tokanize(char[] sector, String value){
	   char[][] records = null;
	   if(value == "Index"){
		   int recordCounter = indexRecordSize;
		   int startCounter = 0;
		   int numRecordsInSec = numberOfRecords(sector, value);
		   records = new char[numRecordsInSec][recordCounter];

		   for(int i = 0; i < records.length; i++){
			   for(int j  = startCounter, k = 0; j < recordCounter - 1; j++, k++){
				   records[i][k] = sector[j];
			   }
			   startCounter = recordCounter;
			   recordCounter += indexRecordSize;
		   }
	   } else if(value == "Data"){
		   int recordCounter = recordSize;
		   int startCounter = 0;
		   int numRecordsInSec = numberOfRecords(sector, value);
		   records = new char[numRecordsInSec][recordCounter];
		   for(int i = 0; i < records.length; i++){
			   for(int j = startCounter, k = 0; j < recordCounter - 1; j++, k++){
				   records[i][k] = sector[j];
			   }
			   startCounter = recordCounter;
			   recordCounter += recordSize;
		   }
	   } else {
		throw new UnsupportedOperationException();   
	   }
	   return records;
   }
   
   // count the number of records a given file has.
   private int numberOfRecords(char[] sector, String value){
	   int hops = 0, files = 0;	
	  if(value == "Index"){
	   
		  while(sector[hops] != '\0'){
			  hops += indexRecordSize;
			  ++files;
		  }
	  } else if(value == "Data"){
		  while(sector[hops] != '\0'){
			  hops += recordSize;
			  ++files;
		  }
	  } else {
		  throw new UnsupportedOperationException();  
	  }
	  return files;
   }
  
   private char[] getSectorNumber(char[] buffer){
	   int i = keySize, ind = 0;
	   char[] ret = new char[sectorSize];
	   while(buffer[i] != '\0' && i < indexRecordSize){
		   ret[ind] = buffer[i];
		   i++;
		   ind++;
	   }
	   return ret;
   }
   
   //return the integer representation of an upper case character.
   private int convertToUpper(char ch){
	  int value = Character.valueOf(ch);
	  if(value >= 97 && value <= 122){
		  value -= 32;
	  }
	  return value;
   }
   
   private int buildInt(char[] sec){
	   int i = 0;
	   String a = new String();
	   while(sec[i] != '\0'){
		   a += sec[i];
		   i++;
	   }
	   return Integer.parseInt(a);
   }
   
   private char[] format(char[] key){
	   char[] ret = new char[keySize];
	   int i = 0;
	   while(i < keySize && i < key.length){
		   ret[i] = key[i];
		   i++;
	   }
	   //the unused spaces should be padded out with null chars by default.
	   return ret;			   
   }
   
   //record has sector and key.
   private char[] getKey(char[] record){
	   char[] ret = new char[keySize];
	   for(int i = 0; i < keySize; i++){
		   ret[i] = record[i];
	   }
	   return ret;
   }
   
   private boolean checkDataSector(char[] key, int sectorNumber){
	   //get the probable sector.
	   disk.readSector(sectorNumber, buffer);
	   char[][] tokens = tokanize(buffer, "Data");
	   
	   //loop though the sector searching for the data.
	   for(int i = 0; i < tokens.length; i++){
		   char[] checkKey = getKey(tokens[i]);
		   //if it's found print it, return true, store the sector number for easy retrieval.
		   if(String.valueOf(checkKey).equalsIgnoreCase(String.valueOf(key))){
			   this.residingSector = sectorNumber;
			   this.data = "Mountain Name: " + String.valueOf(key) + " Country: " + getCountry(tokens[i]) + " Height: "
					   + getHeight(tokens[i]);
			   return true;
		   }
		   
	   }
	   //else return false.
	   return false;
   }
   
   private String getCountry(char[] segment){
	   String c = new String();
	   
	   for(int i = keySize; i < (keySize + countrySize); i++){
		   c += segment[i];
	   }
	   return c;
   }
   
   private String getHeight(char[] segment){
	   String c = new String();
	 
	   for(int i = keySize + countrySize; i < recordSize - 1; i++){
		   c += segment[i];
	   }
	   return c;
   }
   
   //break up the initial input and remove #
	private String[] tokanizeInput(char[] input) {
		String[] ret = new String[3];
		int i = 0, j = 0;
		while (i < 3 ) {
			String temp = new String();
			while (input[j] != '#') {
				temp += input[j];
				++j;
				if(j >= input.length){
					ret[i] = temp;
					return ret;
				}
			}
			ret[i] = temp;
			++i;
			++j;
		}
		return ret;
   }
   
	private char[] buildRecord(String[] input){
		char[] record = new char[recordSize];
		int i;
		//add the key. The key can be customized.
		for(i = 0; i < keySize; i++){
			if(input[0].length() > i){
				record[i] = input[0].charAt(i);
			} else {
				record[i] = '\0';
			}
		}
		
		//add the country.
		for(int j = 0; j < countrySize; ++j, ++i){
			if(input[1].length() > j){
				record[i] = input[1].charAt(j);
			} else {
				record[i] = '\0';
			} 
		}
		
		for(int j = 0; j < 6; ++j, ++i){
			if(input[2].length() > j){
				record[i] = input[2].charAt(j);
			} else {
				record[i] = '\0';
			} 
		}
		return record;
	}
}