package pw.zotan.psylog.di

import pw.zotan.psylog.data.substances.parse.SubstanceParser
import pw.zotan.psylog.data.substances.parse.SubstanceParserInterface
import pw.zotan.psylog.data.substances.repositories.SearchRepository
import pw.zotan.psylog.data.substances.repositories.SearchRepositoryInterface
import pw.zotan.psylog.data.substances.repositories.SubstanceRepository
import pw.zotan.psylog.data.substances.repositories.SubstanceRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSubstanceParser(
        substanceParser: SubstanceParser
    ): SubstanceParserInterface

    @Binds
    @Singleton
    abstract fun bindSubstanceRepository(
        substanceRepository: SubstanceRepository
    ): SubstanceRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        substanceRepository: SearchRepository
    ): SearchRepositoryInterface
}