/*
 * AndroidArchitectureResearch
 *
 * Created by artembirmin on 8/4/2022.
 */

package com.incetro.countype.presentation.dialog.inputdialog.di

import com.incetro.countype.common.di.componentmanager.ComponentManager
import com.incetro.countype.common.di.componentmanager.ComponentsManager
import com.incetro.countype.common.di.scope.FeatureScope
import com.incetro.countype.presentation.dialog.inputdialog.view.InputDialogFragment
import dagger.Component

@FeatureScope
@Component
interface InputDialogComponent {
    fun inject(inputDialog: InputDialogFragment)

    @Component.Builder
    interface Builder {
        fun build(): InputDialogComponent
    }

    object Manager : ComponentManager<InputDialogComponent>(
        clazz = InputDialogComponent::class.java,
        createAndSave = {

            val componentManager = ComponentsManager.getInstance()

            val dialogComponent = DaggerInputDialogComponent.builder()
                .build()

            componentManager.addComponent(dialogComponent)
        })
}
