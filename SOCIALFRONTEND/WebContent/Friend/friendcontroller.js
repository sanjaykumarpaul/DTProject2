app.controller("friendrequsetcontroller", function ($scope,$http,$location,$rootScope) {

	$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/friend/getAllMyFriendRequests/"+ $rootScope.currentuser.userid)

	.then(function(response) {
		$scope.myfriendreqs = response.data;
		console.log("all my friendsreqs  fetched")
	},function(error)
	{
		console.log("Error on retrieving forums")
	});

});




app.controller("friendcontroller", function ($scope,$http,$location,$rootScope) {
	
	function fetchAllUsers()
	{
		
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/friend/getMyFriends/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.myfriends = response.data;
			console.log("all my friends fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/friend/getAllOtherUsers/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.otherusers = response.data;
			console.log("all other users fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		

		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/friend/getOnlineFriends/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.onlineusers = response.data;
			console.log("all online users fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/friend/getAllMyFriendRequests/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.myfriendreqs = response.data;
			console.log("all my friendsreqs  fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		
		
	}
	;
	fetchAllUsers();
	
	$scope.insertFriend = function(friendid)
	{
		console.log('entered add friend method'+friendid);
		$http.get('http://localhost:8080/SOCIALRESTCONTROLLER/friend/addFriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllUsers(), function(response) {
			console.log("successful friend add ");
			$location.path("/mywall")
		});
	}
	
	
	

	$scope.unfriend = function(friendid)
	{
	console.log("in unfriend method")
		$http.get('http://localhost:8080/SOCIALRESTCONTROLLER/friend/unfriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllUsers(), function(response) {
			console.log("successful friend add ");
			$location.path("/mywall")
		});
	}
	
	$scope.acceptfriend = function(friendid)
	{
	console.log("in unfriend method")
		$http.get('http://localhost:8080/SOCIALRESTCONTROLLER/friend/acceptfriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllUsers(), function(response) {
			console.log("successful friend add ");
			$location.path("/mywall")
		});
	}
	
	$rootScope.friendpreview=function(friendid)
	{
		if(friendid==$rootScope.currentuser.userid)
			{
			$location.path("/mywall")
			}
		else
			{
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/user/getUser/"+friendid)

		.then(function(response) {
			$rootScope.friendpreviewdata = response.data;
			$scope.fr=response.data;
			console.log($rootScope.friendpreviewdata.email);
			console.log($rootScope.friendpreviewdata.userid);
			console.log($rootScope.currentuser.userid);
		},function(error)
		{
			console.log("Error on retrieving forums")
		});

	$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/user/ismyfriend/"+$rootScope.friendpreviewdata.userid+"/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.ismyfriend = response.data;
		
		},function(error)
		{
			
		});
		
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/user/friendsfriends/"+$rootScope.friendpreviewdata.userid+"/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.friendsfriends = response.data;
		
		},function(error)
		{
			
		});
		
		
		
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/blogs/getAllMyBlogs/"+$rootScope.friendpreviewdata.userid)
		.then(function(response)
		{
			
			$rootScope.friendblogs=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		
		$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/forums/myforums/"+$rootScope.friendpreviewdata.userid)
		.then(function(response)
		{
			
			$rootScope.friendforums=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		$location.path("/friendwall")
	}
	}
	
	
});