package org.abanapps.regal_time.store

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import org.abanapps.regal_time.store.data.domain.CustomerRepository
import org.abanapps.regal_time.store.navigation.SetupNavGraph
import org.abanapps.regal_time.store.shared.Constants
import org.abanapps.regal_time.store.shared.navigation.Screen
import org.koin.compose.koinInject

@Composable
fun App() {

//    var counter by remember {
//        mutableIntStateOf(0)
//    }
//
//    val coroutineScope = rememberCoroutineScope()

//    val input by rememberSaveable {
//        mutableIntStateOf(5)
//    }

    MaterialTheme {
        val customerRepository = koinInject<CustomerRepository>()
        val isUserAuthenticated = remember { customerRepository.getCurrentUserId() != null }
        val startDestination =
            remember { if (isUserAuthenticated) Screen.HomeGraph else Screen.Auth }
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
            SetupNavGraph(
                startDestination = startDestination
            )
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