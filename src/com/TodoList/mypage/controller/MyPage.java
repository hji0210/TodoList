package com.TodoList.mypage.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.TodoList.mypage.util.CalendarUtil;

public class MyPage {
	
	MyPageController myPagecon = new MyPageController();
	
	private static final Pattern IS_ONLY_NUMBER = Pattern.compile("^[0-9]*?");
	
	// '마이페이지 Main' 출력
	public void mypageRun(Scanner sc) {
		
		boolean whileLoop = true;
		
		System.out.println("\r\n"
				+ "          ___  ___       ______                   \r\n"
				+ "          |  \\/  |       | ___ \\                  \r\n"
				+ "          | .  . | _   _ | |_/ /__ _   __ _   ___ \r\n"
				+ "          | |\\/| || | | ||  __// _` | / _` | / _ \\\r\n"
				+ "          | |  | || |_| || |  | (_| || (_| ||  __/\r\n"
				+ "          \\_|  |_/ \\__, |\\_|   \\__,_| \\__, | \\___|\r\n"
				+ "                    __/ |              __/ |      \r\n"
				+ "                   |___/              |___/       \r\n"
				+ "");
		
		while(whileLoop) {
			
			
			myPagecon.printTodayAchievementRate();
			
			System.out.println();
			System.out.println(
					"       ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣	\r\n"
					+ "      |　메뉴선택　　　　　　　　　　　　　　　　　　　　　　[－][口][×] \r\n"
					+ "      |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜1.주간통계|　　｜2.월간통계｜   |3.연간통계|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　　　　＿＿＿＿＿＿　　　　＿＿＿＿＿＿＿　　　　＿＿＿＿＿　       \r\n"
					+ "      | 　　　｜4.일자검색|　 ｜5.기간검색｜   |9.돌아가기|        \r\n"
					+ "      |　　　　￣￣￣￣￣￣　　　　￣￣￣￣￣￣￣　　　　￣￣￣￣￣        \r\n"
					+ "      |　									　　		　\r\n"
					+ "      ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\r\n"
					+ "");
		   
		    System.out.print("       메뉴를 선택해 주세요. =>");
		    
		    String menuNum = sc.nextLine().trim();
		    
		    int year = -1;
		    int month = -1;
		    int day = -1;
		    int[] arrYearMonth;
		    int[] arrYearMonthDay;
		    
		    switch(menuNum) {
		    
		    case "1" :
		    	printWeekStatisticsMenu(sc);
		    	break;
		    	
		    case "2" :
		    	System.out.println();
		    	arrYearMonth = printYearMonth(sc);
		    	year = arrYearMonth[0];
		    	month = arrYearMonth[1];
		    	printMonthStatisticsResult(year, month, sc);
		    	break;
		    	
		    case "3" :
		    	System.out.println();
		    	year = printYear(sc);
		    	printYearStatisticsResult(year, sc);
		    	break;
		    	
		    case "4" :
		    	System.out.println();
		    	arrYearMonthDay = printYearMonthDay(sc);
		    	year = arrYearMonthDay[0];
		    	month = arrYearMonthDay[1];
		    	day = arrYearMonthDay[2];
		    	printSearchResultByDay(year, month, day, sc);
		    	break;
		    	
		    case "5" :
		    	System.out.println();
		    	System.out.println("[ 시작일 ]을 입력해 주세요");
	    		arrYearMonthDay = printYearMonthDay(sc);
	    		LocalDate startDate = CalendarUtil.getDate(arrYearMonthDay);
	    		System.out.println();
	    		System.out.println("[ 종료일 ]을 입력해 주세요");
	    		arrYearMonthDay = printYearMonthDay(sc);
	    		LocalDate lastDate = CalendarUtil.getDate(arrYearMonthDay);
	    		printSearchResultBetweenDays(startDate, lastDate, sc);
		    	break;
		    	
		    case "9" :
		    	System.out.println("마이페이지를 종료합니다.");
		    	whileLoop = false;
		    	break;
		    	
			default :
				System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");

		    }	
 	    }
	}

	// '주간 통계' 출력
	private void printWeekStatisticsMenu(Scanner sc) {
		
		boolean whileLoop = true;
		
		while(whileLoop) {
			
			System.out.println();
			System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ [주간통계] ≡≡≡≡≡ ≡≡≡≡≡");
			System.out.println();
			System.out.println("      1.주간 달성률 / 2.주간 계획 / 9.이전");
			System.out.println();
			System.out.println("     ≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡≡≡≡ ≡≡≡≡≡ ≡≡≡≡≡");
			System.out.println();
			System.out.print("      메뉴 선택 => ");
			
		    String menuNum = sc.nextLine().trim();
		    
		    int year = -1;
		    int month = -1;
		    int[] arrYearMonth;
		    
		    switch(menuNum) {
		    
		    case "1" :
		    	System.out.println();
		    	arrYearMonth = printYearMonth(sc);
		    	year = arrYearMonth[0];
		    	month = arrYearMonth[1];
		    	printWeekAchievementRate(year, month, sc);
		    	break;
		    	
		    case "2" :
		    	System.out.println();
		    	arrYearMonth = printYearMonth(sc);
		    	year = arrYearMonth[0];
		    	month = arrYearMonth[1];
		    	printWeekMenu(year, month, sc);
		    	break;
		    	
		    case "9" :
		    	whileLoop = false;
		    	break;
		    	
			default :
				System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");
				
		    }
 	    }
	}
	
	// '주간 달성률' 출력
	private void printWeekAchievementRate(int year, int month, Scanner sc) {
		
		boolean whileLoop = true; 
		
		while(whileLoop) {
			
			System.out.println("\n------------- 주간 달성률 -------------");
			System.out.println();
			myPagecon.createWeeksAchievementRate(year, month);
		    System.out.println("9. 이전");
		    System.out.println();
		    System.out.print("이전으로 돌아가려면 '9'를 입력해 주세요. =>");
		    String menuNum = sc.nextLine().trim();
		    
		    switch(menuNum) {
		  
		    case "9" :
		    	whileLoop = false;
		    	break;
		    	
			default :
				System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");
				
		    }
 	    }
	}

	// '주간 계획' 출력
	private void printWeekMenu(int year, int month, Scanner sc) {
		
		List<LocalDate> mondayList = CalendarUtil.findByMondayList(year, month);
		
		boolean whileLoop = true;
		
		while(whileLoop) {
			
			System.out.println("\n------------- 주간 계획 -------------");
			System.out.println();
			for(int i = 0; i < mondayList.size(); i++) {
				int weekNum = i + 1;
				System.out.print(weekNum + ". ");
				System.out.println(CalendarUtil.getWeekName(weekNum));
			}
		    System.out.println("9. 이전");
		    System.out.println();
		    System.out.print("메뉴를 입력해 주세요. =>");
		    int menuNum = Integer.parseInt(sc.nextLine().trim());
		    
		    if(menuNum == 9) {
		    	whileLoop = false;
		    	break;
		    } else if (menuNum > 0 && menuNum <= mondayList.size()) {
		    	myPagecon.printWeekTodoList(year, month, menuNum);
		    } else {
		    	System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");
		    }	
 	    }
	}

	// '월간 통계' 출력
	private void printMonthStatisticsResult(int year, int month, Scanner sc) {
		boolean whileLoop = true;
		
		while(whileLoop) {
			
			System.out.println("\n------------- 월간 통계 -------------");
			System.out.println();
			myPagecon.printMonthStatistics(year, month);
		    System.out.println("9. 이전");
		    System.out.println();
		    System.out.print("이전으로 돌아가려면 '9'를 입력해 주세요. =>");
		    String menuNum = sc.nextLine().trim();
		    
		    switch(menuNum) {
		    
		    case "9" :
		    	whileLoop = false;
		    	break;
		    	
			default :
				System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");
				
		    }
 	    }
	}
	
	// 연간 통계
	private void printYearStatisticsResult(int year, Scanner sc) {
		boolean whileLoop = true;
		
		while(whileLoop) {
			
			System.out.println("\n------------- " + year + "년 연간 통계 -------------");
			System.out.println();
			myPagecon.printYearStatistics(year);
			System.out.println("\n");
		    System.out.println("9. 이전");
		    System.out.println();
		    System.out.print("이전으로 돌아가려면 '9'를 입력해 주세요. =>");
		    String menuNum = sc.nextLine().trim();
		    
		    switch(menuNum) {
		    
		    case "9" :
		    	whileLoop = false;
		    	break;
		    	
			default :
				System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");
				
		    }
 	    }
	}
	
	// 일자 검색
	private void printSearchResultByDay(int year, int month, int day, Scanner sc) {
		boolean whileLoop = true;
		
		LocalDate targetDate = LocalDate.of(year, month, day);
		
		while(whileLoop) {
			System.out.println("\n------------- " + targetDate + " " + CalendarUtil.getDayName(targetDate) + "  -------------");
			System.out.println();
			System.out.println("\t( 달성률 : " + myPagecon.averageAchievementRateByDay(targetDate) + "% )");
			myPagecon.searchTodoListByDay(targetDate);
			System.out.println();
			System.out.println("9. 이전");
		    System.out.println();
		    System.out.print("이전으로 돌아가려면 '9'를 입력해 주세요. =>");
		    String menuNum = sc.nextLine().trim();
		    
		    switch(menuNum) {
		    
		    case "9" :
		    	whileLoop = false;
		    	break;
		    	
			default :
				System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");
				
		    }
		}
	}
	
	// 기간 검색
	private void printSearchResultBetweenDays(LocalDate startDate, LocalDate lastDate, Scanner sc) {
		boolean whileLoop = true;
		
		while(whileLoop) {
			System.out.println("\n------------- " + startDate + " " + CalendarUtil.getDayName(startDate) + " ~ " + lastDate + " " + CalendarUtil.getDayName(lastDate) + "  -------------");
			myPagecon.searchTodoListBetweenDays(startDate, lastDate);
			System.out.println();
			System.out.println("9. 이전");
		    System.out.println();
		    System.out.print("이전으로 돌아가려면 '9'를 입력해 주세요. =>");
		    String menuNum = sc.nextLine().trim();
		    
		    switch(menuNum) {
		    
		    case "9" :
		    	whileLoop = false;
		    	break;
		    	
			default :
				System.out.println("메뉴를 잘 못 선택하셨습니다. 다시 입력해 주세요.");
				
		    }
		}
	}
	
	// '연도' 입력 (빈 문자 없이 숫자만 입력했는지 확인 포함)
	private int printYear(Scanner sc) {
		boolean whileLoopOfYear = true;
		int targetYear = 0;

		while(whileLoopOfYear) {
			System.out.print("'연도'를 입력해 주세요.(예시 : 2023) =>");
	        String year = sc.nextLine().trim();
	        
	        if(IS_ONLY_NUMBER.matcher(year).matches() && !year.equals("")) {
	        	targetYear = Integer.parseInt(year);
	        	whileLoopOfYear = false;
	        	break;
	        } 
	       
	        if(!IS_ONLY_NUMBER.matcher(year).matches() || year.equals("")) {
	        	System.out.println();
	        	System.out.println("숫자가 아닙니다. '연도'를 정확히 입력해 주세요");
	        	System.out.println();
	        }
	    
		}
		return targetYear;
	}
	
	
	
	// '연도 및 월' 입력 (빈 문자 없이 숫자만 입력했는지 확인 포함)
	private int[] printYearMonth(Scanner sc) {
		int[] arrYearMonth = new int[2];
		
		boolean whileLoopOfYear = true;
		boolean whileLoopOfMonth = true;
		
		while(whileLoopOfYear) {
			System.out.print("'연도'를 입력해 주세요.(예시 : 2023) =>");
	        String year = sc.nextLine().trim();
	        
	        if(IS_ONLY_NUMBER.matcher(year).matches() && !year.equals("")) {
	        	arrYearMonth[0] = Integer.parseInt(year);
	        	whileLoopOfYear = false;
	        	break;
	        } 
	       
	        if(!IS_ONLY_NUMBER.matcher(year).matches() || year.equals("")) {
	        	System.out.println();
	        	System.out.println("숫자가 아닙니다. '연도'를 정확히 입력해 주세요");
	        	System.out.println();
	        }
	    
		}
		
		while(whileLoopOfMonth) {
			 System.out.print("'월'을 입력해 주세요.(예시 : 1 ~ 12) =>");
		     String month = sc.nextLine().trim();
	        
	        if(IS_ONLY_NUMBER.matcher(month).matches() && !month.equals("")) {
	        	arrYearMonth[1] = Integer.parseInt(month);
	        	whileLoopOfMonth = false;
	        	break;
	        } 
	        
	        if(!IS_ONLY_NUMBER.matcher(month).matches() || month.equals("")) {
	        	System.out.println();
	        	System.out.println("숫자가 아닙니다. '월'을 정확히 입력해 주세요");
	        	System.out.println();
	        }
	        	
		}
		return arrYearMonth;
	}
	
	// '연도, 월 및 일' 입력 (빈 문자 없이 숫자만 입력했는지 확인 포함)
	private int[] printYearMonthDay(Scanner sc) {
		int[] arrYearMonthDay = new int[3];
		
		boolean whileLoopOfYear = true;
		boolean whileLoopOfMonth = true;
		boolean whileLoopOfDay = true;
		
		while(whileLoopOfYear) {
			System.out.print("'연도'를 입력해 주세요.(예시 : 2023) =>");
	        String year = sc.nextLine().trim();
	        
	        if(IS_ONLY_NUMBER.matcher(year).matches() && !year.equals("")) {
	        	arrYearMonthDay[0] = Integer.parseInt(year);
	        	whileLoopOfYear = false;
	        	break;
	        } 
	       
	        if(!IS_ONLY_NUMBER.matcher(year).matches() || year.equals("")) {
	        	System.out.println();
	        	System.out.println("숫자가 아닙니다. '연도'를 정확히 입력해 주세요");
	        	System.out.println();
	        }
	    
		}
		
		while(whileLoopOfMonth) {
			 System.out.print("'월'을 입력해 주세요.(예시 : 1 ~ 12) =>");
		     String month = sc.nextLine().trim();
	        
	        if(IS_ONLY_NUMBER.matcher(month).matches() && !month.equals("")) {
	        	arrYearMonthDay[1] = Integer.parseInt(month);
	        	whileLoopOfMonth = false;
	        	break;
	        } 
	        
	        if(!IS_ONLY_NUMBER.matcher(month).matches() || month.equals("")) {
	        	System.out.println();
	        	System.out.println("숫자가 아닙니다. '월'을 정확히 입력해 주세요");
	        	System.out.println();
	        }
	        	
		}
		
		while(whileLoopOfDay) {
			 System.out.print("'일'을 입력해 주세요.(예시 : 1 ~ 31) =>");
		     String day = sc.nextLine().trim();
	        
	        if(IS_ONLY_NUMBER.matcher(day).matches() && !day.equals("")) {
	        	arrYearMonthDay[2] = Integer.parseInt(day);
	        	whileLoopOfDay = false;
	        	break;
	        } 
	        
	        if(!IS_ONLY_NUMBER.matcher(day).matches() || day.equals("")) {
	        	System.out.println();
	        	System.out.println("숫자가 아닙니다. '일'을 정확히 입력해 주세요");
	        	System.out.println();
	        }
	        	
		}
		return arrYearMonthDay;
	}
	
}
