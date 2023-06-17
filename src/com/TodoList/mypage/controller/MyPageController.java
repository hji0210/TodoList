package com.TodoList.mypage.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.Controller.FileController;
import com.TodoList.mypage.util.CalendarUtil;
import com.VO.TodoVO;

public class MyPageController {
	
	FileController fc = new FileController();
	List<TodoVO> todoList = fc.fileRead();
	
	private final static int WEEK_DAYS = 7;
	private final static int HIGH_RANK = 3;
	
	// '오늘 달성률' 출력
	public void printTodayAchievementRate() {
		LocalDate today = LocalDate.now();
		int todayAchievementRate = averageAchievementRateByDay(today);
		int starNum = todayAchievementRate / 10;
		System.out.println("       [TODAY : " + today + " " + CalendarUtil.getDayName(today) + " ]");
		System.out.println();
	    System.out.print("       오늘 달성률 : " + todayAchievementRate + "%");
	    printWidthStar(starNum);
	}
	
	// '주간 달성률' 결과 출력
	public void createWeeksAchievementRate(int year, int month) {
		
		List<LocalDate> mondayList = CalendarUtil.findByMondayList(year, month);

	    int weekNum = 1;
        for (LocalDate monday : mondayList) {
        	System.out.print(CalendarUtil.findMonthName(month) + " ");
        	System.out.print(CalendarUtil.getWeekName(weekNum) + ". ");
        	int weekAchievementRate = averageAchievementRateByWeek(monday);
        	System.out.println("(달성률 : " + weekAchievementRate + "%)");
        	System.out.println();
            for (int i = 0; i < WEEK_DAYS; i++) {
                LocalDate targetDate = monday.plusDays(i);
                System.out.print(targetDate);
                int dayAchievementRate = averageAchievementRateByDay(targetDate);
                int starNum = dayAchievementRate / 10;
                if(dayAchievementRate==0) {
                	System.out.print("  0" + dayAchievementRate + "%");
                }else {
                	System.out.print("  " + dayAchievementRate + "%");
                }
                
                
                printWidthStar(starNum);
                System.out.println();
            }
            weekNum++;
            System.out.println();
            System.out.println();
        }
	}

	// 일별 달성률 구하기
	public int averageAchievementRateByDay(LocalDate targetDate) {
		int allTodoCount = 0;
		int checkTodoCount = 0;
		for(TodoVO todoVO : todoList) {
			String target = targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			if(todoVO.getDate().equals(target)) {
				allTodoCount++;
				if(todoVO.getCheck().equals("Y")) {
					checkTodoCount++;
				}
			}
		}
		if(checkTodoCount == 0 || allTodoCount == 0) {
			return 0;
		} else {
			return (checkTodoCount * 100) / allTodoCount;
		}
	}
	
	// 주별 평균 달성률 구하기 
	private int averageAchievementRateByWeek(LocalDate monday) {
		int sumAchievementRateByDay = 0;
		for(int i = 0; i < WEEK_DAYS; i++) {
			LocalDate targetDate = monday.plusDays(i);
			sumAchievementRateByDay += averageAchievementRateByDay(targetDate);
		}
		return sumAchievementRateByDay / WEEK_DAYS;
	}
	
	// 달성률 '*' 가로 그래프로 표현하기
	private void printWidthStar(int starNum) {
        	System.out.print("  ");
        	for(int k = 0; k < starNum; k++) {
        		System.out.print("◆");        		
        	}
        	for (int s = 0; s < 10-starNum; s++) {
				System.out.print("◇");			
			}
        }


	// '주간 계획' 결과 출력
	public void printWeekTodoList(int year, int month, int menuNum) {
		List<LocalDate> mondayList = CalendarUtil.findByMondayList(year, month);
		
		String weekName = CalendarUtil.getWeekName(menuNum);
		System.out.println("\n------------- " + weekName + " -------------");
		
		LocalDate monday = mondayList.get(menuNum - 1);
		for(int i = 0; i < WEEK_DAYS; i++) {
			LocalDate targetDate = monday.plusDays(i);
			System.out.println();
			System.out.println("# " + targetDate + " " + CalendarUtil.getDayName(targetDate));
			List<TodoVO> targetTodoList = getTodoListByDay(targetDate);
			printTodoListByDay(targetTodoList);
		}	
	}

	// '월간 통계' 결과 출력
	public void printMonthStatistics(int year, int month) {
		
		int achivementRateOfThisMonth = getAchievementRateOfMonth(year, month);
		int previousMonth = month - 1;
		int yearOfPreviousMonth = year;
		
		if(previousMonth == 0) {
			yearOfPreviousMonth = year - 1;
			previousMonth = 12;
		}
		
		int achievmentRateOfPreviousMonth = getAchievementRateOfMonth(yearOfPreviousMonth, previousMonth);
		
		System.out.print("[ " + year + "년도 " + month + "월 달성률");
		System.out.println(" ( 달성률 : " + achivementRateOfThisMonth + "% )" + " ]");
		System.out.println("\n");
		
		int starNumOfThisMonth = achivementRateOfThisMonth / 10;
		int starNumOfPreviousMonth = achievmentRateOfPreviousMonth / 10;
		

        
        //저번달
        System.out.println("     |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|");
        for(int j = 0; j < 1; j++) {
        	System.out.println("  ");
        	System.out.print("        <" + previousMonth + "월> ");
        	for(int k = 0; k < starNumOfPreviousMonth; k++) {
        		System.out.print("◆");
        		tSleep(100);
        	}
        	for (int s = 0; s < 10-starNumOfPreviousMonth; s++) {
				System.out.print("◇");
				tSleep(100);
			}
        }
        System.out.print("  " + achievmentRateOfPreviousMonth + "%");
        System.out.println();
        //이번달
        for(int j = 0; j < 1; j++) {
        	System.out.println("  ");
        	System.out.print("        <" + month + "월> ");
        	for(int k = 0; k < starNumOfThisMonth; k++) {
        		System.out.print("◆");
        		tSleep(100);
        	}
        	for (int s = 0; s < 10-starNumOfThisMonth; s++) {
				System.out.print("◇");
				tSleep(100);
			}
        }
        System.out.print("  " + achivementRateOfThisMonth + "%");
        System.out.println();
        System.out.println();
        System.out.println("     |＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
        System.out.println();
//		printHeightStar(starNumOfThisMonth, starNumOfPreviousMonth);
//		
//		System.out.println();
//		System.out.print("\t" + " ");
//        System.out.print(" <" + previousMonth + "월>");
//        System.out.print("     <" + month + "월>");
//        System.out.println("\n\n");
         
        int rateVariation = achivementRateOfThisMonth - achievmentRateOfPreviousMonth;
        try {
			TimeUnit.MILLISECONDS.sleep(100);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        if(rateVariation > 0) {
        	System.out.println("            (o ^ o ^ o)            ");
        	System.out.println();
        	System.out.println("     이전 달 보다 달성률이 높아졌어요!     ");
        	System.out.println();
        } else {
        	System.out.println("             (˶> _ <˶)            ");
        	System.out.println();
        	System.out.println("    이전 달 보다 달성률이 낮거나 정체됐어요!  ");
        	System.out.println();
        }
        try {
			TimeUnit.MILLISECONDS.sleep(200);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        System.out.println(" ┏━━━━━━━[ "+year+"년 "+month+"월 "+"주요 키워드 ]━━━━━━━━━━━━┓");
       System.out.println();
        List<String> highRankKeyWordList = getHighRankKeyWordList(year, month);
        System.out.print("         ");
        highRankKeyWordList.stream().forEach(it -> System.out.print("#" + it + " "));
        System.out.println();
        System.out.println();
		System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println();
	}
	
	// '연간 통계' 결과 출력
	public void printYearStatistics(int year) {
		List<Integer> statisticsList = getYearStatistics(year);
		List<Integer> starCountList = getStarCountList(statisticsList);
		statisticsList.stream().forEach(it -> System.out.print("\t" + it + "%"));
		System.out.println("\n");
		printHeightStarOfYear(starCountList);
		IntStream.rangeClosed(1, 12).forEach(it -> System.out.print("\t" + it + "월"));
	}

	// 월별 평균 달성률 구하기
	private int getAchievementRateOfMonth(int year, int month) {
		LocalDate firstDay = CalendarUtil.getFirstDayOfMonth(year, month);
		int daysOfMonth = firstDay.lengthOfMonth();
		int sumAchievementRateOfDay = 0;
		
		for(int i = 0; i < daysOfMonth; i++) {
			LocalDate targetDate = firstDay.plusDays(i);
			sumAchievementRateOfDay += averageAchievementRateByDay(targetDate);
		}
		
		if(sumAchievementRateOfDay == 0) {
			return 0;
		}
		return sumAchievementRateOfDay / daysOfMonth;
	}
	
	// 특정 연도의 월별 평균 달성률 리스트
	public List<Integer> getYearStatistics(int year) {
		List<Integer> statisticsList = new ArrayList<>();
		LocalDate startMonth = LocalDate.of(year, 1, 1);
		
		for(int i = 0; i < 12; i++) {
            int month = startMonth.plusMonths(i).getMonthValue();
            statisticsList.add(getAchievementRateOfMonth(year, month));
        }
		return statisticsList;
	}
	
	// 특정 연도의 월별 평균 달성률 기준 '*' 개수 구하기
	public List<Integer> getStarCountList(List<Integer> statisticsList) {
		List<Integer> starCountList = new ArrayList<>();
		
		statisticsList.stream().forEach(it -> {
			if(it == 0) {
				starCountList.add(0);
				return;
			}
			int starNum = it / 10;
			starCountList.add(starNum);
		});
		return starCountList;
	}
	
	// 달성률 '*' 세로 그래프로 표현하기(연간 통계)
	private void printHeightStarOfYear(List<Integer> starCountList) {
		
		int row = starCountList.stream().mapToInt(it -> it).max().orElse(0);
		int column = starCountList.size();
		
		String[][] arr = new String[row][column];
		
		for(int i = 0; i < column; i++) {
			int starNum = starCountList.get(i);
			
			for(int j = 0; j < starNum; j++) {
				arr[j][i] = "*";
			}
		}
		
		for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] == null) {
                    arr[i][j] = " ";
                }
            }
        }
		
		for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < column; j++) {
                System.out.print("\t " + arr[i][j]);
            }
            System.out.println();
        }
	}
	
	// 달성률 '*' 세로 그래프로 표현하기(월간 통계)
	private void printHeightStar(int starNumOfThisMonth, int starNumOfPreviousMonth) {
		
		int row = Math.max(starNumOfThisMonth, starNumOfPreviousMonth);
		String[][] arr = new String[row][4];
		
		for (int i = 0; i < starNumOfPreviousMonth; i++) {
            arr[i][0] = "*";
        }
		
		for (int i = 0; i < starNumOfThisMonth; i++) {
            arr[i][3] = "*";
        }
		
		for (int i = 0; i < row; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == null) {
                    arr[i][j] = " ";
                }
            }
        }
		
		for (int i = row - 1; i >= 0; i--) {
			System.out.print("\t" + "  ");
            for (int j = 0; j < 4; j++) {
                System.out.print("  " + arr[i][j]);
            }
            System.out.println();
        }
	}

	// 일자 검색
	public void searchTodoListByDay(LocalDate targetDate) {
		List<TodoVO> targetTodoList = getTodoListByDay(targetDate);
		printTodoListByDay(targetTodoList);
	}
	
	// 기간 검색
	public void searchTodoListBetweenDays(LocalDate startDate, LocalDate lastDate) {
		boolean whileLoop = true;
		int i = 0;
		
		while(whileLoop) {
			LocalDate targetDate = startDate.plusDays(i);
			i++;
		
			System.out.println();
			System.out.print("# " + targetDate + " " + CalendarUtil.getDayName(targetDate));
			System.out.println(" ( 달성률 : " + averageAchievementRateByDay(targetDate) + "% )");
			List<TodoVO> targetTodoList = getTodoListByDay(targetDate);
			printTodoListByDay(targetTodoList);
			
			if(targetDate.isEqual(lastDate)) {
				whileLoop = false;
				break;
			}
		}	
	}
	
	// 특정 일자의 TODOList 조회 
	private List<TodoVO> getTodoListByDay(LocalDate targetDate) {
		String target = targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return todoList
			   .stream()
			   .filter(it -> it.getDate().equals(target))
			   .collect(Collectors.toList());
	}
	
	// 특정 일자의 TODOList 출력
	private void printTodoListByDay(List<TodoVO> targetTodoList) {
		if(targetTodoList.size() == 0) {
			System.out.println();
			System.out.println("\t\t[ 텅 ]");
			System.out.println();
			return;
		} 
		
		IntStream
		.range(0, targetTodoList.size())
		.forEach(index -> {
			System.out.println();
			System.out.print("\t" + (index+1) + ". ");
			System.out.print(targetTodoList.get(index).getContent());
			System.out.println("  " + changeCheck(targetTodoList.get(index).getCheck()));
		});
	}
	
	// check 필드 값이 "Y" 이면, "V" 로 변환
	private String changeCheck(String check) {
		return (check.equals("Y")) ? "√" : " ";
	}
	
	// '주요 키워드' 조회
	public List<String> getHighRankKeyWordList(int year, int month) {
		List<String> highRankWordList = new ArrayList<>();
		List<String> contentList = getAllContentOfMonth(year, month);
		Map<String, Integer> wordsMap = getWordsFromContentList(contentList);
		Set<Integer> highRankCountSet = getHighRankCount(wordsMap);
		
		wordsMap.keySet().stream().forEach(key -> {
	            if(highRankCountSet.contains(wordsMap.get(key))) {
	            	highRankWordList.add(key);
	            }
	        });
		return highRankWordList;
	}
	
	// 월별 '내용' 모두 조회
	private List<String> getAllContentOfMonth(int year, int month) {
		LocalDate firstDay = CalendarUtil.getFirstDayOfMonth(year, month);
		int daysOfMonth = firstDay.lengthOfMonth();
		List<String> contentList = new ArrayList<>();
		
		for(int i = 0; i < daysOfMonth; i++) {
			LocalDate targetDate = firstDay.plusDays(i);
			List<TodoVO> targetDateTodoList = getTodoListByDay(targetDate);
			
			if(targetDateTodoList.size() == 0) {
				continue;
			}
			targetDateTodoList.stream().forEach(it -> contentList.add(it.getContent()));
		}
		return contentList;
	}
	
	// 월별 '단어' 모두 Map 에 저장
	private Map<String, Integer> getWordsFromContentList(List<String> contentList) {
		Map<String, Integer> wordsMap = new HashMap<>();
		
		for(int i = 0; i < contentList.size(); i++) {
			String[] wordsArray = contentList.get(i).split(" ");
			
			Arrays.stream(wordsArray).forEach(it -> {
				if(!wordsMap.containsKey(it)) {
					wordsMap.put(it, 1);
				} else {
					int count = wordsMap.get(it);
					count++;
					wordsMap.put(it, count);
				}
			});
		}
		return wordsMap;
	}
	
	// 상위 '단어 빈도수' 구하기
	private Set<Integer> getHighRankCount(Map<String, Integer> wordsMap) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Set<Integer> highRankCountSet = new HashSet<>();

        wordsMap.values().stream().forEach(it -> maxHeap.add(it));
        
        for(int i = 0; i < HIGH_RANK; i++) {
        	highRankCountSet.add(maxHeap.poll());
        }
		return highRankCountSet;
	}
	
	public void tSleep(int msec) {
		try {
			TimeUnit.MILLISECONDS.sleep(msec);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}
