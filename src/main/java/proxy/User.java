package proxy;

public class User implements IUser{
	String name; 
	String id;
	
	public User(String name,String id){ 
		this.name = name; 
		this.id = id;
	}

	public String getName() {	
		System.out.println("get name value----" + name);
		return name; 
	}

	public String getId() {
		System.out.println("get Id value----" + id);
		return id;
	} 
}
