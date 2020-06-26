package com.ynov.memokotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ynov.memokotlin.R
import com.ynov.memokotlin.storage.Memo
import com.ynov.memokotlin.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add.*
import javax.inject.Inject


class AddFragment : DaggerFragment() {

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory

    lateinit var memoViewModel: MemoViewModel


    // Method #1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    // Method #2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        btnAdd.setOnClickListener {
            Navigation.findNavController(requireActivity(),R.id.container).popBackStack()
        }
    }

    // Method #3
    private fun saveMemoToDatabase() {

        (activity as MainActivity?)?.showFloatingButton()

        if (validations()) {
            Toast.makeText(activity, "Mémo enregistré", Toast.LENGTH_SHORT).show()
            saveMemo()
        } else
            Toast.makeText(activity, "Mémo annulé", Toast.LENGTH_SHORT).show()

    }

    // Method #4
    override fun onDestroyView() {
        super.onDestroyView()
        saveMemoToDatabase()
    }


    // Method #5
    private fun saveMemo() {
        val memo = Memo(0,addTitle.text.toString(),addContent.text.toString())

        //If title is null set Empty Title
        if (addTitle.text.isNullOrEmpty()) {
           memo.title = "Title"

            //Call viewmodel to save the data
            memoViewModel.insert(memo)

        }else{
            //Call viewmodel to save the data
            memoViewModel.insert(memo)
        }
    }

    // Method #6
    fun validations(): Boolean {
        return !(addTitle.text.isNullOrEmpty()
                && addContent.text.isNullOrEmpty())
    }


    // Method #7
    private fun setupViewModel() {
        memoViewModel = ViewModelProvider(this,viewmodelProviderFactory).get(MemoViewModel::class.java)
    }
}