package com.example.accubitsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.accubitsapp.db.model.movie.ResultsMovie


@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(quotes : List<ResultsMovie>)


    @Query("SELECT * FROM ResultsMovie")
    fun getMovies() : LiveData<List<ResultsMovie>>

//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveAddress(quotes : AddressData)
//
//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveAllAddress(quotes : List<AddressData>)
//
//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveCompany(quotes : List<CompanyData>)
//
//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveAllCompany(quotes : List<CompanyData>)
//
//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveGeo(quotes : List<GeoData>)
//
//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveAllGeo(quotes : List<GeoData>)


}