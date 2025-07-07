package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.api.CadastroVeiculoAPI;
import com.example.vistomaisandroid.api.IOnCadastrarVeiculo;
import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.dto.VeiculoDTO;
import com.example.vistomaisandroid.repositorio.ProprietarioRepositorio;
import com.example.vistomaisandroid.repositorio.VeiculoRepositorio;
import com.example.vistomaisandroid.utils.Loader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CadastroVeiculoActivity extends AppCompatActivity {

    private int idVeiculoEditar = 0;
    private VeiculoDTO veiculoDTOEditar;
    private EditText edtMarca;
    private EditText edtModelo;
    private EditText edtAnoFabricacao;
    private EditText edtAnoModelo;
    private Button btnSalvar;
    private Set<String> camposObrigatorios = new HashSet<>();
    private VeiculoRepositorio veiculoRepositorio;
    private ProprietarioRepositorio proprietarioRepositorio;
    private Spinner spnCategoriaVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        // setar os campos obrigatórios
        this.camposObrigatorios.add("marca");
        this.camposObrigatorios.add("modelo");
        this.camposObrigatorios.add("ano_fabricacao");
        this.camposObrigatorios.add("ano_modelo");

        // mapear elementos de interface
        this.edtMarca = this.findViewById(R.id.edt_marca);
        this.edtModelo = this.findViewById(R.id.edt_modelo);
        this.edtAnoFabricacao = this.findViewById(R.id.edt_ano_fabricacao);
        this.edtAnoModelo = this.findViewById(R.id.edt_ano_modelo);
        this.btnSalvar = this.findViewById(R.id.btn_salvar_veiculo);
        this.spnCategoriaVeiculo = this.findViewById(R.id.spn_categoria_veiculo);

        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // salvar veiculo que seria cadastrar ou editar o mesmo
                salvarVeiculo();
            }
        });

        if (this.getIntent().getIntExtra("veiculo_editar_id", 0) != 0) {
            this.idVeiculoEditar = this.getIntent().getIntExtra("veiculo_editar_id", 0);
        }

        if (this.idVeiculoEditar != 0) {
            this.btnSalvar.setText("Salvar");
        } else {
            this.btnSalvar.setText("Cadastrar");
        }

        TextView txtTituloMenuTopoTelaCadastroVeiculo = this.findViewById(R.id.txt_menu_topo_titulo);
        ImageButton btnRetornar = this.findViewById(R.id.btn_back_menu_topo);

        txtTituloMenuTopoTelaCadastroVeiculo.setText("Cadastro de Veiculo");
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            this.veiculoRepositorio = new VeiculoRepositorio(this);
            this.proprietarioRepositorio = new ProprietarioRepositorio(this);
        } catch (Exception e) {
            // apresentar alerta de erro
        }

        // configurar os spinners
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.buscarCategorias();

    }

    // buscar no servidor categorias de veiculos
    private void buscarCategorias() {

        try {
            Loader.apresentar("Consultando as categorias de veiculo, aguarde...", this);

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // consultar as categorias na base de dados

                    Loader.fechar();

                    buscarProprietarios();
                }
            }, 3000);
        } catch (Exception e) {
            Loader.fechar();
            // apresentar alerta de erro
        }

    }

    // buscar no servidor proprietarios para o veiculo
    private void buscarProprietarios() {

        try {
            Loader.apresentar("Consultando os proprietários, aguarde...", this);

            new Handler()
                    .postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // buscar proprietários na base local do app
                            List<ProprietarioDTO> proprietarios = proprietarioRepositorio.listar();

                            Loader.fechar();

                            if (proprietarios.size() > 0) {

                            } else {
                                // apresentar alerta que deve ter proprietário na base para prosseguir com cadastro/edição
                            }

                        }
                    }, 3000);
        } catch (Exception e) {
            Loader.fechar();
        }

    }

    // buscar veiculo pelo id para poder fazer edição do mesmo
    private void buscarVeiculoPeloId() {

        try {

        } catch (Exception e) {

        }

    }

    // apresentar alerta de erro para o usuário
    private void apresentarAlertaErro(String mensagemErro) {
        AlertDialog.Builder builderAlertDialogErro = new AlertDialog.Builder(this);

        builderAlertDialogErro.setTitle("Atenção!");
        builderAlertDialogErro.setMessage(mensagemErro);

        builderAlertDialogErro.setPositiveButton("OK", null);

        builderAlertDialogErro.create().show();
    }

    private Boolean validarCamposVeiculo() {
        Boolean ok = true;

        String marca = this.edtMarca.getText().toString();
        String modelo = this.edtModelo.getText().toString();
        String anoFabricacao = this.edtAnoFabricacao.getText().toString();
        String anoModelo = this.edtAnoModelo.getText().toString();

        if (marca.isBlank() && this.camposObrigatorios.contains("marca")) {
            ok = false;
            this.apresentarAlertaErro("Informe a marca do veículo.");
        } else if (modelo.isBlank() && this.camposObrigatorios.contains("modelo")) {
            ok = false;
            this.apresentarAlertaErro("Informe o modelo do veículo");
        } else if (anoFabricacao.isBlank() && this.camposObrigatorios.contains("ano_fabricacao")) {
            ok = false;
            this.apresentarAlertaErro("Informe o ano de fabricação.");
        } else if (Integer.parseInt(anoFabricacao) <= 0) {
            ok = false;
            this.apresentarAlertaErro("Ano de fabricação inválido.");
        } else if (anoModelo.isBlank() && this.camposObrigatorios.contains("ano_modelo")) {
            ok = false;
            this.apresentarAlertaErro("Informe o ano de modelo.");
        } else if (Integer.parseInt(anoModelo) <= 0) {
            ok = false;
            this.apresentarAlertaErro("Ano de modelo inválido.");
        }

        return ok;
    }

    // cadastrar veiculo
    private void cadastrarVeiculo() {
        VeiculoDTO veiculoDTOCadastrar = new VeiculoDTO();

        veiculoDTOCadastrar.setModelo(this.edtModelo.getText().toString());
        veiculoDTOCadastrar.setMarca(this.edtMarca.getText().toString());
        veiculoDTOCadastrar.setAnoFabricacao(Integer.parseInt(this.edtAnoFabricacao.getText().toString()));
        veiculoDTOCadastrar.setAnoModelo(Integer.parseInt(this.edtAnoModelo.getText().toString()));
        veiculoDTOCadastrar.setPlaca("");
        veiculoDTOCadastrar.setNumeroChassi("");
        veiculoDTOCadastrar.setRenavam("");
        veiculoDTOCadastrar.setCor("");

        /*CadastroVeiculoAPI.cadastrarVeiculo(veiculoDTOCadastrar, new IOnCadastrarVeiculo() {
            @Override
            public void onRealizandoRequisicao() {
                // realizando a requisição para o servidor, apresentar loader para o usuário
            }

            @Override
            public void onSucesso(String mensagemSucesso) {
                // cadastro efetivado com sucesso no servidor, apresentar alerta de sucesso
            }

            @Override
            public void onErro(String mensagemErro) {
                // erro na hora de realizar o cadastro no servidor, apresentar alerta de erro
            }
        });*/
    }

    // editar veiculo
    private void editarVeiculo() {

    }

    // salvar veiculo
    private void salvarVeiculo() {

        try {

            if (!this.validarCamposVeiculo()) {

                return;
            }

            if (this.veiculoDTOEditar == null) {
                // cadastrar veiculo novo
                this.cadastrarVeiculo();
            } else {
                // editar veiculo já existente
                this.editarVeiculo();
            }

        } catch (Exception e) {
            this.apresentarAlertaErro("Erro ao tentar-se salvar o veiculo: " + e.getMessage());

            Log.e("erro_salvar_veiculo", e.getMessage());
        }

    }

    // controlar evento de quando o usuário clicar no botão de retornar no menu do topo
    @Override
    public void onBackPressed() {
        this.retornar();
    }

    private void retornar() {
        startActivity(new Intent(this, VeiculosActivity.class));
        finish();
    }

}