/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 26/2/2022.
 */

package com.incetro.countype.di.app.module

import com.incetro.countype.data.repository.NoteRepository
import com.incetro.countype.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository
}
