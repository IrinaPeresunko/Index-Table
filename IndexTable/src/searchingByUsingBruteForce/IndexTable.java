package searchingByUsingBruteForce;

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
		//this.countOfTag = 10;
	}
	private class IndexesOfTag{
		private String tag;
		private int[] ids;
		private int i;
		protected int countOfIndexes = 5;
		
		public IndexesOfTag(String tag, int id){
			this.tag = tag;
			ids = new int[this.countOfIndexes];
			i=0;
			fillArrayOfIndexesForTag(id);
		}
		private void fillArrayOfIndexesForTag(int id){
			if(i>this.countOfIndexes-1){
				recreateArrayOfIds();
			}
			this.ids[i] = id;
			i++;
		}
		private void recreateArrayOfIds() {
			int[] temp = this.ids;
			this.countOfIndexes = this.countOfIndexes*2;
			this.ids = new int[this.countOfIndexes];
			for(int i=0;i<temp.length;i++){
				this.ids[i] = temp[i];
			}
			//System.err.println(Arrays.toString(this.ids));
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
			if(N>countOfTag-1){
				recreateArrayOfTags();
			}
			if(isTagAlreadyExist(input[i])!=true){
				tags[N] = new IndexesOfTag(input[i], this.id);
				orderingItems();
				N++;
			}
			else{
				int row = getRowForElement(input[i]);
				tags[row].fillArrayOfIndexesForTag(this.id);
			}
		}
		id++;
	}
	private boolean isElementAlreadyExist(int id,int[] array){
		int length = 0;
		while(length<array.length && array[length]!=0){
			if(id == array[length]){
				return true;
			}
			else length++;
		}
		return false;
	}
	private int calculateCountOfIds(int[] position){
		int countOfIds = 0;
		for(int i=0;i<position.length;i++){
			countOfIds += tags[position[i]].countOfIndexes;
		}
		return countOfIds;
	}
	public void searchIds(String userInput){
		int[] position = getPositionForTagsInTheString(userInput);
		
		int countOfIndexes = calculateCountOfIds(position);
		
		int[] output = new int[position.length*countOfIndexes];
		for(int i=0,k=0;i<position.length;i++){
			int length = tags[position[i]].ids.length;
			int j=0; 
			while(j<length && tags[position[i]].ids[j]!=0){
				if(isElementAlreadyExist(tags[position[i]].ids[j],output)!=true){
						output[k] = tags[position[i]].ids[j];
						k++;j++;
				}
				else{
					j++;
				}
			}
		}
		int j=0;
		System.out.print("\n"+"Found ids: [");
		while(j<countOfIndexes && output[j]!=0){
			System.out.print(output[j]+", ");
			j++;
		}
		System.out.print("]");
	}
	private int[] getPositionForTagsInTheString(String userInput){
		String[] input = parsingUserInputString(userInput);
		int[] position = new int[getCountOfWords(userInput)];
		for(int i=0;i<getCountOfWords(userInput);i++){
			int row = getRowForElement(input[i]);
			position[i] = row;
					//divideByPart(row,0,N-1);
		}
		//System.out.println(Arrays.toString(position));
		return position;
	}
	private void recreateArrayOfTags(){
		IndexesOfTag[] temp = this.tags;
		this.countOfTag = countOfTag*2;
		this.tags = new IndexesOfTag[this.countOfTag];
		for(int i=0;i<temp.length;i++){
			this.tags[i] = temp[i];
		}
		//System.out.println(Arrays.toString(tags));
	}
	private int getRowForElement(String element){
		int length = N-1;
		//System.out.println("length="+length);
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
	private boolean isTagAlreadyExist(String input){
		int i=0;
		
		while(i<N){
			if(input.equals(this.tags[i].getTag())!=true){
				i++;
			}
			else{
				return true;
			}
		}
		return false;
	}
	private void orderingItems(){
		int length = N;
		int current = N;
		boolean flag = false;
		
		while(length>0 && flag!=true){
			//if(tags[current].getTag().charAt(0)<tags[length-1].getTag().charAt(0)){
			if(tags[length-1].getTag().compareTo(tags[current].getTag())>0){
				IndexesOfTag temp = tags[length-1];
				tags[length-1] = tags[current];
				tags[current] = temp;
				current = length-1;
				length--;
			}
			else{
				flag = true;
			}
		}	
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
