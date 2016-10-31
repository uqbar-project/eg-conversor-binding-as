package ar.edu.uqbar.conversor_binding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import ar.edu.uqbar.conversor_binding.databinding.ActivityConversorBinding;

public class ConversorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);
        ActivityConversorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_conversor);
        ConversorModel conversor = new ConversorModel();
        binding.setConversor(conversor);
    }

}
