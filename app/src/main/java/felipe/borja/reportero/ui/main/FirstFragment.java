package felipe.borja.reportero.ui.main;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import felipe.borja.reportero.Info;
import felipe.borja.reportero.ListAdapter;
import felipe.borja.reportero.ListAdapterInfo;
import felipe.borja.reportero.Noticia;
import felipe.borja.reportero.R;
import felipe.borja.reportero.medios;

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int pagina;
    RecyclerView recyclerView;
    private ArrayList<Noticia> noticias;
    private ArrayList<Info> infos;
    private PageViewModel pageViewModel;
    //public ArrayList<String> lista;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("pagina", page);
        args.putString("titulo", title);
        fragmentFirst.setArguments(args);
        Log.e("newInstance","p"+page);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagina = getArguments().getInt("pagina", -1);
        title = getArguments().getString("titulo");
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        pageViewModel.setIndex(pagina);
        noticias  = new ArrayList<>();
        infos  = new ArrayList<>();
        addNoticias();
        addInfo();
        Log.e("onCreate","p"+pagina);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.section_label2);
        //tvLabel.setText(page + " -- " + title);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(llm);
        ListAdapter adapter;
        ListAdapterInfo adapterInfo;
        adapter = new ListAdapter(view.getContext(),noticias);
        adapterInfo = new ListAdapterInfo(view.getContext(),infos);
        //lista  = new ArrayList<>();
        //switch (pagina) {
        //case 0:
        //  case 1:
          //  default: throw new IllegalStateException("Index incorrecto: " + pagina);
        //}
        Log.e("onCreateView","p"+pagina);
        switch (pagina) {
            case 0:recyclerView.setAdapter(adapter);break;
            case 1:recyclerView.setAdapter(adapterInfo);break;
            default: throw new IllegalStateException("Index incorrecto: " + pagina);
        }
        adapter.notifyDataSetChanged();
        adapterInfo.notifyDataSetChanged();
        //adapter.ordenar();
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //adapter.ordenar();
              //  lista.add("asdfghjkl");
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void addNoticias(){
        //noticias.clear();
        noticias.add(new Noticia("Quito: Salvoconductos otorgados por el municipio quedan suspendidos",
                "El Municipio de Quito informó mediante la Secretaría de Movilidad que los salvoconductos emitidos en Quito desde el 14 de septiembre hasta el 22 de diciembre quedan suspendidos hasta una nueva disposición del COE Nacional",
                "https://www.eluniverso.com/noticias/2020/12/23/nota/8716897/quito-salvoconductos-otorgados-municipio-quedan-suspendidos" ,medios.eluniverso));
        noticias.add(new Noticia("Cero salidas nocturnas por 15 días",
                "El Gobierno nacional decretó un nuevo estado de excepción que rige desde el 21 de diciembre del 2020 y que se extenderá durante 30 días. Esta es una de las nueve disposiciones anunciadas por el presidente",
                "https://www.ultimasnoticias.ec/las-ultimas/excepcion-ecuador-navidad-covid.html" ,medios.ultimasnoticias));
        noticias.add(new Noticia("Ecuador registra alta velocidad de transmisión del covid-19",
                "Las autoridades del Comité de Operaciones de Emergencia Nacional (COE) justificaron el estado de excepción por 30 días, debido al incremento exponencial de aglomeraciones en los cantones más poblados y por las reuniones de festividades.",
                        "https://www.elcomercio.com/actualidad/ecuador-covid-velocidad-transmision-contagio.html", medios.elcomercio));
        noticias.add(new Noticia("El covid-19 ha tenido siete mutaciones principales a la nueva variante se la ha denominado SARS-CoV-2 VUI 202012/01.",
                "El Centro Europeo para la Prevención y el Control de Enfermedades (ECDC) comenzó a examinar la nueva cepa del coronavirus de rápida propagación, que surgió en el Reino Unido, y sus implicaciones en la salud humana.",
                "https://www.eltelegrafo.com.ec/noticias/sociedad/6/covid-19-siete-mutaciones-principales" ,medios.eltelegrafo));




    }

    private void addInfo(){
        //infos.clear();
        infos.add(new Info("Organización Panamericana de la Salud",
                "https://www.paho.org/es/temas/coronavirus/brote-enfermedad-por-coronavirus-covid-19"));
        infos.add(new Info("Organización Mundial de la Salud",
                "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019"));
        infos.add(new Info("Ministerio de Salud Pública",
                "https://www.salud.gob.ec/coronavirus-covid-19/"));
    }
/*
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private final Context context;
        private int posi;

        public RecyclerAdapter(Context context1, List<String> valuesList) {
            super();//context1, R.layout.rest_list_item,valuesList
            this.context=context1;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final TextView nombre;

            ViewHolder(View v) {
                super(v);
                nombre =  v.findViewById(R.id.nombre);
            }

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_list_item, parent, false);

            return new ViewHolder(rowView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            this.posi=holder.getAdapterPosition();
            String tipoTxt;
            holder.nombre.setText(lista.get(posi));

            holder.nombre.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View view) {
                    // Acty.profileFragment.setActual();

                }
            });
        }


        @Override
        public int getItemCount() {
            return lista.size();
        }

        public void ordenar(){
            Collections.sort(lista, new Comparator<String>(){
                @Override
                public int compare(String x, String y) {
                    return (x.compareTo(y));
                }
            });
        }
    }
*/
}