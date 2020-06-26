package com.ynov.memokotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynov.memokotlin.R
import com.ynov.memokotlin.storage.Memo
import com.ynov.memokotlin.ui.adapter.MemoAdapter
import com.ynov.memokotlin.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : DaggerFragment(),
    MemoAdapter.Interaction {

    private lateinit var memoAdapter: MemoAdapter

    private lateinit var memoViewModel: MemoViewModel

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory

    lateinit var allMemos: List<Memo>


    // Method #1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allMemos = mutableListOf()
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    // Method #2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()    // Step 1
        initRecyclerView()  // Step 2
        observerLiveData()  // Step 3
    }

    // Method #3
    private fun observerLiveData() {
        memoViewModel.getAllMemos().observe(viewLifecycleOwner, Observer { lisOfMemos ->
            lisOfMemos?.let {
                allMemos = it
                memoAdapter.swap(it)
            }
        })
    }

    // Method #4
    private fun initRecyclerView() {
        recyclerView.apply {
            memoAdapter = MemoAdapter(
                allMemos,
                this@ListFragment
            )
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = memoAdapter
            val swipe = ItemTouchHelper(initSwipeToDelete())
            swipe.attachToRecyclerView(recyclerView)
        }
    }

    // Method #5
    private fun setupViewModel() {
        memoViewModel =
            ViewModelProvider(this, viewmodelProviderFactory).get(MemoViewModel::class.java)
    }

    // Method #6
    private fun initSwipeToDelete(): ItemTouchHelper.SimpleCallback {
        //Swipe recycler view items on RIGHT
        return object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                memoViewModel.delete(allMemos.get(position))
                val memo = allMemos.get(position)
                // Show database naming of the mémo to make sure that it is completely erased from storage
                Toast.makeText(activity, "Mémo $memo supprimé", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Method #7
    override fun onItemSelected(position: Int, item: Memo) {
        val navDirection = ListFragmentDirections.actionListFragmentToEditFragment(item)
        findNavController().navigate(navDirection)
    }
}


