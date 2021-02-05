package kurmakaeva.anastasia.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kurmakaeva.anastasia.asteroidradar.PictureOfDay

@Entity(tableName = "picture_of_the_day")
data class DatabasePictureOfTheDay constructor(
    @PrimaryKey
    val url: String,
    val title: String,
    val mediaType: String
)

fun DatabasePictureOfTheDay.asDomainModel(): PictureOfDay {
    return PictureOfDay(mediaType, title, url)
}