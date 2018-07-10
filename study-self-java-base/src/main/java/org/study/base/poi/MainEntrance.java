package org.study.base.poi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public class MainEntrance {
    public static void main(String[] args) {
        ApnExcelParseTool excelParseTool = new ApnExcelParseTool();

        excelParseTool.setFilePath("G:\\翔安智慧法庭\\翔安法院\\名单.xls");

        try {
            Workbook workbook = excelParseTool.initWorkBook();

            List<ApnModel> outData = new ArrayList<>();

            if (workbook != null) {
                excelParseTool.parseWorkbook(workbook, outData);
            }

            List<ApnModel> unique = new ArrayList<>();

//            for (ApnModel apnModel : outData) {
//                if (!unique.contains(apnModel)) {
//                    unique.add(apnModel);
//                } else {
//                    System.out.println(apnModel.toString());
//                }
//            }

//            if (unique.size() > 0) {
//                System.out.println("size: " + unique.size());
//                new ApnWriteTool().write("/home/zhangjian/Desktop/apns-conf.xml", unique);
//            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
