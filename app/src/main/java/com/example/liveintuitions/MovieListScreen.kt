package com.example.liveintuitions

import android.graphics.Movie
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(viewModel: MovieViewModel = MovieViewModel(), navController: NavController) {
    val movies by remember { viewModel.movies }

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Movies") }, actions = {
                IconButton(onClick = {
                    // Handle button click
                    Firebase.auth.signOut()
                    navController.navigate("login")
                }) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Log out")

                }
            })


        }
    ) {
        Button(onClick = { Firebase.auth.signOut()}) {
            Text(text = "Log out")
        }
        LazyColumn(modifier = Modifier.padding(it)) {
            items(movies) { movie ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(it)) {
                        Text(movie.title, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(movie.overview)
                    }
                }
            }
        }
    }
}