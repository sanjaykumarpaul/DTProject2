app.controller("picuploadcontroller", function ($scope,$location,$http,$rootScope,$cookieStore) {

	 $http.post("http://localhost:8080/SOCIALRESTCONTROLLER/user/login",$rootScope.currentuser).then(function(response)
			 {
		
		
		 $rootScope.currentuser=response.data;
		 $cookieStore.put('user',response.data);
		
		
			 });
	
	
});

app.controller("registercontroller", function ($scope,$location,$http,$rootScope,$cookieStore) {
	 $scope.msg = "Register  page";
	 $scope.Users={firstname:'',lastname:'',email:'',password:'',role:'ROLE_USER',isonline:'NO',status:'P'};
	 $scope.register=function()
	 {
		 console.log("in register controller angualar");
		
		 $http.post("http://localhost:8080/SOCIALRESTCONTROLLER/user/register",$scope.Users).then(function(response){
		 
			 console.log("Registerd Successfully")
			 $scope.Users=response.data;
			 $location.path("/login")
								
			},function(error){
				console.error("Error while creating user"+error)
			});
		 
	 }
	 
	 
	 
	 $scope.login=function()
	 {
		 console.log("in login method");
		 $http.post("http://localhost:8080/SOCIALRESTCONTROLLER/user/login",$scope.Users).then(function(response)
				 {
			
			
			 $rootScope.currentuser=response.data;
			 if($rootScope.currentuser.errormessage!="login success")
				 {
				 alert($rootScope.currentuser.errormessage)
				 }
			
		if($rootScope.currentuser.errormessage=="You are not yet approved by admin" || $rootScope.currentuser.errormessage=="You rejected please contact admin" || $rootScope.currentuser.errormessage=="You are not registered yet" || $rootScope.currentuser.errormessage=="email id or password incorrect")
			{
			$location.path("/login")
			
			} 
		else
			{
			$cookieStore.put('user', $rootScope.currentuser);
			 console.log("ROLE"+$rootScope.currentuser.role)
			 $location.path("/blog")
			}
			
				 
				 });
		 
	 }
	 
});



app.controller("logoutcontroller", function ($scope,$location,$http,$rootScope,$cookieStore) {
console.log("in logout controlelr")
	 $scope.logout=function()
	 {
	 
		 console.log( $rootScope.currentuser.email)
			$http.get("http://localhost:8080/SOCIALRESTCONTROLLER/user/logout/"+ $rootScope.currentuser.email)
				.then(function(response)
				{
					 $rootScope.currentuser=null;
					 $cookieStore.remove('user');
				
					 $location.path("/login")
					
				},function(error)
				{
					
				});
	 
	 }
	 
	 
	 
	 


	
});



app.controller("userrequestcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	function fetchAlluserreq()
	{
	
	 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/user/getAllUsersreq")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.userrequests=response.data;
	
		 $location.path('/userrequests')
							
		},function(error){
			console.error("Error while fetching requests");
		});
	}
	
	
	fetchAlluserreq();
	
	
	
	
	
	
	 $scope.acceptuserrequests=function(id)
	 {
		 
		 
		console.log("in user request  accept method")
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/user/approveusers/"+id).then(fetchAlluserreq(),function(response){
			 
			 console.log("userrequets accepted  successfully");
			 $location.path('/userrequests')
								
			},function(error){
				console.error("Error while accepting userrequets");
			});
		$location.path('/blog')
		 
	 }
	 
	 
	 
	 $scope.rejectuserrequests=function(id)
	 {
		 
		 
		console.log("in user request  reject method")
		 $http.get("http://localhost:8080/SOCIALRESTCONTROLLER/user/rejectusers/"+id).then(fetchAlluserreq(),function(response){
			 
			 console.log("userrequets rejected  successfully");
			 $location.path('/userrequests')
								
			},function(error){
				console.error("Error while rejecting userrequets");
			});
		$location.path('/blog')
		 
	 }

	 
	
	
});