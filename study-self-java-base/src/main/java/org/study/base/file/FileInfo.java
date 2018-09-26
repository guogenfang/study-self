package org.study.base.file;

import java.io.Serializable;

/**
 * base bean
 * 
 * @author ylcao@xinshiyun.com
 * @since Created by kazaff on 2014/12/1.
 */
public class FileInfo implements Serializable {

	private String md5;
	private int chunkIndex;
	private String size;
	private String name;
	private Integer userId;
	private String id;
	private int chunks;
	private int chunk;
	private String lastModifiedDate;
	private String type;
	private String ext;
	private String hashKey;
	private Integer parentId;
	private String path;

	private String realName;

	public FileInfo() {
	}

	public FileInfo(String name, String size, int chunkIndex) {
		this.name = name;
		this.size = size;
		this.chunkIndex = chunkIndex;
	}

	public FileInfo(Integer userId, String id) {
		this.userId = userId;
		this.id = id;
	}

	public FileInfo(String md5) {
		this.md5 = md5;
	}

	public FileInfo(int chunks, int chunk, Integer userId, String id,
                    String name, String size, String lastModifiedDate, String type) {
		this.userId = userId;
		this.id = id;
		this.name = name;
		this.size = size;
		this.chunks = chunks;
		this.chunk = chunk;
		this.lastModifiedDate = lastModifiedDate;
		this.type = type;
	}

	public FileInfo(String name, int chunks, String ext, String md5) {
		this.name = name;
		this.chunks = chunks;
		this.ext = ext;
		this.md5 = md5;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public int getChunkIndex() {
		return chunkIndex;
	}

	public void setChunkIndex(int chunkIndex) {
		this.chunkIndex = chunkIndex;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "FileInfo{" +
				"md5='" + md5 + '\'' +
				", chunkIndex=" + chunkIndex +
				", size='" + size + '\'' +
				", name='" + name + '\'' +
				", userId='" + userId + '\'' +
				", id='" + id + '\'' +
				", chunks=" + chunks +
				", chunk=" + chunk +
				", lastModifiedDate='" + lastModifiedDate + '\'' +
				", type='" + type + '\'' +
				", ext='" + ext + '\'' +
				", hashKey='" + hashKey + '\'' +
				", realName='" + realName + '\'' +
				", parentId='" + parentId + '\'' +
				'}';
	}
}
