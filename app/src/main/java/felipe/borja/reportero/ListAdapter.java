package felipe.borja.reportero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static felipe.borja.reportero.R.drawable.ico_com;
import static felipe.borja.reportero.R.drawable.ico_hor;
import static felipe.borja.reportero.R.drawable.ico_mer;
import static felipe.borja.reportero.R.drawable.ico_pri;
import static felipe.borja.reportero.R.drawable.ico_rep;
import static felipe.borja.reportero.R.drawable.ico_tel;
import static felipe.borja.reportero.R.drawable.ico_ult;
import static felipe.borja.reportero.R.drawable.ico_uni;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private int posi;
    List<Noticia> lista=new ArrayList<>();
    List<Info> infos=new ArrayList<>();


    public ListAdapter(Context context1, List<Noticia> valuesList) {
        super();//context1, R.layout.rest_list_item,valuesList
        this.context=context1;
        lista=valuesList;
        //infos.clear();
        Log.e("ListAdapter","notic");
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView cabeza;
        final TextView cuerpo;
        final ImageView logo;
        final RelativeLayout contenedor;
        ViewHolder(View v) {
            super(v);
            cabeza =  v.findViewById(R.id.nombre);
            cuerpo =  v.findViewById(R.id.descrip);
            logo =  v.findViewById(R.id.logo);
            contenedor =  v.findViewById(R.id.item_container);

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
        String urlTemp;

        holder.cabeza.setText(lista.get(posi).getCabeza());
        holder.cuerpo.setText(lista.get(posi).getCuerpo());
        urlTemp = lista.get(posi).getLink();
        setLogoMedios(holder,lista.get(posi));
        Log.e("onBindViewHolder","esNoticia");

        final String url = urlTemp;
        holder.contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLink(url);
            }
        });


    }

    private void setLogoMedios(ViewHolder holder,Noticia noticia){
        medios med=noticia.getMedio();
        switch (med){
            case elcomercio:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_com));break;
            case lahora:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_hor));break;
            case eluniverso:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_uni));break;
            case eltelegrafo:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_tel));break;
            case mercurio:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_mer));break;
            case primicias:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_pri));break;
            case ultimasnoticias:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_ult));break;
            case larepublica:holder.logo.setImageDrawable( context.getResources().getDrawable(ico_rep));break;

            default:
        }
    }

    public void abrirLink(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
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
