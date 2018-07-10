import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;

public class T111 {
	public static void main(String[] args) {
		System.out.println("好adf@3AFFadf".toUpperCase());
		List<User> list = Arrays.asList(new User(1,"名字1"), new User(2,"名字2"), new User(3,"名字3"));
		List<User> l = list.stream().map(u->{return u;}).collect(Collectors.toList());
		System.out.println(l);
		System.out.println("------------");
		User model = new User(2, "名字2");
		Long tmp = list.stream().filter(d->{
			if(Objects.isNull(model.getId())) {
				return !d.getName().equals(model.getName());
			}
			else {
				return !d.getName().equals(model.getName()) || (d.getName().equals(model.getName()) && d.getId() == model.getId());				
			}
		}).count();
		System.out.println(tmp);
	}

	static class User {
		private Integer id;
		private String name;

		/**
		 * 
		 */
		public User() {
			// TODO Auto-generated constructor stub
		}
		
		public User(Integer id, String name){
			this.id = id;
			this.name = name;
		}
		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "id:" + id + ",name:" + name;
		}

	}
}
