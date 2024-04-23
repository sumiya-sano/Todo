package com.websarva.wings.android.todo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.websarva.wings.android.todo.components.EditDialog
import com.websarva.wings.android.todo.components.TaskList
import com.websarva.wings.android.todo.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()){

    if (viewModel.isShowDialog){
        EditDialog()
    }


    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.isShowDialog = true }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription ="新規作成",
                )
            }
    }
    ){
        val it = it
        val tasks by viewModel.tasks.collectAsState(initial = emptyList())

        TaskList(
            tasks = tasks,
            onClickRow = {
                viewModel.SetEditingTask(it)
                viewModel.isShowDialog = true
            } ,
            onClickDelete = { viewModel.deleteTask(it) },
        )
    }

}