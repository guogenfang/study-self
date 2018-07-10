package org.study.self.jooq.config;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class GeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        return super.getJavaClassName(definition, mode)
                .replaceAll("TPl", "")
                .replaceAll("TGjfb", "")
                .replaceAll("TFgdq", "")
                .replaceAll("TFzzx", "")
                .replaceAll("TSfpj", "")
                .replaceAll("TCc", "");
    }

    @Override
    public String getJavaIdentifier(Definition definition) {
        return super.getJavaIdentifier(definition)
                .replaceAll("T_PL_", "")
                .replaceAll("T_GJFB_", "")
                .replaceAll("T_FGDQ_", "")
                .replaceAll("T_FZZX_", "")
                .replaceAll("T_SFPJ_", "")
                .replaceAll("T_CC_", "");
    }

}
