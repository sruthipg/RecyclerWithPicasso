package com.example.accubitsapp

import android.app.Application
import com.example.accubitsapp.db.UserDataBase
import com.example.accubitsapp.network.MyApi
import com.example.accubitsapp.network.NetworkConnectionInterceptor
import com.example.accubitsapp.repository.HomeRepository
import com.example.accubitsapp.viewmodel.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { UserDataBase(instance()) }
        bind() from singleton { HomeRepository(instance(), instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}