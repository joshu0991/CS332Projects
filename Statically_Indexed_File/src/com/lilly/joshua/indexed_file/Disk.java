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
   }
   public Disk(int sectorCount, int sectorSize)
   {
	   this.sectorCount = sectorCount;
	   this.sectorSize = sectorSize;
   }
   public void readSector(int sectorNumber, char[] buffer)   // sector to buffer
   {
	   
   }                                                        
   
   public void writeSector(int sectorNumber, char[] buffer)  // buffer to sector 
   {
	   
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