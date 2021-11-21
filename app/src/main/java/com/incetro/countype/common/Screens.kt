package com.incetro.countype.common

import com.incetro.countype.recordslist.view.RecordsListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object RecyclerViewScreen : SupportAppScreen() {
        override fun getFragment() = RecordsListFragment.newInstance()
    }
}