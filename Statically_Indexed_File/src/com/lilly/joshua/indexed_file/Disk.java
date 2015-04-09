package com.lilly.joshua.indexed_file;

public class Disk
{
   private int sectorCount;   // sectors on the disk
   private int sectorSize;    // characters in a sector
   private char[][] store;    // all disk data is stored here
   public Disk()    // for default sectorCount and sectorSize
   {
	   this.sectorCount = 10000;
	   this.sectorSize = 512;
	   store = new char[10000][512];
   }
   public Disk(int sectorCount, int sectorSize)
   {
	   this.sectorCount = sectorCount;
	   this.sectorSize = sectorSize;
	   store = new char[sectorCount][sectorSize];
   }

	public char[] readSector(int sectorNumber, char[] buffer)   // sector to buffer
   {
		//have to read one sector at a time. 
	 for(int i = 0; i < sectorSize; i++){
		 buffer[i] = store[sectorNumber][i];
	 }
	 return buffer;
   }
	
   public void writeSector(int sectorNumber, char[] buffer) throws DiskOverFlowError  // buffer to sector 
   {
	   System.out.println("Writing " + String.valueOf(buffer) + " to sector number " + sectorNumber);
	   //length of the data to write.
	   int length = buffer.length;
	   int currentSectorSize = calculateSectorSize(sectorNumber);
	   
	   //this is a redundant check to make sure the disk user
	   //is checking to make sure the buffer size is large enough
	   //if this fails the record should have been written to 
	   //the overflow buffer.
	   if((sectorNumber - currentSectorSize) > 60){//okay to write in this case.
	   		//add the values to the empty sector.
		   for(int i = currentSectorSize; i <= (length - 1) + currentSectorSize; i++){
			   store[sectorNumber][i] = buffer[(i - currentSectorSize)];
		   }
	   } else {
		   throw new DiskOverFlowError("Tried to write to a full sector."
		   		+ "  Did you mean to write to the overflow buffer?");
		   
	   }
	   char[] tester = new char[sectorSize];
	   for(int test = 0; test < 300; test++){
		   tester[test] = store[sectorNumber][test];
	   }
	   System.out.println("New sector is " + String.valueOf(tester));
   }
   
   private int calculateSectorSize(int sectorNumber){
	   int size = 0;
	   while(size < sectorSize){
		   if(store[sectorNumber][size] != 0){
			   size += 60;
		   } else {
			   break;
		   }
	   }
	   return size;
   }
   
   public int getSectorCount()
   {
      return sectorCount;
   }
   
   public int getSectorSize()
   {
      return sectorSize;
   }
   
}