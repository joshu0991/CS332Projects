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
	   
   }
   public boolean insertRecord(char[] record)
   {
	   return true;
   }   
   public boolean findRecord(char[] record)
   {
	   return true;
   }   
   // there is no delete operation
   private int getSector(String key)   // returns sector number indicated by key
   {
	   return 0;
   }  
   
}