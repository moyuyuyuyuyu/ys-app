package com.microstone.app.util.toImage;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.words.FontSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author XieXiaoDong
 * @date 2021/7/16/0016
 * @description 方法描述
 */
public class PPTUtil {
    private static InputStream license;

    /**
     * 获取license
     *
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            license = PPTUtil.class.getClassLoader().getResourceAsStream("license2.xml");// license路径
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void ppt2pdf(String intPutPath,String outPutPath) {
        // 验证License
        if (!getLicense()) {
            return;
        }

        try {
            FontSettings.setFontsFolder(File.separator + "usr" + File.separator + "share" + File.separator + "fonts" +File.separator + "zh_CN",true);
            //   long old = System.currentTimeMillis();
            File file = new File(outPutPath);// 输出pdf路径
            Presentation pres = new Presentation(intPutPath);//输入ppt路径
            FileOutputStream fileOS = new FileOutputStream(file);
            pres.save(fileOS, SaveFormat.Pdf);
            fileOS.close();

            //    long now = System.currentTimeMillis();
            //    System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件保存在:" + file.getPath()); //转化过程耗时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
