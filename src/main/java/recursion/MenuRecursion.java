package recursion;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class MenuRecursion {
	public static void main(String[] args) {
		List<Menu> list = new ArrayList<>();
		list.add(new Menu(1,"一级菜单1",0));
		list.add(new Menu(2,"一级菜单2",0));
		list.add(new Menu(3,"一级菜单3",0));
		list.add(new Menu(4,"二级菜单4",1));
		list.add(new Menu(5,"二级菜单5",2));
		List <Menu> treeResult = getTreeNode(list, 0);
		Gson gson = new Gson();
		System.out.println(gson.toJson(treeResult));
	}
	
	private static List<Menu> getTreeNode(List<Menu> data, Integer pid){
		List<Menu> result = new ArrayList<Menu>();
        List<Menu> temp = new ArrayList<Menu>();
        for (Menu node : data) {
			if(node.getParentId().equals(pid)){
				result.add(node);
				temp = getTreeNode(data, node.getId());
				if(temp.size() > 0){
					node.setChildren(temp);
				}
			}
		}
        return result;
    }
}
