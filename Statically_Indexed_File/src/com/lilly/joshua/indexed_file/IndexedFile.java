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
	   
   }
   public boolean insertRecord(char[] record)
   {
	   return true;
   }   
   public boolean findRecord(char[] record) throws UnsupportedOperationException
   {
	   checkTree(String.valueOf(record));
	   //checkOverflow(String.valueOf(record));
	   return true;
   }   
   // there is no delete operation
   private int getSector(String key)   // returns sector number indicated by key
   {
	   
	   return 0;
   }
   
   private int checkTree(String key) throws UnsupportedOperationException{
	   int pointer = indexRoot;
	   int i = indexLevels;
	   while(i > 0){
		   int nextNode = findNextNode(key, pointer);
		   pointer = nextNode;
		   i--;
	   }
	   return 0;
   }
   
   private int checkOverflow(String key){
	   return 0;
   }
   
   //node - the sector to read. key - what were looking for.
   private int findNextNode(String key, int node) throws UnsupportedOperationException{
	   disk.readSector(node, buffer);
	   String[] records = tokanize(buffer);
	   int recordPos = 0;
	   int i = 0;
	   String sectorNumber = getSectorNumber(records[recordPos]);
	   
	   //if we get to the very last file in the list this will automatically return the 
	   //last sector if we run out of keys to check.
	   while(recordPos < records.length){
		   
		   String rec = records[recordPos];
		   // if there is a character at the location we are still checking the key.
			if (rec.charAt(i) != '\0' && key.charAt(i) != '\0') {
				// the key letter is less than the letter from the buffer.
				// we want to follow that path.
				if (convertToUpper(rec.charAt(i)) < convertToUpper(key.charAt(i))) {
					// get the sector number from this portion of the buffer.
					sectorNumber = getSectorNumber(records[recordPos]);
					i = 0;
					recordPos++;
				} else if (convertToUpper(rec.charAt(i)) > convertToUpper(key.charAt(i))) {
					return Integer.parseInt(sectorNumber);
				} else {
					// characters are the same. 
					i++;
					continue;
				}
			// we have reached the end of a given key. Figure out 
			// which was shorter if neither move on to the next record.
			} else {
			
				if(key.length() < rec.length()){
					return Integer.parseInt(sectorNumber);
				//if the key was longer than the record gp to the right of
				//the record and check that listing.
				} else if (key.length() > rec.length()){
					recordPos++;
				//they are 100% the same. This should never happen if we are careful with inserts.
				} else {
					throw new UnsupportedOperationException();
				}
			}
	   }
	   return Integer.parseInt(sectorNumber);
   }
   
   //get the records and their associated sectors and store then in an array of string
   //for easy pickins.
   private String[] tokanize(char[] sector){
	   int numRecordsInSec = numberOfRecords(sector);
	   String[] records = new String[numRecordsInSec];
	   //records are always 34 in length
	   int recordCounter = 34;
	   int startCounter = 0;
	   //index records will always be 34 chars.
	   for(int i = 0; i < records.length; i++){
		   //get the key with the sector and put in an array
		   String record = new String();
		   for(int j  = startCounter; j < recordCounter; j++){
			   record += sector[j];
		   }
		   startCounter = recordCounter;
		   records[i] = record;
		   recordCounter += 34;
	   }
	   return records;
   }
   
   // count the number of records a given file has.
   private int numberOfRecords(char[] sector){
	   int hops = 0, files = 0;
	   
	 //index records are always 34 chars.
	   while(sector[hops] != '\0'){
		   hops += 34;
		   ++files;
	   }
	   return files;
   }
  
   private String getSectorNumber(String buffer){
	   int i = keySize;
	   String ret = new String();
	   while(buffer.charAt(i) != '\0'){
		   ret += buffer.charAt(i);
		   i++;
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
}