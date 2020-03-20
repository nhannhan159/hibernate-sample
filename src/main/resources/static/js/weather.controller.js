'use strict';
var weatherController = function(weatherService) {
    this.$onInit = function() {
        var self = this;
        self.cities = [];
        self.cityWeathers = undefined;
        weatherService.getCities().then(function(cities) {
            self.cities = cities;
        });
        weatherService.getCityWeathers().then(function(cityWeathers) {
            self.cityWeathers = cityWeathers;
        });
    };

    this.addCity = function(cityName) {
        var self = this;
        var isDuplicate = false;
        self.cities.forEach(function(city) {
            if (cityName === city.name) {
                isDuplicate = true;
            }
        });
        if (!isDuplicate) {
            weatherService.addCity(cityName).then(function(city) {
                self.cities.push(city);
                weatherService.getCityWeathers().then(function(cityWeathers) {
                    self.cityWeathers = cityWeathers;
                });
            });
        }
    };

    this.deleteCity = function(index) {
        var self = this;
        weatherService.deleteCity(self.cities[index].name).then(function() {
            self.cities.splice(index, 1);
            weatherService.getCityWeathers().then(function(cityWeathers) {
                self.cityWeathers = cityWeathers;
            });
        });
    };

    this.getCityWeathers = function(cityName) {
        var self = this;
        weatherService.getCityWeathers(cityName).then(function(cityWeathers) {
            self.cityWeathers = cityWeathers;
        });
    };

    this.deleteCityWeather = function(index) {
        var self = this;
        weatherService.deleteCityWeather(self.cityWeathers[index].id).then(function() {
            self.cityWeathers.splice(index, 1);
        });
    };

    this.getReadableTime = function(timestamp) {
        var m_names = ["January", "February", "March",
            "April", "May", "June", "July", "August", "September",
            "October", "November", "December"];
        var date = new Date(parseInt(timestamp) * 1000);
        var date_date = date.getDate();
        var date_month = date.getMonth();
        var date_year = date.getFullYear();
        return m_names[date_month] + ' ' + date_date + ', ' + date_year;
    };

    this.getCelsiusTemperature = function(kelvinValue) {
        return (parseFloat(kelvinValue) - 273.15).toFixed(2);
    };
};