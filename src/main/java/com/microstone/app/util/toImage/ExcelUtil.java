package com.microstone.app.util.toImage;

import com.aspose.cells.Workbook;
import com.aspose.words.FontSettings;
import com.aspose.words.License;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author XieXiaoDong
 * @date 2021/7/16/0016
 * @description 方法描述
 */
public class ExcelUtil {
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = PdfUtil.class.getClassLoader().getResourceAsStream("\\license4.xml"); // license.xml应放在…\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void excel2pdf(String from,String to) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try {
            FontSettings.setFontsFolder(File.separator + "usr" + File.separator + "share" + File.separator + "fonts" +File.separator + "zh_CN",true);
            File pdfFile = new File(to);// 输出路径
            Workbook wb = new Workbook(from);// 原始excel路径
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            wb.save(fileOS, com.aspose.cells.SaveFormat.PDF);
            fileOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
