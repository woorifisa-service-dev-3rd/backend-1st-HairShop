package haircutmaven.model;

import lombok.Builder;

@Builder
public class Hairstyle_menu {
	private int styleId; // 스타일 ID
	private String hairstyle; // 헤어스타일
	private int cost; // 비용

}
