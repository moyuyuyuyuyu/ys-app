package com.microstone.app.util.toImage;


import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import com.microstone.app.feign.IOssClient;
import org.apache.http.entity.ContentType;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.microstone.core.oss.model.MsFile;
import org.microstone.core.tool.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author XieXiaoDong
 * @date 2021/7/15/0015
 * @description pdf转图片支持打水印
 */
public class PDFToImage {

    private static Logger logger = LoggerFactory.getLogger(SaveImage.class);
//    // 水印透明度
//    private static float alpha = 0.0f;
//    // 水印横向位置
//    private static int positionWidth = 150;
//    // 水印纵向位置
//    private static int positionHeight = 300;
//    // 水印文字字体
//    private static Font font = new Font("仿宋", Font.BOLD, 26);
//    // 水印文字颜色
//    private static Color color = Color.GRAY;


    private static IOssClient ossClient;

    private static IOssClient getOssClient() {
        if (ossClient == null) {
            ossClient = SpringUtil.getBean(IOssClient.class);
        }
        return ossClient;
    }

    /**
     * 生成pdf的缩略图
     *
     * @param zoom       缩略图显示倍数，1表示不缩放，0.5表示缩小到50%
     * @param inputFile  需要生成缩略图的书籍的完整路径
     * @param outputFile 生成缩略图的放置路径
     */
    public List<ImageValue> pdfToImage(float zoom, String inputFile, String outputFile) {
        List<ImageValue> list = null;
        Document document = null;
        try {
            list = new ArrayList(0);
            document = new Document();
            document.setFile(inputFile);
            float rotation = 0;
            int maxPages = document.getPageTree().getNumberOfPages();
            for (int i = 0; i < maxPages; i++) {
                //设置文件名
                long fileName = System.currentTimeMillis();

                BufferedImage bfimage = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, zoom);

                bfimage = setGraphics(bfimage);

                RenderedImage rendImage = bfimage;
                ImageIO.write(rendImage, "jpg", new File(outputFile + File.separator + File.separator+ fileName + i + ".jpg"));
                bfimage.flush();
                //list.add(outputFile + fileName + i + ".jpg");


                //上传图片至OSS服务器
                ImageValue imageValue = new ImageValue();
                File pdfFile = new File(outputFile + fileName + i + ".jpg");
                FileInputStream fileInputStream = new FileInputStream(pdfFile);
                MultipartFile multipartFile = new MockMultipartFile("file", pdfFile.getName(),
                        ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                IOssClient ossClient = getOssClient();
                MsFile msFile = ossClient.putFile(multipartFile);
                BufferedImage image = ImageIO.read(pdfFile);
                int width = image.getWidth();
                int height = image.getHeight();
                imageValue.setHeight(height);
                imageValue.setWidth(width);
                imageValue.setIndex(i + 1);
                imageValue.setLink(msFile.getLink());
                imageValue.setRate(divide(height,width));
                imageValue.setType("jpg");
                logger.info("图片宽度：" + width);
                logger.info("图片高度：" + height);
                logger.info("OSS图片路径：" + msFile.getLink());
                //将图片路径返回
                list.add(imageValue);



            }




        } catch (Exception e) {
            e.printStackTrace();
        }

        if (document != null) {
            document.dispose();
        }
        return list;
    }


    //加水印
    public static BufferedImage setGraphics(BufferedImage bfimage) {
//        Graphics2D g = bfimage.createGraphics();
//        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        // 5、设置水印文字颜色
//        g.setColor(color);
//        // 6、设置水印文字Font
//        g.setFont(font);
//        // 7、设置水印文字透明度
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
//        //设置旋转
//        g.rotate(-Math.PI/6);
//        g.drawString("数禧科技", 0, (bfimage.getHeight()/2)*1);
//        // 9、释放资源
//        g.dispose();
        return bfimage;
    }

    /**
     * 下载文件到本地
     *
     * @param fileUrl   远程地址
     * @param fileLocal 本地路径
     * @throws Exception
     * @author nadim
     * @date Sep 11, 2015 11:45:31 AM
     */
    public static void downloadFile(String fileUrl, String fileLocal) throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }

        //读文件流
        DataInputStream in = new DataInputStream(urlCon.getInputStream());
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileLocal));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }

    public static String divide(Integer a , Integer b){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float) a / (float) b);
    }

}
