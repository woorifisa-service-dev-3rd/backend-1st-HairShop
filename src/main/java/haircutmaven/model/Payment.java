package haircutmaven.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
public class Payment {

	private int paymentId; // 결제 ID
	private Date paymentDate; // 결제 날짜
	private int amount; // 결제 금액
	private int designerId; // 디자이너 ID (외래 키)
	private int customerId; // 고객 ID (외래 키)
	private int styleId; // 스타일 ID (외래 키)

	 public String billing(String designername,String customername,String stylename) {
	        return "------------------------\n" +
	               "        RECEIPT         \n" +
	               "------------------------\n" +
	               "날짜: " + paymentDate + "\n" +
	               "결제 금액: " + amount + " KRW\n" +
	               "디자이너 명: " + designername + "\n" +
	               "손님 명: " + customername + "\n" +
	               "스타일 명: " + stylename + "\n" +
	               "------------------------";
	    }

}
