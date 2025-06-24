package com.example.vistomaisandroid.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vistomaisandroid.R;

public class ProprietarioViewHolder extends RecyclerView.ViewHolder {

    public TextView txtNomeCompletoProprietario;
    public TextView txtCpfProprietario;
    public TextView txtTelefoneProprietario;
    public TextView txtEmailProprietario;

    public ProprietarioViewHolder(@NonNull View itemView) {
        super(itemView);

        // mapear elementos de interface do item da listagem de proprietarios
        this.txtNomeCompletoProprietario = itemView.findViewById(R.id.txt_nome_completo_proprietario);
        this.txtCpfProprietario = itemView.findViewById(R.id.txt_cpf_proprietario);
        this.txtEmailProprietario = itemView.findViewById(R.id.txt_email_proprietario);
        this.txtTelefoneProprietario = itemView.findViewById(R.id.txt_telefone_proprietario);
    }

}
