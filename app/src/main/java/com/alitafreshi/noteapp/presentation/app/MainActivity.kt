package com.alitafreshi.noteapp.presentation.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.alitafreshi.noteapp.R
import com.alitafreshi.noteapp.databinding.FragmentContainerLayoutBinding
import com.alitafreshi.noteapp.presentation.ui.theme.NoteAppTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NoteAppTheme {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
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
