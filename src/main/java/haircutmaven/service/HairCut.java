package haircutmaven.service;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HairCut {

	public static String RandomCut(String haircut) {
	    Random random = new Random();
	    int randomNum = random.nextInt(10); // 0부터 9까지의 숫자 중 하나 반환
	    log.info(""+ randomNum);
	    if (randomNum == 7) {
	        return "fail";
	    } else {
	        return haircut;
	    }
	}
	
	public static void printHairstyle(int hairLength,String hairStyle) {
		if(hairLength==1 && hairStyle.equals("컷")) {
			System.out.println("|||||||||||||||||||||||\r\n"
					+ "|||||||||||||||||||||||");
		}else if(hairLength==1 && hairStyle.equals("파마")) {
			System.out.println("((((((((((((((((((((\r\n"
					+ "))))))))))))))))))))\r\n"
					+ "((((((((((((((((((((\r\n"
					+ "))))))))))))))))))))\r\n"
					+ "(((((((((((((((((((");
		}else if(hairLength==1 && hairStyle.equals("매직")) {
			System.out.println("|||||||||||||||||||||||\r\n"
					+ "|||||||||||||||||||||||\r\n"
					+ "|||||||||||||||||||||||\r\n"
					+ "|||||||||||||||||||||||\r\n"
					+ "|||||||||||||||||||||||");
		}else if(hairLength==2 && hairStyle.equals("컷")) {
			System.out.println("|||||||||||||||||||||||");
		}else if(hairLength==2 && hairStyle.equals("파마")) {
			System.out.println("))))))))))))))))))))\r\n"
					+ "((((((((((((((((((((");
		}else if(hairLength==2 && hairStyle.equals("매직")) {
			System.out.println("|||||||||||||||||||||||\r\n"
					+ "|||||||||||||||||||||||");
		}
	}
}
