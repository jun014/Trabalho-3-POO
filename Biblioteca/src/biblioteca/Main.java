package biblioteca;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.Days;

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
			System.out.println("3 - Registrar emprestimo/devolucao;");
			System.out.println("4 - Listar(livros, usuarios, emprestimos);");
			System.out.println("5 - Modificar data;");
			System.out.println("6 - Sair.");
			int menu = entrada.nextInt();
		
			switch(menu) {
			case 1:
				novoUsuario();
				break;
			case 2:
				cadastrarLivro();
				break;
			case 3:
				emprestimoOUdevolucao();
				break;
			case 4:
				listar();
				break;
			case 5:
				modificarData();
			case 6:
				System.out.println("Programa terminado.");
				break;
			default:
				System.out.println("Opcao nao existe.");
				break;
			}
		}
		
	}
	
	private static void modificarData() {
		
		try(Scanner entrada = new Scanner(System.in)) {
			System.out.println("Ano:");
			String ano = entrada.nextLine();
			System.out.println("Mes:");
			String mes = entrada.nextLine();
			System.out.println("Dia:");
			String dia = entrada.nextLine();
			String data = ano + "-" + mes + "-" + dia;
			System.out.println(data);
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
									+ null + "#").split("#");
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
	
	private static void emprestimoOUdevolucao() throws IOException {
		
		try(Scanner entrada = new Scanner(System.in)) {
			System.out.println("Registrar emprestimo de:");
			System.out.println("1 - Livro geral;");
			System.out.println("2 - Livro texto;");
			System.out.println("3 - Registrar devolucao;");
			System.out.println("4 - Voltar para o menu principal.");
			int menu = entrada.nextInt();
			
			switch(menu) {
			case 1:
				Usuario user1 = new Usuario();
				user1 = selecionarUsuario();
				if(user1 == null) {
					System.out.println("Erro.");
					break;
				}
				String livro1 = selecionarLivro(menu);
				if(livro1 == null) {
					System.out.println("Livro nao encontrado.");
					break;
				} else {
					RegEmprestimo reg1 = new RegEmprestimo();
					reg1.setCodigo();
					reg1.setInfo(user1.regCodigo, user1.tipo, user1.nome, livro1);
					reg1.registro();
				}
				break;
			case 2:
				Usuario user2 = new Usuario();
				user2 = selecionarUsuario();
				if(user2 == null) {
					System.out.println("Erro.");
					break;
				}
				if(user2.tipo == 1) {
					System.out.println("Emprestimo para usuario nao permitido.");
					break;
				} else {
					String livro2 = selecionarLivro(menu);
					if(livro2 == null) {
						System.out.println("Livro nao encontrado.");
						break;
					} else {
						RegEmprestimo reg2 = new RegEmprestimo();
						reg2.setCodigo();
						reg2.setInfo(user2.regCodigo, user2.tipo, user2.nome, livro2);
						reg2.registro();
					}
				}
				break;
			case 3:
				RegDevolucao reg = new RegDevolucao();
				reg = selecionarEmprestimo();
				reg.registro();
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

	private static Usuario selecionarUsuario() throws IOException {
		
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual usuario?");
		int user = entrada.nextInt();
		String csvArquivo = "files/usuarios.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			if(user == codigo) {
				int tipo = Integer.parseInt(li[1]);
				if(tipo == 1 && checarEmprestimos(codigo) == 2) {
					System.out.println("Usuario chegou no limite de emprestimos.");
					ler.close();
					return null;
				}
				if(tipo == 2 && checarEmprestimos(codigo) == 4) {
					System.out.println("Usuario chegou no limite de emprestimos.");
					ler.close();
					return null;
				}
				if(tipo == 3 && checarEmprestimos(codigo) == 6) {
					System.out.println("Usuario chegou no limite de emprestimos.");
					ler.close();
					return null;
				}
				Usuario selUser = new Usuario();
				selUser.setInfoSel(codigo, tipo, li[2]);
				ler.close();
				return selUser;
			}
		}
		ler.close();
		return null;
		
	}
	
	private static String selecionarLivro(int menu) throws IOException {
		
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual livro? digite o codigo.");
		int x = entrada.nextInt();
		String csvArquivo = "files/livros.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			int tipo = Integer.parseInt(li[1]);
			if(x == codigo && menu == tipo) {
				ler.close();
				return li[2];
			}
		}
		ler.close();
		return null;
		
	}
	
	private static RegDevolucao selecionarEmprestimo() throws NumberFormatException, IOException {
		
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual o codigo do emprestimo?");
		int codigo = entrada.nextInt();
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		while((li = ler.readNext()) != null) {
			int regCodigo = Integer.parseInt(li[0]);
			if(codigo == regCodigo) {
				RegDevolucao reg = new RegDevolucao();
				int codUser = Integer.parseInt(li[1]);
				int tipo = Integer.parseInt(li[2]);
				reg.setInfo(regCodigo, codUser, tipo, li[3], li[4]);
				ler.close();
				return reg;
			}
		}
		ler.close();
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
		String[] li = null;
		
		while((li = ler.readNext()) != null) {
			System.out.println("Codigo:" + li[0]
							+ " Tipo:" + li[1]
							+ " Titulo:" + li[2]
							+ " Quantidade:" + li[3]
							+ " Data:" + li[4]);
		}
		System.out.println("Tipo: 1=Texto; 2=Geral.");
		ler.close();
		menuPrincipal();
		
	}

	private static void listarUsuarios() throws IOException {

		String csvArquivo = "files/usuarios.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			System.out.println("Codigo:" + li[0]
							+ " Tipo:" + li[1] 
							+ " Nome:" + li[2]
							+ " CPF:" + li[3]
							+ " Endereco:" + li[4]
							+ " Telefone:" + li[5]
							+ " Numero USP:" + li[6]
							+ " Curso/Instituto:" + li[7]
							+ " Emprestimos:" + checarEmprestimos(codigo));
		}
		System.out.println("Tipo: 1=Comunidade; 2=Aluno; 3=Professor.");
		ler.close();
		menuPrincipal();
		
	}
	
	private static void listaEmprestimos() throws IOException {
		
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			int tipo = Integer.parseInt(li[2]);
			System.out.println("Codigo:" + li[0] +
								" Codigo do usuario:" + li[1] +
								" Tipo do usuario:" + li[2] +
								" Nome do usuario:" + li[3] +
								" Titulo do livro:" + li[4] +
								" Data de emprestimo:" + li[5] +
								" Situacao:" + checarDevolucao(codigo, tipo));
		}
		System.out.println("Tipo: 1-Comunidade; 2-Aluno; 3-Professor.");
		ler.close();
		menuPrincipal();
	}
	
	private static String checarDevolucao(int codigo, int tipo) throws IOException {
		
		String csvArquivo = "files/devolucao.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		while((li = ler.readNext()) != null) {
			int regCodigo = Integer.parseInt(li[0]);
			if(codigo == regCodigo) {
				ler.close();
				return "devolvido";
			}
		}
		String csvArquivo2 = "files/emprestimos.csv";
		CSVReader ler2 = new CSVReader(new FileReader(csvArquivo2));
		while((li = ler2.readNext()) != null) {
			int regCodigo = Integer.parseInt(li[0]);
			if(codigo == regCodigo) {
				DateTime data = new DateTime(li[5]);
		        DateTime dataAtual = new DateTime();
				Days d = Days.daysBetween(data, dataAtual);
				int atraso = d.getDays();
				if(tipo == 1 && atraso > 15) {
					ler.close();
					ler2.close();
					return "nao devolvido(atraso de " + (atraso-15) + " dia(s))";
				}
				if(tipo == 2 && atraso > 15) {
					ler.close();
					ler2.close();
					return "nao devolvido(atraso de " + (atraso-15) + " dia(s))";
				}
				if(tipo == 3 && atraso > 60) {
					ler.close();
					ler2.close();
					return "nao devolvido(atraso de " + (atraso-60) + " dia(s))";
				}
			}
		}
		ler.close();
		ler2.close();
		return "nao devolvido";
	}
	
	private static int checarEmprestimos(int codigo) throws IOException {
		
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		int emprestimos = 0;
		while((li = ler.readNext()) != null) {
			int regCodigo = Integer.parseInt(li[1]);
			if(codigo == regCodigo) {
				emprestimos++;
			}
		}
		String csvArquivo2 = "files/devolucao.csv";
		CSVReader ler2 = new CSVReader(new FileReader(csvArquivo2));
		while((li = ler2.readNext()) != null) {
			int regCodigo2 = Integer.parseInt(li[1]);
			if(codigo == regCodigo2) {
				emprestimos--;
			}
		}
		ler.close();
		ler2.close();
		return emprestimos;
	}

}
