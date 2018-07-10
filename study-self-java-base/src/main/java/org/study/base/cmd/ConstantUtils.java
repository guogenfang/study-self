package org.study.base.cmd;

public interface ConstantUtils {
	static final int PAGE_SIZE = 10;

	/**
	 * @author ylshu@xinshiyun.com
	 * 2014年11月21日下午3:12:06
	 * 		用户状态：
	 * 			-1：未登录
	 * 			 0：无权限
	 * 			 1：正常
	 */
	static final int STATUS_NO_LOGIN = -1;
	static final int STATUS_NO_PERMISSION = 0;
	static final int STATUS_ACCESS = 1;

	/**
	 * 		角色等级：
	 * 			 1：系统管理员
	 * 			 2：操作员
	 * 			 3：书记员
	 * 			 4：普通用户
	 * 			 5：审判长
	 */
	static final int ROLE_ADMIN = 1;
	static final int ROLE_CZY = 2;
	static final int ROLE_SJY = 3;
	static final int ROLE_COMMON = 4;
	static final int ROLE_JUDGE = 5;
	
	/**
	 * 		法庭类别：
	 * 			 1：标准法庭
	 * 			 2：简约法庭
	 */
	static final int ROOM_BIAO = 1;
	static final int ROOM_JIAN = 2;
	
	/**
	 * 		编码机返回值：
	 * 			 1：已经启动
	 * 			 2：启动成功
	 */
	static final int STARTED = 1;
	static final int START_OK = 2;
	static final int START_FAILED = -1;
	static final int STOPED = 3;
	static final int STOP_OK = 4;
	static final int STOP_FAILED = -3;
	
	/**
	 * 		客户端庭审返回值：
	 * 			 1：正常
	 * 			 2：编码机异常
	 * 			 3：录像服务器异常
	 * 			 4：转发异常
	 * 			 5：法庭正在使用中
	 * 			 6：法庭故障
	 * 			 7: Remoting异常
	 * 			 8：远程提讯异常
	 */
	static final int OK = 1;
	static final int ENCODER_EXCEPTION = 2;
	static final int RECODER_EXCEPTION = 3;
	static final int RELAY_EXCEPTION = 4;
	static final int ROOM_ISUSED = 5;
	static final int ROOM_ISEXCEPTION = 6;
	static final int REMOTE_ACCESS_EXCEPTION = 7;
	static final int REMOTE_ARRAIGNMENT_EXCEPTION = 8;

	/**
	 * 		控制返回值：
	 * 			 1：正常
	 * 			 2：编码机异常
	 * 			 3：VGA异常
	 * 			 4：矩阵异常
	 */
	static final int CTRL_OK = 1;
	static final int CTRL_ENCODER_EXCEPTION = 2;
	static final int CTRL_VGA_EXCEPTION = 3;
	static final int CTRL_MATRIX_EXCEPTION = 4;
	
	/**
	 * 		本地磁盘返回值：
	 * 			 -1：存储IP有误
	 * 			 -2：存储用户名密码有误
	 * 			 -3：存储已存在
	 */
	static final String STORE_IP_FAILED_STR = "IP不正确";
	static final String STORE_NAME_OR_PWD_FAILED_STR = "用户名称或者是密码不正确";
	static final int STORE_IP_FAILED = -1;
	static final int STORE_NAME_OR_PWD_FAILED = -2;
	static final int STORE_IS_EXISTED = -3;

	static final int CATALOG_CIVIL = 1;				// 民事案件
	static final int CATALOG_CRIMINAL = 2;			// 刑事案件
	static final int CATALOG_OTHER = 4;				// 其它案件
	static final int CATALOG_ADMINISTRATIVE = 6;	// 行政案件
}
