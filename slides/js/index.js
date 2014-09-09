
(function() {
  var app = angular.module('slides', ['ngRoute', 'slideControllers']);

  app.config(['$routeProvider',
    function($routeProvider) {
      $routeProvider.
          when('/:slide', {
            templateUrl: 'views/title.html',
            controller: 'Title'
          }).
          when('/:slide/:slideId', {
            templateUrl: 'views/slide.html',
            controller: 'Slide'
          }).
          when('/', {
            templateUrl: 'views/catalog.html',
            controller: 'Catalog'
          }).
          otherwise({
            redirectTo: '/'
          });
    }]);

  var controllers = angular.module('slideControllers', []);
  var data = null;

  function loadData($http, slide, callback) {
    if (data === null) {
      $http.get('/slides/' + slide + '.json').then(
          function(response) {
            data = response.data;
            callback(data);
          });
    } else {
      callback(data);
    }
  }

  controllers.controller('Title', ['$scope', '$http', '$routeParams',
    function($scope, $http, $routeParams) {
      function updateData(data) {
        $scope.title = data;
      }

      loadData($http, $routeParams.slide, updateData);

      document.addEventListener('keydown', function handler(event) {
        if (event.keyCode === 39) {
          location.href = '#/' + $routeParams.slide + '/0';
          document.removeEventListener('keydown', handler);
        }
      });
    }]);

  controllers.controller('Slide', ['$scope', '$http', '$routeParams',
    function($scope, $http, $routeParams) {
      var index = $routeParams.slideId;

      function updateData(data) {
        $scope.slide = data.pages[index];
        index = Number($routeParams.slideId < data.pages.length ?
            $routeParams.slideId : data.pages.length - 1);
      }

      loadData($http, $routeParams.slide, updateData);

      document.addEventListener('keydown', function handler(event) {
        if (event.keyCode === 37 && index > 0) {
          location.href = '#/' + $routeParams.slide + '/' + (index - 1);
          document.removeEventListener('keydown', handler);
        } else if (event.keyCode === 37) {
          location.href = '#/' + $routeParams.slide;
          document.removeEventListener('keydown', handler);
        } else if (event.keyCode === 39 && index < data.pages.length - 1) {
          location.href = '#/' + $routeParams.slide + '/' + (index + 1);
          document.removeEventListener('keydown', handler);
        }
      });
    }]);
})();
