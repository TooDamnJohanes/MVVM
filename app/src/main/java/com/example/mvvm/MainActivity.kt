package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // CRIO ESSA VARIAVEL AQUI EM CIMA, COMO LATEINIT, PORQUE ELA PRECISA DE UM CONTEXTO
    // PARA NÃO SOFRER CRASH, EU DOU UM LATE INIT, E INSTANCIO ELA DEPOIS QUE O CONTEXTO
    // ESTIVER PRONTO
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // COM O CONTEXTO JÁ EXISTENTE, INICIALIZO A VARIÁVEL.
        //DEIXAMOS O SISTEMA INSTANCIAR O VIEWMODEL

        // 1- PASSAMOS O VIEWMODELPROVIDER, COM O THIS(CONTEXTO)
        // 2- DEPOIS PASSAMOS O .GET, O ARQUIVO QUE IREMOS INICIAR
        // PASSAMOS PARA O VIEW MODEL PROVIDER, A ESPECIFICAÇÃO DA NOSSA MAIN VIEW MODEL, NÃO CRIAMOS
        // DEIXAMOS QUE O SISTEMA CRIE ISSO, PARA QUE ELE EXECUTE O CICLO DE VIDA, COMO ELE DEVERIA
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }

        // AQUI EU CRIO A VARIAVEL QUE IRÁ OBSERVAR AS MUDANÇAS DE TEXTO QUE IRÃO OCORRER NA VARIAVEL
        // DE TEXTO DA MINHA MAIN VIEW. SE OUVER ALGUMA MUDANÇA, EU JÁ ATRIBUO O TEXTO DELA
        // PARA A MINHA VARIAVEL DE TEXTO NA TELA
        viewModel.textWelcome.observe(this, Observer {
            textWelcome.text = it
        })

        // AQUI, ESTOU OUVINDO UMA VARIAVEL QUE É UM BOOLEAN, SE ELE MUDA DE ESTADO, UM TOAST
        // É DISPARADO.
        // COLOCAMOS applicationContext AO INVÉS DE this, PORQUE ESTAMOS DENTRO DO CONTEXTO DO
        // OBSERVER
        viewModel.login.observe(this, Observer {
            if(it) {
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "ERRADO FDP!", Toast.LENGTH_SHORT).show()
            }
        })

        buttonLogin.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id
        if(id == (R.id.buttonLogin))
        {
            val login = editName.text.toString()
            viewModel.login(login)
        }
    }
}