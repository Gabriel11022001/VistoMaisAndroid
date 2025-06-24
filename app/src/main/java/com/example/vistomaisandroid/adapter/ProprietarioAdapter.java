package com.example.vistomaisandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.viewholder.IOnVisualizar;
import com.example.vistomaisandroid.viewholder.ProprietarioViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioAdapter extends RecyclerView.Adapter<ProprietarioViewHolder> {

    private List<ProprietarioDTO> proprietarios = new ArrayList<>();
    private IOnVisualizar iOnVisualizarProprietario;

    public ProprietarioAdapter(IOnVisualizar iOnVisualizarProprietario) {
        this.iOnVisualizarProprietario = iOnVisualizarProprietario;
    }

    @NonNull
    @Override
    public ProprietarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.proprietario_adapter, parent, false);

        return new ProprietarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProprietarioViewHolder holder, int position) {
        ProprietarioDTO proprietarioDTO = this.proprietarios.get(position);

        holder.txtNomeCompletoProprietario.setText(proprietarioDTO.getNomeCompleto().toUpperCase());
        holder.txtTelefoneProprietario.setText(proprietarioDTO.getTelefone());
        holder.txtEmailProprietario.setText(proprietarioDTO.getEmail());
        holder.txtCpfProprietario.setText(proprietarioDTO.getCpf());

        // configurar evento de visualizar o proprietário quando o usuário clicar sobre o item da lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnVisualizarProprietario.visualizar(proprietarioDTO.getProprietarioId());
            }
        });
    }

    @Override
    public int getItemCount() {

        return this.proprietarios.size();
    }

    // atualizar os dados da recyclerview com a listagem dos proprietários
    public void setProprietarios(List<ProprietarioDTO> proprietarios) {
        this.proprietarios = proprietarios;

        super.notifyDataSetChanged();
    }

}
