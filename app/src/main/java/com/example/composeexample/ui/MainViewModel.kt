package com.example.composeexample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeexample.R

class MainViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    val chocoBoCharacters = listOf(
        "chocobo" to R.drawable.chocobo,
        "Mog" to R.drawable.mog,
        "Golem" to R.drawable.golem,
        "Goblin" to R.drawable.goblin,
        "Black Mage (Black Magician)" to R.drawable.blackmage,
        "White Mage" to R.drawable.white_magic,
        "Chubby Chocobo" to R.drawable.chubby,
        "Behemoth" to R.drawable.behemoth,
        "Bahamut" to R.drawable.bahamut,
        "Squall Leonhart" to R.drawable.squall_leonhart
    )

    fun setMessage(message: String) {
        _message.value = message
    }



}