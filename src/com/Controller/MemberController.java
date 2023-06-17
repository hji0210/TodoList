package com.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.VO.MemberVO;
import com.VO.TodoVO;

public class MemberController {

	String filePath = "C:/Temp/todo/member.txt";

	// ====회원가입====
	public void memberJoin(String id, String pw, String nick) {

		Writer writer = null;

		try {

			// 문자 기반 출력 스트림 생성
			writer = new FileWriter(filePath, true); // 기존 문서에 이어쓰기

			// 문자열 줄력
			writer.write("\r\n" + id + "," + pw + "," + nick);

			writer.flush();
			writer.close();

			
		} catch (IOException e) {
			System.out.println("\n===== catch() :  IOException =====");
			e.printStackTrace();
		} finally {
			// releases all system resources from the streams
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				System.out.println("\n===== finally() :  IOException  =====");
				e.printStackTrace();
			}
		}

	}//

	// ====로그인====
	public Map<String, String> memberLoin(String id, String pw) {

		// TODO Auto-generated method stub

		List<MemberVO> mvoList = new ArrayList<MemberVO>();
		List<String> memberList = null;
		BufferedReader br = null;
		String loginCheck = "F";
		String loginNick = "";
		Map<String, String> loginMap = new HashMap<String, String>();

		if (!(filePath == null)) {
			memberList = new ArrayList<String>();
			try {
				br = new BufferedReader(new FileReader(filePath));
				String s;

				while ((s = br.readLine()) != null) {

					memberList.add(s);
				}
				br.close();
			} catch (IOException e) {
				System.err.println(e);
			} finally {
				try {
					if (br != null) {
						br.close();
					}
				} catch (Exception ex) {
				}
			}
		}
		// 리스트 뽑아서 vo에 저장
		for (int i = 0; i < memberList.size(); i++) {
			MemberVO mvo = new MemberVO();
			String[] arr = memberList.get(i).split(",");
			mvo.setId(arr[0]);
			mvo.setPw(arr[1]);
			mvo.setNick(arr[2]);

			mvoList.add(mvo);
		}

		for (int i = 0; i < mvoList.size(); i++) {
			if (mvoList.get(i).getId().equals(id) && mvoList.get(i).getPw().equals(pw)) {
				loginCheck = "T";
				loginNick = mvoList.get(i).getNick();
			}
		}

		loginMap.put("loginCheck", loginCheck);
		loginMap.put("loginNick", loginNick);

		return loginMap;

	}//

}//
