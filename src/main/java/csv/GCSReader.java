package csv;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.nio.channels.Channels;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import org.relique.io.TableReader;

public class GCSReader implements TableReader {
	
	public Reader getReader(Statement statement, String tableName) throws SQLException {

			
			try {
				Credentials credentials = GoogleCredentials
		                  .fromStream(new FileInputStream("cloud-cost-control.json"));
				Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
				.build().getService();
				String bucketName = "subhashmeenatestbucket3";  
				
				Bucket bucket = storage.get(bucketName);
						
				
				Blob blob = bucket.get("acme.csv");
				ReadChannel channelReader = blob.reader();
				InputStream inputStream = Channels.newInputStream(channelReader);
				InputStreamReader reader = new InputStreamReader(inputStream);
				return reader;
			} catch(Exception e) {
				System.out.println("Caught exception: "+e.getMessage());
				throw new SQLException(e.getMessage());
			}

			
		}
	
	  public List<String> getTableNames(Connection connection){
	    Vector<String> v = new Vector<String>();
	    v.add("acme");
	    return v;
	  }

	}


