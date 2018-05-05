package com.mipesoideal.mipesoideal;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class IMC extends AppCompatActivity {
    double sobrePesoFinal;
    private double alturaCM;
    private double pesoIdeal;
    long edad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        final Context mContext = this;

        TextView IMCExtra = findViewById(R.id.IMCTextView);
        TextView valuePesoIDeal = findViewById(R.id.pesoIdealIMCTextView);
        TextView valueRangoPeso = findViewById(R.id.rangopesoIMCTextView);
        TextView valueSobrePeso = findViewById(R.id.sobrePesoIMCTextView);
        ImageView sobrePesoImageView = findViewById(R.id.sobrePesoImageView);
        LinearLayout menor18LinearLayout = findViewById(R.id.delgado_linear_Layout);
        LinearLayout normalLinearLayout = findViewById(R.id.normalLinearLayout);
        LinearLayout gordoLinearLayout = findViewById(R.id.gordoLinearLayout);
        LinearLayout obesoLinearLayout = findViewById(R.id.ObesolinearLayout);
        LinearLayout extraObesLinearLayout = findViewById(R.id.extraObesoLinearLayout);
        Button VerTablaButton = findViewById(R.id.verTablaButton);
        Button VerFormulaButton = findViewById(R.id.verFormulas);
        View view = findViewById(R.id.view);
        TextView librasATrabajar = findViewById(R.id.sobrePesoTextView);
        TextView librasActuales = findViewById(R.id.libraActual);

        DecimalFormat df = new DecimalFormat("#.#");

        Double imc = getIntent().getDoubleExtra("imc", 0);
        Double alturaEnMetros = getIntent().getDoubleExtra("altura", 0);
        Double libras = getIntent().getDoubleExtra("libras", 0);
        edad = getIntent().getLongExtra("edad", 0);
        String generoActivo = getIntent().getStringExtra("genero");


        VerTablaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IMC.this, TablaDePesos.class);
                startActivity(intent);
            }
        });
        VerFormulaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog(mContext, pesoIdealMLIC(alturaCM), pesoIdealBroca(alturaCM), pesoIdealwanDerVaelHombre(alturaCM), pesoIdealPerrault(alturaCM, edad), pesoIdealLorentzHombre(alturaCM));
            }
        });


        GradientDrawable drawable = (GradientDrawable) view.getBackground().mutate();


        if (imc < 18) {
            drawable.setColor(getResources().getColor(R.color.menor18));
            menor18LinearLayout.setBackground(getResources().getDrawable(R.drawable.select_background));
        } else if (imc > 18 && imc < 25) {
            drawable.setColor(getResources().getColor(android.R.color.holo_blue_dark));
            normalLinearLayout.setBackground(getResources().getDrawable(R.drawable.select_background));
        } else if (imc > 25 && imc < 30) {
            drawable.setColor(getResources().getColor(R.color.gordoColor));
            gordoLinearLayout.setBackground(getResources().getDrawable(R.drawable.select_background));
        } else if (imc > 30 && imc < 40) {
            drawable.setColor(getResources().getColor(R.color.redButton));
            obesoLinearLayout.setBackground(getResources().getDrawable(R.drawable.select_background));
        } else if (imc > 40) {
            drawable.setColor(getResources().getColor(R.color.mayor40));
            extraObesLinearLayout.setBackground(getResources().getDrawable(R.drawable.select_background));
        }


        df.format(imc);
        alturaEnMetros = Double.parseDouble(df.format(alturaEnMetros));
        alturaCM = convertirACM(alturaEnMetros);

        IMCExtra.setText(getString(R.string.imc_value, imc));
        librasActuales.setText(getString(R.string.libra_actual, libras));


        ArrayList<LibrasIdeal> generoLibrasIdealsArraylist;


        ArrayList<LibrasIdeal> mujerLibrasIdealsArraylist = new ArrayList<>();
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.5, 45.00, 58.67));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.6, 56.48, 63.50));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.7, 57.80, 74.46));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.8, 69.40, 83.06));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.9, 84.68, 92.13));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(2.0, 93.64, 101.67));


        ArrayList<LibrasIdeal> hombreLibrasIdealsArraylist = new ArrayList<>();
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.5, 45.00, 62.41));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.6, 56.48, 70.56));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.7, 57.80, 79.21));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.8, 64.80, 88.36));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.9, 72.20, 98.01));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(2.0, 80.00, 108.16));

        if (generoActivo.equals("Hombre")) {
            generoLibrasIdealsArraylist = hombreLibrasIdealsArraylist;
           pesoIdealHombre();
        } else {
            generoLibrasIdealsArraylist = mujerLibrasIdealsArraylist;
            pesoIdealMujer();
        }


        for (int i = 0; i < generoLibrasIdealsArraylist.size(); i++) {
            double altura = generoLibrasIdealsArraylist.get(i).getAltura();
            if (pesoIdeal < libras) {
                sobrePesoFinal = libras - pesoIdeal;
                librasATrabajar.setText(getString(R.string.kilos_de_mas));
            } else {
                sobrePesoFinal = pesoIdeal - libras;
                sobrePesoImageView.setImageResource(R.drawable.perderpeso);
                librasATrabajar.setText(getString(R.string.kilos_de_menos));
            }
            if (altura == alturaEnMetros) {
                valuePesoIDeal.setText(getString(R.string.peso_idealIMC, pesoIdeal));
                valueRangoPeso.setText(getString(R.string.rango_peso_ideal, generoLibrasIdealsArraylist.get(i).getLibraMinima(), generoLibrasIdealsArraylist.get(i).getLibraMaxima()));
                valueSobrePeso.setText(getString(R.string.sobre_pesoIMC, sobrePesoFinal));
            }
        }


    }


    public double convertirACM(double metro) {
        return metro * 100;
    }

    public double pesoIdealBroca(double alturaEnCM) {
        return alturaEnCM - 100;
    }

    public double pesoIdealMLIC(double alturaEnCM) {
        return 50 + (0.75 * (alturaEnCM - 150));
    }

    public double pesoIdealLorentzHombre(double alturaEnCM) {
        return (alturaEnCM - 100) - (alturaEnCM - 150) / 4;
    }

    public double pesoIdealLorentzMujer(double alturaEnCM) {
        return (alturaEnCM - 100) - (alturaEnCM - 150) / 2;
    }

    public double pesoIdealPerrault(double alturaEnCM, long edad) {
        return alturaEnCM - 100 + ((edad / 10) * 0.9);
    }

    public double pesoIdealwanDerVaelHombre(double alturaEnCM) {

        return ((alturaEnCM - 150) * 0.75) + 50;
    }

    public double pesoIdealwanDerVaelMujer(double alturaEnCM) {

        return ((alturaEnCM - 150) * 0.6) + 50;
    }

    public void pesoIdealHombre() {
        pesoIdeal = pesoIdealMLIC(alturaCM)
                + pesoIdealBroca(alturaCM)
                + pesoIdealPerrault(alturaCM, edad)
                + pesoIdealwanDerVaelHombre(alturaCM)
                + pesoIdealLorentzHombre(alturaCM);
        pesoIdeal = pesoIdeal / 5;
    }

    public void pesoIdealMujer() {
        pesoIdeal = pesoIdealMLIC(alturaCM)
                + pesoIdealBroca(alturaCM)
                + pesoIdealPerrault(alturaCM, edad)
                + pesoIdealwanDerVaelMujer(alturaCM)
                + pesoIdealLorentzMujer(alturaCM);
        pesoIdeal = pesoIdeal / 5;
    }
}
