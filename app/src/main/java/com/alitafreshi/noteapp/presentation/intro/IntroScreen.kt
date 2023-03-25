package com.alitafreshi.noteapp.presentation.intro

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.alitafreshi.components.util.spacing
import com.alitafreshi.noteapp.R


@Composable
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
            .padding(all = MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {

        LottieAnimation(
            composition = lottieCompositionResult.value,
            progress = lottieAnimationProgress,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )

        Text(
            modifier = modifier.fillMaxWidth(),
            text = "خوش آمدید",
            style = MaterialTheme.typography.h5.copy(
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
            style = MaterialTheme.typography.subtitle1.copy(
                textAlign = TextAlign.Center,
                color = Color.Black.copy(alpha = 0.6f),
                lineHeight = 30.sp
            )
        )
        Button(
            onClick = navigateToMainScreen,
            modifier = modifier.fillMaxWidth(0.5f),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                modifier = modifier.padding(vertical = MaterialTheme.spacing.buttonInnerPaddingSmall),
                text = "شروع کنید",
                style = MaterialTheme.typography.button.copy(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}