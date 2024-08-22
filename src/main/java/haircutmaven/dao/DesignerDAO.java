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
			System.out.println("==================================================================");
			System.out.printf("%-10s %-20s %-15s %-10s %-10s%n", "번호", "이름", "직급", "시술 횟수", " 평점");
			while(resultSet.next()) {
				int designerId = resultSet.getInt("designer_Id");        // 디자이너 ID
				String designerName = resultSet.getString("designer_Name");   // 디자이너 이름
				String designerRank = resultSet.getString("designer_Rank");   // 디자이너 등급
				int totalCount = resultSet.getInt("total_Count");        // 총 작업 수
				BigDecimal rating = resultSet.getBigDecimal("rating");
				designers.add(new Designer(designerId,designerName,designerRank,totalCount,rating));
				  // 각 디자이너 정보를 표 형식으로 출력
			    System.out.printf("%-10d %-20s %-15s %-10d %-10.2f%n", designerId, designerName, designerRank, totalCount, rating);
			}
			System.out.println("==================================================================");
			
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
	public static void updateDesignerList(int designer_id, BigDecimal rating) {
	    // 업데이트 쿼리: total_count를 증가시키고, 필요한 경우 designer_rank를 변경
	    final String updateQuery = "UPDATE designer SET total_count = total_count + 1, " +
	                               "rating = ((rating * (total_count - 1)) + ?) / total_count, " +
	                               "designer_rank = CASE WHEN total_count = 1 THEN '일반 디자이너' ELSE designer_rank END " +
	                               "WHERE designer_id = ?";

	    try {
	        connection = DBConnection.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
	        preparedStatement.setBigDecimal(1, rating);
	        preparedStatement.setInt(2, designer_id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static String getDesignerNameById(int designer_id) {
		final String selectQuery = "SELECT designer_name FROM designer WHERE designer_id = ?";
		String designerName = null;

		try {
			connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, designer_id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				designerName = resultSet.getString("designer_name");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return designerName;
	}
}
