'use strict';
var app = angular
	.module('weatherApp', ['angular.filter', 'ngMaterial'])
	.controller("weatherController", weatherController)
	.service("weatherService", weatherService);