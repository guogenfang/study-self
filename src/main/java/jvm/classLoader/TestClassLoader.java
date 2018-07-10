package jvm.classLoader;

class AA{
	static{
		System.out.println("AAAAAAAAAAAAA");
	}
}

public class TestClassLoader extends ClassLoader{
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		return super.loadClass(name);
	}
	
	public static void main(String[] args) {
		try {
			
			//系统类加载器
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			System.out.println(classLoader);
			Class<?> c1= classLoader.loadClass("classLoader.AA");
			System.out.println(Class.forName("classLoader.AA"));
			
			System.out.println(Class.forName("java.lang.String").getClassLoader());
			System.out.println(Class.forName("classLoader.TestClassLoader").getClassLoader());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
