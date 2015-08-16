package rajan.testing.file.main;

import java.io.File;

import rajan.testing.file.service.DbUpdateServiceImpl;

public class TestFileListing {

	public static void main(String[] args) {

		
		File curDir = new File("F:\\Media & Entertainment");
		DbUpdateServiceImpl svcObj = new DbUpdateServiceImpl();
		
		long startTime=System.nanoTime();
		svcObj.updateDirStructure(curDir);
		
		System.out.println("Time Taken is: "+(System.nanoTime()-startTime)/1000000);
		
		
	}

	

	
	
}
