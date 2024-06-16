package com.example.shoppieadmin.domain.auth.main.models

sealed class MainActivityEvent {
    data object ReadTokenOnEntry: MainActivityEvent()
}
