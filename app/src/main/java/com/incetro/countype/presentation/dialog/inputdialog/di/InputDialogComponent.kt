/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 8/4/2022.
 */

package com.incetro.countype.presentation.dialog.inputdialog.di

import com.incetro.countype.app.componentmanager.ComponentManager
import com.incetro.countype.app.componentmanager.ComponentsManager
import com.incetro.countype.common.di.activity.ActivityComponent
import com.incetro.countype.common.di.scope.FeatureScope
import com.incetro.countype.presentation.dialog.inputdialog.view.InputDialogFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [ActivityComponent::class], modules = [InputDialogModule::class])
interface InputDialogComponent {
    fun inject(inputDialog: InputDialogFragment)

    @Component.Builder
    interface Builder {
        fun activityComponent(component: ActivityComponent): Builder
        fun build(): InputDialogComponent
    }

    object Manager : ComponentManager<InputDialogComponent>(
        clazz = InputDialogComponent::class.java,
        createAndSave = {

            val componentManager = ComponentsManager.getInstance()
            val activityComponent = ActivityComponent.Manager.getComponent()

            val dialogComponent = DaggerInputDialogComponent.builder()
                .activityComponent(activityComponent)
                .build()

            componentManager.addComponent(dialogComponent)
        })
}
