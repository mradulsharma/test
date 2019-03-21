package com.madiv.generic.box.factory;

import java.util.ArrayList;
import java.util.List;

import com.madiv.generic.box.out.SenderResponse;
import com.madiv.generic.util.NumberUtil;

public class SenderCommon {
	public SenderResponse getResponse() {
		SenderResponse response = new SenderResponse();
		
		if(NumberUtil.randomWithRange(0, 3) == 2) {
			response.setStatus(false);
			
			List<String> errors = new ArrayList<>();
			errors.add("Error " + NumberUtil.randomWithRange(10, 20));
			errors.add("Error " + NumberUtil.randomWithRange(10, 20));
			errors.add("Error " + NumberUtil.randomWithRange(10, 20));
			
			response.setErrors(errors);
			
		} else {
			response.setStatus(true);
		}
		
		return response;
	}
}
