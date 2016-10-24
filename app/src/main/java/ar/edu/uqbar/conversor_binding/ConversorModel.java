package ar.edu.uqbar.conversor_binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by fernando on 10/24/16.
 */

public class ConversorModel extends BaseObservable {
    final double FACTOR_CONVERSION = 1.609344;

    private Conversor conversor = new Conversor();

    public void convertir() {
        conversor.convertir();
        notifyPropertyChanged(BR.kilometros);
    }

    @Bindable
    public String getMillas() {
        return "" + conversor.getMillas();
    }

    @Bindable
    public void setMillas(String millas) {
        try {
            conversor.setMillas(new BigDecimal(millas));
        } catch (NumberFormatException e) {
        }
    }

    @Bindable
    public String getKilometros() {
        return "" + conversor.getKilometros();
    }

}
