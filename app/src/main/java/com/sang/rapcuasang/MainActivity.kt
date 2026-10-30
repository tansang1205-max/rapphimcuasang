package com.sang.rapcuasang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sang.rapcuasang.ui.screen.home.HomeScreen
import com.sang.rapcuasang.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            HomeScreen(
                viewModel = viewModel,
                onMovieClick = { slug ->
                    // Xử lý chuyển hướng đến trang chi tiết hoặc player ở đây
                }
            )
        }
    }
}
