package com.example.utils;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 *
 * @author  Administrator
 * @date    2018年5月23日 下午10:46:36
 * @version 1.0
 *
 */
public class ImageControlUtil {
	
	//android链接服务器获取图片

	public static void sendImage(final String path,final Handler mHandler,final File file,final int msgwhat) {  
		
		 //开启一个线程  
        Thread thread = new Thread()  
        {  
            @Override  
            public void run() {  
            	
        		
        		//Log.i("logB", path);
            	Log.i("logB", file.getAbsolutePath());
        	    try {  
            		URL url = new URL(path);
            	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            	    conn.setConnectTimeout(5000);
            	    conn.setRequestMethod("GET");
                    //如果响应码为200，说明请求成功
        	        if(conn.getResponseCode() == 200){  
        	        	Log.i("logB", "success");
        	        	//获取服务器响应头中的流 
        	            InputStream inputStream = conn.getInputStream();  
        	
        			    FileOutputStream fos = new FileOutputStream(file);  
                        byte[] b = new byte[1024];  
                        int len = 0;  
                        while((len = inputStream.read(b)) != -1)  
                        {  
                            fos.write(b, 0, len);  
                        }  
                        fos.close();  
                         
                        
                        //从本地加载图片  
        	            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());  
        	            inputStream.close(); 
        	            
						//发生更新UI的消息  
                        Message msg = mHandler.obtainMessage(msgwhat);  
                        msg.obj = bitmap;  
                        mHandler.sendMessage(msg);
        	        }  
        	    } catch (Exception e) {  
        	        e.printStackTrace();  
        	    }  
        	} 
            
        };
		
        //启动线程任务  
        thread.start();  
	}
	
	
	
	
	
	
	
	
	
}
