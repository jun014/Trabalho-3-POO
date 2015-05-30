package biblioteca;

import java.util.Scanner;

class Professor extends Usuario {
	protected String nUSP;
	protected String instituto;
	
	Scanner entrada = new Scanner(System.in);
	
	public void setNUSP(String nUSP) {
		this.nUSP = nUSP;
	}
	
	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}
	
	@Override
	public void setEmprestimos() {
		if(this.emprestimos < 6) {
			this.emprestimos += 1;
			System.out.println("Emprestimo registrado.");
		} else {
			System.out.println("Limite de emprestimos atingido.");
		}
	}
	
	public String getNUSP() {
		return this.nUSP;
	}
	public String getInstituto() {
		return this.instituto;
	}
	
	@Override
	public void setInfo() {
		super.setInfo();
		System.out.println("Numero USP:");
		this.setNUSP(entrada.nextLine());
		System.out.println("Instituto:");
		this.setInstituto(entrada.nextLine());
	}
	
}
