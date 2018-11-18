package com.example.vostro.ev3_curp.Adapter;

import android.content.Context;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vostro.ev3_curp.Entidades.Usuario;
import com.example.vostro.ev3_curp.R;
import java.util.ArrayList;

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.UsuarioViewHolder>
{
    ArrayList<Usuario> listaUsuario;
    Context context;

    public ListaUsuarioAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @NonNull
    @Override
    public ListaUsuarioAdapter.UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_usuario, parent, false);
        return new ListaUsuarioAdapter.UsuarioViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ListaUsuarioAdapter.UsuarioViewHolder holder, int position) {


        /**
         * Es aqui donde se obtiene el get de cada una de las variables para poder obtener el dato almacenado
         * **/

        Usuario usuario = listaUsuario.get(position);
        holder.names.setText(  usuario.getNames());
        holder.firstlastname.setText(usuario.getFirstlastname());
        holder.secondlastname.setText(usuario.getSecondlastname());
        holder.gender.setText(usuario.getGender());
        holder.datebirth.setText( usuario.getMonth() + "/" + usuario.getDay() + "/" + usuario.getYear());
        holder.federal_entity.setText(usuario.getFederal_entity());
        holder.curp.setText(usuario.realizacionCurp());
        holder.url.setImageURI(Uri.parse(usuario.getUrl()));


    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }



    /**
     * Es aqui donde se declaran cada una de las variables de TextView que se emplean en en el
     * list_item_usuario.xml, ya que aqui se declararan para arriba poderle asignar el valor.
     * **/
    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView names, firstlastname, secondlastname, gender, datebirth, federal_entity, curp ;
        ImageView url;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            names = (TextView) itemView.findViewById(R.id.txt_names);
            firstlastname = (TextView) itemView.findViewById(R.id.txt_firstLastName);
            secondlastname = (TextView) itemView.findViewById(R.id.txt_secondLastName);
            gender = (TextView) itemView.findViewById(R.id.txt_gender);
            datebirth = (TextView) itemView.findViewById(R.id.txt_datebirth);
            federal_entity = (TextView) itemView.findViewById(R.id.txt_entidad_federativa);
            curp = (TextView) itemView.findViewById(R.id.txt_curp);
            url = (ImageView) itemView.findViewById(R.id.image_view_user);
        }

    }


}
