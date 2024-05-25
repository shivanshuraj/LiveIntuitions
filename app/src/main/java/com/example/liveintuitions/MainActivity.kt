package com.example.liveintuitions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.liveintuitions.ui.theme.LiveIntuitionsTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = FirebaseAuth.getInstance()



        setContent {
            LiveIntuitionsTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("movies") { MovieListScreen() }
                }
            }
        }

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            recreate()
        }
    }
}

//Displays a list of movies fetched from API
@Composable
fun MoviesList(modifier: Modifier = Modifier) {

}

//@Composable
//fun AuthenticationCheck() {
//    val auth = remember { FirebaseAuth.getInstance() }
//    var isUserLoggedIn by remember { mutableStateOf(auth.currentUser != null) }
//
//    LaunchedEffect(auth) {
//        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//            isUserLoggedIn = firebaseAuth.currentUser != null
//        }
//        auth.addAuthStateListener(authStateListener)
//        onDispose {
//            auth.removeAuthStateListener(authStateListener)
//        }
//    }
//
//    if (isUserLoggedIn) {
//        MovieListScreen()
//    } else {
//        LoginScreen()
//    }
//}


