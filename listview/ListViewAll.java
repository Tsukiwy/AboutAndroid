package com.example.listview;

public class ListViewAll {
private String title;
private Float score;
private String brief;
private String tag; //访问服务端的图片url(resources/XXimg/)
private String no;//缓存图片编号

//构造器
public ListViewAll(String tag,String title, Float score, String brief,String no) {
	this.title = title;
	this.score = score;
	this.brief = brief;
	this.tag = tag;
	this.no = no;
}

public ListViewAll(){
	
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Float getScore() {
	return score;
}
public void setScore(Float score) {
	this.score = score;
}
public String getBrief() {
	return brief;
}
public void setBrief(String brief) {
	this.brief = brief;
}
public String getTag() {
	return tag;
}
public void setTag(String tag) {
	this.tag = tag;
}

public String getNo() {
	return no;
}

public void setNo(String no) {
	this.no = no;
}


}
