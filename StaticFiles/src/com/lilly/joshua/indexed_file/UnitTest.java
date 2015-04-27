package com.lilly.joshua.indexed_file;

public class UnitTest {
	public static void main(String[] args){
		Disk disk = new Disk();
		IndexedFile indexedFile = null;
		Loader loader = new Loader(disk);
		try {
			indexedFile = new IndexedFile(disk, loader.getRecordSize(),
					loader.getKeySize(), loader.getFirstAllocated(), loader.getIndexStart(),
					loader.getIndexSectors(), loader.getIndexRoot(), loader.getIndexLevels());
		} catch (KeyOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//call the unit test.
		findDataInSingleOverFlow(indexedFile);
		findDataInMultipleOverFlows(indexedFile);
		findSimpleInsert(indexedFile);
		
	}
	
	//this will only populate a single overflow sector.
	private static void findDataInSingleOverFlow(IndexedFile indexedFile){
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
		} else {
			System.out.println("----------------------------Failed findDataInSingleOverFlow------------------------------");
		}
	}
	
	//This will populate multiple over flow sectors.
	private static void findDataInMultipleOverFlows(IndexedFile indexedFile){
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
		} else {
			System.out.println("---------------------Failed findDataInMultipleOverFlow-------------------");
		}
	}
	
	private static void findSimpleInsert(IndexedFile indexedFile){
		boolean passed = true;
		passed = indexedFile.insertRecord("Mountain Number 3#United States#22237".toCharArray());
		passed = indexedFile.findRecord("Mountain Number 3".toCharArray());
		if(passed == true){
			System.out.println("----------------------Passed findSimpleInsert------------------------");
		} else {
			System.out.println("----------------------Failed findSimpleInsert-------------------------");
		}
	}
}
