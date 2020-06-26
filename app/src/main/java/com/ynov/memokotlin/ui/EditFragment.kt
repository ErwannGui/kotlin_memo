package com.ynov.memokotlin.ui

import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_edit.*
import javax.inject.Inject


class EditFragment : DaggerFragment() {

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory

    lateinit var memoViewModel: MemoViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    // Method #1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareMemoForEditing()
        setupViewModel()

        btnEdit.setOnClickListener {
            Navigation.findNavController(requireActivity(),R.id.container).popBackStack()
        }
    }

    // Method #2
    private fun saveMemoToDatabase() {

        (activity as MainActivity?)?.showFloatingButton()

        if (validations()) {
            Toast.makeText(activity, "Mémo modifié", Toast.LENGTH_SHORT).show()
            saveMemo()
            val id:Int = EditFragmentArgs.fromBundle(arguments!!).memo?.id!!
            Log.e("DEBUG","saving memo $id")

        } else {
            Toast.makeText(activity, "Mémo annulé", Toast.LENGTH_SHORT).show()
            //Delete the memo if all fields are empty (this is done by user)
            val id: Int = EditFragmentArgs.fromBundle(arguments!!).memo?.id!!
            memoViewModel.deleteById(id)
            Log.e("DEBUG", "deleting memo")
        }
    }

    // Method #3
    override fun onDestroyView() {
        super.onDestroyView()
        saveMemoToDatabase()
    }

    // Method #4
    private fun saveMemo() {

        //getting the id from bundle , we are using that id to update/edit the memo
        val id:Int? = EditFragmentArgs.fromBundle(arguments!!).memo?.id

        val memo = Memo(id!!,editTitle.text.toString(),editContent.text.toString())

        //If title is null set Empty Title
        if (editTitle.text.isNullOrEmpty()) {
            memo.title = "Title"

            //Call viewmodel to save the data
            memoViewModel.update(memo)

        }else{
            //Call viewmodel to save the data
            Log.e("DEBUG","saving memo update is called")
            memoViewModel.update(memo)
        }
    }

    // Method #5
    fun validations(): Boolean {
        return !(editTitle.text.isNullOrEmpty()
                && editContent.text.isNullOrEmpty())
    }


    // Method #6
    private fun setupViewModel() {
        memoViewModel = ViewModelProvider(this,viewmodelProviderFactory).get(MemoViewModel::class.java)
    }


    // Method #7
    private fun prepareMemoForEditing() {
    // Getting the memo from the bundle
        //Save args plugin is used as i believe bundle is not good for sending large data
        arguments?.let {
            val safeArgs = EditFragmentArgs.fromBundle(it)
            val memo = safeArgs.memo
            editTitle.setText(memo?.title.toString())
            editContent.setText(memo?.content.toString())
        }

    }
}

