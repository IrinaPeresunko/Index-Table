package indexTableSimpleRealization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IndexTableTest {

	public static void main(String[] args) {
		long start=System.nanoTime();
		
		String[] t = {
				"ids","ids","ids","ids","ids","necessary","necessary","necessary","necessary","necessary","tags","searching","i","love","irka","cry","baby","enter","bla","oye","eee",
				"test","does","it","work","hope","yes","no","a","b","c","d","e","f","the", "london", "of", "middle",
				"Rosemary", "fell", "was", "not", "exactly", "beautiful", "she", "young", "brilliant", "extremely", "modern",
				"dressed", "and", "amazingly","promotion", "salary", "trade", "worker", "few", "foods", "people", "feel", 
				"passionate", "about", "passion", "goes", "beyond", "love","sweetness","candies", "desserts",
				"peoplecrave", "caramel","whipped", "cream", "different", "true", "chocoholic", "thinking", "evoke",
				"pleasurable", "response", "grab", "nice", "cup", "cocoa",
				"beautiful", "new", "watch", "run", "eighteen", "months", "losing", "gaining", "breaking", "part", "machinery", "stopping",
				"believe", "infallible", "judgments", "time", "consider", "constitution", "anatomy", "imperishable", "night", "down", "grieved", "recognized", "messenger", "forerunner",
				"calamity","prepared","cross-question", "rigidly", "serious", "cost", "hundred", "dollars", "originally", "seemed", "paid", "repairs", "presently", "watchmaker", "old",
				"acquaintance", "steamboat", "engineer", "other", "good", "engineer", "either", "examined", "parts", "carefully", "delivered", "verdict", "confidence", "manner", 
				"steam", "monkey"};
		
		IndexTable indexTable = new IndexTable();
		
	    for(int i=0,k=0;i<10000;i++){
	    	indexTable.add(t[k]);
	    	if(k<t.length-1) k++;
	    	else{
	    		k=0;
	    	}
	    }
	   
	    indexTable.print();
	    
	    long end=System.nanoTime();
		System.out.println("time:"+((end-start)/1000000000.0));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userInput = null;		
	    System.out.println("Enter necessary tags for searching ids: "); 
	      try {
			userInput = (String) br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    start=System.nanoTime();
	    indexTable.add(userInput);
	   // indexTable.print();
	    indexTable.searchIds(userInput);
	    end=System.nanoTime();
		System.out.println("\n"+"time:"+((end-start)/100000000.0));
	}
}
