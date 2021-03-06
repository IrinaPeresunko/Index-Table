package roughCopy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IndexTableTest {

	public static void main(String[] args) {
		long start=System.nanoTime();
		
		IndexTable indexTable = new IndexTable();
	    indexTable.add("ids,necessary,tags,searching");
	    indexTable.add("enter,ids,tags,searching");
	    indexTable.add("ids,for,tags");
	    indexTable.add("necessary");
	    
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
	    indexTable.add(userInput);
	    indexTable.print();
	}

}
