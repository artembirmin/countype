/*
 * My Application
 *
 * Created by artembirmin on 10/5/2022.
 */

package com.incetro.countype.common.di.notelist

import com.incetro.countype.app.componentmanager.ComponentManager
import com.incetro.countype.app.componentmanager.ComponentsManager
import com.incetro.countype.common.di.activity.ActivityComponent
import com.incetro.countype.common.di.scope.FeatureScope
import com.incetro.countype.presentation.notelist.view.NoteListFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [
        NoteListModule::class
    ]
)
interface NoteListComponent {

    fun inject(noteListFragment: NoteListFragment)

    @Component.Builder
    interface Builder {
        fun activityComponent(component: ActivityComponent): Builder
        fun build(): NoteListComponent
    }

    object Manager : ComponentManager<NoteListComponent>(
        clazz = NoteListComponent::class.java,
        createAndSave = {

            val componentManager = ComponentsManager.getInstance()
            val activityComponent = ActivityComponent.Manager.getComponent()

            val taskFolderListComponent = DaggerNoteListComponent.builder()
                .activityComponent(activityComponent)
                .build()

            componentManager.addComponent(taskFolderListComponent)
        })
}