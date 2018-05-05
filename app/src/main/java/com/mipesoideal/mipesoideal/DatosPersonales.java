package com.mipesoideal.mipesoideal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class DatosPersonales extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ImageButton mujerImageButton;

    private ImageButton hombreImageButton;
    private ToggleButton pulgadaLibrasToggleButton;
    private ToggleButton kgCentimetroToggleButton;
    private String generoActivo = "Hombre";
    private EditText editTextLibras;
    private EditText editTextAlturapulgada;
    private EditText editTextAlturaPie;
    private TextView fechaTextView;
    private Double convertido;
    private EditText alturaMetroEditText;
    private final static double VALORMETRO = 3.2808;
    private final static double VALORKILOGRAMO = 0.453592;
    private double valorLibras;
    private double valorPie;
    private double valorPulgada;
    private double valorIMC;
    private double valorMetro;
    private double valorAlturaAlCuadrado;
    private int ano;
    private int dia;
    private int mes;
    private long edad = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        Calendar calendario = Calendar.getInstance();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH);
        ano = calendario.get(Calendar.YEAR);

        //Asiganar valor a cada Componente
        mujerImageButton = findViewById(R.id.mujerImageButton);
        hombreImageButton = findViewById(R.id.hombreImageButton);
        ImageButton calendarioImageButton = findViewById(R.id.calendarioImageButton);
        ImageButton guardarImageButton = findViewById(R.id.guardarImageButton);
        pulgadaLibrasToggleButton = findViewById(R.id.pgLbButton);
        kgCentimetroToggleButton = findViewById(R.id.cmKgButton);
        editTextLibras = findViewById(R.id.pesoPersonal);
        editTextAlturapulgada = findViewById(R.id.editTextAlturaPulgada);
        editTextAlturaPie = findViewById(R.id.editTextAlturaPie);
        alturaMetroEditText = findViewById(R.id.editTextAlturaMetro);
        fechaTextView = findViewById(R.id.fecha);


        if (generoActivo.equals("Hombre")) {
            hombreImageButton.setImageResource(R.drawable.hombrered);
        }

        //Listener de cada boton
        mujerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mujerActiva();

            }
        });
        hombreImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hombreActivo();
            }
        });
        calendarioImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });


        guardarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosPersonales.this, IMC.class);

                if (edad > 15 ) {
                    if (editTextAlturaPie.length() > 0 && editTextAlturapulgada.length() > 0 && editTextLibras.length() > 0) {
                        intent.putExtra("imc", IMCEnLibras());
                        intent.putExtra("altura", valorMetro);
                        intent.putExtra("libras", valorLibras);
                        intent.putExtra("edad", edad);
                        intent.putExtra("genero", generoActivo);
                        startActivity(intent);
                    } else if (alturaMetroEditText.length() > 0) {
                        intent.putExtra("imc", IMCEnKiloYMetros());
                        intent.putExtra("altura", valorMetro);
                        intent.putExtra("libras", valorLibras);
                        intent.putExtra("edad", edad);
                        intent.putExtra("genero", generoActivo);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DatosPersonales.this, "Completa los campos", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(DatosPersonales.this, "Tienes que ser mayor de 15, leer las instruciones", Toast.LENGTH_LONG).show();
                }
            }
        });


        pulgadaLibrasToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonPulgadaLibraActiva();
            }
        });
        kgCentimetroToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonKiloGramoCentimetroActiva();
            }
        });

    }

    public void hombreActivo() {
        mujerImageButton.setImageResource(R.drawable.chica);
        hombreImageButton.setImageResource(R.drawable.hombrered);
        generoActivo = "Hombre";
    }

    public void mujerActiva() {
        mujerImageButton.setImageResource(R.drawable.chicared);
        hombreImageButton.setImageResource(R.drawable.hombre);
        generoActivo = "Mujer";
    }

    public void botonPulgadaLibraActiva() {
        pulgadaLibrasToggleButton.setBackgroundColor(getResources().getColor(R.color.redButton));
        pulgadaLibrasToggleButton.setTextColor(getResources().getColor(R.color.blancon));
        kgCentimetroToggleButton.setTextColor(getResources().getColor(R.color.colorAccent));
        kgCentimetroToggleButton.setBackgroundColor(getResources().getColor(R.color.transparent));
        editTextLibras.setHint("Libra");
        alturaMetroEditText.setVisibility(View.GONE);
        editTextAlturapulgada.setVisibility(View.VISIBLE);
        editTextAlturaPie.setVisibility(View.VISIBLE);
    }

    public void botonKiloGramoCentimetroActiva() {
        kgCentimetroToggleButton.setBackgroundColor(getResources().getColor(R.color.redButton));
        kgCentimetroToggleButton.setTextColor(getResources().getColor(R.color.blancon));
        pulgadaLibrasToggleButton.setTextColor(getResources().getColor(R.color.colorAccent));
        pulgadaLibrasToggleButton.setBackgroundColor(getResources().getColor(R.color.transparent));
        editTextAlturapulgada.setVisibility(View.GONE);
        editTextAlturaPie.setVisibility(View.GONE);
        editTextLibras.setHint("Kilogramo");
        alturaMetroEditText.setVisibility(View.VISIBLE);
    }

    public double IMCEnLibras() {
        obtenerValoresDeLosCampos();
        valorLibras = valorLibras * VALORKILOGRAMO;
        valorPulgada = valorPulgada / 12;
        valorPie = valorPie + valorPulgada;
        valorMetro = valorPie / VALORMETRO;
        valorAlturaAlCuadrado = Math.pow(valorMetro, 2);
        valorIMC = valorLibras / valorAlturaAlCuadrado;

        editTextLibras.setText("");
        editTextAlturaPie.setText("");
        editTextAlturapulgada.setText("");
        alturaMetroEditText.setText("");


        return valorIMC;
    }

    public Double IMCEnKiloYMetros() {
        obtenerValoresDeLosCampos();
        valorAlturaAlCuadrado = Math.pow(valorMetro, 2);
        valorIMC = valorLibras / valorAlturaAlCuadrado;
        editTextLibras.setText("");
        editTextAlturaPie.setText("");
        editTextAlturapulgada.setText("");
        alturaMetroEditText.setText("");
        return valorIMC;

    }

    public Double ConvertirDouble(String numeroAConvertir) {
        try {
            convertido = Double.parseDouble(numeroAConvertir);
        } catch (NumberFormatException ex) {
            Log.e("tag", "error al pasa el numero");
        }
        return convertido;
    }

    public void obtenerValoresDeLosCampos() {
        valorLibras = ConvertirDouble(editTextLibras.getText().toString());
        valorPie = ConvertirDouble(editTextAlturaPie.getText().toString());
        valorPulgada = ConvertirDouble(editTextAlturapulgada.getText().toString());
        valorMetro = ConvertirDouble(alturaMetroEditText.getText().toString());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        fechaTextView.setText(getString(R.string.fecha, dayOfMonth, monthOfYear, year));
        edad = ano - year;
        if ((mes < monthOfYear) || ((mes == monthOfYear) && (dia < dayOfMonth))) {
            --edad;
        }
    }

    public void showDatePickerDialog(View v) {
        MyDatePicker newFragment = new MyDatePicker();
        newFragment.show(getFragmentManager(), "DatePicker");
    }



}
