package haircutmaven;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import haircutmaven.dao.PaymentDAO;
import haircutmaven.util.DBConnection;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	
	public static void main(String[] args) throws IOException, SQLException, ParseException {
		log.info(DBConnection.getConnection().toString());
		PaymentDAO.InsertPayment(null, 10000, 1, 1, 2);
		try {
			log.info(PaymentDAO.GetPaymentListByDate("2024-08-21").toString());
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(PaymentDAO.GetOnePaymentByCustomer(1).toString());
	}
}
 