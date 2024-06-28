package com.example.shoppieadmin.domain.main.models

sealed class MainActivityEvent {
    data object ReadTokenOnEntry: MainActivityEvent()
}
