package rajan.testing.file.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rajan.testing.file.dao.HibernateDAOImpl;
import rajan.testing.file.persistence.DirStructure;
import rajan.testing.file.persistence.FileDetails;

public class DbUpdateServiceImpl {

	HibernateDAOImpl hibObj = new HibernateDAOImpl();
	List<DirStructure> dirTable = new ArrayList<DirStructure>();

	List<FileDetails> fileTable = new ArrayList<FileDetails>();
	HashMap<String, Boolean> subKey = new HashMap<String, Boolean>();
	String PARENT_DIRECTORY;

	public void updateDirStructure(File dirToScan) {
		PARENT_DIRECTORY = dirToScan.getName();
		getDirStructure(dirToScan);

		for (DirStructure d : dirTable) {
			if (dirToScan.getName().equalsIgnoreCase(d.getParentDir())) {
				d.setTopLevel(true);
			}
			//hibObj.updateDb(d);
		}

		for (FileDetails f : fileTable) {
			f.setHasSubtitles(subKey.containsKey(f.getFileName().substring(0,
					f.getFileName().lastIndexOf('.'))));

			//hibObj.updateDb(f);
		}

		hibObj.updateDir(dirTable);
		hibObj.updateFile(fileTable);
	}

	public void getDirStructure(File dirToScan) {

		File[] fileList = dirToScan.listFiles();
		DirStructure dirStruct = null;
		FileDetails fileDetails = null;

		for (File f : fileList) {

			if (f.isDirectory()) {
				dirStruct = new DirStructure();
				dirStruct.setDirName(f.getName());
				dirStruct.setParentDir(f.getParentFile().getName());
				dirTable.add(dirStruct);
				getDirStructure(f);
			}

			if (f.isFile()) {
				if (f.getName().endsWith(".srt")) {
					// System.out.println("It is Subtitle File and won't go in Db");
					subKey.put(
							f.getName().substring(0,
									f.getName().indexOf(".srt")), true);
				} else {
					fileDetails = new FileDetails();
					fileDetails.setFileName(f.getName());
					String parentDir = "/";
					fileDetails.setFileSize((f.length() / 1024 / 1024));

					File temp = f.getParentFile();
					while (true) {

						parentDir = "/" + temp.getName() + parentDir;

						if (temp.getName().equalsIgnoreCase(PARENT_DIRECTORY)) {
							break;
						} else {
							temp = temp.getParentFile();
						}
					}

					
					fileDetails.setParentDir(parentDir);

					fileTable.add(fileDetails);
				}

			}
		}
	}

}
