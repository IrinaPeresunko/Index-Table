package indexTableSimpleRealization;

public class IndexTable {
	static int n;
	static int id;
	int countOfParameters=4;
	int countOfIDs=10;
	int[][] arrayOfID;
	Object[][] keyWord;
	
	public IndexTable(){
		this.n=0;
		this.id=0;
		arrayOfID = new int[countOfIDs][countOfIDs];
		keyWord = new Object[countOfIDs][1];
	}
//	private void rebuilding(){
//		
//	}
	private int addParameters(Parameters parameters, int i){
		int countOfAddedParameters=0;
		boolean[] isAlreadyExist=new boolean[countOfParameters];
		int n=0;
		int j=i;
		while(j!=-1 && n!=0){
			if(parameters.getName().equals(keyWord[j][1]) && parameters.getName()!=null ){
				isAlreadyExist[n] = true;
			}
			if(parameters.getLastName().equals(keyWord[j][1]) && parameters.getLastName()!=null ){
				isAlreadyExist[n+1] = true;
			}
			if(parameters.getAge().equals(keyWord[j][1]) && parameters.getAge()!=null ){
				isAlreadyExist[n+2] = true;
			}
			if(parameters.isMarried().equals(keyWord[j][1]) && parameters.isMarried()!=null ){
				isAlreadyExist[n+3] = true;
			}
			j--;
		}
		j=0;
			if(isAlreadyExist[j]!=true){
				keyWord[j][1] = parameters.getName();
				countOfAddedParameters++;
			}
			if(isAlreadyExist[j]!=true){
				keyWord[j+1][1] = parameters.getLastName();
				countOfAddedParameters++;
			}
			if(isAlreadyExist[j]!=true){
				keyWord[j+2][1] = parameters.getAge();
				countOfAddedParameters++;
			}
			if(isAlreadyExist[j]!=true){
				keyWord[j+3][1] = parameters.isMarried();
				countOfAddedParameters++;
			}
			
		return countOfAddedParameters;
	}
//	public void add(Parameters parameters){
//		if(n < ){
//			addParameters(parameters,0);
//			n+=4;
//		}
//		if(n>=40){
//			rebuilding();
//			return;
//		}
		
		
	}
