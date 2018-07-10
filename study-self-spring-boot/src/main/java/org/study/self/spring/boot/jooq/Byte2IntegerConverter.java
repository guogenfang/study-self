package org.study.self.spring.boot.jooq;

import org.jooq.Converter;

import java.util.Objects;

public class Byte2IntegerConverter implements Converter<Byte, Integer> {

    @Override
    public Integer from(Byte databaseObject) {
        return Objects.isNull(databaseObject) ? null : databaseObject.intValue();
    }

    @Override
    public Byte to(Integer userObject) {
        return Objects.isNull(userObject) ? null : userObject.byteValue();
    }

    @Override
    public Class<Byte> fromType() {
        return Byte.class;
    }

    @Override
    public Class<Integer> toType() {
        return Integer.class;
    }

}
