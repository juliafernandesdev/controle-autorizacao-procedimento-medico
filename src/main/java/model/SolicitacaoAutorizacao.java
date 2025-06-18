package model;

import java.time.LocalDateTime;

/**
 * Representa uma solicitação de autorização de procedimento médico feita por um usuário.
 * Contém os dados da requisição e o resultado da autorização.
 */
public class SolicitacaoAutorizacao {
	
	private int id;
    private int procedimento;
    private int idade;
    private String sexo;
    private boolean autorizado;
    private String motivoNegacao;
    private LocalDateTime data;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProcedimento() { return procedimento; }
    public void setProcedimento(int procedimento) { this.procedimento = procedimento; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public boolean isAutorizado() { return autorizado; }
    public void setAutorizado(boolean autorizado) { this.autorizado = autorizado; }

    public String getMotivoNegacao() { return motivoNegacao; }
    public void setMotivoNegacao(String motivoNegacao) { this.motivoNegacao = motivoNegacao; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

}
