package org.abanapps.regal_time.store

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import kotlinx.coroutines.delay
import org.abanapps.regal_time.store.navigation.SetupNavGraph
import org.abanapps.regal_time.store.shared.Constants
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import regaltimestore.composeapp.generated.resources.Res
import regaltimestore.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {

    var counter by remember {
        mutableIntStateOf(0)
    }

    val coroutineScope = rememberCoroutineScope()

//    val input by rememberSaveable {
//        mutableIntStateOf(5)
//    }

    MaterialTheme {

        var appReady by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(
            Unit
        ) {
            GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = Constants.WEB_CLIENT_ID))
//            delay(3000)
            appReady = true
        }


        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = appReady
        ) {
            SetupNavGraph()
        }


//        Column(
//            modifier = Modifier.fillMaxSize().safeContentPadding(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//
//            NativeButton(onClick = {
//                counter ++
//            },)
//
//            Text("Counter: $counter")
//
//        }

//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
//
//         Text("Input: $input \nOutput ${getNativeResponse(input)}")
//
//        }

    }

}