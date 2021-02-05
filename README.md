# Asteroid Radar

## About the project

This project was one of my assignments part of the Android Kotlin Developer nanodegree by Udacity.
It's been created using:

* Kotlin
* MVVM
* Jetpack compose
* Room persistence library
* LiveData
* Navigation component
* Data binding
* Kotlin Coroutines
* Work Manager
* NASA REST API

And the following third-party libraries:

* [Retrofit 2](https://github.com/square/retrofit)
* [Moshi](https://github.com/square/moshi)
* [Picasso](https://square.github.io/picasso/)
* [Stetho](https://github.com/facebookarchive/stetho)

## Project description

Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids in a period of time, their data (size, velocity, distance to Earth) and if they are potentially hazardous.

The app is consists of two screens: A Main screen with a list of all the detected asteroids and a Details screen that displays the data of that asteroid once it´s selected in the Main screen list. The main screen shows the NASA image of the day, making this app more striking.

The application can be used both online and offline, as it's been set up to cache weekly information on asteroids once a day. 


```


## Built With

To build this project you are going to use the NASA NeoWS (Near Earth Object Web Service) API, which you can find here.
https://api.nasa.gov/

You will need an API Key which is provided for you in that same link, just fill the fields in the form and click Signup.

## License

[GNU General Public License v3](https://www.gnu.org/licenses/gpl-3.0.en.html)
