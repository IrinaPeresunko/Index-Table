package indexTable;

import java.util.Arrays;

class IndexTable {
	private int id;
	private int N;
	private int[] countOfElements;
	private int countOfRow = 16;
	private int countOfElementsInRow = 4;
	private String[][] tags;
	private int[][] ids;
	
	public IndexTable(){
		this.tags = new String[countOfRow][1];
		this.ids = new int[countOfRow][countOfElementsInRow];
		this.id=1;
		this.N=0;
		this.countOfElements=new int[countOfRow];
	}
	private int getRowForElement(String element){
		int length = N;
		int row = -1;
		while(length>=0 && row!=length){
			if(element.equals(tags[length][0])==true){
				row = length;
			}
			else{
				length--;
			}
		}
		return row;
	}
	private boolean isAlreadyExist(String input){
		int length = N;
//		int row = getRowForElement(input);
		while(length>=0){
			if(input.equals(tags[length][0])!=true){
				length--;
			}
			else{
				return true;
			}
		}
		return false;
	}
	public void add(String userInput){
		String[] input = parsingUserInputString(userInput);

		for(int i=0;i<getCountOfWords(userInput);i++){
			if(isAlreadyExist(input[i])!=true){
				tags[N][0] = input[i];
				ids[N][countOfElements[N]] = id;
				countOfElements[N] = ++countOfElements[N];
				N++;
			}
			else{
				int row = getRowForElement(input[i]);
				ids[row][countOfElements[row]] = id;
				countOfElements[row] = ++countOfElements[row];
				//N++;
			}
		}
		id++;
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
		System.out.println("tags = "+Arrays.deepToString(tags));
		System.out.println("ids = "+Arrays.deepToString(ids));
	}
}
