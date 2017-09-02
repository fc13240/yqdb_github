package com.p2p.webapp.common.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ClipImageTool{
	
	 /**
	  * 
	  * @param sourceImgFileName 源文件名
	  * @param x 截取的起始x位置 
	  * @param y 截取的起始y位置
	  * @param w 截取的宽度  0 代表不截取
	  * @param h 截取的高度  0 代表不截取
	  * @param sx 缩放的X比例（宽） 尺寸大小 1.0表示不缩放
	  * @param sy 缩放的Y比例（高） 尺寸大小 1.0表示不缩放
	  * @param targetImgFileName 目标文件名，含扩展名，暂时固定必须以 png作为扩展名。
	  * @return
	  */
	 public static int clipImage(String sourceImgFileName,int x,int y,int w,int h,double sx, double sy,String targetImgFileName){
		 BufferedImage sourceImage=null;
		 BufferedImage targetImage=null;
		 File sourceFile = null;
		 File targetFile = null;

		 sourceFile = new File(sourceImgFileName);
		 if(!sourceFile.exists()){
//			 System.out.println("输入文件不存在！"+sourceImgFileName);
			 return 1;			 
		 }
		 try {
			 sourceImage=ImageIO.read(sourceFile);
	     } catch (IOException e) {
//			 System.out.println("输入文件不是一个图片文件！"+sourceImgFileName);
			 return 2;
		 }
	     
		 //需要判读位置是否超出范围     
	     //超出部分，暂不补白
	     if(sourceImage.getHeight()*sy<y+h){
//			 System.out.println("截取位置的高度超出范围 "+sourceImgFileName);
			 return 3;	    	 
	     }
	     if(sourceImage.getWidth()*sx<x+w){
//			 System.out.println("截取位置的宽度超出范围 "+sourceImgFileName);
			 return 4;	    	 
	     }

	     //增加缩放效果  质量效果见参数：AffineTransformOp.TYPE_NEAREST_NEIGHBOR
	     if(sx!=1.0d || sy!=1.0d){
//		     System.out.println("缩放");
		     AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);
		     AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		     //缩放结果
		     sourceImage = op.filter(sourceImage, null);  	    	 
	     }
	     else{
//	    	 System.out.println("不缩放");
	     }
	     //判断是否需要截取 也许是源图即可
	     if((sourceImage.getHeight()==y+h && sourceImage.getWidth()==x+w)
	    	|| y+h+x+w==0	 ){
//	    	 System.out.println("不截取");
	    	 targetImage = sourceImage;
	     }
	     else{
//		      System.out.println("截取");
			  int rgbs[]=new int[w*h];
			  rgbs=sourceImage.getRGB(x,y, w, h, rgbs,0,w);
			  targetImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
			  targetImage.setRGB(0, 0, w, h, rgbs,0,w);
	     }
	     
		  targetFile = new File(targetImgFileName);
		  //targetFile.deleteOnExit();

    	  //注意如果是 gif 类型，只能转成 png（才能保留好的效果，如透明）  
		  //使用 jpg 时，缩小正常，但要截取后发现颜色失真
		  String formatName = "png";
		  try {
			if(ImageIO.write(targetImage,formatName, targetFile)){
//				 System.out.println("保存文件成功！"+targetImgFileName);		
				 return 0;				
			}else{
//				 System.out.println("保存文件失败！"+sourceImgFileName);		
				 return 5;								
			}

		} catch (Exception e) {
			e.printStackTrace();
//			 System.out.println("保存文件错误！"+targetImgFileName);
			 return 6;
		}	    	 
	     
		//return 0;
	 }

	 /**
	  * 等比缩放图片（不变形），不截取
	  * @param sourceImgFileName 源文件名
	  * @param w 缩放后的宽度  >0 
	  * @param h 缩放后的高度  >0 
	  * @param targetImgFileName 目标文件名，含扩展名，暂时固定必须以 png作为扩展名。
	  * @return
	  */
	 public static int ScaleImage(String sourceImgFileName,int w,int h,String targetImgFileName){
		 BufferedImage sourceImage=null;
		 BufferedImage targetImage=null;
		 File sourceFile = null;
		 File targetFile = null;

		 sourceFile = new File(sourceImgFileName);
		 if(!sourceFile.exists()){
			 System.out.println("输入文件不存在！"+sourceImgFileName);
			 return 1;			 
		 }
		 try {
			 sourceImage=ImageIO.read(sourceFile);
	     } catch (IOException e) {
			 System.out.println("输入文件不是一个图片文件！"+sourceImgFileName);
			 return 2;
		 }
	     
		 //需要取出原图宽高，计算出缩小比例
	     double sw = (double)w / (double)sourceImage.getWidth();
	     double sh = (double)h / (double)sourceImage.getHeight();
	     //取最小的比例值
	     double s = sw>sh?sh:sw;
	     

	     //增加缩放效果  质量效果见参数：AffineTransformOp.TYPE_NEAREST_NEIGHBOR
		     System.out.println("缩放");
		     AffineTransform transform = AffineTransform.getScaleInstance(s, s);
		     AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		     //缩放结果
		     targetImage = op.filter(sourceImage, null);  	    	 
	     
		  targetFile = new File(targetImgFileName);
		  //targetFile.deleteOnExit(); 退出时删除

    	  //注意如果是 gif 类型，只能转成 png（才能保留好的效果，如透明）  
		  //使用 jpg 时，缩小正常，但要截取后发现颜色失真
		  String formatName = "png";
		  try {
			if(ImageIO.write(targetImage,formatName, targetFile)){
				 System.out.println("保存文件成功！"+targetImgFileName);		
				 return 0;				
			}else{
				 System.out.println("保存文件失败！"+sourceImgFileName);		
				 return 5;								
			}

		} catch (Exception e) {
			e.printStackTrace();
			 System.out.println("保存文件错误！"+targetImgFileName);
			 return 6;
		}	    	 
	 }
	 
	 /**
	  * 不等比缩放图片（变形），不截取
	  * @param sourceImgFileName 源文件名
	  * @param w 缩放后的宽度  >0 
	  * @param h 缩放后的高度  >0 
	  * @param targetImgFileName 目标文件名，含扩展名，暂时固定必须以 png作为扩展名。
	  * @return
	  */
	 public static int ScaleImage1(String sourceImgFileName,int w,int h,String targetImgFileName){
		 BufferedImage sourceImage=null;
		 BufferedImage targetImage=null;
		 File sourceFile = null;
		 File targetFile = null;

		 sourceFile = new File(sourceImgFileName);
		 if(!sourceFile.exists()){
			 System.out.println("输入文件不存在！"+sourceImgFileName);
			 return 1;			 
		 }
		 try {
			 sourceImage=ImageIO.read(sourceFile);
	     } catch (IOException e) {
			 System.out.println("输入文件不是一个图片文件！"+sourceImgFileName);
			 return 2;
		 }
	     
		 //需要取出原图宽高，计算出缩小比例
	     double sw = (double)w / (double)sourceImage.getWidth();
	     double sh = (double)h / (double)sourceImage.getHeight();
	     
	     

	     //增加缩放效果  质量效果见参数：AffineTransformOp.TYPE_NEAREST_NEIGHBOR
		     System.out.println("缩放");
		     AffineTransform transform = AffineTransform.getScaleInstance(sw, sh);
		     AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		     //缩放结果
		     targetImage = op.filter(sourceImage, null);  	    	 
	     
		  targetFile = new File(targetImgFileName);
		  //targetFile.deleteOnExit(); 退出时删除

    	  //注意如果是 gif 类型，只能转成 png（才能保留好的效果，如透明）  
		  //使用 jpg 时，缩小正常，但要截取后发现颜色失真
		  String formatName = "png";
		  try {
			if(ImageIO.write(targetImage,formatName, targetFile)){
				 System.out.println("保存文件成功！"+targetImgFileName);		
				 return 0;				
			}else{
				 System.out.println("保存文件失败！"+sourceImgFileName);		
				 return 5;								
			}

		} catch (Exception e) {
			e.printStackTrace();
			 System.out.println("保存文件错误！"+targetImgFileName);
			 return 6;
		}	    	 
	 }
	 
     public static void main(String args[]){
    	//测试 
//    	ClipImageTool.clipImage("D:\\IMG_2396.JPG",1000,500,500,500,0.5,0.5,"D:\\IMG_new7.jpg");
    	 //只是缩放
    	ClipImageTool.ScaleImage("D:\\IMG_2396.JPG",1000,500,"D:\\IMG_s4.jpg");
     }
} 