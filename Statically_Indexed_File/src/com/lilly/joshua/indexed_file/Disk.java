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

	public void readSector(int sectorNumber, char[] buffer)   // sector to buffer
   {
	 for(int i = 0; i < sectorSize; i++){
		 buffer[i] = store[sectorNumber][i];
	 }
   }
	
   public void writeSector(int sectorNumber, char[] buffer)  // buffer to sector 
   {
	   int length = buffer.length;
	   
	   //verify we have a size that fits in a sector.
	   if(length < sectorSize){
	   		//empty the sector
	   		clearSector(sectorNumber);
	   		//add the values to the empty sector.
		   for(int i = 0; i <= length - 1; i++){
			   store[sectorNumber][i] = buffer[i];
		   }
	   }
   }
   
   private void clearSector(int sectorNumber){
	   for(int i = 0; i < sectorSize; i++){
		   store[sectorNumber][i] = ' ';
	   }
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