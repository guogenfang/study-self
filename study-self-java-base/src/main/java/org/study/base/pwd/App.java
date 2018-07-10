package org.study.base.pwd;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @api {get} /user/:id Request User information
 * @apiName GetUser
 * @apiGroup User
 *
 * @apiParam {Number} id Users unique ID.
 *
 * @apiSuccess {String} firstname Firstname of the User.
 * @apiSuccess {String} lastname  Lastname of the User.
 */
public class App {
    public static void main( String[] args ) throws Exception {
    	String string = "a=1252821871&b=tencentyun&k=AKIDgaoOYh2kOmJfWVdH4lpfxScG2zPLPGoK&e=1438669115&t=1436077115&r=11162&u=0&f=";
    	byte [] b = HmacSHA1Encrypt(string, "nwOKDouy5JctNOlnere4gkVoOUz5EYAb");
    	String temp = new String(b,"utf-8") + string;
    	System.out.println(new String(Base64.getEncoder().encode(temp.getBytes())));
    }
    
    private static final String MAC_NAME = "HmacSHA1";    
    private static final String ENCODING = "UTF-8";   
    
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception{
        byte[] data=encryptKey.getBytes(ENCODING);  
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称  
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);   
        //生成一个指定 Mac 算法 的 Mac 对象  
        Mac mac = Mac.getInstance(MAC_NAME);   
        //用给定密钥初始化 Mac 对象  
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);    
        //完成 Mac 操作 
        return mac.doFinal(text);    
    } 
}
