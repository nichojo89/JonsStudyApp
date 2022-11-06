package com.nicholssoftware.jonsstudyapp.framework.di

import com.nicholssoftware.jonsstudyapp.framework.CalculatorViewModel
import com.nicholssoftware.jonsstudyapp.framework.NoteListViewModel
import com.nicholssoftware.jonsstudyapp.framework.NoteViewModel
import dagger.Component

//@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
//interface ViewModelComponent {
////    fun inject(noteViewModel: NoteViewModel)
//    fun inject(listViewModel: NoteListViewModel)
//}
@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(listViewModel: NoteListViewModel)
    fun inject(noteViewModel: NoteViewModel)
    fun inject(calculatorViewModel: CalculatorViewModel)
}