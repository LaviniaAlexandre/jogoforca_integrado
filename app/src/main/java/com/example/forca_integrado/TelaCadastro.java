package com.example.forca_integrado;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaCadastro extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private EditText textoDaPalavra;
    private Button btnCadastrar, btnListar;
    private RadioGroup grupo;
    private String categoriaSelecionada, palavra;

    private BD bd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = new BD(TelaCadastro.this);
        textoDaPalavra = findViewById(R.id.textPalavra);
        btnCadastrar = findViewById(R.id.button2);
        btnCadastrar.setOnClickListener(this);
        btnListar = findViewById(R.id.button3);
        btnListar.setOnClickListener(this);
        grupo = findViewById(R.id.id_grupo);
        grupo.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnCadastrar) {
            String texto = textoDaPalavra.getText().toString();

            // Vamos testar a caixa de texto para ver se tem conteúdos
            boolean TemTextoDigitado = false;
            if (texto.isEmpty()) {
                Toast.makeText(this, "Falou palavra! Animal!", Toast.LENGTH_SHORT).show();
            } else {
                TemTextoDigitado = true;
            }

            RadioButton r = findViewById(R.id.radioButton);
            RadioButton r1 = findViewById(R.id.radioButton2);
            RadioButton r2 = findViewById(R.id.radioButton3);
            RadioButton r3 = findViewById(R.id.radioButton4);
            RadioButton r4 = findViewById(R.id.radioButton5);

            boolean temRadioChecado = false;
            if (r.isChecked() || r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked()) {
                temRadioChecado = true;
            } else {
                Toast.makeText(this, "Falou marcar categoria!", Toast.LENGTH_SHORT).show();
            }
            if (TemTextoDigitado && temRadioChecado){
                //aqui pode salvars no BD
                Palavra palavra1 = new Palavra();
                palavra1.setPalavraDigitada(texto);
                bd.salvarPalavra(palavra1);
            }

        }
        if(view == btnListar){

        }
    }

    @Override
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {
        if(radioGroup == grupo){
            RadioButton temporario = findViewById(i);
            Toast.makeText(TelaCadastro.this, temporario.getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}





