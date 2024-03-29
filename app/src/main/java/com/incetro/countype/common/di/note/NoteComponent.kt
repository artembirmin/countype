package com.incetro.countype.common.di.note

import com.incetro.countype.app.componentmanager.ComponentManager
import com.incetro.countype.app.componentmanager.ComponentsManager
import com.incetro.countype.common.di.activity.ActivityComponent
import com.incetro.countype.common.di.scope.FeatureScope
import com.incetro.countype.presentation.note.view.NoteFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [ActivityComponent::class], modules = [NoteModule::class])
interface NoteComponent {

    fun inject(noteFragment: NoteFragment)

    @Component.Builder
    interface Builder {
        fun activityComponent(component: ActivityComponent): Builder
        fun build(): NoteComponent
    }

    object Manager : ComponentManager<NoteComponent>(
        clazz = NoteComponent::class.java,
        createAndSave = {

            val componentManager = ComponentsManager.getInstance()
            val activityComponent = ActivityComponent.Manager.getComponent()

            val taskFolderListComponent = DaggerNoteComponent.builder()
                .activityComponent(activityComponent)
                .build()

            componentManager.addComponent(taskFolderListComponent)
        })
}