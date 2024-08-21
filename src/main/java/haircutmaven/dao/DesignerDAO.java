package haircutmaven.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import haircutmaven.model.Designer;
import haircutmaven.util.DBConnection;

public class DesignerDAO {
	
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	
	List<Designer> designers = new ArrayList<>();
	public List<Designer> findAll() throws SQLException{
		final String selectQuery = "SELECT * FROM designer";
		try {
			connection = DBConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectQuery);
			
			while(resultSet.next()) {
				int designerId = resultSet.getInt("designer_Id");        // 디자이너 ID
				String designerName = resultSet.getString("designer_Name");   // 디자이너 이름
				String designerRank = resultSet.getString("designer_Rank");   // 디자이너 등급
				int totalCount = resultSet.getInt("total_Count");        // 총 작업 수
				BigDecimal rating = resultSet.getBigDecimal("rating");
				designers.add(new Designer(designerId,designerName,designerRank,totalCount,rating));
				System.out.println(designerId+". "+designerName +" " +designerRank+ "평점 : "+ rating);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			connection.close();
		}
		return designers;
	}
	

//	public static List<Designer> insertDesignerList(List<Designer> designers,int designerId, String designerName, String designerRank, int totalCount, BigDecimal rating ) {
//		designers.add(new Designer(designerId,designerName,designerRank,totalCount,rating));
//		return designers;
//	}
	
	//디자이너 업데이트 메소드 - 디자이너id와 고객의 평점을 받아서 처리한다.
	public static void updateDesignerList(int designer_id, int rating){
		
		 final String updateQuery = "UPDATE designer SET total_count = total_count + 1, " +
                 "rating = ((rating * (total_count - 1)) + ?) / total_count " +
                 "WHERE designer_id = ?";
		
		try {
			 connection = DBConnection.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		        preparedStatement.setInt(1, rating);
		        preparedStatement.setInt(2, designer_id);
		        preparedStatement.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
