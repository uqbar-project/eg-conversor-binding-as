package ar.edu.uqbar.conversor_binding;

import java.math.BigDecimal;

/**
 * Created by fernando on 10/24/16.
 */

public class Conversor {
    final double FACTOR_CONVERSION = 1.609344;

    private BigDecimal millas;
    private BigDecimal kilometros;

    public BigDecimal getMillas() {
        return millas;
    }

    public void setMillas(BigDecimal millas) {
        this.millas = millas;
    }

    public BigDecimal getKilometros() {
        return kilometros;
    }

    public void setKilometros(BigDecimal kilometros) {
        this.kilometros = kilometros;
    }

    public void convertir() {
        this.kilometros = redondear(this.millas.multiply(new BigDecimal(FACTOR_CONVERSION)));
    }

    public Conversor() {
        this.millas = new BigDecimal(0);
        this.kilometros = new BigDecimal(0);
    }

    private BigDecimal redondear(BigDecimal bigDecimal) {
        return bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
    }

}
