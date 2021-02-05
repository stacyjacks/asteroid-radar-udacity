package kurmakaeva.anastasia.asteroidradar.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kurmakaeva.anastasia.asteroidradar.Asteroid
import kurmakaeva.anastasia.asteroidradar.Constants.DEFAULT_END_DATE_DAYS
import kurmakaeva.anastasia.asteroidradar.PictureOfDay
import kurmakaeva.anastasia.asteroidradar.api.Network
import kurmakaeva.anastasia.asteroidradar.api.asDatabaseModel
import kurmakaeva.anastasia.asteroidradar.api.parseAsteroidsJsonResult
import kurmakaeva.anastasia.database.AsteroidsDatabase
import kurmakaeva.anastasia.database.DatabaseAsteroid
import kurmakaeva.anastasia.database.asDomainModel
import java.time.LocalDate

class AsteroidsRepository(private val database: AsteroidsDatabase) {
    @RequiresApi(Build.VERSION_CODES.O)
    val todaysDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val endDate = todaysDate.plusDays(DEFAULT_END_DATE_DAYS.toLong()).toString()

    val pictureOfTheDay: LiveData<PictureOfDay> = Transformations
            .map(database.asteroidDao.getPictureOfTheDay()) {
        it?.asDomainModel()
    }

    val asteroidsOfTheDay: LiveData<List<Asteroid>> = Transformations
            .map(database.asteroidDao.getTodaysAsteroids(todaysDate.toString())) {
        it.asDomainModel()
    }

    val asteroidsOfTheWeek: LiveData<List<Asteroid>> = Transformations
            .map(database.asteroidDao.getWeekOfAsteroids(todaysDate.toString(), endDate)) {
        it.asDomainModel()
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val jsonResult = Network.nasaApiService.loadAsteroids(todaysDate.toString())
                val asteroidList = parseAsteroidsJsonResult(jsonResult)
                val dbAsteroidList = mutableListOf<DatabaseAsteroid>()

                for (asteroid in asteroidList) {
                    val dbAsteroid = DatabaseAsteroid(
                            asteroid.id,
                            asteroid.codename,
                            asteroid.closeApproachDate,
                            asteroid.absoluteMagnitude,
                            asteroid.estimatedDiameter,
                            asteroid.relativeVelocity,
                            asteroid.distanceFromEarth,
                            asteroid.isPotentiallyHazardous
                    )
                    dbAsteroidList.add(dbAsteroid)
                }

                database.asteroidDao.insertAll(dbAsteroidList)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun refreshPictureOfTheDay() {
        withContext(Dispatchers.IO) {
            try {
                val pictureOfDay = Network.nasaApiService.loadImageOfTheDay()
                database.asteroidDao.insertAsteroidPicture(pictureOfDay.asDatabaseModel())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}