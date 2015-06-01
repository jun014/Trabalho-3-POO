package biblioteca;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RegEmprestimo {
	protected static int codigo;
	protected int codUser;
	protected int tipo;
	protected String nome;
	protected String livro;
	protected LocalDate regData;
	
	LocalDate data = LocalDate.now();
	
	public void setCodigo() throws IOException {
		@SuppressWarnings("resource")
		CSVReader ler = new CSVReader(new FileReader("files/emprestimos.csv"));
		List<String[]> lista = ler.readAll();

		RegEmprestimo.codigo = lista.size() + 1;
	}
	
	public void setInfo(int codUser, int tipo, String nome, String livro) {
		this.codUser = codUser;
		this.tipo = tipo;
		this.nome = nome;
		this.livro = livro;
		this.regData = data;
	}
	
	public void registro() throws IOException {
		FileWriter csvArquivo = new FileWriter("files/emprestimos.csv", true);
		CSVWriter escrever = new CSVWriter(csvArquivo);
		
		String[] dados = (RegEmprestimo.codigo + "#"
							+ this.codUser + "#"
							+ this.tipo + "#"
							+ this.nome + "#"
							+ this.livro + "#"
							+ this.regData).split("#");
		escrever.writeNext(dados, false);
		escrever.close();
		System.out.println("Emprestimo registrado com sucesso.");
	}
	
}
