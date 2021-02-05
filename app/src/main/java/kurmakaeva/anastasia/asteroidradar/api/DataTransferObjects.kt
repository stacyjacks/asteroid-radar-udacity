package kurmakaeva.anastasia.asteroidradar.api

import com.squareup.moshi.JsonClass
import kurmakaeva.anastasia.asteroidradar.Asteroid
import kurmakaeva.anastasia.asteroidradar.PictureOfDay
import kurmakaeva.anastasia.database.DatabaseAsteroid
import kurmakaeva.anastasia.database.DatabasePictureOfTheDay

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val asteroids: List<NetworkAsteroid>)

@JsonClass(generateAdapter = true)
data class NetworkAsteroid(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

@JsonClass(generateAdapter = true)
data class NetworkPictureOfDay(
        val media_type: String,
        val title: String,
        val url: String
)

fun NetworkAsteroidContainer.asDomainModel(): List<Asteroid> {
    return asteroids.map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun NetworkAsteroidContainer.asDatabaseModel(): Array<DatabaseAsteroid> {
    return asteroids.map {
        DatabaseAsteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}

fun NetworkPictureOfDay.asDomainModel(): PictureOfDay {
    return PictureOfDay(media_type, title, url)
}

fun NetworkPictureOfDay.asDatabaseModel(): DatabasePictureOfTheDay {
    return DatabasePictureOfTheDay(url, title, media_type)
}