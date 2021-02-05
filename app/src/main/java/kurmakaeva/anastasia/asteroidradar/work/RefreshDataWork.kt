package kurmakaeva.anastasia.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kurmakaeva.anastasia.asteroidradar.repository.AsteroidsRepository
import kurmakaeva.anastasia.database.getDatabase
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidsRepository(database)


        return try {
            repository.refreshAsteroids()
            Result.success()

        } catch (exception: HttpException) {
            Result.retry()
        }
    }

}