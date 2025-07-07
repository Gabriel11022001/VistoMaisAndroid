package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vistomaisandroid.MainActivity;
import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.adapter.ProprietarioAdapter;
import com.example.vistomaisandroid.api.IOnConsultando;
import com.example.vistomaisandroid.api.ListagemProprietariosAPI;
import com.example.vistomaisandroid.dto.EnderecoProprietarioDTO;
import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.model.Proprietario;
import com.example.vistomaisandroid.repositorio.ProprietarioRepositorio;
import com.example.vistomaisandroid.utils.FiltroProprietarios;
import com.example.vistomaisandroid.utils.ValidaEstaOnline;
import com.example.vistomaisandroid.viewholder.IOnVisualizar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProprietariosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListagemProprietarios;
    private ProprietarioRepositorio proprietarioRepositorio;
    private List<ProprietarioDTO> proprietarios = new ArrayList<>();
    private ProprietarioAdapter proprietarioAdapter;
    private TextView txtNaoExistemProprietarios;
    private LinearLayout loader;
    private TextView txtMensagemLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proprietarios);

        // mapear elementos de interface
        this.recyclerViewListagemProprietarios = this.findViewById(R.id.recycler_listagem_proprietarios);
        this.txtNaoExistemProprietarios = this.findViewById(R.id.txt_mensagem_nao_possui_proprietarios);
        this.loader = this.findViewById(R.id.loader_carregando_proprietarios);
        this.txtMensagemLoader = this.findViewById(R.id.txt_mensagem_loader);
        FloatingActionButton btnRedirecionarTelaCadastroNovoProprietario = this.findViewById(R.id.btn_cadastrar_novo_proprietario);
        FloatingActionButton btnFiltrarProprietarios = this.findViewById(R.id.btn_filtro_proprietarios);

        // redirecionar o usuáiro para a tela de cadastro de proprietário
        btnRedirecionarTelaCadastroNovoProprietario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroProprietarioActivity.class));
                finish();
            }
        });

        // redirecionar usuário para a tela de filtrar proprietários
        btnFiltrarProprietarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FiltroProprietariosActivity.class));
                finish();
            }
        });

        // configurar a recyclerview com listagem dos proprietários
        this.configurarRecyclerView();

        TextView txtTituloListagemProprietarios = this.findViewById(R.id.txt_menu_topo_titulo);
        ImageButton btnRetornar = this.findViewById(R.id.btn_back_menu_topo);

        txtTituloListagemProprietarios.setText("Proprietários");

        // mapear evento de clique no botão de retornar
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        this.recyclerViewListagemProprietarios.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewListagemProprietarios.setAdapter(this.proprietarioAdapter);

        try {
            this.proprietarioRepositorio = new ProprietarioRepositorio(this);
        } catch (Exception e) {
            // apresentar alerta de erro para o usuário informando que houve erro na hora de conectar na base de dados
        }

    }

    // configurar a recyckler
    private void configurarRecyclerView() {

        IOnVisualizar iOnVisualizarProprietario = new IOnVisualizar() {
            @Override
            public void visualizar(int idEntidadeVisualizar) {
                // configurar visualização do proprietário
                int idProprietario = 0;

                if (ValidaEstaOnline.isOnline(getApplicationContext())) {
                    // passar o proprietario_id_servidor
                    // idProprietario = proprietarioRepositorio.buscarIdProprietarioServidor(idEntidadeVisualizar);
                    idProprietario = idEntidadeVisualizar;
                } else {
                    // passar o id_proprietario que é o id na base local do app
                    idProprietario = idEntidadeVisualizar;
                }

                Intent intentvisualizarProprietario = new Intent(getApplicationContext(), CadastroProprietarioActivity.class);

                intentvisualizarProprietario.putExtra("proprietario_id_editar", idProprietario);

                startActivity(intentvisualizarProprietario);
                finish();
            }
        };

        this.proprietarioAdapter = new ProprietarioAdapter(iOnVisualizarProprietario);
        this.proprietarioAdapter.setProprietarios(new ArrayList<>());
    }

    // apresentar alerta de erro para o usuário
    private void apresentarAlertaErro(String mensagemErro) {
        Log.d("erro_listar_proprietarios", mensagemErro);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {

            if (this.getIntent() != null && this.getIntent().getParcelableExtra("filtro_proprietarios") != null) {
                // filtrar proprietáiros na base local do app
                this.filtrar(this.getIntent().getParcelableExtra("filtro_proprietarios"));

                return;
            }

            // listar proprietários
            this.listarProprietarios();
        } catch (Exception e) {
            Log.e("erro_listar_proprietarios", e.getMessage());
        }

    }

    // filtrar proprietários na base local do app
    private void filtrar(FiltroProprietarios filtroProprietarios) {
        this.txtNaoExistemProprietarios.setVisibility(View.GONE);
        this.recyclerViewListagemProprietarios.setVisibility(View.GONE);

        try {
            List<ProprietarioDTO> proprietarios = filtroProprietarios.filtrar();

            if (proprietarios.size() == 0) {
                this.txtNaoExistemProprietarios.setVisibility(View.VISIBLE);
            } else {
                this.recyclerViewListagemProprietarios.setVisibility(View.VISIBLE);
            }

            this.proprietarioAdapter.setProprietarios(proprietarios);
        } catch (Exception e) {
            this.apresentarAlertaErro("Erro ao tentar-se filtrar os proprietários,");
        }

    }

    /**
     * listar proprietários do servidor, caso o usuário
     * esteja offline ou não retorne proprietários do servidor,
     * tentar consultar na base local do aplicativo
     */
    private void listarProprietarios() {

        // configurar o listener da requisição
        IOnConsultando<Proprietario> iOnConsultandoProprietariosServidor = new IOnConsultando<>() {
            @Override
            public void onRealizandoRequisicao() {
                txtNaoExistemProprietarios.setVisibility(View.GONE);
                recyclerViewListagemProprietarios.setVisibility(View.GONE);

                apresentarLoaderCarregamento("Consultando os proprietários, aguarde...");
            }

            @Override
            public void onSucessoRequisicao(List<Proprietario> retornoListagem) {
                esconderLoader();

                if (retornoListagem.size() == 0) {
                    // consultar na base local do app

                    List<ProprietarioDTO> proprietariosBaseLocal = proprietarioRepositorio.listar();

                    if (proprietariosBaseLocal.size() == 0) {
                        txtNaoExistemProprietarios.setVisibility(View.VISIBLE);
                        recyclerViewListagemProprietarios.setVisibility(View.GONE);
                    } else {
                        txtNaoExistemProprietarios.setVisibility(View.GONE);
                        recyclerViewListagemProprietarios.setVisibility(View.VISIBLE);

                        // setar proprietários por meio do adapter
                        proprietarioAdapter.setProprietarios(proprietariosBaseLocal);

                        Log.d("listagem_proprietarios", "Trouxe um total de " + proprietarios.size() + " proprietários.");
                    }

                } else {
                    txtNaoExistemProprietarios.setVisibility(View.GONE);
                    recyclerViewListagemProprietarios.setVisibility(View.VISIBLE);

                    for (Proprietario proprietario : retornoListagem) {
                        // atualizar os dados dos proprietários na base local do app
                        ProprietarioDTO proprietarioDTO = new ProprietarioDTO();

                        proprietarioDTO.setNomeCompleto(proprietario.getNomeCompleto());
                        proprietarioDTO.setCpf(proprietario.getCpf());
                        proprietarioDTO.setRg(proprietario.getRg());
                        proprietarioDTO.setEmail(proprietario.getEmail());
                        proprietarioDTO.setTelefone(proprietario.getTelefone());
                        proprietarioDTO.setDataNascimento(proprietario.getDataNascimento());
                        proprietarioDTO.setNumeroCnh(proprietario.getNumeroCnh());

                        proprietarioDTO.setEnderecoProprietarioDTO(
                                new EnderecoProprietarioDTO(
                                        0,
                                        proprietario.getCep(),
                                        proprietario.getComplemento(),
                                        proprietario.getLogradouro(),
                                        proprietario.getBairro(),
                                        proprietario.getCidade(),
                                        proprietario.getEstado(),
                                        proprietario.getNumero()
                                )
                        );

                        proprietarioRepositorio.editarProprietarioVindoServidor(proprietario.getCpf(), proprietarioDTO);
                    }

                    // buscar os proprietários da base local do app
                    proprietarioAdapter.setProprietarios(proprietarioRepositorio.listar());
                }

            }

            @Override
            public void onErroRequisicao(String mensagemErro) {
                esconderLoader();

                txtNaoExistemProprietarios.setVisibility(View.GONE);
                recyclerViewListagemProprietarios.setVisibility(View.GONE);

                // apresentar mensagem de erro para o usuário e redirecionar ele para a tela home do app
                apresentarAlertaErro(mensagemErro);
            }
        };

        ListagemProprietariosAPI listagemProprietariosAPI = new ListagemProprietariosAPI();

        // consultar os proprietários no servidor
        listagemProprietariosAPI.listarProprietarios(iOnConsultandoProprietariosServidor);
    }

    // apresentar o loader de carregamento dos proprietários
    private void apresentarLoaderCarregamento(String mensagem) {
        this.loader.setVisibility(View.VISIBLE);
        this.txtMensagemLoader.setText(mensagem);
    }

    // esconder o loader de carregamento dos proprietários
    private void esconderLoader() {
        this.loader.setVisibility(View.GONE);
    }

    private void retornar() {
        // retornar para a tela home
        Intent intentTelaHome = new Intent(this, MainActivity.class);
        startActivity(intentTelaHome);
        finish();
    }

    // controlar evento de retornar para a tela home
    @Override
    public void onBackPressed() {
        this.retornar();
    }

}