package haircutmaven.model;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class Designer {
	
    private int designerId;        // 디자이너 ID
    private String designerName;   // 디자이너 이름
    private String designerRank;   // 디자이너 등급
    private int totalCount;        // 총 작업 수
    private BigDecimal rating;     // 평점
    
	public Designer(int designerId, String designerName, String designerRank, int totalCount, BigDecimal rating) {
		super();
		this.designerId = designerId;
		this.designerName = designerName;
		this.designerRank = designerRank;
		this.totalCount = totalCount;
		this.rating = rating;
	}

	public Designer() {
		super();
	}
	
}
