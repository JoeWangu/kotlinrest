package com.saddict.djrest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.saddict.djrest.ui.RestApp
import com.saddict.djrest.ui.theme.DjRestTheme

class MainActivity : ComponentActivity() {
//    private var pressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DjRestTheme(dynamicColor = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RestApp()
                }
            }
        }
    }
    // on below line we are calling on back press method.
//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        // on below line we are checking if the press time is greater than 2 sec
//        if (pressedTime + 2000 > System.currentTimeMillis()) {
//            // if time is greater than 2 sec we are closing the application.
//            super.onBackPressed()
//            finish()
//        } else {
//            // in else condition displaying a toast message.
//            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
//        }
//        // on below line initializing our press time variable
//        pressedTime = System.currentTimeMillis()
//    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    DjRestTheme(dynamicColor = false) {
//        RestApp()
//    }
//}