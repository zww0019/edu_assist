package com.warzone.util.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

public class Compress {
	public static List<String> scanFile(String path) {
		File allFile = new File(path);
		if (!allFile.exists()) {
			try {
				throw new Exception("指定的路径："+path+"不存在");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		File allFileArr[] = allFile.listFiles();
		List<String> filenames = new ArrayList<String>();
		for (int i = 0; i < allFileArr.length; i++) {
			System.out.println(allFileArr[i]);
			if (allFileArr[i].getName().contains(".zip") || allFileArr[i].getName().contains(".rar")) {
				filenames.add(path + allFileArr[i].getName());
			}
		}
		return filenames;
	}
	public static void unZIP(String path,String descDir) throws IOException{
		ZipFile zip = new ZipFile(path, Charset.forName("GBK"));
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			System.out.println(outPath);
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		zip.close();
	}
	@SuppressWarnings("rawtypes")
	public static void unCompressFiles(List<String> filenames, String descDir) throws Exception {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		for (String path : filenames) {
			if (path.contains(".zip")) {
				unZIP(path, descDir);
			}else{
				unrar(new File(path), pathFile);
			}
		}
		System.out.println("******************解压完毕********************");
	}
	 public static void  unrar(File sourceRar, File destDir) throws Exception {  
	        Archive archive = null;  
	        FileOutputStream fos = null; 
	        String compressFileName = null;
	        System.out.println("Starting...");  
	        try {
	            archive = new Archive(sourceRar);  
	            FileHeader fh = archive.nextFileHeader();  
	            int count = 0;  
	            File destFileName = null;  
	            while (fh != null) {  
	            	if(fh.isUnicode()){
	            		compressFileName = fh.getFileNameW().trim();
	            	}else{
	            		compressFileName = fh.getFileNameString().trim();
	            	}
	                System.out.println((++count) + ") " + compressFileName);  
	                destFileName = new File(destDir.getAbsolutePath() + "/" + compressFileName);  
	                if (fh.isDirectory()) {  
	                    if (!destFileName.exists()) {  
	                        destFileName.mkdirs();  
	                    }  
	                    fh = archive.nextFileHeader();  
	                    continue;  
	                }   
	                if (!destFileName.getParentFile().exists()) {  
	                    destFileName.getParentFile().mkdirs();  
	                }  
	                fos = new FileOutputStream(destFileName);  
	                archive.extractFile(fh, fos);  
	                fos.close();  
	                fos = null;  
	                fh = archive.nextFileHeader();  
	            }  
	  
	            archive.close();  
	            archive = null;  
	            System.out.println("Finished !");  
	        } catch (Exception e) {  
	            throw e;  
	        } finally {  
	            if (fos != null) {  
	                try {  
	                    fos.close();  
	                    fos = null;  
	                } catch (Exception e) {  
	                    //ignore  
	                }  
	            }  
	            if (archive != null) {  
	                try {  
	                    archive.close();  
	                    archive = null;  
	                } catch (Exception e) {  
	                    //ignore  
	                }  
	            }  
	        }  
	    } 
	 public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
	        boolean flag = false;  
	        File sourceFile = new File(sourceFilePath);  
	        FileInputStream fis = null;  
	        BufferedInputStream bis = null;  
	        FileOutputStream fos = null;  
	        ZipOutputStream zos = null;  
	          
	        if(sourceFile.exists() == false){  
	            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");  
	        }else{  
	            try {  
	                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
	                if(zipFile.exists()){  
	                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
	                }else{  
	                    File[] sourceFiles = sourceFile.listFiles();  
	                    if(null == sourceFiles || sourceFiles.length<1){  
	                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");  
	                    }else{  
	                        fos = new FileOutputStream(zipFile);  
	                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
	                        byte[] bufs = new byte[1024*10];  
	                        for(int i=0;i<sourceFiles.length;i++){  
	                            //创建ZIP实体，并添加进压缩包  
	                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
	                            zos.putNextEntry(zipEntry);  
	                            //读取待压缩的文件并写进压缩包里  
	                            fis = new FileInputStream(sourceFiles[i]);  
	                            bis = new BufferedInputStream(fis, 1024*10);  
	                            int read = 0;  
	                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
	                                zos.write(bufs,0,read);  
	                            }  
	                        }  
	                        flag = true;  
	                    }  
	                }  
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } finally{  
	                //关闭流  
	                try {  
	                    if(null != bis) bis.close();  
	                    if(null != zos) zos.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                    throw new RuntimeException(e);  
	                }  
	            }  
	        }  
	        return flag;  
	    }  
}
