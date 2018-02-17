package com.collaboration.dao;

import java.util.ArrayList;

import com.collaboration.model.Blog;
import com.collaboration.model.BlogComments;

public interface BlogDAO {
	public boolean addBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public Blog getBlog(int blogId);
	public ArrayList<Blog> getAllBlogs();
	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
	public boolean addBlogComment(BlogComments blogcomment);
	public boolean deleteBlogComment(BlogComments blogcomment);
	public boolean updateBlogComment(BlogComments blogcomment);
	public BlogComments getBlogComment(int commentId);
	public ArrayList<BlogComments> getAllBlogComments(int blogid);
	public boolean like(int blogid);
	public boolean dislike(int blogid);
	public boolean incview(int blogid);
	public ArrayList<Blog> getAllBlogRequests();
	public ArrayList<Blog> getAllMyBlogs(String email);
}
