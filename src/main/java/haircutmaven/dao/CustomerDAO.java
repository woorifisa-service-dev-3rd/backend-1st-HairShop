package haircutmaven.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import haircutmaven.model.Customer;
import haircutmaven.util.DBConnection;
public class CustomerDAO {
	/*
	 *	1. DB에 Customer 데이터 저장하는 메서드 생성
		2. DB에 Customer 데이터 update 하는 메서드 생성
		3. DB에서 저장된 Customer 데이터 가져오는 메서드 생성
	 */
	
	// customer 객체를 생성해서 customer 정보를 db cusomter table에 저장하기
	public void createCusomter(Customer customer) throws IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		final String selectQuery = String.format("INSERT INTO CUSTOMER (customer_name, hair_length) VALUES ('%s', %d)" ,  customer.getCustomerName(),
                customer.getHairLength());
		
		Statement statement = connection.createStatement();
		statement.executeUpdate(selectQuery, Statement.RETURN_GENERATED_KEYS);
		
		// Customer 생성시 db애서 자동으로 설정된 customer_id를 얻어와서 객체 customer_id에 적용하기
		ResultSet resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
		    int generatedId = resultSet.getInt(1);
		    customer.setCustomerId(generatedId);
		}
		
		
		resultSet.close();
	    statement.close();
	    connection.close();
		
	}
	
	// customer가 선택한 헤어스타일은 customer table의 selected_hairstyle에 넣기
	public void updateHairstyle(Customer customer) throws IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		Statement statement = connection.createStatement();
		final String updateQuery = String.format(
	                "UPDATE CUSTOMER SET selected_hairstyle = '%s' WHERE customer_id = %d",
	                customer.getSelectedHairstyle(),
	                customer.getCustomerId()
	            );
		statement.executeUpdate(updateQuery);
		
		
	    statement.close();
	    connection.close();
	}
	
	
	// customer가 선택한 평점을 customer table의 satisfaction에 넣기
	public void updateSatisfaction(Customer customer) throws IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		Statement statement = connection.createStatement();
		final String updateQuery = String.format(
                "UPDATE CUSTOMER SET satisfaction = '%s' WHERE customer_id = %d",
                customer.getSatisfaction(),
                customer.getCustomerId()
            );
		statement.executeUpdate(updateQuery);
	
		
        statement.close();
        connection.close();
	       
	}
	
	// customer_id를 받으면 customer_name 받아오기
	public String getUsername(int customerId) throws IOException, SQLException {
		String customerName = null;
		Connection connection = DBConnection.getConnection();
		Statement statement = connection.createStatement();
		final String getUsernameQuery = String.format(
                "SELECT customer_name FROM CUSTOMER WHERE customer_id = %d",
                customerId
            );
		 ResultSet resultSet = statement.executeQuery(getUsernameQuery);
		
        if (resultSet.next()) {
            customerName = resultSet.getString("customer_name");
        }
		
        System.out.println(customerId + customerName );
		
        resultSet.close();
        statement.close();
        connection.close();
		return customerName;
	}
	
	
	
}