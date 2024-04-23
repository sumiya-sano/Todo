package com.websarva.wings.android.todo.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.websarva.wings.android.todo.MainViewModel


@JvmOverloads
@Composable
fun EditDialog(viewModel: MainViewModel = hiltViewModel()){
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetPropaties()
        }
    }
    
    AlertDialog(
        onDismissRequest = { viewModel.isShowDialog = false },
        title = { Text(text =
                            if (viewModel.isEditig){
                                "タスク更新中"
                            } else{
                                "タスク新規作成"
                            }
                        )
                },
        text = {
               Column {
                   Text(text = "タイトル")
                   TextField(value = viewModel.title, onValueChange = {viewModel.title = it})
                   Text(text = "詳細")
                   TextField(value = viewModel.description, onValueChange = {viewModel.description = it})
               }
        },
        confirmButton = {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.width(120.dp) ,
                    onClick = { viewModel.isShowDialog = false }
                ) {
                    Text(text = "キャンセル")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        viewModel.isShowDialog = false
                        if (viewModel.isEditig){
                            viewModel.updateTask()
                        } else {
                            viewModel.createTask()
                        }

                    }
                ) {
                    Text(text = "OK")
                }
            }
        }
    )
}

