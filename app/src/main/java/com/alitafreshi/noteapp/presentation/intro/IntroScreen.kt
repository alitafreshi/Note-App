package com.alitafreshi.noteapp.presentation.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.alitafreshi.noteapp.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun IntroScreen(
    modifier: Modifier = Modifier,
    navigateToMainScreen: () -> Unit
) {

    val lottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.getting_start_anim))

    val lottieAnimationProgress by animateLottieCompositionAsState(
        composition = lottieCompositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            composition = lottieCompositionResult.value,
            progress = lottieAnimationProgress
        )

        Text(
            modifier = modifier.fillMaxWidth(),
            text = "خوش آمدید",
            style = MaterialTheme.typography.h3.copy(
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        )

        Text(
            modifier = modifier.fillMaxWidth(),
            text = "اپلیکیشن یادداشت برداری حرفه ای !\n" +
                    " از این بعد هر جا که دلت خواست میتونی\n" +
                    " هرجا که بودی راحت هر چیزی\n" +
                    " رو نوت برداری کنی.",
            style = MaterialTheme.typography.body1.copy(
                textAlign = TextAlign.Center,
                color = Color.Black.copy(alpha = 0.6f)
            )
        )

        Button(

            onClick = navigateToMainScreen
        ) {
            Text(
                text = "شروع کنید",
                style = MaterialTheme.typography.button.copy(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}