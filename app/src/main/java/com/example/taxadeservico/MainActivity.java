package com.example.taxadeservico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //objetos formatadores de moeda corrente e porcentagem
    private static final NumberFormat moedaFormatada = NumberFormat.getCurrencyInstance();
    private static final NumberFormat porcentagemFormatada = NumberFormat.getPercentInstance();

    //variaveis globais
    //valor da conta inserida pelo usuario
    private double valorConta = 0.0;

    //valor inicial da porcentagem da taxa de servico
    private double percent = 0.15;

    //mostra o valor da conta formatado
    private TextView contaTextView;

    //mostra a porcentagem da taxa de servico
    private TextView porcentagemTextView;

    //mostra o valor do servico ja calculado
    private TextView taxaTextView;

    //mostra o total ja calculado
    private TextView totalTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //contaTextView = (TextView) findViewById(R.id.valorTextView);
        porcentagemTextView = (TextView) findViewById(R.id.percentTextView);
        taxaTextView = (TextView) findViewById(R.id.GorjetaTextView);
        totalTextView = (TextView) findViewById(R.id.TotalTextView);

        taxaTextView.setText(moedaFormatada.format(0));
        totalTextView.setText(moedaFormatada.format(0));
    }

    private void calcular() {
        //formata a porcentagem e exibe no componente
        porcentagemTextView.setText(porcentagemFormatada.format(percent));

        //calcula a taxa de servico
        double taxa = valorConta * percent;
        double total = valorConta + taxa;

        //exibe a taxa de servico e o total formatados com moeda corrente
        taxaTextView.setText(moedaFormatada.format(taxa));
        totalTextView.setText(moedaFormatada.format(total));
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener()
    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            percent = i/100;
            calcular();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher valorEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try {
                //obtem o valor da conta e o exibe como moeda corrente
                valorConta = Double.parseDouble(s.toString());
                //valorTextView.setText(moedaFormatada.format(valorConta));
            }
            catch (NumberFormatException e)
            {
                //valorTextView.setText("");
                valorConta = 0.0;
            }

            calcular();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}