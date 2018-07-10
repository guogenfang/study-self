package org.study.base.json.gson;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ggf on 2016/12/12.
 */

public class InviteModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String roomId;
    private String extra;

    public InviteModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public InviteModel(String id, String roomId, String name) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
    }

	@Override
	public String toString() {
		return "InviteModel [id=" + id + ", name=" + name + ", roomId=" + roomId + ", extra=" + extra + "]";
	}
    
    
}
