package com.example.filmlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmlist.adapters.FilmRecyclerViewAdapter
import com.example.filmlist.data.remote.MainRepositoryImpl
import com.example.filmlist.data.remote.RetrofitConfig
import com.example.filmlist.databinding.ActivityMainBinding
import com.example.filmlist.viewModels.MainViewModel
import com.example.filmlist.viewModels.MyViewModelsFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestApi = RetrofitConfig().createApi()
        val mainRepository = MainRepositoryImpl(requestApi)
        viewModel =
            ViewModelProvider(this, MyViewModelsFactory(mainRepository))[MainViewModel::class.java]

        val adapter = FilmRecyclerViewAdapter()
        binding.filmRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.filmRecyclerView.adapter = adapter


        lifecycleScope.launch {
            viewModel.filmsFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
        lifecycleScope.launchWhenCreated {
            adapter.onPagesUpdatedFlow.collectLatest {
                viewModel.confirmLoading()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect{
                if (it){
                    binding.mainScreen.visibility = View.VISIBLE
                }
            }
        }


    }
}