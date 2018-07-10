package org.study.self.jooq.config;

import org.jooq.Converter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Timestamp2DateConverter implements Converter<Timestamp, Date> {

    @Override
    public Date from(Timestamp databaseObject) {
        return Objects.isNull(databaseObject) ? null : new Date(databaseObject.getTime());
    }

    @Override
    public Timestamp to(Date userObject) {
        return Objects.isNull(userObject) ? null : new Timestamp(userObject.getTime());
    }

    @Override
    public Class<Timestamp> fromType() {
        return Timestamp.class;
    }

    @Override
    public Class<Date> toType() {
        return Date.class;
    }

}
