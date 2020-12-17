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

import felipe.borja.reportero.ListAdapter;
import felipe.borja.reportero.R;

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int pagina;
    RecyclerView recyclerView;
    private ArrayList<String> noticias;
    private ArrayList<String> infos;
    private PageViewModel pageViewModel;
    public ArrayList<String> lista;

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
        pagina = getArguments().getInt("pagina", 0);
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
        lista  = new ArrayList<>();
        switch (pagina) {
            case 0:adapter = new ListAdapter(view.getContext(),noticias );break;
            case 1:adapter = new ListAdapter(view.getContext(),infos);break;
            default: throw new IllegalStateException("Index incorrecto: " + pagina);
        }
        Log.e("onCreateView","p"+pagina);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //adapter.ordenar();
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //adapter.ordenar();
                lista.add("asdfghjkl");
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void addNoticias(){
        //noticias.clear();
        noticias.add("asdasdasd");
        noticias.add("zxczxczxc");
        noticias.add("asdasdasd");
        noticias.add("zxczxczxc");

    }

    private void addInfo(){
        //infos.clear();
        infos.add("qweqweqwe");
        infos.add("qweasdzxc");
        infos.add("qweqweqwe");
        infos.add("qweasdzxc");
    }

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

}