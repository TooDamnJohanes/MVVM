package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var mRepository = PersonRepository()

    // CRIAMOS ESSA VARIÁVEL, PORQUE ELA É PASSIVEL DE SER ESCUTADA, DE OBSERVAR AS MUDANÇAS QUE ELA
    // IRÁ SOFRER.
    private var mTextWelcome = MutableLiveData<String>()

    // COMO A mTextWelcome É PRIVADA, CRIAMOS ESSA VARIÁVEL QUE RECEBE O mTextWelcome E VAMOS
    // OBSERVAR ELA NA MAIN
    var textWelcome = mTextWelcome

    // CRIAMOS ESSA VARIÁVEL, PORQUE ELA É PASSIVEL DE SER ESCUTADA, DE OBSERVAR AS MUDANÇAS QUE ELA
    // IRÁ SOFRER.
    private val mLogin = MutableLiveData<Boolean>()
    var login = mLogin

    // INICIALIZAMOS A VARIAVEL AQUI
    init {
        mTextWelcome.value = "Hello World"
    }

    // CRIAMOS UMA FUNÇÃO PARA VALIGAR O LOGIN
    fun login(login: String) {
        val ret = mRepository.login(login)
        mLogin.value = ret
        if(mLogin.value == false) {
            mTextWelcome.value = "PEGA NO MEU PAU"
        }
        else {
            mTextWelcome.value = "Charlie Brown"
        }
    }

}