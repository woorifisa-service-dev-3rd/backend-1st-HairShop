package haircutmaven;

import java.io.IOException;
import java.sql.SQLException;

import haircutmaven.util.DBConnection;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	
	public static void main(String[] args) throws IOException, SQLException {
		log.info(DBConnection.getConnection().toString());
	}
}
 