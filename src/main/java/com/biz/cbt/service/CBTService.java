package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.biz.cbt.dao.CBTDao;
import com.biz.cbt.db.OracleSqlFactory;
import com.biz.cbt.sql.CBTSQL;
import com.biz.cbt.vo.CBTVO;

public class CBTService {

	SqlSessionFactory sessionFactory;
	Scanner scan;
	List<CBTVO> cbtList;
	private String cbt_quenum;
	

	public CBTService() {
		Properties props = new Properties();
		scan = new Scanner(System.in);

		OracleSqlFactory sqlFactory = new OracleSqlFactory();
		this.sessionFactory = sqlFactory.getSessionFactory();
		cbtList = new ArrayList();
		
	}

	

	public CBTVO findByNum(String cbt_quenum) {
		System.out.println("=========================");
		System.out.print(" 문제 번호 >>");

		// String cbt_quenum = scan.nextLine();

		SqlSession session = this.sessionFactory.openSession();
		CBTDao dao = session.getMapper(CBTDao.class);

		CBTVO vo = dao.findByNum(cbt_quenum);
		// for(CBTVO vo : cbtList) {
		// System.out.println(vo);
		return vo;
	}

	private CBTVO cbtInfoInput() {
		System.out.println("===========================");
		System.out.print(" 문제 입력 >> (0:종료) ");
		System.out.println("---------------------------");

		
		System.out.println("문제를 입력하세요 >> ");
		String cbt_que = scan.nextLine();
		if (cbt_que.equals("0"))
			return null;
		
		System.out.print(" 보기 1번>>");
		String cbt_num1 = scan.nextLine();
		

		System.out.print(" 보기 2번>>");
		String cbt_num2 = scan.nextLine();

		System.out.print(" 보기 3번>>");
		String cbt_num3 = scan.nextLine();

		System.out.print(" 보기 4번 >>");
		String cbt_num4 = scan.nextLine();
		
		System.out.println(" 정답 >> ");
		String cbt_answer = scan.nextLine();

		CBTVO vo = new CBTVO();
		vo.setCbt_question(cbt_question);
		vo.setCbt_num1(cbt_num1);
		vo.setCbt_num2(cbt_num2);
		vo.setCbt_num3(cbt_num3);
		vo.setCbt_num4(cbt_num4);
		vo.setCbt_answer(cbt_answer);

		return vo;

	}

	public void update() {
		CBTVO vo = cbtInfoInput();
		if (vo == null)
			return;

		SqlSession session = this.sessionFactory.openSession();
		CBTDao dao = session.getMapper(CBTDao.class);

		int ret = dao.update(vo);

		session.commit();
		session.close();

		if (ret > 0) {
			System.out.println("추가 성공 !!!");
		} else {
			System.out.println("입력 실패 !!!");
		}
	}

	public int insert(CBTVO vo) {
		SqlSession session = this.sessionFactory.openSession();
		CBTDao dao = session.getMapper(CBTDao.class);

		int ret = dao.insert(vo);

		session.commit();
		session.close();

		System.out.println(ret);
		return ret;
	}
	
	public void delete() {
		SqlSession session = this.sessionFactory.openSession();

		CBTDao dao = session.getMapper(CBTDao.class);
		System.out.println("삭제할 번호 >> ");
		String strNum = scan.nextLine();
		
		if(strNum.equals("")) {
			System.out.println("삭제가 취소되었습니다");
			return;
		}
		int Id = Integer.valueOf(strNum); 
		
		CBTVO vo = dao.findByNum(cbt_quenum);
		System.out.println("============================");
		System.out.println("삭제할 데이터 확인");
		System.out.println("----------------------------");
		System.out.println("문제 " + vo.getCbt_question());
		System.out.println("문제1번 " + vo.getCbt_num1());
		System.out.println("문제2번 " + vo.getCbt_num2());
		System.out.println("문제3번 " + vo.getCbt_num3());
		System.out.println("문제4번 " + vo.getCbt_num4());
		System.out.println(" 정답 " + vo.getCbt_answer());
		String confirm = scan.nextLine();
		if(confirm.equals("YES")) {
		dao.delete(cbt_quenum);
		System.out.println("삭제되었습니다");
	}else {
		System.out.println("취소되었습니다.");
	}
	}
	public void cbtView() {

		SqlSession session = this.sessionFactory.openSession();

		CBTDao dao = session.getMapper(CBTDao.class);

		cbtList = dao.selectAll();

		for (CBTVO vo : cbtList) {
		//	System.out.println(vo);
		}

	}


	// 문제를 쪼개서 추가해주는 메서드
	public void list() {

		SqlSession session = sessionFactory.openSession();
		CBTDao dao = session.getMapper(CBTDao.class);
		cbtList = dao.selectAll();

		for (CBTVO vo : cbtList) {
			String cbtNum = vo.getCbt_quenum();
			String[] cbt = cbtNum.split(",");
			vo.setCbt_quenum(cbt[0]);
			vo.setCbt_question(cbt[1]);
			vo.setCbt_num1(cbt[2]);
			vo.setCbt_num2(cbt[3]);
			vo.setCbt_num3(cbt[3]);
			vo.setCbt_num4(cbt[4]);
			vo.setCbt_answer(cbt[5]);
			cbtList.add(vo);
		}
	}
	
	// 문제 보여주는 메서드
	public void viewAllQuestion() {
		SqlSession session = sessionFactory.openSession();
		CBTDao dao = session.getMapper(CBTDao.class);
		cbtList = dao.selectAll();

		System.out.println("문제를 풀어보세요");
		String strNum = scan.nextLine();

		if (strNum.equals("")) {
			System.out.println("취소되었습니다");
			return;
		}

		int intNum = Integer.valueOf(strNum);
		//CBTVO vo = new CBTVO();
		for (CBTVO vo : cbtList) {
			
			String[] nums = {vo.getCbt_num1(),vo.getCbt_num2(),vo.getCbt_num3(),vo.getCbt_num4()};
			Collections.shuffle(cbtList);
			Collections.shuffle(Arrays.asList(nums));
			System.out.print(vo.getCbt_quenum() + "번");
			System.out.println("문제 >>" +vo.getCbt_question());
			System.out.println("1번 >> " + nums[0]);
			System.out.println("2번 >> " + nums[1]);
			System.out.println("3번 >> " + nums[2]);
			System.out.println("4번 >> " + nums[3]);
			
			
			System.out.println("정답을 입력하세요 >> ");
			//this.f
			String strNum1 = scan.nextLine();
			int intNum1 = Integer.valueOf(strNum1);
			
		}
			
		}
	// 메뉴 메서드
	public void cbtMenu() {
		while (true) {
			System.out.println("===============================");
			System.out.println("1.문제 입력 2.문제 풀이  0.종료");
			System.out.println("-------------------------------");
			System.out.print("선택 >>");
			String strM = scan.nextLine();
			int intM = Integer.valueOf(strM);

			if (intM == 0) {
				System.out.println("종료합니다");
			return;
			}
			if (intM == 1) 
				this.cbtmenu2();
			
			if (intM == 2) 
				this.viewAllQuestion();
			
			}
			
		
		}	
	 
	
public void cbtmenu2() {
	while(true) {
		System.out.println("==================================");
		System.out.println("1.문제 등록 2.문제 수정 3. 문제삭제 0.종료");
		System.out.println("-----------------------------------");
		System.out.println("선택>> ");
		String strM = scan.nextLine();
		int intM = Integer.valueOf(strM);
		
		if(intM ==1) this.cbtInfoInput();
		if(intM ==2) this.update();
		if(intM ==3) this.delete();
		if(intM ==0) return;
		
			
	}
	
}
}

/*
 * 문항vo클래스를 만들어서 string 문항; int 정답;
 * 
 * 
 * 문항VO[] 문항 = new 문항VO[4]; for(int i = 0; i < 4; i++) 문항[i] = new 문항vo(); }
 * 
 * 문항[0].문항 = "대한민국"; 문항[0].정답 = 1;
 * 
 * 문항[0].문항 = "미국"; 문항[0].정답 = -1;
 * 
 * 문항[0].문항 = "중국"; 문항[0].정답 = -1;
 * 
 * 문항[0].문항 = "일본"; 문항[0].정답 = -1; Collections.shuffle(Arrays.asLst(문항));
 * sysout("다음중 서울이 수도인 나라는") for(int i = 0; i<4; i++){ sysout((i+1)+문항[i].문항); }
 * scanner scan = new scanner sysout("선택>>") string strN = scan.nextLine();
 * 
 * int intN = Integer.valueof(strN); if(문항[intN-1].정답 =1){ sysout("참잘햇어요) }else{
 * sysout("다시풀어보세요")
 */
