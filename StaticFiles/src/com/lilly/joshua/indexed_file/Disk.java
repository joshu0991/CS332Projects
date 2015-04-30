package com.lilly.joshua.indexed_file;

/**
 * 
 * @author Joshua Lilly
 * Class for representing a hard drive.
 */
public class Disk
{
	static int counter = 0;
   private int sectorCount;   // sectors on the disk
   private int sectorSize;    // characters in a sector
   private char[][] store;    // all disk data is stored here
   
   /**
    * Default constructor for easy default creation
    */
   public Disk()
   {
	   this.sectorCount = 10000;
	   this.sectorSize = 512;
	   store = new char[10000][512];
   }//Disk()
   
   /**
    * Disk should be able to be any size.
    * @param sectorCount - Number of sectors to create a disk with.
    * @param sectorSize - The size of a sector.
    */
   public Disk(int sectorCount, int sectorSize)
   {
	   this.sectorCount = sectorCount;
	   this.sectorSize = sectorSize;
	   store = new char[sectorCount][sectorSize];
   }//Disk

   /**
    * Read a given data sector from the disk and append it to the buffer.
    * @param sectorNumber - The sector to read data from
    * @param buffer - A reference to the buffer to fill up. Should be created by caller.
    */
	public void readSector(int sectorNumber, char[] buffer)   // sector to buffer
   {
		//have to read one sector at a time. 
	 for(int i = 0; i < sectorSize; i++){

		 buffer[i] = store[sectorNumber][i];
	 }
   }//readSector
	
	/**
	 * Write a given buffer to the disk. Should contain all the data that we want in that buffer.
	 * @param sectorNumber - The sector to write data to
	 * @param buffer - A reference to the buffer to write. Should be created by caller.
	 */
   public void writeSector(int sectorNumber, char[] buffer)  // buffer to sector 
   {
	   //length of the data to write.
	   int length = buffer.length;
	   
	   for(int i = 0; i <= (length - 1); i++){
			   store[sectorNumber][i] = buffer[i];
		}
   }
   
   /**
    * 
    * @return The number of sectors on the disk.
    */
   public int getSectorCount()
   {
      return sectorCount;
   }
   
   /**
    * 
    * @return The size of an individual sector.
    */
   public int getSectorSize()
   {
      return sectorSize;
   }

}