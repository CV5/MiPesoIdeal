package com.mipesoideal.mipesoideal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * creado por Christian en la fecha 2018-04-30.
 */

public class AdapterLibraIDeal extends ArrayAdapter<LibrasIdeal> {
    AdapterLibraIDeal(Context context, int resource, List<LibrasIdeal> librasIdeals) {
        super(context, resource, librasIdeals);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(

                    R.layout.items_list_view, parent, false);
        }


        LibrasIdeal currentLibraIdeal = getItem(position);

        TextView libra = listItemView.findViewById(R.id.altura);
        assert currentLibraIdeal != null;
        libra.setText(getContext().getString(R.string.altura,currentLibraIdeal.getAltura()));

        TextView libramini = listItemView.findViewById(R.id.libramini);
        libramini.setText(getContext().getString(R.string.libra_adapter,currentLibraIdeal.getLibraMinima()));

        TextView libramax = listItemView.findViewById(R.id.libramax);
        libramax.setText(getContext().getString(R.string.libra_adapter,currentLibraIdeal.getLibraMaxima()));

        return listItemView;
    }
}
