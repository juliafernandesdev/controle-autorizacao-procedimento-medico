package model;

public class RegraAutorizacao {
	
	private int id;
    private int procedimento;
    private int idade;
    private String sexo;
    private boolean permitido;
	
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(int procedimento) {
		this.procedimento = procedimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public boolean isPermitido() {
		return permitido;
	}
	public void setPermitido(boolean permitido) {
		this.permitido = permitido;
	}
	
    

}
