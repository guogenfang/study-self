package org.study.self.spring.boot.jooq;

import org.jooq.conf.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [简要描述]：
 * @author ggf
 * @date 2017年8月20日
 */
@Configuration
public class JooqConfig {

    @Bean
    Settings settings() {
        return new Settings().withRenderSchema(false);
    }

}
