# final-project-team60_flight
final-project-team60_flight created by GitHub Classroom

The program allows the user to search flights to one of the top 10 popular destinations and check their weather conditions. If the user is not interested in any of the suggested cities, they also have the option to put their desired destination.

First, since this program uses two APIs (Skyscanner and AccuWeather), the user will need to register an account on RapidAPI (https://rapidapi.com/skyscanner/api/skyscanner-flight-search) and AccuWeatherAPI (https://developer.accuweather.com/?gclid=Cj0KCQiAz53vBRCpARIsAPPsz8WRKG6B6FT0thoN_QTieFyb9hSvsAIetcOHdK9dqp6rpbhCFswpNpYaAkQ8EALw_wcB) to get their own API keys.

After the user gets the API keys, they will then open FlightAPICaller.java and put the Skyscanner RapidAPI key in the four "x_rapidapi_key" variables that are located in four methods: callFlightAPI(), getCountryCodeMap(), getCityCode(), and getSessionKey(). Then, the user will need to open WeatherCollection.java and put their AccuWeather API key in two "apiKey" variables that are located in two methods: readCityURL() and readForecastURL().

After above the steps, the user can then open ProgramRunner.java and hit "run" in Eclipse. A Java program will then be populated.

In the Java program, there are three tabs. The first tab provides a list of popular cities. The user can choose one of the listed popular cities and enters their current city, current country, departure and returning dates. After hitting "Enter", the program will then populate the cheapest flight's price, the number of lay-overs, a link to book the flight, and the departure and arrival times of both outbound and inbound flights.

![1](https://user-images.githubusercontent.com/45375527/70186884-bdea0e00-16a1-11ea-9564-680c9e2a334f.PNG)

Troubleshoot:
If an error message is displayed, and the user believes the information they put in is correct, the user will need to hit the "Enter" button until a flight information is displayed. This error usually occurs when the user sends requests to the Skyscanner API too frequently.

The second tab of the Java program allows the user to check the weather conditions of any popular cities. After entering the name of a popular city and hitting "Enter", the program will populate 5 days of weather forecast of the city.

![2](https://user-images.githubusercontent.com/45375527/70187507-40270200-16a3-11ea-95ac-2c711c91158b.PNG)

The third tab allows the user to search flights to their desired destinations. The user will enter their current city and country, destination city and country, and departure and returning dates. After hitting the "Enter" button, the program will populate the price, the program will then populate the cheapest flight's price, the number of lay-overs, a link to book the flight, and the departure and arrival times of both outbound and inbound flights.

![3](https://user-images.githubusercontent.com/45375527/70187776-e70b9e00-16a3-11ea-86e7-d7b416d3dc72.PNG)

Troubleshoot:
If an error message is displayed, and the user believes the information they put in is correct, the user will need to hit the "Enter" button until a flight information is displayed. This error usually occurs when the user sends requests to the Skyscanner API too frequently.
