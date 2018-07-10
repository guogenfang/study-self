package recursion;

import java.util.List;

public class Menu {
	
	private Integer id;
	
	private String name;
	
	private String descriptions;
	
	private Integer parentId;
	
	private List<Menu> children;

	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	Menu(Integer id, String name, Integer parentId){
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public List<Menu> getChildren() {
		return children;
	}
	
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		return "id:" + id + "~~name:" + name + "~~parentId:" + parentId;
	}
	
}
