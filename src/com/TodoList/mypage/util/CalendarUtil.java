package com.TodoList.mypage.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarUtil {

	public final static int JANUARY = 1;
	public final static int FEBRUARY = 2;
	public final static int MARCH = 3;
	public final static int APRIL = 4;
	public final static int MAY = 5;
	public final static int JUNE = 6;
	public final static int JULY = 7;
	public final static int AUGUST = 8;
	public final static int SEPTEMBER = 9;
	public final static int OCTOBER = 10;
	public final static int NOVEMBER = 11;
	public final static int DECEMBER = 12;
	
	public final static List<Integer> MONTH_LIST = Arrays.asList(
			JANUARY,
			FEBRUARY,
			MARCH,
			APRIL,
			MAY,
			JUNE,
			JULY,
			AUGUST,
			SEPTEMBER,
			OCTOBER,
			NOVEMBER,
			DECEMBER
	);
	
	// 영문 '월' 이름으로 변경하기 ( 예시 : 1월 > JANUARY )
	public static String findMonthName(int month) {
		int monthNumber = -1;
		
		for(int number : MONTH_LIST) {
    		if(number == month) {
    			monthNumber = number;
    		}
    	}
		return getMonthName(monthNumber);
	}
	
	public static String getMonthName(int monthNumber) {
		String monthName;
		
		switch(monthNumber) {
		case 1 :
			monthName = "JANUARY";
			break;
		case 2 :
			monthName = "FEBRUARY";
			break;
		case 3 :
			monthName = "MARCH";
			break;
		case 4 :
			monthName = "APRIL";
			break;
		case 5 :
			monthName = "MAY";
			break;
		case 6 :
			monthName = "JUNE";
			break;
		case 7 :
			monthName = "JULY";
			break;
		case 8 :
			monthName = "AUGUST";
			break;
		case 9 :
			monthName = "SEPTEMBER";
			break;
		case 10 :
			monthName = "OCTOBER";
			break;
		case 11 :
			monthName = "NOVEMBER";
			break;
		case 12 :
			monthName = "DECEMBER";
			break;
		default:
			monthName = "UNKNOWN";
			break;
		}
		return monthName;
	}
	
	// 영문 '주' 이름으로 변경하기 ( 예시 : 1주 > 1WEEK )
	public static String getWeekName(int weekNum) {
		String weekName;
		
		switch(weekNum) {
		case 1 :
			weekName = "1WEEK";
			break;
		case 2 :
			weekName = "2WEEK";
			break;
		case 3 :
			weekName = "3WEEK";
			break;
		case 4 :
			weekName = "4WEEK";
			break;
		case 5 :
			weekName = "5WEEK";
			break;
		case 6 :
			weekName = "6WEEK";
			break;
		default:
			weekName = "UNKNOWN";
			break;
		}
		
		return weekName;
	}
	
	// 한글 '요일' 이름으로 변경하기
	public static String getDayName(LocalDate date) {
		int dayNum = date.getDayOfWeek().getValue();
		
		String dayName;
		
		switch(dayNum) {
		case 1 :
			dayName = "월요일";
			break;
		case 2 :
			dayName = "화요일";
			break;
		case 3 :
			dayName = "수요일";
			break;
		case 4 :
			dayName = "목요일";
			break;
		case 5 :
			dayName = "금요일";
			break;
		case 6 :
			dayName = "토요일";
			break;
		case 7 :
			dayName = "일요일";
			break;
		default:
			dayName = "UNKNOWN";
			break;
		}
		return dayName;
	}
	
	// 월요일 리스트 구하기
	public static List<LocalDate> findByMondayList(int year, int month) {
			
			List<LocalDate> mondayList = new ArrayList<>();
			LocalDate date= LocalDate.of(year, month, 1);
			
			int weekCount = 1;
	        boolean whileLoop = true;
	
	        if(date.getDayOfWeek().getValue() != 1) {
	            LocalDate answer = date.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY)).minusWeeks(1);
	            mondayList.add(answer);
	        }
	        
		    while(whileLoop) {
		
		    LocalDate answer = date.with(TemporalAdjusters.dayOfWeekInMonth(weekCount, DayOfWeek.MONDAY));
		
		    if (!answer.getMonth().equals(date.getMonth())) {
		        whileLoop = false;
		        break;
		    }
		    weekCount++;
		    mondayList.add(answer);
	        }
		    return mondayList;
		}
	
	// 이번 달의 첫 날
	public static LocalDate getFirstDayOfMonth(int year, int month) {
		LocalDate date = LocalDate.of(year, month, 1);
        return date.with(TemporalAdjusters.firstDayOfMonth());
	}
	
	// 입력받은 '연도, 월, 일' 로 날짜 만들기
	public static LocalDate getDate(int[] arrYearMonthDay) {
		return LocalDate.of(arrYearMonthDay[0], arrYearMonthDay[1], arrYearMonthDay[2]);
	}
	
}
