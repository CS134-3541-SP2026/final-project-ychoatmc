package com.example.pricecheckshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.pricecheckshoppinglist.ui.theme.PriceCheckShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PriceCheckShoppingApp()
        }
    }
}

@PreviewScreenSizes
@Composable
fun PriceCheckShoppingApp() {
    Text(
        text = "Hello!",
    )
}

@Preview(showBackground = true)
@Composable
fun PriceCheckShoppingAppPreview() {
    PriceCheckShoppingListTheme {
        PriceCheckShoppingApp()
    }
}