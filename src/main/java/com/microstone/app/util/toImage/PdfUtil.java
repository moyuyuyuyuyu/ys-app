package com.microstone.app.util.toImage;


import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class PdfUtil {

    public static boolean getLicense() {
        boolean result = false;
        try {
            //InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/license.xml");
            InputStream is = PdfUtil.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在…\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void doc2pdf(String input,String output) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try {
            FontSettings.setFontsFolder(File.separator + "usr" + File.separator + "share" + File.separator + "fonts" +File.separator + "zh_CN",true);
            //FontSettings.setFontsFolder("C:\\Windows\\Fonts",true);
            long old = System.currentTimeMillis();
            //File file = new File("./test.pdf"); //新建一个空白pdf文档
            File file = new File(output); //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(input); //Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
