package indexTableImplementsAsHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IndexTable {
	private Map<String, IndexesOfTags> indexTable;
	IndexesOfTags[] indexes;
	private int N;
	private int counter;
	private int countOfIndexes = 10;
	
	public IndexTable(){
		this.indexTable = new HashMap<String, IndexesOfTags>();
		this.indexes = new IndexesOfTags[countOfIndexes];
		this.N=0;
		this.counter=0;
	}
	private class IndexesOfTags{
		private List<Integer> indexesOfTag;
		private String tag;
		
		public IndexesOfTags(String tag, int id){
			this.indexesOfTag = new ArrayList<Integer>();
			this.tag = tag;
			indexesOfTag.add(new Integer(id));
		}
//		public void setTag(String tag){
//			this.tag = tag;
//		}
		public void addIndex(int index){
			this.indexesOfTag.add(new Integer(index));
		}
		public String getTag(){
			return this.tag;
		}
		public List<Integer> getIndexesOfTag(){
			if(this.indexesOfTag!=null){
				return this.indexesOfTag;
			}
			else{
				System.err.println("N="+N+"ERRRRRROOOOOOOOOOOORRRRRRRRRR");
				return this.indexesOfTag;
			}
		}
	}
	public int getPositionOfTagInTheArrayOfIndexes(String tag){
		int position=0;
		int pointer = 0;
		boolean flag = false;
		while(pointer<indexes.length && flag!=true){
			if(tag.equals(indexes[pointer].getTag())==true){
				position = pointer;
				flag = true;
			}
			else{
				pointer++;
			}
		}
		return position;
	}
	public void recreateArrayOfIds() {
		IndexesOfTags[] temp = this.indexes;
		this.countOfIndexes = this.countOfIndexes*2;
		this.indexes = new IndexesOfTags[this.countOfIndexes];
		for(int i=0;i<temp.length;i++){
			this.indexes[i] = temp[i];
		}
		//System.err.println(Arrays.toString(this.ids));
	}
	public void add(String userInput){
		String[] tags = parsingUserInputString(userInput);
		
		for(int i=0;i<tags.length;i++){
			if(N>this.countOfIndexes-1){
				recreateArrayOfIds();
			}
			if(!indexTable.containsKey(tags[i])){
//				indexes[i].setTag(tags[i]);
//				indexes[i].addIndex(N);
				indexes[N] = new IndexesOfTags(tags[i],counter);
				indexTable.put(tags[i], indexes[N]);
				N++;
			}
			else{
				int position = getPositionOfTagInTheArrayOfIndexes(tags[i]);
				indexes[position].addIndex(counter);
				
				indexTable.put(tags[i], indexes[position]);
			}
		}
		counter++;
	}
	
	public void searchIds(String userInput){
		String[] tags = parsingUserInputString(userInput);
		
		//int[] countOfIndexes = new int[tags.length];
		List<Integer> result = new ArrayList<Integer>();
		
		for(int i=0,j=0;i<tags.length;i++){
			List<Integer> temp = new ArrayList<Integer>();
			temp = indexTable.get(tags[i]).getIndexesOfTag(); 
			while(j<temp.size()){
				if(!result.contains(temp.get(j))){
					result.add(temp.get(j));
				}
				j++;
			}
			j=0;
		}
		System.out.println("result = ["+result.toString()+"]");
	}
	public void print(){
//		for (Entry<String, IndexesOfTags> entry : indexTable.entrySet()) {
//		  if(entry.getValue().getIndexesOfTag()!=null){
//			  System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue().getIndexesOfTag());
//		  }      
//		}
		
		Iterator<Entry<String, IndexesOfTags>> it = indexTable.entrySet().iterator();
		for(int i=0;i<N;i++){
	        Map.Entry<String, IndexesOfTags> pair = (Map.Entry<String, IndexesOfTags>)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue().getIndexesOfTag());
	       // it.remove(); // avoids a ConcurrentModificationException
	    }
		
//		Set<Map.Entry<String, IndexesOfTags>> entry = indexTable.entrySet();
//		Iterator iterator = entry.iterator();
//		for(int i=0;i<N;i++){
//			Map.Entry<String, IndexesOfTags> temp = entry.
//			System.out.println("Key: " + entry. + " Value: " + indexTable.values());
//		}	
	}
	public void getResult(int[] countOfIndexes){
		System.out.print("\n"+"Found ids: [");
		int j=0;
		while(j<countOfIndexes.length){
			System.out.print(countOfIndexes[j]+", ");
			j++;
		}
		System.out.print("]");
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
