package com.ynov.memokotlin.di

import androidx.lifecycle.ViewModel
import com.ynov.memokotlin.ui.MemoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    // Method #1
    @Binds
    @IntoMap
    @ViewModelKey(MemoViewModel::class)
    abstract fun bindMainViewModel(memosViewModel: MemoViewModel): ViewModel
}
