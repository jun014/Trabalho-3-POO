package biblioteca;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		
		menuPrincipal();
		
	}

	private static void menuPrincipal() throws IOException {
		
		try(Scanner entrada = new Scanner(System.in)) {
		System.out.println("Menu Principal.");
		System.out.println("1 - Novo usuario;");
		System.out.println("2 - Cadastrar livro;");
		System.out.println("3 - Registrar emprestimo;");
		System.out.println("4 - Listar(livros, usuarios, emprestimos);");
		System.out.println("5 - Sair.");
		int menu = entrada.nextInt();
		
		switch(menu) {
		case 1:
			novoUsuario();
			break;
		case 2:
			cadastrarLivro();
			break;
		case 3:
			emprestimo();
			break;
		case 4:
			listar();
			break;
		case 5:
			System.out.println("Programa terminado.");
			break;
		default:
			System.out.println("Opcao nao existe.");
			break;
		}
		//menu.close();
		}
		
	}
	
	private static void novoUsuario() throws IOException {
		
		try(Scanner entrada = new Scanner(System.in)) {
		System.out.println("Tipo de usuario: ");
		System.out.println("1 - usuario normal;");
		System.out.println("2 - aluno;");
		System.out.println("3 - professor;");
		System.out.println("4 - voltar para o menu principal.");
		int menu = entrada.nextInt();
		
		FileWriter csvArquivo = new FileWriter("files/usuarios.csv", true);
		CSVWriter escrever = new CSVWriter(csvArquivo);
		
		switch(menu) {
		case 1:
			Usuario user = new Usuario();
			user.tipo = 1;
			user.setCodigo();
			user.setInfo();
			String[] dados1 = (user.getCodigo() + "#" 
								+ user.getTipo() + "#" 
								+ user.getNome() + "#" 
								+ user.getCPF() + "#" 
								+ user.getEndereco() + "#" 
								+ user.getTelefone() + "#" 
								+ null + "#" 
								+ null).split("#");
			escrever.writeNext(dados1, false);
			escrever.close();
			break;
		case 2:
			Aluno aluno = new Aluno();
			aluno.tipo = 2;
			aluno.setCodigo();
			aluno.setInfo();
			String[] dados2 = (aluno.getCodigo() + "#" 
								+ aluno.getTipo() + "#" 
								+ aluno.getNome() + "#" 
								+ aluno.getCPF() + "#" 
								+ aluno.getEndereco() + "#" 
								+ aluno.getTelefone() + "#" 
								+ aluno.getNUSP() + "#" 
								+ aluno.getCurso()).split("#");
			escrever.writeNext(dados2, false);
			escrever.close();
			break;
		case 3:
			Professor professor = new Professor();
			professor.tipo = 3;
			professor.setCodigo();
			professor.setInfo();
			String[] dados3 = (professor.getCodigo() + "#" 
								+ professor.getTipo() + "#" 
								+ professor.getNome() + "#" 
								+ professor.getCPF() + "#" 
								+ professor.getEndereco() + "#" 
								+ professor.getTelefone() + "#" 
								+ professor.getNUSP() + "#" 
								+ professor.getInstituto()).split("#");
			escrever.writeNext(dados3, false);
			escrever.close();
			break;
		case 4:
			break;
		default:
			System.out.println("Opcao nao existente.");
			break;
		}
		menuPrincipal();
		}
		
	}
	
	private static void cadastrarLivro() throws IOException {
		
		try(Scanner entrada = new Scanner(System.in)) {
		System.out.println("Cadastro de livro:");
		System.out.println("1 - Texto;");
		System.out.println("2 - Geral;");
		System.out.println("3 - Voltar para o menu principal.");
		int menu = entrada.nextInt();
		
		FileWriter csvArquivo = new FileWriter("files/livros.csv", true);
		CSVWriter escrever = new CSVWriter(csvArquivo);
		
		switch(menu) {
		case 1:
			CadastroLivro texto = new CadastroLivro();
			texto.setTipo(1);
			texto.setCodigo();
			texto.setInfo();
			String[] dados1 = (texto.getCodigo() + "#" 
								+ texto.getTipo() + "#" 
								+ texto.getTitulo() + "#" 
								+ texto.getQuantidade() + "#" 
								+ texto.getDataReg()).split("#");
			escrever.writeNext(dados1, false);
			escrever.close();
			break;
		case 2:
			CadastroLivro geral = new CadastroLivro();
			geral.setTipo(2);
			geral.setCodigo();
			geral.setInfo();
			String[] dados2 = (geral.getCodigo() + "#" 
								+ geral.getTipo() + "#" 
								+ geral.getTitulo() + "#" 
								+ geral.getQuantidade() + "#" 
								+ geral.getDataReg()).split("#");
			escrever.writeNext(dados2, false);
			escrever.close();
			break;
		case 3:
			menuPrincipal();
			break;
		default:
			System.out.println("Opcao inexistente.");
			break;
		}
		menuPrincipal();
		}
		
	}
	
	private static void emprestimo() throws IOException {
		
		try(Scanner entrada = new Scanner(System.in)) {
		System.out.println("Emprestimo");
		System.out.println("1 - Livro geral;");
		System.out.println("2 - Livro texto;");
		System.out.println("3 - Voltar para o menu principal.");
		int menu = entrada.nextInt();
		
		switch(menu) {
		case 1:
			Usuario user1 = new Usuario();
			user1 = selecionarUsuario();
			String livro1 = selecionarLivro(menu);
			if(livro1 == null) {
				//scan.close();
				break;
			} else {
				RegEmprestimo reg1 = new RegEmprestimo();
				reg1.setInfo(user1.regCodigo, user1.tipo, user1.nome, livro1);
				reg1.registro();
			}
			//scan.close();
			break;
		case 2:
			Usuario user2 = new Usuario();
			user2 = selecionarUsuario();
			if(user2.tipo == 1) {
				System.out.println("Emprestimo para usuario nao permitido.");
				//scan.close();
				break;
			} else {
				String livro2 = selecionarLivro(menu);
				if(livro2 == null) {
					//scan.close();
					break;
				} else {
					RegEmprestimo reg2 = new RegEmprestimo();
					reg2.setInfo(user2.regCodigo, user2.tipo, user2.nome, livro2);
					reg2.registro();
				}
			}
			//scan.close();
			break;
		case 3:
			//scan.close();
			break;
		default:
			System.out.println("Opcao nao existente.");
			//scan.close();
			break;
		}
		menuPrincipal();
		}
		
	}

	private static Usuario selecionarUsuario() throws IOException {
		
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual usuario?");
		int user = entrada.nextInt();
		String csvArquivo = "files/usuarios.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] col = null;
		while((col = ler.readNext()) != null) {
			int y = Integer.parseInt(col[0]);
			if(user == y) {
				int x = Integer.parseInt(col[1]);
				Usuario selUser = new Usuario();
				selUser.setInfoSel(y, x, col[2]);
				ler.close();
				return selUser;
				
				/*switch(x) {
				case 1:
					Usuario selUser = new Usuario();
					selUser.setInfoSel(y, x, row[2]);
					return selUser;
				case 2:
					Aluno selAluno = new Aluno();
					selAluno.setInfoSel(y, x, row[2]);
					return selAluno;
				case 3:
					Professor selProfessor = new Professor();
					selProfessor.setInfoSel(y, x, row[2]);
					return selProfessor;
				default:
					System.out.println("Houve um problema no sistema, contate o administrador.");
					break;
				}*/
			}
		}
		ler.close();
		return null;
		
	}
	
	private static String selecionarLivro(int tipo) throws IOException {
		
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual livro? digite o codigo.");
		int x = entrada.nextInt();
		String csvArquivo = "files/livros.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] col = null;
		while((col = ler.readNext()) != null) {
			int y = Integer.parseInt(col[0]);
			int k = Integer.parseInt(col[1]);
			if(x == y && tipo == k) {
				//scan.close();
				ler.close();
				return col[2];
			}
		}
		//scan.close();
		ler.close();
		System.out.println("Livro nao encontrado.");
		return null;
		
	}

	private static void listar() throws IOException {
		
		try(Scanner entrada = new Scanner(System.in)) {
		System.out.println("Listar:");
		System.out.println("1 - Livros;");
		System.out.println("2 - Usuarios;");
		System.out.println("3 - Emprestimos;");
		System.out.println("4 - Voltar para o menu principal.");
		int menu = entrada.nextInt();
		
		switch(menu) {
		case 1:
			listarLivros();
			break;
		case 2:
			listarUsuarios();
			break;
		case 3:
			listaEmprestimos();
			break;
		case 4:
			menuPrincipal();
			break;
		default:
			System.out.println("Opcao inexistente.!");
			break;
		}
		}
		
	}

	private static void listarLivros() throws IOException {
		
		String csvArquivo = "files/livros.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] col = null;
		
		while((col = ler.readNext()) != null) {
			System.out.println("Codigo:" + col[0]
							+ " Tipo:" + col[1]
							+ " Titulo:" + col[2]
							+ " Quantidade:" + col[3]
							+ " Data:" + col[4]);
		}
		System.out.println("Tipo: 1=Texto; 2=Geral.");
		ler.close();
		menuPrincipal();
		
	}

	private static void listarUsuarios() throws IOException {

		String csvArquivo = "files/usuarios.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] col = null;
		
		while((col = ler.readNext()) != null) {
			System.out.println("Codigo:" + col[0]
							+ " Tipo:" + col[1] 
							+ " Nome:" + col[2]
							+ " CPF:" + col[3]
							+ " Endereco:" + col[4]
							+ " Telefone:" + col[5]
							+ " Numero USP:" + col[6]
							+ " Curso/Instituto:" + col[7]);
		}
		System.out.println("Tipo: 1=Comunidade; 2=Aluno; 3=Professor.");
		ler.close();
		menuPrincipal();
		
	}
	
	private static void listaEmprestimos() throws IOException {
		
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] col = null;
		
		while((col = ler.readNext()) != null) {
			System.out.println("Codigo do usuario:" + col[0] +
								" Tipo de usuario:" + col[1] +
								" Nome do usuario:" + col[2] +
								" Titulo do livro:" + col[3] +
								" Data do emprestimo:" + col[4]);
		}
		System.out.println("Tipo: 1-Comunidade; 2-Aluno; 3-Professor.");
		ler.close();
		menuPrincipal();
	}

}
