package com.aus.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.beanutils.BeanUtils;
 
/**
 * 文件操作
 */
public class ExportCSVFile {
	 private final static byte commonCsvHead[] = { (byte) 0xEF, (byte) 0xBB,
			   (byte) 0xBF };
  /**
   * 生成为CVS文件 
   * @param exportData
   *       源数据List
   * @param map
   *       csv文件的列表头map
   * @param outPutPath
   *       文件路径
   * @param fileName
   *       文件名称
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath,
                   String fileName) {
    File csvFile = null;
    BufferedWriter csvFileOutputStream = null;
    try {
      File file = new File(outPutPath);
      if (!file.exists()) {
        file.mkdir();
      }
      //定义文件名格式并创建
      csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
      // UTF-8使正确读取分隔符"," 
      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
        csvFile), "GB2312"), 1024);
      // 写入文件头部 
      for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
        csvFileOutputStream
          .write((String) propertyEntry.getValue() != null ? (String) propertyEntry
            .getValue() : "");
        if (propertyIterator.hasNext()) {
          csvFileOutputStream.write(",");
        }
      }
      csvFileOutputStream.newLine();
      // 写入文件内容 
      for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
        Object row = (Object) iterator.next();
        for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
          .hasNext();) {
          java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
            .next();
          if(propertyEntry.getKey() != null){
        	  if( BeanUtils.getProperty(row,
      	            (String) propertyEntry.getKey()) != null){
        	  csvFileOutputStream.write((String) BeanUtils.getProperty(row,
        	            (String) propertyEntry.getKey()));
        	  }else{
        		  csvFileOutputStream.write("");
        	  }
          }else{
        	  csvFileOutputStream.write("");
          }
         
          if (propertyIterator.hasNext()) {
            csvFileOutputStream.write(",");
          }
        }
        if (iterator.hasNext()) {
          csvFileOutputStream.newLine();
        }
      }
      csvFileOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        csvFileOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return csvFile;
  }
 
  /**
   * 下载文件
   * @param response
   * @param csvFilePath
   *       文件路径
   * @param fileName
   *       文件名称
   * @throws IOException
   */
  public void exportFile(HttpServletResponse response, String csvFilePath, String fileName)
                                                  throws IOException {
    response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("HistoryTransaction.csv", "UTF-8"));
    InputStream in = null;
    try {
      in = new FileInputStream(csvFilePath);
      int len = 0;
      byte[] buffer = new byte[1024];
    //  response.setCharacterEncoding("GB2312");
      OutputStream out = response.getOutputStream();
      while ((len = in.read(buffer)) > 0) {
       // out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        out.write(buffer, 0, len);
      }
    } catch (FileNotFoundException e) {
     // System.out.println(e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
 
  /**
   * 删除该目录filePath下的所有文件
   * @param filePath
   *      文件目录路径
   */
  public static void deleteFiles(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
      File[] files = file.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
          files[i].delete();
        }
      }
    }
  }
 
  /**
   * 删除单个文件
   * @param filePath
   *     文件目录路径
   * @param fileName
   *     文件名称
   */
  public static void deleteFile(String filePath, String fileName) {
    File file = new File(filePath);
    if (file.exists()) {
      File[] files = file.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
          if (files[i].getPath().equals(fileName)) {
            files[i].delete();
            return;
          }
        }
      }
    }
  }
 
  
  public  void createCSVFileCommon(HttpServletResponse response,LinkedHashMap<String, String> contextMap,List<?> dateList) {
	  String saveDirectory;
		try {
			saveDirectory = URLDecoder.decode(this.getClass().getResource("/").getPath().replace("/WEB-INF", "").replace("/classes", ""),
						"utf-8") + "tmp";
			 String fileName = (int) (Math.random() * 100000)+"";
			 //创建文件
			 File tempCVS = ExportCSVFile.createCSVFile(dateList, contextMap, saveDirectory, fileName);
			 //下载文件
			 exportFile(response,tempCVS.getPath(),fileName+".csv");
			 //删除文件
			 deleteFile(saveDirectory,tempCVS.getPath());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}