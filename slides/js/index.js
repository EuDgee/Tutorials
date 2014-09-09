
(function() {
  var app = angular.module('slides', []);
  app.controller('Pages', function($scope, $http) {
    $http.get('/slides/' + location.href.split('?')[1] + '.json').then(
        function(response) {
          $scope.pages = response.data;
        });
  });
})();
