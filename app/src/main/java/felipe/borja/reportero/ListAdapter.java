package felipe.borja.reportero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static felipe.borja.reportero.R.drawable.ico_com;
import static felipe.borja.reportero.R.drawable.ico_tel;
import static felipe.borja.reportero.R.drawable.ico_ult;
import static felipe.borja.reportero.R.drawable.ico_uni;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private int posi;
    List<Noticia> lista=new ArrayList<>();
    List<String> infos=new ArrayList<>();

    public ListAdapter(Context context1, List<Noticia> valuesList) {
        super();//context1, R.layout.rest_list_item,valuesList
        this.context=context1;
        lista=valuesList;
        //infos.clear();
    }

    public ListAdapter(Context context, ArrayList<String> infos) {
        this.context=context;
        this.infos=infos;
        //lista.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView cabeza;
        final TextView cuerpo;
        final ImageView logo;

        ViewHolder(View v) {
            super(v);
            cabeza =  v.findViewById(R.id.nombre);
            cuerpo =  v.findViewById(R.id.descrip);
            logo =  v.findViewById(R.id.logo);
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
        if (lista.isEmpty()){
            holder.cabeza.setText(infos.get(posi));
            holder.cuerpo.setText(infos.get(posi));
        }else{
            holder.cabeza.setText(lista.get(posi).getCabeza());
            holder.cuerpo.setText(lista.get(posi).getCuerpo());
        }
        setLogoMedios(holder,lista.get(posi));
        holder.cabeza.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                // Acty.profileFragment.setActual();

            }
        });
    }

    private void setLogoMedios(ViewHolder holder,Noticia noticia){
        medios med=noticia.getMedio();
        switch (med){
            case elcomercio:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_com));break;
            case eluniverso:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_uni));break;
            case eltelegrafo:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_tel));break;
            case ultimasnoticias:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_ult));break;
            default:
        }
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void ordenar(){
        Collections.sort(lista, new Comparator<Noticia>(){
            @Override
            public int compare(Noticia x, Noticia y) {
                return (x.getCabeza().compareTo(y.getCabeza()));
            }
        });
    }
}
