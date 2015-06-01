package biblioteca;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

class RegDevolucao extends RegEmprestimo{
	
	private int codigo;
	
	public void setInfo(int codigo, int codUser, int tipo, String nome, String livro) {
		this.codigo = codigo;
		super.setInfo(codUser, tipo, nome, livro);
	}
	
	public void registro() throws IOException {
		FileWriter csvArquivo = new FileWriter("files/devolucao.csv", true);
		CSVWriter escrever = new CSVWriter(csvArquivo);
		
		String[] dados = (this.codigo + "#"
							+ this.codUser + "#"
							+ this.tipo + "#"
							+ this.nome + "#"
							+ this.livro + "#"
							+ this.regData).split("#");
		escrever.writeNext(dados, false);
		escrever.close();
		System.out.println("Devolucao registrada com sucesso.");
	}
	
}
