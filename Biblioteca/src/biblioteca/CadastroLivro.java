package biblioteca;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class CadastroLivro {
	
	private static int codigo;
	private int tipo;
	private String titulo;
	private LocalDate dataReg;
	
	Scanner entrada = new Scanner(System.in);
	LocalDate data = LocalDate.now();
	
	public void setCodigo() throws IOException {
		@SuppressWarnings("resource")
		CSVReader ler = new CSVReader(new FileReader("files/livros.csv"));
		List<String[]> lista = ler.readAll();
		
		CadastroLivro.codigo = (lista.size() + 1000) + 1;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setDataReg(LocalDate data) {
		this.dataReg = data;
	}
	
	public int getCodigo() {
		return CadastroLivro.codigo;
	}
	
	public int getTipo() {
		return this.tipo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public LocalDate getDataReg() {
		return this.dataReg;
	}
	
	public void setInfo() {
		System.out.println("Titulo:");
		this.setTitulo(entrada.nextLine());
		this.setDataReg(data);
	}

}
