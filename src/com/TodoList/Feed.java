package com.TodoList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.Controller.FeedController;
import com.VO.TodoVO;

public class Feed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}//

	Scanner sc = new Scanner(System.in);
	List<TodoVO> todayList = new ArrayList<TodoVO>();
	String checkBox ="";

	public void feedRun() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date = formatter.format(calendar.getTime());

		FeedController feedcon = new FeedController();
		boolean loop = true;

		System.out.println("\r\n"
				+ "　　　　              ______               _ \r\n"
				+ "　　　　              |  ___|             | |\r\n"
				+ "　　　　              | |_  ___   ___   __| |\r\n"
				+ "　　　　              |  _|/ _ \\ / _ \\ / _` |\r\n"
				+ "　　　　              | | |  __/|  __/| (_| |\r\n"
				+ "　　　　              \\_|  \\___| \\___| \\__,_|\r\n"
				+ "　　　　                       \r\n"
				+ "");
		System.out.println(" 　　　　   [" + date + "]");
		
		while (loop) {
			System.out.println();
			System.out.println(
					"       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"

				   + "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
					+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜1.할일추가|　　｜2.조회하기｜   |3.수정하기|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜4.삭제하기|　 ｜5.체크하기｜   |9.돌아가기|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　									　　		　\r\n"
					+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n"
					+ "");
			


			System.out.print("      메뉴 선택 => ");
			String menu = sc.nextLine();

			if (menu.contains("1")) {
				System.out.println();
				System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ [추가하기] ≡≡≡≡≡ ≡≡≡≡≡");
				System.out.println();
				System.out.println("         1.할일 추가 / 2.돌아기기");
				System.out.println();
				System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡");
				System.out.println();
				System.out.print("      메뉴 선택 => ");
				String choice1 = sc.nextLine();
				if (choice1.contains("1")) {
					System.out.println();
					System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
					System.out.println("        	       오늘 할일 입력" );
					System.out.println();					
					System.out.print("                 => ");
				
					//사용자 입력값 받기
					String content = sc.nextLine();

					int lastNum = feedcon.getLastNum();
					
					//추가하기 메소드 실행
					feedcon.insert(content, lastNum);
					
					System.out.println("        	        # 입력 성공 ");	
					System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
				}

			} else if (menu.contains("2")) {
				System.out.println();
				
				//List 가져오기
				//todayList = feedcon.todayRead();
				
				
				
				if (todayList.size() == 0) {
					System.out.println("오늘 할일이 없습니다.");
				} else {
					
					
					System.out.println(" ┏━━━━━━━[Todo List]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println(" ┃");
					
					//리스트 출력
//					for (int i = 0; i < todayList.size(); i++) {
//						
//					
//						
//						System.out.print("   "+(i + 1));
//						System.out.print(". " + todayList.get(i).getContent());
//						System.out.print(" / " + todayList.get(i).getDate());
//						if(todayList.get(i).getCheck().contains("Y")) {
//							checkBox ="√";
//						}else if(todayList.get(i).getCheck().contains("N")) {
//							checkBox ="X";
//						}
//						
//						System.out.print(" / " + checkBox);
//						System.out.println();
//						try {
//							TimeUnit.MILLISECONDS.sleep(400);
//							
//						} catch (InterruptedException e) {
//							
//							e.printStackTrace();
//						}
//					}
				
					
					
					
					
					
					
					
					
					
					
					System.out.println(" ┃");
					System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				}

			} else if (menu.contains("3")) {
				System.out.println();
				
				int updateNum = 0;
				todayList = feedcon.todayRead();

				System.out.println(" ┏━━━━━━━[수정 하기]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");
				for (int i = 0; i < todayList.size(); i++) {
					System.out.print("   "+(i + 1));
					System.out.print(". " + todayList.get(i).getContent());
					System.out.print(" / " + todayList.get(i).getDate());
					System.out.print(" / " + todayList.get(i).getCheck());
					System.out.println();
					
					
				}
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	      수정 할 번호 : ");
				String choiceUp = sc.nextLine();

				System.out.println("           	        내용 입력  ");
				System.out.print("                =>");
				String contentUp = sc.nextLine();

				
				for (int i = 0; i < todayList.size(); i++) {
					if (choiceUp.contains("" + (i + 1) + "")) {

						updateNum = todayList.get(i).getNum();
					}
				}
				feedcon.update(updateNum, contentUp);
				System.out.println("        	      # 할일 수정 성공 ");	
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");
				

			} else if (menu.contains("4")) {
				System.out.println();
				System.out.println(" ┏━━━━━━━[삭제 하기]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");
				int deleteNum = 0;
				todayList = feedcon.todayRead();

				for (int i = 0; i < todayList.size(); i++) {
					System.out.print("   "+(i + 1));
					System.out.print(". " + todayList.get(i).getContent());
					System.out.print(" / " + todayList.get(i).getDate());
					if(todayList.get(i).getCheck().contains("Y")) {
						checkBox ="√";
					}else if(todayList.get(i).getCheck().contains("N")) {
						checkBox ="X";
					}
					
					System.out.print(" / " + checkBox);
					System.out.println();
					
					
				}
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();
				
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	      삭제 할 번호 : ");
				String choiceDel = sc.nextLine();

				for (int i = 0; i < todayList.size(); i++) {
					if (choiceDel.contains("" + (i + 1) + "")) {

						deleteNum = todayList.get(i).getNum();
					}
				}

				feedcon.delete(deleteNum);
				System.out.println("        	      # 할일 삭제 성공 ");	
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");

			} else if (menu.contains("5")) {
				
				int checkNum = 0;
				String checkContent = "";
				String checkDate = "";
				todayList = feedcon.todayRead();
				System.out.println();
				System.out.println(" ┏━━━━━━━[체크 하기]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" ┃");
				for (int i = 0; i < todayList.size(); i++) {
					System.out.println();
					System.out.print("   "+(i + 1));
					System.out.print(". " + todayList.get(i).getContent());
					System.out.print(" / " + todayList.get(i).getDate());
					if(todayList.get(i).getCheck().contains("Y")) {
						checkBox ="√";
					}else if(todayList.get(i).getCheck().contains("N")) {
						checkBox ="X";
					}
					
					System.out.print(" / " + checkBox);
					System.out.println();
					
					
				}			
				System.out.println(" ┃");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();
				
				System.out.println("               |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
				System.out.print("        	      체크 할 번호 : ");
				String check = sc.nextLine();
				
				System.out.println("           	 1.체크하기 √ / 2.체크풀기 X  ");
				System.out.print("                =>");
				
				String check12 = sc.nextLine();
				String checkYN = "Y";
				if (check12.contains("1")) {
					checkYN = "Y";
				} else if (check12.contains("2")) {
					checkYN = "N";
				}

				for (int i = 0; i < todayList.size(); i++) {
					if (check.contains("" + (i + 1) + "")) {

						checkNum = todayList.get(i).getNum();
						checkContent = todayList.get(i).getContent();
						checkDate = todayList.get(i).getDate();

					}
				}
				feedcon.check(checkNum, checkContent, checkDate, checkYN);
				System.out.println("        	      # 할일 삭제 성공 ");	
				System.out.println("               |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\r\n");

			}else if (menu.contains("9")) {
				App app = new App();
				app.appRun("NICK");
			}

		} // while

	};// feedRun

}//
