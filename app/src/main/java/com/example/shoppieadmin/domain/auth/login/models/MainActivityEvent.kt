package com.example.shoppieadmin.domain.auth.login.models

sealed class MainActivityEvent {
    data object ReadTokenOnEntry: MainActivityEvent()
}
