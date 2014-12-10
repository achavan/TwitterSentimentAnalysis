package sample;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;



import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

public class Twitter {


	 static FileOutputStream fos = null;
       static OutputStreamWriter osw = null;
       static BufferedWriter bw = null;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException 
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setJSONStoreEnabled(true)
		  .setOAuthConsumerKey("G0z6UhwhTeTUPbtnr9A5AQ")
		  .setOAuthConsumerSecret("2OBrYSmOG7h2FAU1qbDC7qNE1dlwecM6VSHzcYdsxAc")
		  .setOAuthAccessToken("1212146767-5zkw6mVzWcxzQMGtKDwuqe3LSJVyBm6lOVKnLC9")
		  .setOAuthAccessTokenSecret("jjua2XhmhslhtcoxqX1axGDdOMEzr7LweF29RCIwzznKa");
		TwitterFactory tf = new TwitterFactory(cb.build());
		 File file=null;
        String fileName = "statuses" +".json";
        Random or = new Random();
		
		file=new File(fileName);
		if(!file.exists())
		{	
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   fos = new FileOutputStream(fileName,true);
           osw = new OutputStreamWriter(fos, "UTF-8");
           bw = new BufferedWriter(osw);
	
		twitter4j.Twitter twitter = tf.getInstance();

        Query query = new Query("Game of Thrones");
        
        query.setCount(1000);
        query.setLang("en");
        
        QueryResult result;
            try {
				result = twitter.search(query);
		
		
				 for (Status status : result.getTweets()) {
				        //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
				        String rawJSON = TwitterObjectFactory.getRawJSON(status);
				        
				        System.out.println(rawJSON);
				        storeJSON(rawJSON, fileName);
				    }
            // String rawJSON = TwitterObjectFactory.getRawJSON(result);
                 
              //storeJSON(result, fileName);
                // System.out.println(result.getTweets());
                 	
          
        	} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            finally {
	            if (bw != null) {
	                try {
	                    bw.close();
	                } catch (IOException ignore) {
	                }
	            }
		
	        	}
	}

	private static void storeJSON(String rawJSON, String fileName) throws FileNotFoundException, UnsupportedEncodingException 
	{
	        try {
	         
	            bw.write(rawJSON);
	            bw.newLine();
	           
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

}
}
