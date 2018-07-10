/**
 * This class is generated by jOOQ
 */
package org.study.self.spring.boot.model;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * InnoDB free: 11264 kB
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Room implements Serializable {

    private static final long serialVersionUID = 1350258635;

    private Integer id;
    private String  name;
    private Integer model;
    private String  recordSaveUrl;
    private Integer courtCode;
    private String  courtName;

    public Room() {}

    public Room(Room value) {
        this.id = value.id;
        this.name = value.name;
        this.model = value.model;
        this.recordSaveUrl = value.recordSaveUrl;
        this.courtCode = value.courtCode;
        this.courtName = value.courtName;
    }

    public Room(
        Integer id,
        String  name,
        Integer model,
        String  recordSaveUrl,
        Integer courtCode,
        String  courtName
    ) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.recordSaveUrl = recordSaveUrl;
        this.courtCode = courtCode;
        this.courtName = courtName;
    }

    public Integer getId() {
        return this.id;
    }

    public Room setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getModel() {
        return this.model;
    }

    public Room setModel(Integer model) {
        this.model = model;
        return this;
    }

    public String getRecordSaveUrl() {
        return this.recordSaveUrl;
    }

    public Room setRecordSaveUrl(String recordSaveUrl) {
        this.recordSaveUrl = recordSaveUrl;
        return this;
    }

    public Integer getCourtCode() {
        return this.courtCode;
    }

    public Room setCourtCode(Integer courtCode) {
        this.courtCode = courtCode;
        return this;
    }

    public String getCourtName() {
        return this.courtName;
    }

    public Room setCourtName(String courtName) {
        this.courtName = courtName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Room (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(model);
        sb.append(", ").append(recordSaveUrl);
        sb.append(", ").append(courtCode);
        sb.append(", ").append(courtName);

        sb.append(")");
        return sb.toString();
    }
}