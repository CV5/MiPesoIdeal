package com.mipesoideal.mipesoideal;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

/**
 * creado por Christian en la fecha 2018-05-04.
 */

class MyDialog {

    MyDialog(Context mContext, double formulaMLIC, double formulaBroca, double formulaWanDerVael, double formulaPerrault, double formulaLorentz) {

        Dialog mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(true);
        mDialog.setContentView(R.layout.dialogo_layout);
        mDialog.show();
        TextView MLIC = mDialog.findViewById(R.id.valorFormulaMLIC);
        TextView Broca = mDialog.findViewById(R.id.valorFormulaBroca);
        TextView WanDerVael = mDialog.findViewById(R.id.valorFormulaWanDerVael);
        TextView Perrault = mDialog.findViewById(R.id.valorFormulaPerrault);
        TextView Lorentz = mDialog.findViewById(R.id.valorFormulaLorentz);


        MLIC.setText(mContext.getString(R.string.peso_idealIMC, formulaMLIC));
        Broca.setText(mContext.getString(R.string.peso_idealIMC, formulaBroca));
        WanDerVael.setText(mContext.getString(R.string.peso_idealIMC, formulaWanDerVael));
        Perrault.setText(mContext.getString(R.string.peso_idealIMC, formulaPerrault));
        Lorentz.setText(mContext.getString(R.string.peso_idealIMC, formulaLorentz));


    }


}
