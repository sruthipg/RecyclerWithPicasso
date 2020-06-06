package com.example.accubitsapp.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


import com.example.accubitsapp.R
import com.example.accubitsapp.db.model.movie.ResultsMovie
import com.example.accubitsapp.utils.Coroutines
import com.example.accubitsapp.utils.hide
import com.example.accubitsapp.utils.show
import com.example.accubitsapp.viewmodel.HomeViewModel
import com.example.accubitsapp.viewmodel.HomeViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_home.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory: HomeViewModelFactory by instance()

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        bindUI()
    }



    private fun bindUI() = Coroutines.main {
        progress_bar.show()

        viewModel.userlist?.await().observe(this, Observer {
                emp->
            progress_bar.hide()
        if (emp!=null && emp.size!=0){
            initRecyclerView(emp.toEmpItem())
        }

        })
    }


    private fun initRecyclerView(empItem: List<MovieItemItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(empItem)
        }



        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }


    private fun List<ResultsMovie>.toEmpItem() : List<MovieItemItem>{
        return this.map {
            MovieItemItem(it)
        }
    }
}