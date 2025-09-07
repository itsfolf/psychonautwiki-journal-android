package pw.zotan.psylog.data.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pw.zotan.psylog.data.room.experiences.ExperienceDao
import pw.zotan.psylog.data.room.experiences.entities.CustomSubstance
import pw.zotan.psylog.data.room.experiences.entities.CustomUnit
import pw.zotan.psylog.data.room.experiences.entities.Experience
import pw.zotan.psylog.data.room.experiences.entities.Ingestion
import pw.zotan.psylog.data.room.experiences.entities.InstantConverter
import pw.zotan.psylog.data.room.experiences.entities.ShulginRating
import pw.zotan.psylog.data.room.experiences.entities.SubstanceCompanion
import pw.zotan.psylog.data.room.experiences.entities.TimedNote

@TypeConverters(InstantConverter::class)
@Database(
    version = 7,
    entities = [Experience::class, Ingestion::class, SubstanceCompanion::class, CustomSubstance::class, ShulginRating::class, TimedNote::class, CustomUnit::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2),
        AutoMigration (from = 2, to = 3),
        AutoMigration (from = 3, to = 4),
        AutoMigration (from = 4, to = 5),
        AutoMigration (from = 5, to = 6),
        AutoMigration (from = 6, to = 7),
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun experienceDao(): ExperienceDao
}