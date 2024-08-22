package haircutmaven;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import haircutmaven.dao.PaymentDAO;

import java.util.List;
import java.util.Scanner;
import haircutmaven.dao.CustomerDAO;
import haircutmaven.model.Customer;
import haircutmaven.model.Payment;
import haircutmaven.service.HairCut;
import haircutmaven.dao.DesignerDAO;
import haircutmaven.dao.Hairstyle_menuDAO;
import haircutmaven.util.DBConnection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		Scanner scanner = new Scanner(System.in);
		log.info(DBConnection.getConnection().toString());
		
		while(true){
		// 고객 환영 멘트 출력 
		System.out.println("[전 직원]  : 환영합니다 손님!! 저희 미용실 영업시간은 오전 10시부터 오후 8시까지입니다.");
		System.out.println();
		System.out.println("[카운터 알바] : 고객님 성함이 어떻게 되시나요???~~");
		// 고객의 이름(String)과 머리 길이 (int - 1 or 0) 입력받기
		System.out.print("당신의 이름은??: ");
		String customerName = scanner.nextLine();
		System.out.println("[카운터 알바] : 아아~ "+customerName+"!!!!!! 손님 이시구나~~ 참 멋있으시네요~~ 고객님 머리 길이가???~~");
		int hairLength = -1;
		System.out.println();
		while (hairLength != 1 && hairLength != 2) {
		    System.out.println("고객님 머리 길이를 입력해주세요. 1. 긴 기장 2. 짧은 기장 중 선택해주세요.");
		    try {
		        hairLength = Integer.parseInt(scanner.nextLine());
		        if (hairLength != 1 && hairLength != 2) {
		            System.out.println("[카운터 알바] : 똑바로 말하세요 손놈~~");
		        }
		    } catch (NumberFormatException e) {
		        System.out.println("숫자를 입력해 주세요.");
		    }
		}
		if(hairLength==1) {
			System.out.println(customerName+"님의 머리는 아주 [길고] 머리결이 엘레강스~~ 하네요!! 잘 오셨어요");
		}
		else {
			System.out.println(customerName+"님의 머리는 [짧은편 이시구] 머리결이 엘레강스~~ 하네요!! 잘 오셨어요");
		}
		// 고객 객체 생성 후 customerDAO.createCusomer로 customer taber에 고객 저장
		Customer customer = new Customer(customerName, hairLength);
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.createCusomter(customer);
		
		log.info("고객정보 db에 저장 - Name: {}, Hair Length: {}", customerName, hairLength); //로그
		
		
		// 디자이너 선택해주세요 멘트 출력 - > 호철님
		System.out.println("저희 미용실에는 아주 멋진 4명의 디자이너가 있어요~~~");
		System.out.println("[" +customerName+"]님 완전 럭키비키자나~~~ 모든 디자이너중 선택 가능하세요!!!");
		System.out.println("[누구한테 머리를 짤라볼까??] (1~4중 선택)");
		// 디자이너 리스트 db에서 가져온 후 콘솔에 출력
		DesignerDAO designer = new DesignerDAO();
		designer.findAll();
		int selectedDesigner = Integer.parseInt(scanner.nextLine());
		// 디자이너 선택후 선택한 디자이너는 payment tabled의 selected_designer에 넣기
		System.out.println("디자이너 선택 완료!!!");
		String designerName = DesignerDAO.getDesignerNameById(selectedDesigner);
		System.out.println("["+designerName+"] : 후후훗... 날 선택하다니 최고의 머리를 만들어 주지");
		System.out.println("["+designerName+"] : 이름이 [" +customerName+"]님이라 했나 원하는 스타일로 짤라주지");
		// 헤어스타일 선택해주세요 멘트 출력 
		System.out.println("헤어스타일 선택해주세요.");
		//System.out.println("원하는 시술을 선택해주세요.");
		

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

		System.out.println(hairStyle+"(으)로 정했나보군 오랜만에 실력발휘를 해보지");
		// 시술 진행

		try{
			Thread.sleep(1000);
			System.out.println("=====시술중=====");
			System.out.println("꺅~~");
			System.out.println("오~~~");
			System.out.println("멋있자너~~");
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("빰");
		}
		// 시술 끝내고 
		
		System.out.println("손님, 머리 다 됐습니다~ 뒷모습 거울로 보여드릴게요~");
		//랜덤 머리자르기
		if(HairCut.RandomCut(hairStyle).equals("fail"))
			{
					System.out.println("아이고 손이 미끄러졌네??");
					hairStyle="폭탄 머리";
			}
	
		System.out.println("두둥 ~ "+ hairStyle);
		
		HairCut.printHairstyle(hairLength,hairStyle);
		// 평점 받기 멘트 출력
		System.out.println("["+designerName+"]이 당신을 뚫어져라 쳐다보고있다......");
		System.out.println("시술이 끝났다 디자이너 평점을 입력하자. 1~5");
		BigDecimal rating =new BigDecimal(scanner.nextLine());
		customer.setSatisfaction(rating);
		
		DesignerDAO.updateDesignerList(selectedDesigner, rating);
		//designer.findAll();
		//System.out.println(customer.getSatisfaction());
		// 입력 받은 평점 customer table rating에 update
		
		customerDAO.updateSatisfaction(customer);
		
		log.info("customerid"+customer.getCustomerId());
	

		System.out.println("카운터 알바 : 이번 시술은 만족스러우셨나요? 0.네, 만족스러워요 1.아니요");
		Integer satisfy = Integer.parseInt(scanner.nextLine());
		int payamount = 0;
		if (satisfy == 1 && hairStyle.equals("폭탄 머리"))
		{
			System.out.println("죄송합니다....");
			System.out.println("["+designerName+"]거기 너!! 시술중에 누가 졸라고했어! 그러니까 머리가 망하지");
			payamount = 0;
			// 불만족하면 죄송합니다. 사과 후 가격 0원으로 계산하고 영수증 보여주고 끝 
		}else {
			//선택하신 비용
			payamount = 100000; //얼만지 받아야됨.
			// 만족하면 영수증 보여주고 끝
		}
		
		//paymentdb에 insert.
		Date currentDate = new Date(System.currentTimeMillis());
		PaymentDAO.InsertPayment(currentDate, payamount, selectedDesigner, customer.getCustomerId(), 1);

		Payment onepersonpayment = PaymentDAO.GetOnePaymentByCustomer(customer.getCustomerId());
		System.out.println(onepersonpayment.billing(DesignerDAO.getDesignerNameById(selectedDesigner), customer.getCustomerName(), hairStyle));
		
		System.out.print("우리 미용실에 또 올 손님이 있을까??~~  있다면 1 아니면 아무 키나 입력 : ");
		String oneMore = scanner.nextLine();
		oneMore.trim();
		if(!oneMore.equals("1")) {
			System.out.println("오늘하루도 고생했다 우리 디자이너들 오늘 얼마나 벌었을까??");
			System.out.println("------정산서------");
			System.out.println("날짜: " + currentDate);
			
			
			// 오늘의 총 매출 계산
			List<Payment> paymentsToday = PaymentDAO.GetPaymentListByDate(currentDate.toString());
			int totalAmount = paymentsToday.stream()
					.mapToInt(Payment::getAmount)
					.sum();
			
			// 출력
			System.out.println("오늘의 총 매출: " + totalAmount + "원");
			System.out.println("----------------\n\n");
			System.out.println("======================");
			System.out.println("미용실 영업 종료함니다~~~~~~");
			System.out.println("======================");
			break;
		}
		// 미용실 끝나면 오늘의 하루 매출 보여주고 디자이너 하루 결과 보여주기
		
		
	        
	       
	        


	}
		
	}
	
	
}

