package org.study.base;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * [简要描述]：二维码图片生成地址
 * 
 * @author ggf 2018年2月8日
 */
public class QRCodeUtils {
	public static void main(String[] args) {
		encodeStream("https://www.baidu.com", 300, 300);
	}

	public static String encodeFile(String contents, int width, int height, String realUploadPath, String imageName) {
		// 生成条形码时的一些配置
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 内容所使用字符集编码

		OutputStream out = null;
		try {
			if (realUploadPath != null && realUploadPath != "") {
				out = new FileOutputStream(realUploadPath + "/" + imageName);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BitMatrix bitMatrix;
		try {
			// 生成二维码
			bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageName;
	}
	
	public static String encodeStream(String contents, int width, int height) {
		// 生成条形码时的一些配置
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 内容所使用字符集编码
		BitMatrix bitMatrix;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			// 生成二维码
			bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
			return Base64.getEncoder().encodeToString(outputStream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
