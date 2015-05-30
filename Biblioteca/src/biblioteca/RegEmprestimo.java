package biblioteca;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.opencsv.CSVWriter;

public class RegEmprestimo {
	private int codigo;
	private int tipo;
	private String nome;
	private String livro;
	private LocalDate regData;
	
	LocalDate data = LocalDate.now();
	
	public void setInfo(int codigo, int tipo, String nome, String livro) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.nome = nome;
		this.livro = livro;
		this.regData = data;
	}
	
	public void registro() throws IOException {
		FileWriter csvArquivo = new FileWriter("files/emprestimos.csv", true);
		CSVWriter escrever = new CSVWriter(csvArquivo);
		
		String[] dados = (this.codigo + "#"
							+ this.tipo + "#"
							+ this.nome + "#"
							+ this.livro + "#"
							+ this.regData).split("#");
		escrever.writeNext(dados, false);
		escrever.close();
		System.out.println("Emprestimo registrado com sucesso.");
	}
	
}
