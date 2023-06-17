package com.TodoList;

import java.io.Console;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;

import com.Controller.MemberController;
import com.VO.MemberVO;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberController mc = new MemberController();

		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		
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
		
		while (loop) {
			
			

			System.out.println(
					"       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"
					+ "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
					+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜1.로그인|　　　｜2.회원가입｜    |9.종료　|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　									　　		　\r\n"
					+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n"
					+ "");
			
			System.out.print("      메뉴 선택 => ");
			String ch = sc.nextLine();
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			if(ch.contains("1")) {
				
			System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
			System.out.print("        	      ID 입력 : ");
			String id  = sc.nextLine();
				
				System.out.println();
				System.out.print("        	      PW 입력 : ");	
				
				String pw = sc.nextLine();
				Map<String, String> loginMap =mc.memberLoin(id, pw);
				String check = loginMap.get("loginCheck");
				String nick = loginMap.get("loginNick");
				
				if(check.equals("T")) {
					System.out.println();
					System.out.println("        	      # 로그인 성공 ");	
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
					
					try {
						TimeUnit.MILLISECONDS.sleep(600);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
					App app = new App();
					app.appRun(nick);
				}else {
					System.out.println();
					System.out.println("                 # 입력하신 정보가 잘못되었습니다.");
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
					
					try {
						TimeUnit.MILLISECONDS.sleep(300);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
				
					
				}
		
				
			}else if (ch.contains("2")) {
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	      ID 입력 : ");
				String id  = sc.nextLine();
				
				System.out.print("        	      PW 입력 : ");	
				
				String pw = sc.nextLine();
				
				System.out.print("        	     NICK 입력 : ");	
				
				String nick = sc.nextLine();
				
				mc.memberJoin(id, pw, nick);
				System.out.println();
				System.out.println("        	      # 회원가입 성공 ");	
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
				
			}
		}
		
		
		
	
		
		
	}//
	

	

}//
