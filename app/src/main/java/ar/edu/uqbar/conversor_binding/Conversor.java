package ar.edu.uqbar.conversor_binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by fernando on 10/24/16.
 */

public class Conversor extends BaseObservable {
    final double FACTOR_CONVERSION = 1.609344;

    private BigDecimal _millas;
    private BigDecimal _kilometros;

    public void convertir() {
        Log.w("Convertir", "Convertir");
        Log.w("Convertir", "" + this._millas);
        this._kilometros = this._millas.multiply(new BigDecimal(FACTOR_CONVERSION));
        Log.w("Convertir", "" + this._kilometros);
        notifyPropertyChanged(BR.kilometros);
    }

    @Bindable
    public String getMillas() {
        return "" + _millas;
    }

    @Bindable
    public void setMillas(String millas) {
        try {
            this._millas = new BigDecimal(millas);
        } catch (NumberFormatException e) {
        }
    }

    @Bindable
    public String getKilometros() {
        return "" + _kilometros;
    }

    public Conversor() {
        this._millas = new BigDecimal(0);
        this._kilometros = new BigDecimal(0);
    }
}
