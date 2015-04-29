package com.lilly.joshua.indexed_file;
//Disk disk, int recordSize, 
//int firstAllocated, int keySize
public class UnitTest {
	private static int numberOfPassingTest = 0;
	private static int numberOfFailingTest = 0;
	private static int testRan = 0;
	public static void main(String[] args){
		Disk disk = new Disk();
		
		//Proves the disk was engineered such that it could be any size.
		Disk disk1 = new Disk(5000, 1024);
		
		IndexedFile indexedFile = null;
		IndexedFile indexedFile1 = null;
		IndexedFile indexedFile2 = null;
		Loader loader = new Loader(disk);
		
		//Proves that the data can be loaded onto a disk of any size and that the record size is variable,
		//in a manner such that the only thing we care about is the key and ensureing we can address all
		//sectors on the genric sized disk.
		Loader loader1 = new Loader(disk, 60, 800, 28);
		Loader loader2 = new Loader(disk1, 58, 1000, 25);
		try {
			//indexed file with conventional arguments.
			indexedFile = new IndexedFile(disk, loader.getRecordSize(),
					loader.getKeySize(), loader.getFirstAllocated(), loader.getIndexStart(),
					loader.getIndexSectors(), loader.getIndexRoot(), loader.getIndexLevels());
			
			//indexed file with non-conventional arguments
			indexedFile1 = new IndexedFile(disk, loader1.getRecordSize(),
					loader1.getKeySize(), loader1.getFirstAllocated(), loader1.getIndexStart(),
					loader1.getIndexSectors(), loader1.getIndexRoot(), loader1.getIndexLevels());
			
			//indexed file with non-conventional arguments
			indexedFile2 = new IndexedFile(disk1, loader2.getRecordSize(),
					loader2.getKeySize(), loader2.getFirstAllocated(), loader2.getIndexStart(),
					loader2.getIndexSectors(), loader2.getIndexRoot(), loader2.getIndexLevels());
			
		} catch (KeyOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//call the unit test with the default set up.
		test_findDataInSingleOverFlow(indexedFile);
		test_findDataInMultipleOverFlows(indexedFile);
		test_findSimpleInsert(indexedFile);
		
		//call the set up with a customized file size.
		test_findDataInSingleOverFlow(indexedFile1);
		test_findDataInMultipleOverFlows(indexedFile1);
		test_findSimpleInsert(indexedFile1);
		
		//call the set up with a customized file size and customized disk.
		test_findDataInSingleOverFlow(indexedFile2);
		test_findDataInMultipleOverFlows(indexedFile2);
		test_findSimpleInsert(indexedFile2);
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Number of test ran: " + testRan);
		System.out.println("Number of passing tests: " + numberOfPassingTest);
		System.out.println("Number of failing tests: " + numberOfFailingTest);
		System.out.println("-------------------------------------------------");
		
	}
	
	//this will only populate a single overflow sector.
	private static void test_findDataInSingleOverFlow(IndexedFile indexedFile){
		++testRan;
		boolean passed = true;
		passed = indexedFile.insertRecord("Snargle 1, Mount#United States#237".toCharArray());
		passed = indexedFile.insertRecord("Snargle 2, Mount#Luxembourg#29334".toCharArray());
		passed = indexedFile.insertRecord("Snargle 3, Mount#Thailand#0".toCharArray());
		passed = indexedFile.insertRecord("Snargle 4, Mount#Detroit#200".toCharArray());
		passed = indexedFile.insertRecord("Snargle 5, Mount#Hole in the Ground#-7".toCharArray());
		passed = indexedFile.insertRecord("Snargle 6, Mount#Guinea-Bissau#10110".toCharArray());
		passed = indexedFile.insertRecord("Snargle 7, Mount#Uruguay#888".toCharArray());
		passed = indexedFile.insertRecord("Snargle 8, Mount#Mars#20682".toCharArray());
		passed = indexedFile.insertRecord("Snargle 9, Mount#Canada#3".toCharArray());
		passed = indexedFile.insertRecord("Snargle 10, Mount#Delaware#14010".toCharArray());
		
		passed = indexedFile.findRecord("Snargle 1, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 2, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 3, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 4, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 5, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 6, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 7, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 8, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 9, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 10, Mount".toCharArray());
		if(passed == true){
			System.out.println("----------------------------Passed findDataInSingleOverFlow-----------------------------");
			++numberOfPassingTest;
		} else {
			System.out.println("----------------------------Failed findDataInSingleOverFlow------------------------------");
			++numberOfFailingTest;
		}
	}
	
	//This will populate multiple over flow sectors.
	private static void test_findDataInMultipleOverFlows(IndexedFile indexedFile){
		++testRan;
		boolean passed = true;
		passed = indexedFile.insertRecord("Snargle 1, Mount#United States#237".toCharArray());
		passed = indexedFile.insertRecord("Snargle 2, Mount#Luxembourg#29334".toCharArray());
		passed = indexedFile.insertRecord("Snargle 3, Mount#Thailand#0".toCharArray());
		passed = indexedFile.insertRecord("Snargle 4, Mount#Detroit#200".toCharArray());
		passed = indexedFile.insertRecord("Snargle 5, Mount#Hole in the Ground#-7".toCharArray());
		passed = indexedFile.insertRecord("Snargle 6, Mount#Guinea-Bissau#10110".toCharArray());
		passed = indexedFile.insertRecord("Snargle 7, Mount#Uruguay#888".toCharArray());
		passed = indexedFile.insertRecord("Snargle 8, Mount#Mars#20682".toCharArray());
		passed = indexedFile.insertRecord("Snargle 9, Mount#Canada#3".toCharArray());
		passed = indexedFile.insertRecord("Snargle 10, Mount#D#14011".toCharArray());
		passed = indexedFile.insertRecord("Snargle 11, Mount#De#14012".toCharArray());
		passed = indexedFile.insertRecord("Snargle 12, Mount#Del#14013".toCharArray());
		passed = indexedFile.insertRecord("Snargle 13, Mount#Delaw#14014".toCharArray());
		passed = indexedFile.insertRecord("Snargle 14, Mount#Delawa#14015".toCharArray());
		passed = indexedFile.insertRecord("Snargle 15, Mount#Delawar#14016".toCharArray());
		passed = indexedFile.insertRecord("Snargle 16, Mount#Delawarerer#14017".toCharArray());
		passed = indexedFile.insertRecord("Snargle 17, Mount#Delawarey#14018".toCharArray());
		passed = indexedFile.insertRecord("Snargle 18, Mount#Delawartttt#14019".toCharArray());
		passed = indexedFile.insertRecord("Snargle 19, Mount#Delawareyyyyuuu#14020".toCharArray());
		passed = indexedFile.insertRecord("Snargle 20, Mount#Delawarerrrrrr#14021".toCharArray());
		
		passed = indexedFile.findRecord("Snargle 1, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 2, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 3, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 4, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 5, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 6, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 7, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 8, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 9, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 10, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 11, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 12, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 13, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 14, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 15, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 16, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 17, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 18, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 19, Mount".toCharArray());
		passed = indexedFile.findRecord("Snargle 20, Mount".toCharArray());
		if(passed == true){
			System.out.println("-----------------------Passed findDataInMultipleOverFlow---------------------");
			++numberOfPassingTest;
		} else {
			System.out.println("---------------------Failed findDataInMultipleOverFlow-------------------");
			++numberOfFailingTest;
		}
	}
	
	private static void test_findSimpleInsert(IndexedFile indexedFile){
		boolean passed = true;
		++testRan;
		passed = indexedFile.insertRecord("Mountain Number 3#United States#22237".toCharArray());
		passed = indexedFile.findRecord("Mountain Number 3".toCharArray());
		if(passed == true){
			System.out.println("----------------------Passed findSimpleInsert------------------------");
			++numberOfPassingTest;
		} else {
			System.out.println("----------------------Failed findSimpleInsert-------------------------");
			++numberOfFailingTest;
		}
	}
}
