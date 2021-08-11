package com.microstone.app.util.toImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/7/15/0015
 * @description 方法描述
 */
public class SaveImage {
    private static Logger logger = LoggerFactory.getLogger(SaveImage.class);


    public static List<ImageValue> saveImage(HttpServletRequest request,String fileUrl) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileAdd = sdf.format(new Date());



        String realPath = request.getSession().getServletContext().getRealPath(File.separator);
        //文件存放路径
        String inputPath = realPath + File.separator + fileAdd + File.separator;
        //没有则创建改目录
        File file = new File(inputPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }

        //将文档下载到本地
        if(fileUrl.contains(".doc") || fileUrl.contains(".docx")){

            //下载到的文件存放路径
            long docTime = System.currentTimeMillis();
            String fileName = docTime + ".doc";
            String rePath = inputPath + File.separator + File.separator + fileName;
            PDFToImage.downloadFile(fileUrl,rePath);
            long newPdfTime = System.currentTimeMillis();
            String newPdf = newPdfTime + ".pdf";
            String newPdfPath = inputPath + File.separator + File.separator + newPdf;
            //word转pdf
            PdfUtil.doc2pdf(rePath,newPdfPath);
            //pdf转图片
            PDFToImage pdfToImage = new PDFToImage();
            List<ImageValue> strings = pdfToImage.pdfToImage(2, newPdfPath, inputPath);
            logger.info("集合内容：" + strings);

            //所有流程完毕后删除文件夹下所有内容
            deleteDir(inputPath);

            return strings;

        }
        if(fileUrl.contains(".ppt") || fileUrl.contains(".pptx")){

            //下载到的文件存放路径
            long docTime = System.currentTimeMillis();
            String fileName = docTime + ".pptx";
            String rePath = inputPath + File.separator + File.separator + fileName;
            PDFToImage.downloadFile(fileUrl,rePath);
            logger.info("ppt路径为：" + rePath);
            long newPdfTime = System.currentTimeMillis();
            String newPdf = newPdfTime + ".pdf";
            String newPdfPath = inputPath + File.separator + File.separator + newPdf;
            //ppt转换成pdf
            PPTUtil.ppt2pdf(rePath,newPdfPath);
            logger.info("pdf路径为：" + newPdfPath);
            //pdf转图片
            PDFToImage pdfToImage = new PDFToImage();
            List<ImageValue> strings = pdfToImage.pdfToImage(2, newPdfPath, inputPath);

            logger.info("集合内容：" + strings);

            //所有流程完毕后删除文件夹下所有内容
            deleteDir(inputPath);

            return strings;
        }
        if(fileUrl.contains(".xlsx") || fileUrl.contains(".xls") || fileUrl.contains(".xlsm")){

            //下载到的文件存放路径
            long docTime = System.currentTimeMillis();
            String fileName = docTime + ".xlsx";
            String rePath = inputPath + File.separator + File.separator + fileName;
            PDFToImage.downloadFile(fileUrl,rePath);
            logger.info("excel路径为：" + rePath);
            long newPdfTime = System.currentTimeMillis();
            String newPdf = newPdfTime + ".pdf";
            String newPdfPath = inputPath + File.separator + File.separator + newPdf;
            //excel转pdf
            TestForExcel2PDF.excel2pdf(rePath,newPdfPath);
            logger.info("pdf路径为：" + newPdfPath);
            //pdf转图片
            PDFToImage pdfToImage = new PDFToImage();
            List<ImageValue> strings = pdfToImage.pdfToImage(2, newPdfPath, inputPath);

            logger.info("集合内容：" + strings);

//            所有流程完毕后删除文件夹下所有内容
            deleteDir(inputPath);

            return strings;
        }
        if(fileUrl.contains(".pdf")){

            //下载到的文件存放路径
            long docTime = System.currentTimeMillis();
            String fileName = docTime + ".pdf";
            String rePath = inputPath + File.separator + File.separator + fileName;
            PDFToImage.downloadFile(fileUrl,rePath);

            //pdf转图片
            PDFToImage pdfToImage = new PDFToImage();
            List<ImageValue> strings = pdfToImage.pdfToImage(2, rePath, inputPath);

            logger.info("集合内容：" + strings);

            //所有流程完毕后删除文件夹下所有内容
            deleteDir(inputPath);

            return strings;
        }
        return null;
    }



    //删除文件加下所有内容
    public static void deleteDir(String path){
        File file = new File(path);
        if(!file.exists()){//判断是否待删除目录是否存在
            System.err.println("The dir are not exists!");
            return ;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for(String name : content){
            File temp = new File(path, name);
            if(temp.isDirectory()){//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            }else{
                if(!temp.delete()){//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }
        return;
    }
}
