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
   
   public IndexedFile(Disk disk, int recordSize, int keySize,
                      int firstAllocated, int indexStart,
                      int indexSectors, int indexRoot, int indexLevels)
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
	   
   }
   public boolean insertRecord(char[] record)
   {
	   return true;
   }   
   public boolean findRecord(char[] record) throws UnsupportedOperationException
   {
	   boolean found = false;
	   found = checkTree(format(record));
	   if(found == true){
		   System.out.println("Record found: " + data);
	   } else {
		   found = checkOverflow(String.valueOf(record));
	   }
	   return found;
   }   
   
   // there is no delete operation
   private int getSector(String key)   // returns sector number indicated by key
   {
	   
	   return 0;
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
	   }
	   return true;
   }
   
   private boolean checkOverflow(String key){
	   return true;
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
		   int recordCounter = 34;
		   int startCounter = 0;
		   int numRecordsInSec = numberOfRecords(sector, value);
		   records = new char[numRecordsInSec][recordCounter];

		   //index records will always be 34 chars.
		   for(int i = 0; i < records.length; i++){
			   for(int j  = startCounter, k = 0; j < recordCounter - 1; j++, k++){
				   records[i][k] = sector[j];
			   }
			   startCounter = recordCounter;
			   recordCounter += 34;
		   }
	   } else if(value == "Data"){
		   //data records are always 60 chars in length.
		   int recordCounter = 60;
		   int startCounter = 0;
		   int numRecordsInSec = numberOfRecords(sector, value);
		   records = new char[numRecordsInSec][recordCounter];
		   for(int i = 0; i < records.length; i++){
			   for(int j = startCounter, k = 0; j < recordCounter - 1; j++, k++){
				   records[i][k] = sector[j];
			   }
			   startCounter = recordCounter;
			   recordCounter += 60;
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
	   
		  //index records are always 34 chars.
		  while(sector[hops] != '\0'){
			  hops += 34;
			  ++files;
		  }
	  } else if(value == "Data"){
		  while(sector[hops] != '\0'){
			  hops += 60;
			  ++files;
		  }
	  } else {
		  throw new UnsupportedOperationException();  
	  }
	  return files;
   }
  
   private char[] getSectorNumber(char[] buffer){
	   int i = keySize, ind = 0;
	   char[] ret = new char[7];
	   while(buffer[i] != '\0' && i < 34){
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
	   while(i < key.length){
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
	   //the field will always be 27 chars.
	   for(int i = keySize; i < 54; i++){
		   c += segment[i];
	   }
	   return c;
   }
   
   private String getHeight(char[] segment){
	   String c = new String();
	   //the field will always be 27 chars.
	   for(int i = keySize + 27; i < recordSize - 1; i++){
		   c += segment[i];
	   }
	   return c;
   }
}