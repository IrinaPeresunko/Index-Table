package indexTableImplementsAsHashMap;

import java.util.HashMap;
import java.util.Map;

public class IndexTable {
	private Map indexTable;
	private int N;
	
	public IndexTable(){
		this.indexTable = new HashMap();
		this.N=0;
	}
	public void add(String userInput){
		String[] tags = parsingUserInputString(userInput);
		
		for(int i=0;i<tags.length;i++){
			if(!indexTable.containsKey(tags[i])){
				indexTable.put(tags[i], new Integer(N));
				N++;
			}
		}
	}
	
	public void searchIds(String userInput){
		String[] tags = parsingUserInputString(userInput);
		
		int[] countOfIndexes = new int[tags.length];
		
		for(int i=0,j=0;i<tags.length;i++){
			countOfIndexes[j] = ((Integer) indexTable.get(tags[i])).intValue(); 
			j++;
		}
	}
	public void print(){
//		for (Map.Entry entry : indexTable.entrySet()) {
//		    System.out.println("Key: " + entry.getKey() + " Value: "
//		        + entry.getValue());
//		}
	}
	private int getCountOfWords(String userInput){
		char[] expression = new char[userInput.length()];
		int countOfWords=1;
		
		for(int i=0;i<userInput.length();i++){
			expression[i] = userInput.charAt(i);
			if(expression[i]==','){
				countOfWords++;
			}
		}
		return countOfWords;
	}
	private String[] parsingUserInputString(String userInput){
		char[] expression = new char[userInput.length()];
		
		for(int i=0;i<userInput.length();i++){
			expression[i] = userInput.charAt(i);
		}
		
		int countOfWords=getCountOfWords(userInput);
		//System.out.println("countOfWords = "+countOfWords);
		
		int[] positionOfSpaces  = new int[countOfWords];
		for(int i=0,k=0;i<userInput.length() && k<countOfWords;i++){
			if(expression[i]==','){
				positionOfSpaces[k]=i;
				k++;
			}
		}
		positionOfSpaces[countOfWords-1] = expression.length;
		//System.out.println("positionOfSpaces: "+Arrays.toString(positionOfSpaces));
		String[] inputTags = new String[countOfWords];
		int index=0;
		
		for(int i=0;i<countOfWords;i++){
			inputTags[i]=userInput.substring(index, positionOfSpaces[i]);
			index = positionOfSpaces[i]+1;	
		}
		return inputTags;
	}
	
	
	
}
