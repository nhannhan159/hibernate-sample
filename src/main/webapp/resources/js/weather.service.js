'use strict';
var weatherService = function($q, $http) {
	this.getCities = function () {
		var defer = $q.defer();
		$http.get("/api/cities")
			.then(function(data) {
				defer.resolve(data.data);
			})
			.catch(function(error) {
				defer.reject();
			});
		return defer.promise;
	};

	this.addCity = function(cityName) {
		var defer = $q.defer();
		$http.post("/api/cities/" + cityName)
			.then(function(data) {
				defer.resolve(data.data);
			})
			.catch(function(error) {
				defer.reject();
			});
		return defer.promise;
	};

	this.deleteCity = function(cityName) {
		var defer = $q.defer();
		$http.delete("/api/cities/" + cityName)
			.then(function(data) {
				defer.resolve(data.data);
			})
			.catch(function(error) {
				defer.reject();
			});
		return defer.promise;
	};

	this.getCityWeathers = function(cityName) {
		var defer = $q.defer();
		cityName = cityName || '';
		$http.get("/api/cityWeathers?cityName=" + cityName)
			.then(function(data) {
				defer.resolve(data.data);
			})
			.catch(function(error) {
				defer.reject();
			});
		return defer.promise;
	};

	this.deleteCityWeather = function(id) {
		var defer = $q.defer();
		$http.delete("/api/cityWeathers/" + id)
			.then(function(data) {
				defer.resolve(data.data);
			})
			.catch(function(error) {
				defer.reject();
			});
		return defer.promise;
	};
};