package haircutmaven.model;

import java.math.BigDecimal;

import lombok.Builder;

public class Customer {

	private int customerId; // 고객 ID
	private int hairLength; // 머리 길이
	private BigDecimal satisfaction; // 만족도
	private String selectedHairstyle; // 선택한 헤어스타일
	private String customerName; // 고객 이름
	
	// Constructor
    public Customer(String customerName, int hairLength) {
        this.customerName = customerName;
        this.hairLength = hairLength;
    }

    // Getters and Setters
    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
    
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getHairLength() {
		return hairLength;
	}

	public void setHairLength(int hairLength) {
		this.hairLength = hairLength;
	}

	public String getSelectedHairstyle() {
		return selectedHairstyle;
	}

	public void setSelectedHairstyle(String selectedHairstyle) {
		this.selectedHairstyle = selectedHairstyle;
	}

	

	

    

    

}
