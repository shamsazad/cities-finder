# Place Finder

This API suggest the name of cities with latitude, longitude and a score  that represent the confidence of suggestion.
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

```
* Clone the repository from git
* Import the project in any IDE that support Java and Springboot
* With maven clean and compile the project
* Run it and see the magic
** To run locally though CLI : mvn spring-boot: run

```
### Prerequisites

What things you need know to run the code and how to install them

* [Maven](https://maven.apache.org/)
* [GeoNames](https://www.geonames.org/)


## Deployment

Currently the application is deployed on [heroku](https://dashboard.heroku.com/apps), at this [link](https://city-suggestor.herokuapp.com/). The application is currently failing because of a [third party jar](https://www.geonames.org/source-code/) that this application uses to find the cities.

### Consuming API
Once the application is running locally, open postman or a browser and call GET end-point of this application.
```
http://localhost:8080/suggestions?q=boston&longitude=-63.2505&latitude=-17.33866
```
**q** is the name of the place the client want to search.<br>
**longitude** and **latitude** are optional parameters that a user can provide to get better suggestions.


