app.controller('productCtrl', function($scope, $http) {
    $scope.productType = "ABC";
    $scope.$watch('productType', function(value) {
    	$scope.productSelected = value;
    	console.log(value);
    	
    	$http.defaults.headers.post["Content-Type"] = "application/json";
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
    	
    	$http.get('http://localhost:8080/getCompaniesByProduct?product='+value)
        .success(function (data, status, headers, config) {
            $scope.companies = data;
        })
        .error(function (data, status, header, config) {
        	console.log("Error while retrieving companies");
        });
    	
    });
    
    $scope.companyChange = function() {
    	$scope.loading = true;
    	var company=$scope.selectedCompany;
    	console.log(company);
    	console.log($scope.loading);
    	
    	$http.defaults.headers.post["Content-Type"] = "application/json";
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
    	$http.get('http://localhost:8080/getConfigByCompany?company='+company)
        .success(function (data, status, headers, config) {
            $scope.configs = data;
            $scope.loading = false;
        })
        .error(function (data, status, header, config) {
        	console.log("Error while retrieving config for company");
        });
	};
	
	$scope.cities=[];
	$scope.loadData = function() {
		$scope.loading = true;
		$http.defaults.headers.post["Content-Type"] = "application/json";
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
    	$http.get('http://localhost:8080/getAllCities')
        .success(function (data, status, headers, config) {
            $scope.cities = data;
            $scope.loading = false;
        })
        .error(function (data, status, header, config) {
        	console.log("Error while retrieving Cities");
        });
	};
    
});
app.controller('myCtrl', function($scope, $http) {
	$scope.message="Hello Raj"
});


