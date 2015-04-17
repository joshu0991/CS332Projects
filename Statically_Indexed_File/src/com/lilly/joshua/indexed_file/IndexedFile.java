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
   public boolean findRecord(char[] record)
   {
	   checkTree(String.valueOf(record));
	   return true;
   }   
   // there is no delete operation
   private int getSector(String key)   // returns sector number indicated by key
   {
	   
	   return 0;
   }
   
   private int checkTree(String key){
	   int pointer = indexRoot;
	   int i = indexLevels;
	   while(i > 0){
		   int nextNode = findNextNode(key, pointer);
		   i--;
	   }
	   return 0;
   }
   
   private int checkOverflow(String key){
	   return 0;
   }
   
   //node - the sector to read. key - what were looking for.
   private int findNextNode(String key, int node){
	   disk.readSector(node, buffer);
	   String[] records = tokanize(buffer);
	   for(int i = 0; i < buffer.length; i++){
		   //the key letter is less than the letter from the buffer.
		   //we want to follow that path.
		  //if (k[i] < buffer[i] ){
			  //get the sector number from this portion of the buffer.
		  //} else if (k[i] > buffer[i]){
			  //key is greater 
		  //} else {
			  //characters are the same.
			 // continue;
		  //}
		  
	   }
	   return 0;
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
}