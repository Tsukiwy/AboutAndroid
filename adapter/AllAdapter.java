package com.example.adapter;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.example.listview.ListViewAll;

import com.example.sns_android_01.R;
import com.example.utils.ImageControlUtil;
import com.example.utils.iputil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA.Info;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 * @author  Administrator
 * @date    2018年5月24日 上午2:17:45
 * @version 1.0
 *	自定义适配器：AllAdapter
 */
public class AllAdapter extends ArrayAdapter<ListViewAll> {
	static final int IMG =1000;
	private int resourceId;
	private View view;
	private File fString;
	
	//通过textViewResourceId知道将哪个xml文件转成View对象
	public AllAdapter(Context context, int textViewResourceId,List<ListViewAll> objects) {
		super(context, textViewResourceId,objects);
		resourceId = textViewResourceId;
		//读取缓存目录
		fString= context.getCacheDir();
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		//根据position获取
		ListViewAll listViewAll = getItem(position);
		//LayoutInflater：布局映射器，通过from方法获取布局映射器对象
		view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		
		//初始化
		//ImageView tag = (ImageView) view.findViewById(R.id.all_tag);
		TextView title = (TextView) view.findViewById(R.id.all_title);
		TextView score = (TextView) view.findViewById(R.id.all_score);
		TextView brief = (TextView) view.findViewById(R.id.all_brief);
		
		
		title.setText(listViewAll.getTitle());
		score.setText(listViewAll.getScore().toString());
		brief.setText(listViewAll.getBrief());
		
		
		//指定文件的路径  ，将文件写入缓存
		final File file = new File(fString,listViewAll.getNo()+".png");  
		Log.i("logB", listViewAll.getTag());
		Log.i("logB", file.getAbsolutePath());
		//如果文件存在，直接从本地打开  
		if(file.exists())  
		    {  
		    System.out.println("从本地读取的");  
		    Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());  
		    ImageView tag = (ImageView) view.findViewById(R.id.all_tag); 
		    tag.setImageBitmap(bm);  
		    } 
		else{
			//服务端获取图片
			ImageControlUtil.sendImage(iputil.getFwqIp()+"/Social_networking_site_01/"+listViewAll.getTag(),handler,file,IMG);
			}
		return view;
	}
	
	
	private final Handler handler = new Handler()  
	{  
	    public void handleMessage(android.os.Message msg)   
	    {  	      
	    	switch (msg.what) {
	    	 case IMG:
				//初始化
				ImageView tag = (ImageView) view.findViewById(R.id.all_tag);
		        tag.setImageBitmap((Bitmap) msg.obj); 
		        break;
	    	}
	    };  
	};  
 
	
}
