package biblioteca;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

class Usuario {
	protected static int codigo;
	protected int regCodigo;
	protected int tipo;
	protected String nome;
	protected String CPF;
	protected String endereco;
	protected String telefone;
	
	Scanner entrada = new Scanner(System.in);
		
	public void setCodigo() throws IOException {
		@SuppressWarnings("resource")
		CSVReader ler = new CSVReader(new FileReader("files/usuarios.csv"));
		List<String[]> lista = ler.readAll();

		Usuario.codigo = lista.size() + 1;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCPF(String cpf) {
		this.CPF = cpf;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getCodigo() {
		return Usuario.codigo;
	}

	public int getTipo() {
		return this.tipo;
	}
	
	public String getNome() {
		return this.nome;
	}
	public String getCPF() {
		return this.CPF;
	}
	public String getEndereco() {
		return this.endereco;
	}
	public String getTelefone() {
		return this.telefone;
	}
	
	public void setInfo() {
		System.out.println("Nome:");
		this.setNome(entrada.nextLine());
		System.out.println("CPF:");
		this.setCPF(entrada.nextLine());
		System.out.println("Endereco:");
		this.setEndereco(entrada.nextLine());
		System.out.println("Telefone:");
		this.setTelefone(entrada.nextLine());
	}
	
	public void setInfoSel(int codigo, int tipo, String nome) {
		this.regCodigo = codigo;
		this.tipo = tipo;
		this.nome = nome;
	}
	
}
