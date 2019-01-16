package com.biz.cbt.exec;

import com.biz.cbt.service.CBTService;
import com.biz.cbt.vo.CBTVO;

public class CBTExec01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CBTService cs = new CBTService();
		CBTVO vo = new CBTVO();
		//cs.cbtView();
		//cs.cbtMenu();
		//cs.shuffle();
		cs.cbtMenu();
		//cs.findByNum(1);
		cs.viewAllQuestion();
		cs.insert(vo);

	}

}
