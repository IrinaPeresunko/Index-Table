package indexTableSimpleRealization;

import java.util.Arrays;

class IndexTable {

	private IndexesOfTag[] tags;
	private int countOfTag = 10;
	private int N;
	private int id;
	
	public IndexTable(){
		this.tags = new IndexesOfTag[countOfTag];
		this.N=0;
		this.id = 1;
	}
	private class IndexesOfTag{
		private String tag;
		private int[] ids;
		private int countOfIndexes = 5;
		private int i;
		
		public IndexesOfTag(String tag, int id){
			this.tag = tag;
			ids = new int[countOfIndexes];
			i=0;
			fillArrayOfIndexesForTag(id);
		}
		public boolean isIdAlreadyExist(int id){
			int length = 0;
			while(length<i){
				if(id == this.ids[length]){
					return true;
				}
				else length++;
			}
			return false;
		}
		private void fillArrayOfIndexesForTag(int id){
			if(isIdAlreadyExist(id)!=true){
				this.ids[i] = id;
				i++;
			}
			else return;
		}
		public String getTag(){
			return this.tag;
		}
		public String toString(){
			return "tag = "+'"'+this.tag+'"' + " : " + Arrays.toString(this.ids);
		}
	}
	public void add(String userInput){
	String[] input = parsingUserInputString(userInput);

	for(int i=0;i<getCountOfWords(userInput);i++){
		if(isAlreadyExist(input[i])!=true){
			tags[N] = new IndexesOfTag(input[i], this.id);
			N++;
		}
		else{
			int row = getRowForElement(input[i]);
			tags[row].fillArrayOfIndexesForTag(this.id);
		}
	}
	id++;
}
	private int getRowForElement(String element){
		int length = N-1;
		int row = -1;
		while(length>=0 && row!=length){
			if(element.equals(tags[length].getTag())==true){
				row = length;
			}
			else{
				length--;
			}
		}
		return row;
	}
	private boolean isAlreadyExist(String input){
		int i=0;
		
		while(i<N){
			if(input.equals(tags[i].getTag())!=true){
				i++;
			}
			else{
				return true;
			}
		}
		return false;
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
	public void print(){
		for(int i=0;i<N;i++){
			System.out.println(tags[i].toString());
		}	
	}
}
