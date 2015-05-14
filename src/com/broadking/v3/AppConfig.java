package com.broadking.v3;

/**
 * 项目中的配置文件
 */
public class AppConfig {
	/** 日志打印标记 (正式打包时debug为false) */
	public static final boolean LOG_DEBUG = true;

	/** 服务器调试标记(false为正式环境地址) */
	private static final boolean SERVER_DEBUG = true;

	/** webService NameSpace */
	public static final String NAMESPACE = "";
	/** 测试服务器 */
	public static final String TEST_SERVER = "http://192.168.1.61/";
	/** 正式服务器 */
	public static final String SERVER = "http://www.broadking.com/";


	public static final String URL = (SERVER_DEBUG ? TEST_SERVER : SERVER)
			+ "broadking_v3.0/index.php?app=api&mod=Oauth";
}
