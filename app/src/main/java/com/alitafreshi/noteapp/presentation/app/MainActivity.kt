package com.alitafreshi.noteapp.presentation.app

import android.os.Bundle
import android.util.Log
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
import com.alitafreshi.components.util.app.Navigation
import com.alitafreshi.noteapp.R
import com.alitafreshi.noteapp.databinding.FragmentContainerLayoutBinding
import com.alitafreshi.noteapp.presentation.ui.theme.NoteAppTheme
import com.alitafreshi.state_manager.AppUiEffects
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

                            })
                    }
                    LaunchedEffect(key1 = true) {

                        appViewModel.init()

                        applicationStateManager.appUiEffects.onEach { appUiEffects ->
                            when (appUiEffects) {

                                Navigation.DetectStartGraph -> detectStartDestination()

                                is Navigation.Navigate -> findNavController(viewId = R.id.nav_host_fragment).navigate(
                                    appUiEffects.deepLink
                                )
                                Navigation.NavigateBack -> findNavController(viewId = R.id.nav_host_fragment).popBackStack()

                                is Navigation.NavigateWithNavOptions -> TODO()

                                is AppUiEffects.ShowSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = appUiEffects.message
                                    )
                                    Log.d("MESSAGE", appUiEffects.message)
                                }

                                is AppUiEffects.UpdateErrorState -> scaffoldState.snackbarHostState.showSnackbar(
                                    message = "error happened"
                                )

                                is AppUiEffects.UpdateLoadingState -> scaffoldState.snackbarHostState.showSnackbar(
                                    message = if (appUiEffects.state) "in loading" else "IDLE"
                                )
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
