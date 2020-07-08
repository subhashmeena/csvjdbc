package csv;
import java.sql.*;
import org.relique.jdbc.csv.CsvDriver;
public class Main {
	
	public static void main(String ...args) throws SQLException {
		try
	    {
	      Class.forName("org.relique.jdbc.csv.CsvDriver");
	      // Give name of Java class that provides database tables.
	      Connection conn = DriverManager.getConnection("jdbc:relique:csv:class:" + GCSReader.class.getName());
	      Statement stmt = conn.createStatement();
	      String sql = "SELECT * FROM acme where acme='0.03016'";
	      ResultSet results = stmt.executeQuery(sql);
	      CsvDriver.writeToCsv(results, System.out, true);
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	}

}
