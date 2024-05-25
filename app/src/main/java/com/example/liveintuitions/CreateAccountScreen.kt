package com.example.liveintuitions

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "CreateAccountScreen"
@Composable
fun CreateAccountScreen(modifier: Modifier = Modifier) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = firstName,
            maxLines = 1,
            placeholder = {"First Name"},
            onValueChange = { firstName = it },
            label = { "First Name" },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(.9f)
        )
        OutlinedTextField(
            maxLines = 1,
            value = lastName,
            onValueChange = { lastName = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(.9f)
        )
        OutlinedTextField(
            maxLines = 1,
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(.9f)
        )
        OutlinedTextField(
            maxLines = 1,
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(.9f)
        )
//        Button(onClick = { createNewUser()}, modifier = Modifier.padding(bottom = 16.dp)) {
//            Text(text = "REGISTER")
//        }
        Text(text = "or using other method", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(bottom = 16.dp)) {
            Text(text = "Sign up with Google")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun createAccPreview() {
    CreateAccountScreen()
}


@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (email.isNotEmpty() && password == confirmPassword) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user=auth.currentUser
                                navController.navigate("login")
                            } else {
                                Log.e(TAG, "RegisterScreen: ", task.exception)
                                Toast.makeText(context, "User registration failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}