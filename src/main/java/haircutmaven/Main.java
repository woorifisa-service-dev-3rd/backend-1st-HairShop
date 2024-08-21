package haircutmaven;

import java.io.IOException;
import java.sql.Connection;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import haircutmaven.dao.PaymentDAO;
import java.util.Scanner;
import haircutmaven.dao.CustomerDAO;
import haircutmaven.model.Customer;
import haircutmaven.dao.DesignerDAO;
import haircutmaven.dao.Hairstyle_menuDAO;
import haircutmaven.util.DBConnection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	

	public static void main(String[] args) throws IOException, SQLException {
		Scanner scanner = new Scanner(System.in);
		log.info(DBConnection.getConnection().toString());
		
		
		// 고객 환영 멘트 출력 
		System.out.println("환영합니다. 고객님!! 저희 미용실 영업시간은 오전 10시부터 오후 8시까지입니다.");
		// 고객의 이름(String)과 머리 길이 (int - 1 or 0) 입력받기
		System.out.println("고객님 성함을 입력해주세요: ");
		String customerName = scanner.nextLine();
		System.out.println("고객님 머리 길이를 입력해주세요. 1. 긴 기장 2. 짧은 기장 중 선택해주세요.");
		int hairLength = Integer.parseInt(scanner.nextLine());
		// 고객 객체 생성 후 customerDAO.createCusomer로 customer taber에 고객 저장
		Customer customer = new Customer(customerName, hairLength);
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.createCusomter(customer);
		
		
		// 디자이너 선택해주세요 멘트 출력 - > 호철님
		System.out.println("디자이너를 선택하세요~~~ (1~4)");
		// 디자이너 리스트 db에서 가져온 후 콘솔에 출력
		DesignerDAO designer = new DesignerDAO();
		designer.findAll();
		int selectedDesigner = Integer.parseInt(scanner.nextLine());
		// 디자이너 선택후 선택한 디자이너는 payment tabled의 selected_designer에 넣기
		System.out.println("디자이너 선택 완료!!!");
		// 헤어스타일 선택해주세요 멘트 출력 
		System.out.println("원하는 시술을 선택해주세요.");
		
		// 헤어스타일 리스트 db에서 가져온 후 콘솔에 출력 -> 규한님이 해야함. 
		Hairstyle_menuDAO.printStyleMenu();
		// 헤어스타일을 선택해한 후 선택한 스타일은 customer table의 selected hairstyle에 넣기
		String hairStyle;
		do {
            hairStyle = scanner.nextLine();
            if (!hairStyle.equals("컷") && !hairStyle.equals("파마") && !hairStyle.equals("매직")) {
                System.out.println("시술명으로 입력해주세요.");
            }
        } while (!hairStyle.equals("컷") && !hairStyle.equals("파마") && !hairStyle.equals("매직"));
		customer.setSelectedHairstyle(hairStyle); 
		customerDAO.updateHairstyle(customer); // 선택한 hairsytle update
		System.out.println(hairStyle+"을 선택하셨습니다.");
		// 시술 진행
		try{
			Thread.sleep(1000);
			System.out.println("시술중");
			System.out.println(".");
			System.out.println(".");
			System.out.println(".");
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("빰");
		}
		// 시술 끝내고 
		System.out.println("손님, 머리 다 됐습니다~ 뒷모습 거울로 보여드릴게요~");
		
		// 평점 받기 멘트 출력
		System.out.println("시술이 끝났습니다. 평점 입력해주세요.");
		BigDecimal rating =new BigDecimal(scanner.nextLine());
		customer.setSatisfaction(rating);
		
		DesignerDAO.updateDesignerList(selectedDesigner, rating);
		//designer.findAll();
		//System.out.println(customer.getSatisfaction());
		// 입력 받은 평점 customer table rating에 update
		
		customerDAO.updateSatisfaction(customer);
		


		
		// 만족하면 영수증 보여주고 끝
		// 지혁님
		
		// 불만족하면 죄송합니다. 사과 후 가격 0원으로 계산하고 영수증 보여주고 끝 
		// 지혁님
		
		// 미용실 끝나면 오늘의 하루 매출 보여주고 디자이너 하루 결과 보여주기
		

	}
}

