<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ejemplo GeoLocalizacion</title>

    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 400px;
            width: 100%;
        }

    </style>
</head>
<body>
<h1>Ejemplo de GeoLocalizacion</h1>
<div id="map"></div>

<script src="js/jquery-3.2.1.min.js"></script>

<script>
    // Initialize and add the map
    function initMap(latitud,logitud) {
        // The location of Uluru


        var uluru = {lat: latitud, lng: logitud};
            // The map, centered at Uluru
            var map = new google.maps.Map(
                    document.getElementById('map'), {zoom: 15, center: uluru});
            // The marker, positioned at Uluru
            var marker = new google.maps.Marker({position: uluru, map: map});
}



</script>
<!--Load the API from the specified URL
* The async attribute allows the browser to render the page while the API loads
* The key parameter will contain your own API key (which is not needed for this tutorial)
* The callback parameter executes the initMap() function
-->
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAK2eGIW33oOI7VX4xvEZnHelanx03Sdec&callback=initMap"></script>
<script>
    var id, cantidad = 0;
    //Indica las opciones para llamar al GPS.
    var opcionesGPS = {
        enableHighAccuracy: false,
        timeout: 5000,
        maximumAge: 0
    }

    $(document).ready(function(){
        console.log("Ejemplo de Geolocalizacion");

        //Obteniendo la referencia directa.
        navigator.geolocation.getCurrentPosition(function(geodata){
            var coordenadas = geodata.coords;

            $("#posicionGps").text("Latitud: "+coordenadas.latitude+", Longitud: "+coordenadas.longitude+", Precisión: "+coordenadas.accuracy+" metros");
            initMap(coordenadas.latitude,coordenadas.longitude);
        }, function(){
            $("#posicionGps").text("No permite el acceso del API GEO");
        }, opcionesGPS);

    });
</script>

</body>
</html>