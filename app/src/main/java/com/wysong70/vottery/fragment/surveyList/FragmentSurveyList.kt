package com.wysong70.vottery.fragment.surveyList

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wysong70.vottery.databinding.FragmentSurveyListBinding


class FragmentSurveyList : Fragment() {
    private lateinit var viewModel: SurveyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
        return FragmentSurveyListBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = requireActivity().obtainViewModel(SurveyListViewModel::class.java)
            vm = viewModel
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = SurveyRecyclerAdapter(arrayListOf())
                layoutManager = LinearLayoutManager(requireActivity())
            }
        }.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    fun <T : ViewModel> FragmentActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProvider(viewModelStore,
            ViewModelFactory.getInstance(
                application
            )
        ).get(viewModelClass)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}

class ViewModelFactory private constructor()
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(SurveyListViewModel::class.java) ->
                    SurveyListViewModel()
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                        )
                            .also { INSTANCE = it }
                }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}