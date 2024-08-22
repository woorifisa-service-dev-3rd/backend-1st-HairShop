package haircutmaven;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import haircutmaven.dao.DesignerDAO;
import haircutmaven.util.DBConnection;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	
	public static void main(String[] args) throws IOException, SQLException {
		log.info(DBConnection.getConnection().toString());
		Connection conn = DBConnection.getConnection();
		DesignerDAO designer = new DesignerDAO();
		designer.findAll();
		designer.updateDesignerList(1, 4);
		designer.findAll();
	}
}
 