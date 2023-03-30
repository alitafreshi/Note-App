package com.alitafreshi.noteapp.presentation.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.alitafreshi.noteapp.R
import com.alitafreshi.noteapp.databinding.FragmentContainerLayoutBinding
import com.alitafreshi.noteapp.presentation.ui.theme.NoteAppTheme
import com.alitafreshi.state_manager.AppEvents
import com.alitafreshi.state_manager.AppStateManager
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var applicationStateManager: AppStateManager

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val scaffoldState = rememberScaffoldState()

            NoteAppTheme {
                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        val fragmentContainerView = createRef()
                        AndroidViewBinding(
                            factory = FragmentContainerLayoutBinding::inflate,
                            modifier = Modifier.constrainAs(ref = fragmentContainerView) {
                                bottom.linkTo(parent.bottom)
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints

                            }, update = {
                                detectStartDestination()
                            })
                    }
                    LaunchedEffect(key1 = true) {
                        applicationStateManager.appEvents.onEach { appEvents ->
                            when (appEvents) {
                                is AppEvents.Navigation.Navigate -> findNavController(viewId = R.id.nav_host_fragment).navigate(
                                    appEvents.deepLink
                                )
                                AppEvents.Navigation.NavigateBack -> findNavController(viewId = R.id.nav_host_fragment).popBackStack()

                                is AppEvents.Navigation.NavigateWithNavOptions -> TODO()

                                is AppEvents.ShowSnackBar -> scaffoldState.snackbarHostState.showSnackbar(
                                    message = appEvents.message
                                )

                                is AppEvents.UpdateErrorState -> TODO()

                                is AppEvents.UpdateLoadingState -> TODO()
                            }

                        }.launchIn(scope = this)
                    }
                }
            }
        }
    }

    private fun detectStartDestination() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        if (appViewModel.getCurrentViewStateOrNew().introState) graph.setStartDestination(com.alitafreshi.task_list.R.id.task_list_nav_graph) else graph.setStartDestination(
            R.id.introFragment
        )
        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}
