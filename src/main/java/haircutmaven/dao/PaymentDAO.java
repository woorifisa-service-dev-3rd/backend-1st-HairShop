package haircutmaven.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import haircutmaven.model.Payment;
import haircutmaven.util.DBConnection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentDAO {

	public static void InsertPayment(Date paymentDate, int amount, int designerId, int customerId,int styleId) 
			throws IOException
	{
		final String insertquery = 
				"INSERT INTO Payment (payment_date,amount,designer_id,customer_id,style_id) Values (?,?,?,?,?)" ;
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertquery,Statement.RETURN_GENERATED_KEYS);) 
		{
			statement.setDate(1,paymentDate);
			statement.setInt(2,amount);
			statement.setInt(3,designerId);
			statement.setInt(4,customerId);
			statement.setInt(5,styleId);
					
			int rowaffected= statement.executeUpdate();
					
			if (rowaffected>0)
			{
				log.info("insertok");
			}else
			{
				log.error("error insert");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static List<Payment> GetPaymentListByDate(String paymentDate) throws IOException, ParseException
	{
		List<Payment> payments = new ArrayList<>();
		
		final String selectallquerybydate ="select * from Payment where payment_date = ?";
		try (Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(selectallquerybydate);
			)
		{
			Date sqlDate = stringToSqlDate(paymentDate);
			statement.setDate(1,sqlDate);
			ResultSet resultset= statement.executeQuery();
			
			while(resultset.next())
			{
				int paymentid = resultset.getInt("payment_id");
				Date paymentdatefromdb = resultset.getDate("payment_date");
				int amount = resultset.getInt("amount");
				int designerid = resultset.getInt("designer_id");
				int customerid = resultset.getInt("customer_id");
				int styleid = resultset.getInt("style_id");
				payments.add(Payment.builder().paymentId(paymentid).paymentDate(paymentdatefromdb).amount(amount)
						.designerId(designerid).customerId(customerid).styleId(styleid).build());
			}
			resultset.close();
			return payments;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			log.error("date로 payment 가져오는 도중 오류 발생");
			return null;
		}
		
		
	}
	
	 private static Date stringToSqlDate(String dateString) throws ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = dateFormat.parse(dateString);
	        return new java.sql.Date(parsedDate.getTime());
	    }
	 
	 public static Payment GetOnePaymentByCustomer(int customerId) throws IOException, ParseException {
		    final String selectQueryByCustomer = "SELECT * FROM Payment WHERE customer_id = ? ORDER BY payment_id DESC LIMIT 1";
		    
		    try (Connection connection = DBConnection.getConnection();
		         PreparedStatement statement = connection.prepareStatement(selectQueryByCustomer)) {
		        
		        statement.setInt(1, customerId);

		        try (ResultSet resultSet = statement.executeQuery()) { 
		            if (resultSet.next()) { 
		                int paymentId = resultSet.getInt("payment_id");
		                Date paymentDateFromDb = resultSet.getDate("payment_date");
		                int amount = resultSet.getInt("amount");
		                int designerId = resultSet.getInt("designer_id");
		                int customerIdFromDb = resultSet.getInt("customer_id");
		                int styleId = resultSet.getInt("style_id");
		                
		                return Payment.builder()
		                        .paymentId(paymentId)
		                        .paymentDate(paymentDateFromDb)
		                        .amount(amount)
		                        .designerId(designerId)
		                        .customerId(customerIdFromDb)
		                        .styleId(styleId)
		                        .build();
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        log.error("Error occurred while fetching payment by customer ID", e);
		    }
		    return null;
		}
	
}
