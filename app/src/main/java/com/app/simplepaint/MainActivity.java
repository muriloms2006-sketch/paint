package com.app.simplepaint;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PaintView areaDesenho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        areaDesenho = findViewById(R.id.areaDesenho);

        Button btnPreto = findViewById(R.id.btnPreto);
        Button btnVermelho = findViewById(R.id.btnVermelho);
        Button btnAzul = findViewById(R.id.btnAzul);
        Button btnVerde = findViewById(R.id.btnVerde);
        Button btnAmarelo = findViewById(R.id.btnAmarelo);

        Button btnLivre = findViewById(R.id.btnLivre);
        Button btnRetangulo = findViewById(R.id.btnRetangulo);
        Button btnCirculo = findViewById(R.id.btnCirculo);

        Button btnLimpar = findViewById(R.id.btnLimpar);

        btnPreto.setOnClickListener(v ->
                areaDesenho.setCor(Color.BLACK));

        btnVermelho.setOnClickListener(v ->
                areaDesenho.setCor(Color.RED));

        btnAzul.setOnClickListener(v ->
                areaDesenho.setCor(Color.BLUE));

        btnVerde.setOnClickListener(v ->
                areaDesenho.setCor(Color.GREEN));

        btnAmarelo.setOnClickListener(v ->
                areaDesenho.setCor(Color.YELLOW));

        btnLivre.setOnClickListener(v ->
                areaDesenho.setForma(PaintView.LIVRE));

        btnRetangulo.setOnClickListener(v ->
                areaDesenho.setForma(PaintView.RETANGULO));

        btnCirculo.setOnClickListener(v ->
                areaDesenho.setForma(PaintView.CIRCULO));

        btnLimpar.setOnClickListener(v ->
                areaDesenho.limpar());
    }
}