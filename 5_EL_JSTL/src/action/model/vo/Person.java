package action.model.vo;

public class Person {
	private String name;
	private char gender;
	private int age;
//	private int nai;
	
	public Person() {}

	public Person(String name, char gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

//	public int getAge() {
		public int getNai() {
		return age;
	}

//	public void setAge(int age) {
		public void setNai(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + "]";
//		return "Person [name=" + name + ", gender=" + gender + ", nai=" + nai + "]";
	}

}
