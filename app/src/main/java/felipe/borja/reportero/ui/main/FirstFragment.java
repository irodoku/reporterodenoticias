package felipe.borja.reportero.ui.main;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import felipe.borja.reportero.MainActivity;
import felipe.borja.reportero.R;
import felipe.borja.reportero.databinding.FragmentMainBinding;

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    RecyclerView recyclerView;
    ArrayList<String> noticias;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
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
        noticias  = new ArrayList<>();
        noticias.add("asdasdasd");
        noticias.add("zxczxczxc");
        noticias.add("qweqweqwe");
        noticias.add("qweasdzxc");
        RecyclerAdapter adapter = new RecyclerAdapter(view.getContext(),noticias );
        recyclerView.setAdapter(adapter);

        adapter.ordenar();
        return view;
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
            holder.nombre.setText(noticias.get(posi));

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
            return noticias.size();
        }

        public void ordenar(){
            Collections.sort(noticias, new Comparator<String>(){
                @Override
                public int compare(String x, String y) {
                    return (x.compareTo(y));
                }
            });
        }
    }

}