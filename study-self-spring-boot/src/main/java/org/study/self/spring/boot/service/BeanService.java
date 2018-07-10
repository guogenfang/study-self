package org.study.self.spring.boot.service;

import org.springframework.stereotype.Service;
import org.study.self.spring.boot.IBeanService;

/**[简要描述]：
 * @author ggf
 * 2018年1月19日
 */
@Service()
public class BeanService implements IBeanService {

	@Override
	public String calc(String arg) {
		System.out.println("-------" + arg + "-------");
		return null;
	}

}
