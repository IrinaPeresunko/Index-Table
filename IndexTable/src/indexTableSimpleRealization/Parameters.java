package indexTableSimpleRealization;

public class Parameters {
	
	private String name;
	private String lastName;
	private Integer age;
	private Boolean isMarried;
	
	public Parameters(String name,String lastName,Integer age,Boolean isMarried){
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
	public Integer getAge(){
		return this.age;
	}
	public Boolean isMarried(){
		return this.isMarried;
	}
}
