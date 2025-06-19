package com.example.vistomaisandroid.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.repositorio.ProprietarioRepositorio;
import com.example.vistomaisandroid.utils.IValida;
import com.example.vistomaisandroid.utils.ValidaCpf;
import com.example.vistomaisandroid.utils.ValidaEmail;
import com.example.vistomaisandroid.utils.ValidaRg;

public class CadastroProprietarioActivity extends AppCompatActivity implements View.OnClickListener {

    private int proprietarioIdEditar = 0;
    private ProprietarioRepositorio proprietarioRepositorio;
    private EditText edtNomeCompleto;
    private EditText edtCpf;
    private EditText edtRg;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtDataNascimento;
    private EditText edtNumeroCnh;
    private EditText edtCep;
    private Button btnSalvarProprietario;
    private IValida validadorCpf = new ValidaCpf();
    private IValida validadorEmail = new ValidaEmail();
    private IValida validadorRg = new ValidaRg();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_proprietario);

        // mapear elementos de interface
        this.edtNomeCompleto = this.findViewById(R.id.edt_nome_completo);
        this.edtCpf = this.findViewById(R.id.edt_cpf);
        this.edtRg = this.findViewById(R.id.edt_rg);
        this.edtTelefone = this.findViewById(R.id.edt_telefone);
        this.edtEmail = this.findViewById(R.id.edt_email);
        this.edtDataNascimento = this.findViewById(R.id.edt_data_nascimento);
        this.edtNumeroCnh = this.findViewById(R.id.edt_numero_cnh);
        this.btnSalvarProprietario = this.findViewById(R.id.btn_salvar_proprietario);
        this.edtCep = this.findViewById(R.id.edt_cep);

        this.btnSalvarProprietario.setOnClickListener(this);

        TextView txtTituloTelaCadastroProprietario = this.findViewById(R.id.txt_menu_topo_titulo);

        txtTituloTelaCadastroProprietario.setText("Cadastro de Proprietário");

        ImageButton btnRetornar = this.findViewById(R.id.btn_back_menu_topo);

        btnRetornar.setOnClickListener(this);

        try {
            this.proprietarioRepositorio = new ProprietarioRepositorio();
        } catch (Exception e) {
            // apresentar alerta de erro ao usuário informando que ocorreu um erro na conexão com a base local do app
        }

        if (this.getIntent() != null && this.getIntent().getIntExtra("proprietario_id_editar", 0) != 0) {
            this.proprietarioIdEditar = this.getIntent().getIntExtra("proprietario_id_editar", 0);

            // buscar proprietário pelo id
            this.buscarProprietarioPeloId();
        }

        this.edtCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String cep = s.toString();

                if (cep.length() == 8) {
                    // o usuário terminou de digitar os 8 caracteres do cep
                    buscarEnderecoPeloCep(cep);
                }

            }
        });
    }

    // buscar o proprietário pelo id e apresentar os dados dele nos campos
    private void buscarProprietarioPeloId() {

    }

    // cadastrar proprietario
    private void cadastrarProprietario() {
        Log.d("cadastro_proprietario", "Cadastrando proprietário...");

        ProprietarioDTO proprietarioDTO = new ProprietarioDTO();

        proprietarioDTO.setNomeCompleto(this.edtNomeCompleto.getText().toString().trim());
        proprietarioDTO.setCpf(this.edtCpf.getText().toString().trim());
        proprietarioDTO.setRg(this.edtRg.getText().toString().trim());
        proprietarioDTO.setTelefone(this.edtTelefone.getText().toString().trim());
        proprietarioDTO.setEmail(this.edtEmail.getText().toString().trim());
        proprietarioDTO.setDataNascimento(this.edtDataNascimento.getText().toString().trim());
        proprietarioDTO.setNumeroCnh(this.edtNumeroCnh.getText().toString().trim());

        // apresentar dados do proprietário que estou tentando cadastrar na base de dados
        Log.d("cadastro_proprietario", proprietarioDTO.toString());

        // cadastrar o proprietário na base local do app
        this.proprietarioRepositorio.cadastrar(proprietarioDTO);
    }

    // editar proprietario
    private void editarProprietario() {

    }

    // buscar endereço do proprietário pelo cep
    private void buscarEnderecoPeloCep(String cep) {
        Log.d("get_endereco", "Consultando endereço do proprietário pelo cep: " + cep);
    }

    private void apresentarAlertaErro(String mensagemErro) {
        AlertDialog.Builder builderAlertDialogErroProprietario = new AlertDialog.Builder(this);

        builderAlertDialogErroProprietario.setTitle("Atenção!");
        builderAlertDialogErroProprietario.setMessage(mensagemErro);

        builderAlertDialogErroProprietario.setPositiveButton("OK", null);
        builderAlertDialogErroProprietario.create().show();
    }

    // valdar campos ao tentar-se salvar o proprietário
    private Boolean validarCamposSalvarProprietario() {
        boolean ok = true;

        String nomeCompleto = this.edtNomeCompleto.getText().toString().trim();
        String cpf = this.edtCpf.getText().toString().trim();
        String rg = this.edtRg.getText().toString().trim();
        String email = this.edtEmail.getText().toString().trim();
        String telefone = this.edtTelefone.getText().toString().trim();
        String dataNascimento = this.edtDataNascimento.getText().toString().trim();
        String numeroCnh = this.edtNumeroCnh.getText().toString().trim();

        if (nomeCompleto.isBlank()) {
            ok = false;
            this.apresentarAlertaErro("Informe o nome do proprietário.");
        } else if (cpf.isBlank()) {
            ok = false;
            this.apresentarAlertaErro("Informe o cpf do proprietário.");
        } else if (!this.validadorCpf.validar(cpf)) {
            ok = false;
            this.apresentarAlertaErro("O cpf informado é inválido.");
        } else if (rg.isBlank()) {
            ok = false;
            this.apresentarAlertaErro("Informe o rg do proprietário.");
        } else if (!this.validadorRg.validar(rg)) {
            ok = false;
            this.apresentarAlertaErro("O rg informado é inválido.");
        } else if (email.isBlank()) {
            ok = false;
            this.apresentarAlertaErro("Informe o e-mail.");
        } else if (!this.validadorEmail.validar(email)) {
            ok = false;
            this.apresentarAlertaErro("O e-mail informado é inválido.");
        } else if (telefone.isBlank()) {
            ok = false;
            this.apresentarAlertaErro("Informe o telefone.");
        } else if (dataNascimento.isBlank()) {
            ok = false;
            this.apresentarAlertaErro("Informe a data de nascimento.");
        } else if (numeroCnh.isBlank()) {
            ok = false;
            this.apresentarAlertaErro("Informe o número da cnh.");
        }

        return ok;
    }

    // salvar proprietário
    private void salvarProprietario() {

        try {

            // validar campos no cadastro/edição de proprietários
            if (!this.validarCamposSalvarProprietario()) {

                return;
            }

            if (this.proprietarioIdEditar == 0) {
                // cadastrar proprietário
                this.cadastrarProprietario();
            } else {
                this.editarProprietario();
                // editar proprietário
            }

        } catch (Exception e) {
            this.apresentarAlertaErro("Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_salvar_proprietario) {
            // salvar proprietário
            this.salvarProprietario();
        } else if (v.getId() == R.id.btn_back_menu_topo) {
            // clicou no botão de retornar
            this.onBackPressed();
        }

    }

    private void retornar() {

    }

    // controlar evento de retornar para a listagem de proprietários
    @Override
    public void onBackPressed() {
        this.retornar();
    }

}