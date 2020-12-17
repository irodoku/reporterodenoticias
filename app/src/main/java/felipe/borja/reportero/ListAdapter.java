package felipe.borja.reportero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private int posi;
    List<String> lista=new ArrayList<String>();

    public ListAdapter(Context context1, List<String> valuesList) {
        super();//context1, R.layout.rest_list_item,valuesList
        this.context=context1;
        lista=valuesList;
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
