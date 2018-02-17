app.controller("blogcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	 
	 $scope.Blog={blogname:'',blogcontent:'',status:'A',likes:'0',dislikes:'0',views:'0',username:$rootScope.currentuser.email,userid:$rootScope.currentuser.userid};
	$scope.BlogComments={blogcomm:'',blogid:'',userid:''};

	function fetchAllBlogs()
	{
	$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getAllBlogs")
		.then(function(response)
		{
			console.log("Blogs retrieve successfully")
			$scope.blogs=response.data;
			console.log($scope.blogs)
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});
	};
	fetchAllBlogs();
	
	function myallblogs()
	{
		console.log("in all my blogs method")
		console.log($rootScope.currentuser.email)
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getAllMyBlogs/"+$rootScope.currentuser.userid)
		.then(function(response)
		{
			
			$rootScope.myblogs=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		
		
	}
	myallblogs();
	
	function fetchBlogById(idd)
	{
		
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getBlogById/"+idd).then(function(response){
				
				$rootScope.gblog=response.data;
				 
				
				console.log("blog fetched successfully")				
				},function(error){
					console.log("error in fetching blog")
				});
		
	}
	
	
	
	
	 $rootScope.maximum=getSelectedBlog
	 function getSelectedBlog(idd)
	 {
		 console.log("in add max method------"+idd)
		 
		 
		 
		 
		 console.log("view incremented")

		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getBlogById/"+idd).then(function(response){
				$rootScope.blogbyid=response.data;
				$rootScope.gblog=response.data;
				$cookieStore.put('blog',$rootScope.gblog);
				 
				
				console.log("blog fetched successfully")				
				},function(error){
					console.log("error in fetching blog")
				});
	
		
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getAllBlogComments/"+idd)
			.then(function(response)
			{
				
				$rootScope.gblogcomm=response.data;
				console.log($rootScope.gblogcomm)
				$cookieStore.put('blogcomm',$rootScope.gblogcomm);
			},function(error)
			{
				
			});
		 
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/incview/"+idd).then(function(response){
				
			},function(error){
			
			});
		
		 
		
		 $location.path('/blogview')
		 
	 }
	 
	
	 
	 
	 

	 $scope.addBlog=function()
	 {
		console.log("in add blog method"+$scope.Blog.blogname)
		 $http.post("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/addBlog",$scope.Blog).then(fetchAllBlogs(),function(response){
			 console.log("Blog added successfully")
								
			},function(error){
				console.error("Error while creating blog")
			});
		$location.path('/blog')
		 
	 }

	 
	 
	 $scope.fetchforedit=function(idd)
	 {
		 
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getBlogById/"+idd).then(function(response){
				$rootScope.eblog=response.data; 
					
				},function(error){
				
				});
		 $location.path('/blogforedit')	 
	 }
	 
	 
	 
	 

	 $scope.editBlog=function()
	 {
		console.log($scope.Blog.blogcontent)
if($scope.Blog.blogcontent==null)
	{
	$scope.Blog.blogcontent=$rootScope.eblog.blogcontent;
	}
		if($scope.Blog.blogname==null){
			$scope.Blog.blogname=$rootScope.eblog.blogname;
		}
		console.log($scope.Blog.blogcontent)
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/updateBlog/"+$rootScope.eblog.blogid+"/"+$scope.Blog.blogname+"/"+$scope.Blog.blogcontent).then(function(response){
			 console.log("Blog updated successfully");
								
			},function(error){
				console.error("Error while updating blog");
			});
		 
		 
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getBlogById/"+$rootScope.eblog.blogid).then(function(response){
				$rootScope.eblog=response.data; 
					
				},function(error){
				
				});
		
		$location.path('/blog')	 
		 
	 }
	 
	 $scope.deleteBlog=function(idd)
	 {
		console.log("in delete blog method")
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/deleteBlog/"+idd).then(fetchAllBlogs(),function(response){
			 console.log("Blog deleted successfully");
								
			},function(error){
				console.error("Error while deleting blog");
			});
		
		
		
		$location.path('/blog')	 
		 
	 }
	 
	 
	
	 
	 $scope.rejetcBlog=function(idd)
	 {
		console.log("in reject blog method")
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/rejectBlog/"+idd).then(fetchBlog(idd),fetchAllBlogs(),function(response){
			 console.log("Blog rejected successfully");
								
			},function(error){
				console.error("Error while rejecting blog");
			});
		$location.path('/blog')	 
		 
	 }
	 
	 $scope.likeBlog=function(idd)
	 {
		console.log("in like blog method")
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/likeBlog/"+idd).then(function(response){
			 console.log("Blog liked successfully");
								
			},function(error){
				console.error("Error while liking blog");
			});
		
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getBlogById/"+idd).then(function(response){
			
			$rootScope.gblog=response.data;
			 
			
			console.log("blog fetched successfully")				
			},function(error){
				console.log("error in fetching blog")
			});
		
		s
		
		$location.path('/blogview')	 
		 
	 }
	 
	 
	 $scope.dislikeBlog=function(idd)
	 {
		console.log("in like blog method")
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/dislikeBlog/"+idd).then(fetchBlogById(idd),function(response){
			 console.log("Blog disliked successfully");
								
			},function(error){
				console.error("Error while disliking blog");
			});
		
		$location.path('/blogview')	 
		 
	 }

	 
	 
	 $scope.addBlogComment=function()
	 {
		console.log("in add blogComment method")
		console.log($rootScope.gblog.blogid+$rootScope.currentuser.email+$scope.BlogComments.blogcomm)

		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/addBlogComments/"+$rootScope.gblog.blogid+"/"+$rootScope.currentuser.email+"/"+$scope.BlogComments.blogcomm).then(function(response){
			 console.log("BlogComments added successfully")
								
			},function(error){
				
			});
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getAllBlogComments/"+$rootScope.gblog.blogid)
		.then(function(response)
		{
			
			$rootScope.gblogcomm=response.data;
			
			
		},function(error)
		{
			
		});		
		
		$location.path('/blogview')	 
		 
	 }
	 
	 
	 $scope.updateBlogComment=function(idd)
	 {
		console.log("in update blogComment method")
		 $http.post("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/updateBlogComments/"+idd,$scope.BlogComments).then(function(response){
			 console.log("BlogComments updated successfully")
								
			},function(error){
				console.error("Error while updating blogComments")
			});
		$location.path('/blog')	 
		 
	 }
	 
	 
	 $scope.deleteBlogComment=function(idd)
	 {
		console.log("in delete blogcomment method")
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/deleteBlogComment/"+idd).then(function(response){
			 console.log("Blogcomments deleted successfully");
								
			},function(error){
				console.error("Error while deleting blogcomments");
			});
		
		$location.path('/blog')	 
		 
	 }
	 
	 
	 
});



app.controller("blogrequestcontroller", function ($scope,$http,$location,$rootScope) {
	function fetchAllblogreq()
	{
	
	 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/blogrequests")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.blogequests=response.data;
	
		 $location.path('/blogrequests')
							
		},function(error){
			
		});
	}
	fetchAllblogreq();
	
	 $rootScope.acceptblogrequests=function(id)
	 {
		 
		 console.log('in acceptblog request method')
		
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/approveblogRequests/"+id).then(fetchAllblogreq(),function(response){
			 
		console.log("accepted successfully")
			 $location.path('/blogrequests')
								
			},function(error){
				console.error("Error while accepting blogrequests Forum");
			});
		 
		 $location.path('/blogmanage')
		 
	 }
	 
	 
	 $rootScope.rejectblogrequests=function(id)
	 {
		 
		 console.log('in acceptblog request method')
		
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/rejectblogRequests/"+id).then(fetchAllblogreq(),function(response){
			 
		console.log("accepted successfully")
			 $location.path('/blogrequests')
								
			},function(error){
				console.error("Error while rejecting blogrequests Forum");
			});
		 
		 $location.path('/blogmanage')
		 
	 }
	
	
});