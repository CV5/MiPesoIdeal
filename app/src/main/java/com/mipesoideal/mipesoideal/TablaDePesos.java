package com.mipesoideal.mipesoideal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class TablaDePesos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_de_pesos);

        ArrayList<LibrasIdeal> mujerLibrasIdealsArraylist = new ArrayList<>();
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.5, 45.00, 58.67));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.6, 56.48, 63.50));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.7, 57.80, 74.46));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.8, 69.40, 83.06));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(1.9, 84.68, 92.13));
        mujerLibrasIdealsArraylist.add(new LibrasIdeal(2.0, 93.64, 101.67));


        final ArrayList<LibrasIdeal> hombreLibrasIdealsArraylist = new ArrayList<>();
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.5, 45.00, 62.41));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.6, 56.48, 70.56));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.7, 57.80, 79.21));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.8, 64.80, 88.36));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(1.9, 72.20, 98.01));
        hombreLibrasIdealsArraylist.add(new LibrasIdeal(2.0, 80.00, 108.16));



        AdapterLibraIDeal arrayAdapterMujer = new AdapterLibraIDeal(this, 0, mujerLibrasIdealsArraylist);
        ListView listView1 = findViewById(R.id.listview_mujer);
        //Adaptarle el adapter a la lista.
        listView1.setAdapter(arrayAdapterMujer);



        AdapterLibraIDeal arrayAdapterHombre = new AdapterLibraIDeal(this, 0, hombreLibrasIdealsArraylist);
        ListView listView = findViewById(R.id.listview_hombre);
        //Adaptarle el adapter a la lista.
        listView.setAdapter(arrayAdapterHombre);


    }

}
