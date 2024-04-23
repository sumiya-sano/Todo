package com.websarva.wings.android.todo

import android.view.accessibility.AccessibilityManager.AudioDescriptionRequestedChangeListener
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var description: String
)
