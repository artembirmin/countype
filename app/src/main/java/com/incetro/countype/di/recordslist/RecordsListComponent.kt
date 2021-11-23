package com.incetro.countype.di.recordslist

import com.incetro.countype.di.app.activity.ActivityComponent
import com.incetro.countype.di.common.scope.PerFeature
import com.incetro.countype.recordslist.view.RecordsListFragment
import dagger.Component

@PerFeature
@Component(dependencies = [ActivityComponent::class], modules = [RecordsListModule::class])
interface RecordsListComponent {

    fun inject(recordsListFragment: RecordsListFragment)

    @Component.Builder
    interface Builder {
        fun activityComponent(component: ActivityComponent): Builder
        fun build(): RecordsListComponent
    }
}