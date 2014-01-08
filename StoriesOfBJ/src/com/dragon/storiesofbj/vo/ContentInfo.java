package com.dragon.storiesofbj.vo;

public class ContentInfo {

	public String content;
	public String login;
	public int up;
	public int down;
	public int comments_count;
	
	public ContentInfo(String content, int up, int down, int comments_count, String login){
		this.content = content;
		this.up = up;
		this.down = down;
		this.comments_count = comments_count;
		this.login = login;
	}
}
