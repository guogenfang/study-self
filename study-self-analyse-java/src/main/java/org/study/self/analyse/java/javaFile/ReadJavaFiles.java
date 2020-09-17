package org.study.self.analyse.java.javaFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.study.self.analyse.java.model.InterfaceModel;
import org.study.self.analyse.java.model.MethodModel;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ReadJavaFiles {
	public static final String EMPTY = "Optional.empty";
	public static String head = "var $serviceSDK = {};\r\n var baseUrl = 'http://192.168.60.232:8001/?';\r\n";
	public static List<InterfaceModel> interfaceModels = new ArrayList<>();
	
	/**
	 * [简要描述]：读取目标目录制java文件,封装到自定义接口和方法的model
	 * @author ggf
	 * @date 2017年5月22日
	 * @param projectDir
	 * @return 
	 */
	public static List<InterfaceModel> listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java")&&path.contains("Service"), (level, path, file) -> {
        	String _interface = path.replace("/", ".");
			_interface = _interface.substring(1, _interface.length() - 5);
			InterfaceModel interfaceModel = new InterfaceModel();
			interfaceModel.setUrl(_interface);
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        List<MethodModel> methodModels = new ArrayList<>();
                        List<MethodDeclaration> mets = n.getMethods();
                        
                        interfaceModel.setName(n.getName().toString());
                        if(!n.getComment().toString().equals("Optional.empty")){
                        	interfaceModel.setComment(n.getComment().get().toString());
						}
                        
                        for (MethodDeclaration method : mets) {
                        	MethodModel model = new MethodModel();
                        	List<String> params = new ArrayList<>();
                        	
                        	model.setName(method.getName().toString());
                        	if(!method.getComment().toString().equals(EMPTY)){
                        		model.setComment(method.getComment().get().toString());
                        	}
                        	model.setReturnType(method.getType().toString());
                        	for (Parameter param : method.getParameters()) {
                        		params.add(param.getNameAsString());
							}
                        	
                        	model.setParams(params);
                        	methodModels.add(model);
						}
                        
                        interfaceModel.setMethodModelList(methodModels);
                        interfaceModels.add(interfaceModel);
                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).explore(projectDir);
        
        return interfaceModels;
    }

	/**
	 * [简要描述]：组装js文件内容
	 * @author ggf
	 * @date 2017年5月20日
	 */
	public static String generateString(){
		//System.out.println(interfaceModels);
		for (InterfaceModel interfaceModel : interfaceModels) {
			StringBuffer mthStr = new StringBuffer(interfaceModel.getComment() + "$serviceSDK." + interfaceModel.getUrlFormat() + " = {");
			for (MethodModel model : interfaceModel.getMethodModelList()) {
				if(StringUtils.isEmpty(model.getMethodParamsToString())){
					mthStr.append(model.getComment() + "'" + model.getName() + "': function(success, error, options){\r\n");
				}else {
					mthStr.append(model.getComment() + "'" + model.getName() + "': function(" + model.getMethodParamsToString() + ", success, error, options){\r\n");
				}
				mthStr.append("var _url = 'i=" + interfaceModel.getUrl() + "&m=" + model.getName() + "';\r\n");
				
				mthStr.append("postService(_url, {'p':JSON.stringify([" + model.getMethodParamsToString()+ "])}, success,error, options);\r\n},\r\n");
			}
			mthStr.append("'_interface': '" + interfaceModel.getUrl() + "'\r\n");
			mthStr.append("}\r\n\r\n");
			
			head += mthStr;
		}
		
		return head + postBody();
	}
	
	public static String postBody(){
		return "function postService(_url, param, success, error, opts) {"
		+ "var options = {"
		+ "	type : 'GET',"
		+ "	timeout : 10000,"
		+ "	contentType : 'application/x-www-form-urlencoded',"
		+ "	dataType : 'json',"
		+ "	error : function(e){},"
		+ "	success : function(res){}}"
	    + "$.extend(options, opts);"
		+ "//_url = baseUrl + _url; \r\n"
	    + "_url = './jsonData/' + _url.replace(/i=/,'').replace(/m=/,'').replace(/&/,'.') + '.json';//请求临时测试数据json \r\n"
	    + "var ajx = $.ajax({"
	    + "url : _url,"
		+ "type : options.type,"
		+ "data : param,"
		+ "timeout : options.timeout,"
		+ "contentType : options.contentType, "
		+ "dataType : options.dataType,"
		+ "error : function(e){"
		+ "	console.log('请求失败');"
		+ "	if(error){"
		+ "		error(e);"
		+ "	}"
		+ "},"
		+ "success : function(res){"
		+ "	//TODO res.code 平台相关错误处理 登录超时。。。。\r\n"
		+ "	if(res.code == 500){"
		+ "		alert('请求数据出错');"
		+ "	}"
		+ "	else if(success){"
		+ "		success(res);"
		+ "	}"
		+ "},"
		+ "complete : function(XMLHttpRequest, status){"
		+ "	console.log(status);"
		+ "　　　　if(status=='timeout'){"
		+ "　　　　　 ajx.abort();"
		+ "　　　　  alert('请求超时.....');"
		+ "　　　　}"
		+ "　　}"
		+ "});"
		+ "}\r\n"
		+ "function getMenuList(success, error) {"
	    + "$serviceSDK.com_ggf_platform_service_api_IMenuService.getMenuList(success, error);"
	    + "}";
	}
}
