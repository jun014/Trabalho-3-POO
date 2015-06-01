package biblioteca;

import java.util.Scanner;

class Aluno extends Usuario {
	protected String nUSP;
	protected String curso;
	
	Scanner entrada = new Scanner(System.in);
	
	public void setNUSP(String nUSP) {
		this.nUSP = nUSP;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String getNUSP() {
		return this.nUSP;
	}
	public String getCurso() {
		return this.curso;
	}
	
	@Override
	public void setInfo() {
		super.setInfo();
		System.out.println("Numero USP:");
		this.setNUSP(entrada.nextLine());
		System.out.println("Curso:");
		this.setCurso(entrada.nextLine());
	}

}
