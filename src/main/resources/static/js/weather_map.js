$(() => {

    //Map
    let mapOptions = {
        accessToken: mapboxToken,
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11', // stylesheet location
        center: [-98.48527, 29.423017], // starting position [lng, lat]
        zoom: 9
    }
    let map = new mapboxgl.Map(mapOptions);

    //Geocoder
    let geocoderOptions = {
        accessToken: mapboxToken,
        mapboxgl: mapboxgl,
        marker: false
    }
    let geocoder = new MapboxGeocoder(geocoderOptions)
    map.addControl(geocoder)
    geocoder.on('result', function (result) {
        lon = result.result.center[0]
        lat = result.result.center[1]
        $('#update-marker').click(function(){
            marker.setLngLat([lon, lat])
        })
        updateCity(lon, lat)
        updateWeather(lat, lon)
    })

    //Marker
    let marker = new mapboxgl.Marker({
        draggable: true
    })
        .setLngLat([-98.48527, 29.423017])
        .addTo(map)
        .on('dragend', onDragEnd);

    //Variables for Functions
    let lon;
    let lat;
    let min;
    let max;
    let description;
    let wind;
    let windDir;
    let pressure;
    let icon;
    // const toolHTML = "<div class='col-12 col-sm-6 col-md-4 col-xl-2 outer'><div class='card my-cards tool-bar'><div class='card-header text-center top'>Toolbar</div><div class='card-body days tool'><button id='update-marker' class='btn btn-outline-dark btn-block'>Show Marker</button></h3></div></div></div>"

    //Functions
    function onDragEnd() {
        const lngLat = marker.getLngLat();
        lon = lngLat.lng
        lat = lngLat.lat;
        updateWeather(lat, lon)
        updateCity(lon, lat)
    }
    function updateWeather(lat,lon){
        $.get("https://api.openweathermap.org/data/2.5/onecall", {
            APPID: OPEN_WEATHER_APPID,
            lat: lat,
            lon: lon,
            units: "imperial",
            exclude: "current,minutely,hourly,alerts"
        }).done(function (data) {
            renderCards(data)
        });
    }
    function getDate(i){
        const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
        let today = new Date();
        let dd = String(today.getDate()).padStart(2, '0');
        let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        let yyyy = today.getFullYear();
        let day;
        if(typeof days[(today.getDay()) + i] === 'undefined'){
            day = days[(today.getDay()) + i - days.length]
        } else {
            day = days[(today.getDay()) + i]
        }
        today = day + ' ' + mm + '/' + (Number(dd) + Number(i)) + '/' + yyyy;
        return today
    }
    function forecast() {
        $.get("https://api.openweathermap.org/data/2.5/onecall", {
            APPID: OPEN_WEATHER_APPID,
            lat: 29.423017,
            lon: -98.48527,
            units: "imperial",
            exclude: "current,minutely,hourly,alerts"
        }).done(function (data) {
            updateCity(-98.48527, 29.423017)
            renderCards(data)
        });
    }
    function renderCards(data){
        let cardHTML = "";
        // cardHTML += toolHTML
        for(var i = 0; i < 5; i++){
            today = getDate(i)
            max = Math.trunc(data.daily[i].temp.max);
            min = Math.trunc(data.daily[i].temp.min);
            description = data.daily[i].weather[0].description
            humidity = data.daily[i].humidity
            wind = Math.trunc(data.daily[i].wind_speed);
            pressure = data.daily[i].pressure
            icon = "https://openweathermap.org/img/w/" + data.daily[i].weather[0].icon + ".png"
            windDirections(data, i)
            cardHTML += "<div class='col-12 col-sm-6 col-md-4 col-xl-2 outer'><div class='card my-cards'>"
            cardHTML += "<div class='card-header text-center top'>"
            cardHTML += today  + "</div>"
            cardHTML += "<div class='card-body'>"
            cardHTML += "<p class='text-center'>" + min + "°F / " + max + "°F" + "</p>"
            cardHTML += "<img src='" + icon + "' class='mx-auto d-block' alt='weather'>"
            cardHTML += "<hr>"
            cardHTML += "<p>" + "Description: <strong>" + description + "</strong></p>"
            cardHTML += "<p>" + "Humidity: <strong>" + humidity + "</strong></p>"
            cardHTML += "<p>" + "Wind: <strong>" + wind + " " + windDir + "</strong></p>"
            cardHTML += "<p>" + "Pressure: <strong>" + pressure + "</strong></p>"
            cardHTML += "</div></div></div>"
        }

        $('#card-row').html(cardHTML)
        $('#update-marker').click(function(){
            let center = map.getCenter()
            marker.setLngLat(center)
            lon = center.lng
            lat = center.lat
            updateWeather(lat, lon)
            updateCity(lon, lat)
        })
    }
    function updateCity(lon, lat) {
        $.get("https://api.openweathermap.org/data/2.5/weather", {
            APPID: OPEN_WEATHER_APPID,
            lat: lat,
            lon: lon,
            units: "imperial"
        }).done(function (data) {
            // console.log(data);
            // $('#current').html(data.main.temp + " °F")
            if(data.name === ""){
                $('#location').html("")
            } else {
                $('#location').html("Current: " + data.name + "   ~   " + Math.trunc(data.main.temp) + "°F")
            }
        });
    }

    //CONVERTS WIND DEGREES INTO A DIRECTION
    function windDirection(data) {
            if (data.wind.deg > 348.75 || data.wind.deg < 11.25) {
                windDir = "N";
            } else if (data.wind.deg >= 11.25 && data.wind.deg < 33.75) {
                windDir = "NNE";
            } else if (data.wind.deg >= 33.75 && data.wind.deg <= 56.25) {
                windDir = "NE";
            } else if (data.wind.deg > 56.25 && data.wind.deg < 78.75) {
                windDir = "ENE";
            } else if (data.wind.deg >= 78.75 && data.wind.deg <= 101.25) {
                windDir = "E";
            } else if (data.wind.deg > 101.25 && data.wind.deg < 123.75) {
                windDir = "ESE";
            } else if (data.wind.deg >= 123.75 && data.wind.deg <= 146.25) {
                windDir = "SE";
            } else if (data.wind.deg > 146.25 && data.wind.deg < 168.75) {
                windDir = "SSE";
            } else if (data.wind.deg >= 168.75 && data.wind.deg <= 191.25) {
                windDir = "S";
            } else if (data.wind.deg > 191.25 && data.wind.deg < 213.75) {
                windDir = "SSW";
            } else if (data.wind.deg >= 213.75 && data.wind.deg <= 236.25) {
                windDir = "SW";
            } else if (data.wind.deg > 236.25 && data.wind.deg < 258.75) {
                windDir = "WSW";
            } else if (data.wind.deg >= 258.75 && data.wind.deg <= 281.25) {
                windDir = "W";
            } else if (data.wind.deg > 281.25 && data.wind.deg < 303.75) {
                windDir = "WNW";
            } else if (data.wind.deg >= 303.75 && data.wind.deg <= 326.25) {
                windDir = "NW";
            } else if (data.wind.deg > 326.25 && data.wind.deg < 348.75) {
                windDir = "NNW";
            }
    }
    function windDirections(data, i) {
            if (data.daily[i].wind_deg > 348.75 || data.daily[i].wind_deg < 11.25) {
                windDir = "N";
            } else if (data.daily[i].wind_deg >= 11.25 && data.daily[i].wind_deg < 33.75) {
                windDir = "NNE";
            } else if (data.daily[i].wind_deg >= 33.75 && data.daily[i].wind_deg <= 56.25) {
                windDir = "NE";
            } else if (data.daily[i].wind_deg > 56.25 && data.daily[i].wind_deg < 78.75) {
                windDir = "ENE";
            } else if (data.daily[i].wind_deg >= 78.75 && data.daily[i].wind_deg <= 101.25) {
                windDir = "E";
            } else if (data.daily[i].wind_deg > 101.25 && data.daily[i].wind_deg < 123.75) {
                windDir = "ESE";
            } else if (data.daily[i].wind_deg >= 123.75 && data.daily[i].wind_deg <= 146.25) {
                windDir = "SE";
            } else if (data.daily[i].wind_deg > 146.25 && data.daily[i].wind_deg < 168.75) {
                windDir = "SSE";
            } else if (data.daily[i].wind_deg >= 168.75 && data.daily[i].wind_deg <= 191.25) {
                windDir = "S";
            } else if (data.daily[i].wind_deg > 191.25 && data.daily[i].wind_deg < 213.75) {
                windDir = "SSW";
            } else if (data.daily[i].wind_deg >= 213.75 && data.daily[i].wind_deg <= 236.25) {
                windDir = "SW";
            } else if (data.daily[i].wind_deg > 236.25 && data.daily[i].wind_deg < 258.75) {
                windDir = "WSW";
            } else if (data.daily[i].wind_deg >= 258.75 && data.daily[i].wind_deg <= 281.25) {
                windDir = "W";
            } else if (data.daily[i].wind_deg > 281.25 && data.daily[i].wind_deg < 303.75) {
                windDir = "WNW";
            } else if (data.daily[i].wind_deg >= 303.75 && data.daily[i].wind_deg <= 326.25) {
                windDir = "NW";
            } else if (data.daily[i].wind_deg > 326.25 && data.daily[i].wind_deg < 348.75) {
                windDir = "NNW";
            }
    }

    //Run on load
    forecast();
});