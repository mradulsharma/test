package com.madiv.generic.box.factory;

import com.madiv.generic.box.out.SenderResponse;
import com.madiv.generic.box.vo.VO;

public interface Sender {
	public SenderResponse processData(VO box);
}
