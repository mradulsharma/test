package com.madiv.filesystemwatcher;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FolderWatcherClient {
	
	public static void main(String[] args) throws Exception {
		FolderWatcherClient client = new FolderWatcherClient();
		client.watchFolder(args[0]);
		System.out.println("Started watching folder "+args[0]+" !!");
		System.out.println("Press CTRL+C to stop");
		
	}
	
	
	private void watchFolder(String folder_path) throws Exception {
		
		
		File directory = new File(folder_path);
		FileAlterationObserver observer = new FileAlterationObserver(directory);
		observer.addListener(new FileAlterationListener() {
			
			@Override
			public void onStop(FileAlterationObserver arg0) {
				//Every interval when watching is finished, control comes here. In this case default 10 seconds
				
			}
			
			@Override
			public void onStart(FileAlterationObserver arg0) {
				//Every interval when it starts, control comes here. In this case default 10 seconds
				
			}
			
			@Override
			public void onFileDelete(File arg0) {
				System.out.println("File deleted !! ["+arg0+"]");
				
			}
			
			@Override
			public void onFileCreate(File arg0) {
				System.out.println("File created !! ["+arg0+"]");
				
			}
			
			@Override
			public void onFileChange(File arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDirectoryDelete(File arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDirectoryCreate(File arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDirectoryChange(File arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		FileAlterationMonitor monitor = new FileAlterationMonitor(); // If no interval given in constructor, default is 10 seconds.
		monitor.addObserver(observer);
		monitor.start();		
		
		
		
		
	}
	
	
}
