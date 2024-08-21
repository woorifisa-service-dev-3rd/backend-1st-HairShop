package haircutmaven.dao;

import haircutmaven.model.Hairstyle_menu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hairstyle_menuDAO {
	static final String USER_NAME = "root";
	static final String PASSWORD = "1234";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String DATABASE="hairshop";
	
	public Hairstyle_menu getHairstyleMenu(){
		final String selectQuery="SELECT * FROM Hairstyle_menu";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		Hairstyle_menu menu = null;
		try{
			connection = DriverManager.getConnection(DB_URL + DATABASE, USER_NAME, PASSWORD);
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			// 쿼리 수행 결과값을 가지고 있는 객체(ResultSet)
			resultSet = statement.executeQuery(selectQuery);
			resultSet.last(); // 커서를 마지막 행으로 이동
	        int size = resultSet.getRow(); // 현재 행의 번호(즉, 총 행 수)를 가져옴
	        resultSet.beforeFirst(); // 커서를 다시 처음으로 이동
	        menu = new Hairstyle_menu(size);
	        int index=0;
			while(resultSet.next()) {
				int style_id=resultSet.getInt("style_id");
				String hairstyle=resultSet.getString("hairstyle");
				int cost=resultSet.getInt("cost");
				menu.setStyle_ids(index, style_id);
				menu.setHairstyle(index, hairstyle);
				menu.setCost(index, cost);
				index++;
			}
		}catch(SQLException e){
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();	
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return menu;
	}
	public static void main(String[] args) {
		Hairstyle_menuDAO dao = new Hairstyle_menuDAO();
        Hairstyle_menu menu=dao.getHairstyleMenu();
        System.out.println("스타일 번호: "+menu.getStyle_ids(1)+ " 헤어스타일: " + menu.getHairstyle(1) + ", 비용: " + menu.getCost(1));
	}
}
