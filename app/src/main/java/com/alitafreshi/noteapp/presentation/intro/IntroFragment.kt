package com.alitafreshi.noteapp.presentation.intro

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.alitafreshi.components.util.app.AppEvents
import com.alitafreshi.noteapp.presentation.app.AppViewModel

class IntroFragment : Fragment() {

    private val appViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(context = requireContext()).apply {
        setContent {
            IntroScreen(navigateToMainScreen = {
                //TODO NAVIGATE TO THE TASK LIST SCREEN AND POP THIS SCREEN FROM THE BACK STACK
                appViewModel.onTriggerEvent(event = AppEvents.UpdateIntroState(introState = true))
                val uri = Uri.parse("https://tafreshiali.ir/taskList")
                findNavController().navigate(uri)
            })
        }
    }
}