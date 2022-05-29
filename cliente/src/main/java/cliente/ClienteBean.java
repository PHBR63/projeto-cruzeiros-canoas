package cliente;

import javax.validation.constraints.NotNull;

public class ClienteBean {
	
	@NotNull (message = "Informe o nome do Cliente.")
	private String nome;
	
	@NotNull (message = "Informe o CPF do Cliente.")
	private String cpf;
	
	@NotNull (message = "Informe o telefone (apenas números) do Cliente.")
	private int telefone;

	public ClienteBean() {
		super();
	}
	
	public ClienteBean(String nome, String cpf, int telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public String getcpf() {
		return cpf;
	}

	public void setcpf(String cpf) {
		this.cpf = cpf;
	}

	public int gettelefone() {
		return telefone;
	}

	public void settelefone(int telefone) {
		this.telefone = telefone;
	}
	
}
