package kurmakaeva.anastasia.asteroidradar

import kurmakaeva.anastasia.apiKey

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY = apiKey // Stored in a different .kt file as a String for security purposes
}