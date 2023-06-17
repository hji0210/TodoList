package com.TodoList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import com.TodoList.mypage.controller.MyPage;

public class App {

	public  void appRun(String nick) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date = formatter.format(calendar.getTime());

		Feed11 feed= new Feed11();
		MyPage myPage = new MyPage();
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\r\n"
				+ "  _______          _         _       _____   _____  _______ \r\n"
				+ " |__   __|        | |       | |     |_   _| / ____||__   __|\r\n"
				+ "    | |  ___    __| |  ___  | |       | |  | (___     | |   \r\n"
				+ "    | | / _ \\  / _` | / _ \\ | |       | |   \\___ \\    | |   \r\n"
				+ "    | || (_) || (_| || (_) || |____  _| |_  ____) |   | |   \r\n"
				+ "    |_| \\___/  \\__,_| \\___/ |______||_____||_____/    |_|   \r\n"
				+ "                                                            \r\n"
				+ "                                                            \r\n"
				+ "");

		System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
		System.out.println("                       "+nick + "의 TodoList");
		System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
		System.out.println();

	
		System.out.println(
				"       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"
				+ "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
				+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
				+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
				+ "      | 　　　｜1.피드 |　　　｜2.마이페이지｜   |9.종료　|        \r\n"
				+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
				+ "      |　									　　		　\r\n"
				+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n"
				+ "");
		

		System.out.print("      메뉴 선택 => ");
		String choice = sc.nextLine();
		
		if (choice.contains("1") ) {
			feed.feedRun();
		}else if (choice.contains("2")) {
			myPage.mypageRun(sc);
			
		}else if (choice.contains("9")) {
			System.out.println("=========TodoList 종료==========");
			sc.close();
		}
		
		
	}//

}//
