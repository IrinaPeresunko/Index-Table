package indexTableSimpleRealization;

public class Parameters {
	
	private String name;
	private String lastName;
	private int age;
	private boolean isMarried;
	
	public Parameters(String name,String lastName,int age,boolean isMarried){
		this.name=name;
		this.lastName=lastName;
		this.age=age;
		this.isMarried=isMarried;
	}
	
	public String getName(){
		return this.name;
	}
	public String getLastName(){
		return this.lastName;
	}
	public int getAge(){
		return this.age;
	}
	public boolean isMarried(){
		return this.isMarried;
	}
}
