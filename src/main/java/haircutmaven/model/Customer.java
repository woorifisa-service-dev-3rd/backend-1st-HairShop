package haircutmaven.model;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class Customer {
	
	   private int customerId;            // 고객 ID
	    private boolean hairLength;        // 머리 길이
	    private BigDecimal satisfaction;   // 만족도
	    private String selectedHairstyle;  // 선택한 헤어스타일
	    private String customerName;       // 고객 이름

}
