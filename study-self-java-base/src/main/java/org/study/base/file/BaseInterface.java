package org.study.base.file;

@ScanClassPermissions({PermissionConstant.ALL,PermissionConstant.PARTY})
public interface BaseInterface {
	
	@ScanClassPermissions(PermissionConstant.ALL)
	String getNameByParam(String head, String first,String second);
	
	@ScanClassPermissions
	String getNameByParam(String head, String second);
}
