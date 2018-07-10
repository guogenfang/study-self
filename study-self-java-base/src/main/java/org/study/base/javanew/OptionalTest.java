package org.study.base.javanew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.study.base.javanew.entity.School;
import org.study.base.javanew.entity.User;

public class OptionalTest {

	@Test
	public void nullT() {
		User user = null;
		String name = Optional.ofNullable(user).map(u -> u.getName()).orElse("没有取到");
		System.out.println(name);
		try {
			Optional.ofNullable(user).map(u -> u.getName()).orElseThrow(() -> new Exception("没找到"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = new User(1, "ggf", new School(1,"学校"));
		Integer id = Optional.ofNullable(user).filter(u -> u.getName() != "ggf").map(u -> u.getSchool()).map(u -> u.getId())
				.orElse(null);
		System.out.println(id);
	}

	/**
	 * 根据条件查询用户 [简要描述]: 2018年1月8日
	 * 
	 * @throws Exception
	 */
	@Test
	public void findUser() throws Exception {
		User user = new User();
		// 默认查询用户状态
		Integer id = Optional.ofNullable(user).map(u -> u.getStatus()).orElse(userStatus.ON_LINE.getValue());
		System.out.println(id);

		// 查询结果处理
		List<User> list = null;
		Optional.ofNullable(list).filter(m -> m.size() == 1).orElseThrow(() -> new Exception("没找到"));
	}

	@Test
	public void pre() {
		List<String> aa = Arrays.asList("aaa", "abbb", "accc", "ddd");
		Optional<String> largest = aa.stream().max((a, b) -> a.compareToIgnoreCase(b));
		// Optional<String> largest = aa.stream().max(String::compareToIgnoreCase);

		List<String> bb = new ArrayList<String>();
		largest.ifPresent(bb::add);

		System.out.println("ifPresent 的用法：" + bb);

		Optional<Boolean> added = largest.map(bb::add);
		System.out.println("会有返回值处理:" + added.get());
	}

	enum userStatus {
		IS_DELETE(0), OFF_LINE(1), ON_LINE(2);
		Integer value;

		private userStatus(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	
}
