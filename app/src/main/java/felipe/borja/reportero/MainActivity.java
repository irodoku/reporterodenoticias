package felipe.borja.reportero;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import felipe.borja.reportero.ui.main.SectionsPagerAdapter;
import felipe.borja.reportero.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static FragmentManager sfManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarHoyCircula();
            }
        });

    }
    //Snackbar.make(view, "texto de prueba", Snackbar.LENGTH_LONG).setAction("boton", null).show();

    void mostrarHoyCircula() {
        sfManager=getSupportFragmentManager();
        DialogFragment dialogo = hoyCircula.newInstance();
        dialogo.show(sfManager, "dialog");
    }

    public static class hoyCircula extends DialogFragment {
        EditText ingresoPlaca, ingresoHora, ingresoDia;
        TextView resultado;
        static Calendar calendario;
        String diaSemana;
        int dia,mes,año,hora;
        private String codigoPlaca;
        private short ultimoDigito;
        private boolean pico;
        static hoyCircula newInstance() {
            return new hoyCircula();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_dialog, container, false);
            ingresoPlaca = v.findViewById(R.id.editText);
            ingresoPlaca.setEnabled(false);
            resultado = v.findViewById(R.id.resultado);
            ingresoHora = v.findViewById(R.id.ingresarHora);

            ingresoDia = v.findViewById(R.id.ingresarFecha);
            ingresoDia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mostrarDialogoFecha();
                }
            });
            ingresoPlaca.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                        try {
                            hora=Integer.valueOf(ingresoHora.getText().toString());
                            //minuto=Integer.valueOf(ingresoMinuto.getText().toString());
                            codigoPlaca = String.valueOf(ingresoPlaca.getText());
                            ultimoDigito=Short.parseShort(String.valueOf(codigoPlaca.charAt(codigoPlaca.length()-1)));
                            setPico(hora);
                            if (tieneMulta(calendario,ultimoDigito)){
                                resultado.setText("NO CIRCULA");
                            }else{
                                resultado.setText("PUEDE CIRCULAR");
                            }
                            if (!pico)
                                resultado.setText("FUERA DE HORA PICO");
                        } catch (NumberFormatException nfe){
                            resultado.setText("DATOS INCORRECTOS");
                        }
                        return true;
                    }
                    return false;
                }
            });
            fechaActual();
            return v;
        }

        void mostrarDialogoFecha(){
            hoyCircula.DatePickerFragment dialogoFecha = hoyCircula.DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                    hora = Integer.parseInt(ingresoHora.getText().toString());
                    //minuto = Integer.parseInt(ingresoMinuto.getText().toString());
                    Calendar calendarNuevo=calendario;
                    calendarNuevo.set(año, mes - 1, dia, hora,0);
                    diaSemana = getDiaSemana(calendarNuevo);
                    ingresoDia.setText((diaSemana + "  " + dia + " / " + (mes + 1) + " / " + año));
                    ingresoPlaca.setEnabled(true);
                }
            });
            dialogoFecha.show(sfManager, "datePicker");
        }

        private void setPico(int hora) {
            pico = (hora > 5) && (hora < 20) ;
        }

        public Boolean tieneMulta(Calendar calendario, Short ultimoDigito){
            Log.e("cal ",""+calendario.get(Calendar.DAY_OF_WEEK));
            Log.e("dig ",""+ultimoDigito);

            switch (calendario.get(Calendar.DAY_OF_WEEK)){
                case 7:
                case 2:
                case 4:
                    if ((ultimoDigito==1)||(ultimoDigito==3)||(ultimoDigito==5)||(ultimoDigito==7)||(ultimoDigito==9))return true;
                    break;
                case 1:
                case 3:
                case 5:
                    if ((ultimoDigito==2)||(ultimoDigito==4)||(ultimoDigito==6)||(ultimoDigito==8)||(ultimoDigito==0))return true;
                    break;
            }
            return false;
        }

        private void fechaActual(){
            calendario = Calendar.getInstance();
            calendario.getTime();
            ingresoHora.setText(String.valueOf((Calendar.getInstance().get(Calendar.HOUR_OF_DAY))));
            //ingresoMinuto.setText(String.valueOf((Calendar.getInstance().get(Calendar.MINUTE))));
            dia=(calendario.get(Calendar.DAY_OF_MONTH));
            mes=(calendario.get(Calendar.MONTH)+1);
            año=(calendario.get(Calendar.YEAR));
            hora=(calendario.get(Calendar.HOUR));
            //minuto=(calendario.get(Calendar.MINUTE));
            ingresoDia.setText(("Fecha: "+dia+" / "+mes+" / "+año));
        }


        protected static String getDiaSemana(Calendar calendario){
            //Siendo el primer día de la semana martes (1)
            switch (calendario.get(Calendar.DAY_OF_WEEK)){
                case 7: return "Lunes";
                case 1: return "Martes";
                case 2: return "Miércoles";
                case 3: return "Jueves";
                case 4: return "Viernes";
                case 5: return "Sábado";
                case 6: return "Domingo";
            }
            return null;
        }

        //@SuppressLint("ValidFragment")
        public static class DatePickerFragment extends DialogFragment{
            private DatePickerDialog.OnDateSetListener listener;

            public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.setListener(listener);
                return fragment;
            }

            public void setListener(DatePickerDialog.OnDateSetListener listener) {
                this.listener = listener;
            }

            @Override
            @NonNull
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(getActivity(), listener, year, month, day);
            }
        }
    }


}