package com.incetro.phrasescalculator.common

import com.incetro.phrasescalculator.phraseslist.view.PhrasesCalculatorFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object RecyclerViewScreen : SupportAppScreen() {
        override fun getFragment() = PhrasesCalculatorFragment.newInstance()
    }
}