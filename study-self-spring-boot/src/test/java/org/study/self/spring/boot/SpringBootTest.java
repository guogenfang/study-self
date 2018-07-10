package org.study.self.spring.boot;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@org.springframework.boot.test.context.SpringBootTest({
        "dubbo.enabled=false",
        "spring.datasource.tomcat.initial-size=1",
        "spring.aop.auto=false"
})
abstract class SpringBootTest {
	
}
