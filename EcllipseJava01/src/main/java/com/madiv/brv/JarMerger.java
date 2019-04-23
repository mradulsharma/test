package com.madiv.brv;
//package com.madiv;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.file.Files;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//
//import com.madiv.error.MsgCodeImpl;
//import com.madiv.error.MsgHelper;
//
//public class JarMerger {
//	final static Logger LOG = Logger.getLogger(JarMerger.class);
//	
//	private List<String> sourceFilesModifed = null;
//	private String targetJarFilePath = null;
//	
//	
//	// Constants : Start
//	private static final String FILE_LIST_PATH_IN_RESOURCES = "FileList.txt"; 
//	private static final String JAR_TEMP_WORK_FOLDER = "target"+File.separator+"jarcontents";
//	private static final String BEFORE = "BEFORE";
//	private static final String AFTER = "AFTER";
//	// Constants : End
//
//	public JarMerger() {
//	}
//	
//	
//	
//	public JarMerger(List<String> sourceFilesModifed, String targetJarFilePath) {
//		this.sourceFilesModifed = sourceFilesModifed;
//		this.targetJarFilePath = targetJarFilePath;
//	}
//
//	public void mergeAndPack() throws Exception {
//		preTask();
//
//		performTask();
//
//		postTask();
//	}
//
//	private void postTask() throws IOException {
//		logJarFileDetails(AFTER);
//	}
//
//	private void performTask() throws Exception {
//		// Create temporary folder structure.
//		File tempJarWorkFolder = new File(JAR_TEMP_WORK_FOLDER);
//		tempJarWorkFolder.mkdir();
//		
//		//Iterate of each file list, and create folder structure in jar work folder
//		// and copy compiled java class file.
//		{
//			File classFile = null;
//			File directoryInTempWork = null;
//			try {
//				for (String file : sourceFilesModifed) {
//					// Create folder structure
//					directoryInTempWork = new File(JAR_TEMP_WORK_FOLDER + File.separator + getDirectoryPath(file));
//					directoryInTempWork.mkdirs();
//
//					// Copy class file.
//					String compiledClassPath = file.replace("src", "target" + File.separator + "classes").replace(".java", ".class");
//					classFile = new File(compiledClassPath);
//					FileUtils.copyFileToDirectory(classFile, directoryInTempWork);
//				}
//			} catch (IOException e) {
//				LOG.error(MsgHelper.getMessage(MsgCodeImpl.MADIV_02, classFile.getCanonicalPath(), directoryInTempWork.getAbsolutePath()));
//				throw new Exception(MsgHelper.getMessage(MsgCodeImpl.MADIV_02, classFile.getCanonicalPath(), directoryInTempWork.getAbsolutePath()));
//			}
//
//		}		
//		
//		//Pack the contents of Jar Temp Work folder into JAR file
//		{
//			Runtime r = Runtime.getRuntime();
//			Process p = r.exec("cmd /c jar vfu "+targetJarFilePath+" "+ getFolderToPack(tempJarWorkFolder));
//			p.waitFor();
//			BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String line = "";
//
//			while ((line = b.readLine()) != null) {
//				LOG.info(MsgHelper.getMessage(MsgCodeImpl.MADIV_05,line));
//			}
//
//			b.close();			
//		}
//		
//
//	}
//
//	private String getFolderToPack(File tempJarWorkFolder) {
//		return tempJarWorkFolder.toPath()+File.separator+tempJarWorkFolder.list()[0];
//	}
//
//
//
//	private String getDirectoryPath(String file) {
//		String folderPath = file.substring(file.indexOf("src")+4, file.lastIndexOf(File.separator)); 
//		
//		return folderPath;
//	}
//
//
//
//	private void preTask() throws Exception {
//		try {
//			if (sourceFilesModifed == null) sourceFilesModifed = loadFileList();
//		} catch (IOException e) {
//			LOG.error(MsgHelper.getMessage(MsgCodeImpl.MADIV_06, FILE_LIST_PATH_IN_RESOURCES), e);
//		}
//		
//		if(targetJarFilePath == null) {
//			LOG.error(MsgHelper.getMessage(MsgCodeImpl.MADIV_01));
//			throw new Exception(MsgHelper.getMessage(MsgCodeImpl.MADIV_01));
//		}else if (!(new File(targetJarFilePath).exists())) {
//			LOG.error(MsgHelper.getMessage(MsgCodeImpl.MADIV_07, targetJarFilePath));
//			throw new Exception(MsgHelper.getMessage(MsgCodeImpl.MADIV_07, targetJarFilePath));
//		}else{
//			logJarFileDetails(BEFORE);
//		}
//		
//		//Delete temp jar work folder 
//		File d = new File(JAR_TEMP_WORK_FOLDER);
//		FileUtils.forceDelete(d);
//		
//	}
//
//	private void logJarFileDetails(String executionState) throws IOException {
//		File file = new File(targetJarFilePath);
//		BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
//		LOG.info(MsgHelper.getMessage(MsgCodeImpl.MADIV_03, targetJarFilePath, executionState, attr.lastModifiedTime().toString())); 
//		LOG.info(MsgHelper.getMessage(MsgCodeImpl.MADIV_04, targetJarFilePath, executionState, attr.size()));
//	}
//
//
//
//	private List<String> loadFileList() throws IOException {
//		List<String> retValue = null;
//
//		// Get file from resources folder
//		ClassLoader classLoader = getClass().getClassLoader();
//		String filePath = classLoader.getResource(FILE_LIST_PATH_IN_RESOURCES).getFile();
//		
//		File file = new File(filePath);
//
//		try (Scanner scanner = new Scanner(file)) {
//
//			while (scanner.hasNextLine()) {
//				if (retValue == null) retValue = new ArrayList<String>();
//				String line = scanner.nextLine();
//				retValue.add(line);
//			}
//
//			scanner.close();
//
//		} catch (IOException e) {
//			throw e;
//		}
//
//		return retValue;
//	}
//
//	public List<String> getSourceFilesModifed() {
//		return sourceFilesModifed;
//	}
//
//	public void setSourceFilesModifed(List<String> sourceFilesModifed) {
//		this.sourceFilesModifed = sourceFilesModifed;
//	}
//
//	public String getTargetJarFilePath() {
//		return targetJarFilePath;
//	}
//
//	public void setTargetJarFilePath(String targetJarFilePath) {
//		this.targetJarFilePath = targetJarFilePath;
//	}
//	
//	
//	
//	
//	
//	
//	
//	public static void main(String[] args) throws Exception {
//		JarMerger merger = new JarMerger();
//		merger.setTargetJarFilePath(args[0]);
//		merger.mergeAndPack();
//	}
//	
//}
