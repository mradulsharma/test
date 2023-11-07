package com.madiv.generic.box.factory;

import com.madiv.generic.box.CSV;
import com.madiv.generic.box.out.SenderResponse;
import com.madiv.generic.box.vo.VO;

public class SenderB extends SenderCommon implements Sender {

	@Override
	public SenderResponse processData(VO box) {
		System.out.println("Class :"+this.getClass().getName());
		
		System.out.println("Processing...");
		System.out.println("Start : Get Boxed Value");
		
		CSV csv = (CSV)box.get();
		
		System.out.println(csv.formateToCSV());
		System.out.println("End : Get Boxed Value");
		
		
		return getResponse();
	}

}
