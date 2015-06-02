//Programa feito por: Anderson Jun Morikawa - numero USP: 8936989

package biblioteca;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.Days;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Main {
	
	//Variavel global para utilizar tempo modificado
	static Tempo tempo = new Tempo();

	public static void main(String[] args) throws IOException {
		
		menuPrincipal();
		
	}

	private static void menuPrincipal() throws IOException {
		
		//ler opcao do menu principal
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
				tempo.setInfo();
				menuPrincipal();
				break;
			case 6:
				System.out.println("Programa terminado.");
				break;
			default:
				System.out.println("Opcao nao existe.");
				break;
			}
		}
		
	}

	private static void novoUsuario() throws IOException {
		
		//ler opcao do menu de tipos de usuarios
		try(Scanner entrada = new Scanner(System.in)) {
			System.out.println("Tipo de usuario: ");
			System.out.println("1 - usuario normal;");
			System.out.println("2 - aluno;");
			System.out.println("3 - professor;");
			System.out.println("4 - voltar para o menu principal.");
			int menu = entrada.nextInt();
			
			//abrir arquivo para registro do novo usuario
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
		
		//ler opcao do menu de tipo de livro
		try(Scanner entrada = new Scanner(System.in)) {
			System.out.println("Cadastro de livro:");
			System.out.println("1 - Texto;");
			System.out.println("2 - Geral;");
			System.out.println("3 - Voltar para o menu principal.");
			int menu = entrada.nextInt();
			
			//abrir arquivo onde sera registrado o livro
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
									+ geral.getDataReg()).split("#");
				escrever.writeNext(dados2, false);
				escrever.close();
				break;
			case 3:
				menuPrincipal();
				break;
			default:
				System.out.println("Opcao nao existente.");
				break;
			}
			menuPrincipal();
		}
		
	}
	
	private static void emprestimoOUdevolucao() throws IOException {
		
		//ler opcao do menu de emprestimo/devolucao de livro
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
				//caso for digitado codigo de usuario que nao esta cadastrado.
				if(user1 == null) {
					System.out.println("Usuario nao encontrado ou suspenso.");
					break;
				}
				String livro1 = selecionarLivro(menu);
				//caso for digitado codigo de livro que nao esta cadastrado.
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
				//caso for digitado codigo de usuario que nao esta cadastrado.
				if(user2 == null) {
					System.out.println("Usuario nao encontrado ou suspenso.");
					break;
				}
				if(user2.tipo == 1) {
					System.out.println("Tipo de livro restrito para emprestimo, somente para professores ou alunos.");
					break;
				} else {
					String livro2 = selecionarLivro(menu);
					//caso for digitado codigo de livro que nao esta cadastrado.
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
				//caso o codigo do emprestimo nao seja encontrado
				if(reg == null) {
					System.out.println("Emprestimo nao encontrado.");
					break;
				}
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
		
		//ler o codigo do usuario a ser selecionado
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual usuario?");
		int user = entrada.nextInt();
		
		//abrir arquivo para buscar usuario
		String csvArquivo = "files/usuarios.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		
		//loop para encontrar o usuario
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			if(user == codigo) {
				int tipo = Integer.parseInt(li[1]);
				checarAtraso(codigo, tipo);
				LocalDate ate = suspensoAte(codigo);
				LocalDate hoje = LocalDate.now();
				//se usuario estiver suspenso por atraso na devolucao de livro
				if(hoje.isBefore(ate)) {
				System.out.println("Usuario suspenso ate: " + ate);
				ler.close();
				return null;
				}
				//checar se usuario esta no limite de emprestimos, de acordo com o tipo de usuario
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
				//caso esteja tudo OK, cria-se um objeto do usuario
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
		
		//ler qual livro deseja emprestar
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual livro? digite o codigo.");
		int codigoLivro = entrada.nextInt();
		
		//abrir arquivo de livros
		String csvArquivo = "files/livros.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para encontrar o livro
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			int tipo = Integer.parseInt(li[1]);
			if(codigoLivro == codigo && menu == tipo) {
				ler.close();
				return li[2];
			}
		}
		ler.close();
		return null;
		
	}
	
	private static RegDevolucao selecionarEmprestimo() throws NumberFormatException, IOException {
		
		//ler o codigo do emprestimo
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual o codigo do emprestimo?");
		int codigo = entrada.nextInt();
		
		//abrir arquivo de emprestimos
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para encontrar o registro do emprestimo
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
		
		//ler opcao do tipo de lista a ser exibida
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
				System.out.println("Opcao nao existente.");
				break;
			}
		}
		
	}

	private static void listarLivros() throws IOException {
		
		//abrir arquivo de livros
		String csvArquivo = "files/livros.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para listar todos os livros
		while((li = ler.readNext()) != null) {
			System.out.println("Codigo:" + li[0]
							+ " - Tipo:" + li[1]
							+ " - Titulo:" + li[2]
							+ " - Data:" + li[3]);
		}
		System.out.println("Tipo: 1=Texto; 2=Geral.");
		ler.close();
		menuPrincipal();
		
	}

	private static void listarUsuarios() throws IOException {

		//abrir arquivo de usuarios
		String csvArquivo = "files/usuarios.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para listar todos os usuarios
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			System.out.println("Codigo:" + li[0]
							+ " - Tipo:" + li[1] 
							+ " - Nome:" + li[2]
							+ " - CPF:" + li[3]
							+ " - Endereco:" + li[4]
							+ " - Telefone:" + li[5]
							+ " - Numero USP:" + li[6]
							+ " - Curso/Instituto:" + li[7]
							+ " - Emprestimos:" + checarEmprestimos(codigo));
		}
		System.out.println("Tipo: 1=Comunidade; 2=Aluno; 3=Professor.");
		ler.close();
		menuPrincipal();
		
	}
	
	private static void listaEmprestimos() throws IOException {
		
		//abrir arquivo de emprestimos
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para listar todos os emprestimos
		while((li = ler.readNext()) != null) {
			int codigo = Integer.parseInt(li[0]);
			int tipo = Integer.parseInt(li[2]);
			System.out.println("Codigo:" + li[0] +
								" - Codigo do usuario:" + li[1] +
								" - Tipo do usuario:" + li[2] +
								" - Nome do usuario:" + li[3] +
								" - Titulo do livro:" + li[4] +
								" - Data de emprestimo:" + li[5] +
								" - Situacao:" + checarDevolucao(codigo, tipo));
		}
		System.out.println("Tipo: 1-Comunidade; 2-Aluno; 3-Professor.");
		ler.close();
		menuPrincipal();
		
	}
	
	private static String checarDevolucao(int codigo, int tipo) throws IOException {
		
		//abrir arquivo de devolucao
		String csvArquivo = "files/devolucao.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para verificar existencia do registro de devolucao
		while((li = ler.readNext()) != null) {
			int regCodigo = Integer.parseInt(li[0]);
			//caso houve a devolucao do livro
			if(codigo == regCodigo) {
				ler.close();
				return "devolvido";
			}
		}
		//caso nao se tenha registro da devolucao do livro
		//abrir arquivo de registro de emprestimos
		String csvArquivo2 = "files/emprestimos.csv";
		CSVReader ler2 = new CSVReader(new FileReader(csvArquivo2));
		//loop para encontrar o registro
		while((li = ler2.readNext()) != null) {
			int regCodigo = Integer.parseInt(li[0]);
			if(codigo == regCodigo) {
				//eh verificado a quanto tempo o livro foi emprestado
				DateTime data = new DateTime(li[5]);
				DateTime dataAtual = modificarData();
				Days d = Days.daysBetween(data, dataAtual);
				int atraso = d.getDays();
				//caso houve um atraso acima do limite, de acordo com o tipo de usuario
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
		//caso nao tenha ultrapassado o limite de emprestimo
		ler.close();
		ler2.close();
		return "nao devolvido";
	}
	
	private static int checarEmprestimos(int codigo) throws IOException {
		
		//abrir arquivo de emprestimos
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//variavel para fazer a contagem de emprestimos
		int emprestimos = 0;
		//loop para encontrar o registro de emprestimo
		while((li = ler.readNext()) != null) {
			int regCodigo = Integer.parseInt(li[1]);
			//caso o emprestimo tenha sido realizado, a variavel eh incrementada
			if(codigo == regCodigo) {
				emprestimos++;
			}
		}
		//abrir arquivo de devolucao
		String csvArquivo2 = "files/devolucao.csv";
		CSVReader ler2 = new CSVReader(new FileReader(csvArquivo2));
		//loop para encontrar o registro da devolucao
		while((li = ler2.readNext()) != null) {
			int regCodigo2 = Integer.parseInt(li[1]);
			//caso tenha sido registrado a devolucao, a variavel eh decrementada
			if(codigo == regCodigo2) {
				emprestimos--;
			}
		}
		ler.close();
		ler2.close();
		//retorna a quantidade de emprestimos pendentes
		return emprestimos;
	}
	
	private static void checarAtraso(int codigo, int tipo) throws IOException {
		
		//abrir arquivo de emprestimos
		String csvArquivo = "files/emprestimos.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para encontrar registro
		while((li = ler.readNext()) != null) {
			int codigoEmprestimo = Integer.parseInt(li[0]);
			int codigoUser = Integer.parseInt(li[1]);
			if(codigo == codigoUser) {
				//abrir arquivo de devolucao
				String csvArquivo2 = "files/devolucao.csv";
				CSVReader ler2 = new CSVReader(new FileReader(csvArquivo2));
				String[] li2 = null;
				//variavel para marcar se houve devolucao
				boolean devolveu = false;
				//loop para encontrar o codigo de emprestimo no arquivo de devolucao
				while((li2 = ler2.readNext()) != null) {
					int codigoEmprestimoDev = Integer.parseInt(li2[0]);
					//caso seja encontrado, variavel se torna true
					if(codigoEmprestimo == codigoEmprestimoDev) {
						devolveu = true;
					}
				}
				//caso nao seja encontrado o registro, eh calculado o atraso total, de acordo com o tipo do usuario
				if(devolveu == false) {
					DateTime data = new DateTime(li[5]);
			        DateTime dataAtual = modificarData();
					Days d = Days.daysBetween(data, dataAtual);
					int atraso = d.getDays();
					if(tipo == 1 && atraso > 15) {
						ler.close();
						ler2.close();
						registrarData(codigo, (atraso-15));
					}
					if(tipo == 2 && atraso > 15) {
						ler.close();
						ler2.close();
						registrarData(codigo, (atraso-15));
					}
					if(tipo == 3 && atraso > 60) {
						ler.close();
						ler2.close();
						registrarData(codigo, (atraso-60));
					}
				}
				ler2.close();
			}
		}
		ler.close();

	}
	
	private static void registrarData(int codigo, int atraso) throws IOException {
		
		LocalDate hoje = LocalDate.now();
		LocalDate ate = hoje.plusDays(atraso);
		//abrir arquivo de suspensao de usuario
		FileWriter csvArquivo = new FileWriter("files/suspensao.csv", true);
		CSVWriter escrever = new CSVWriter(csvArquivo);
		//registro no arquivo de suspensao
		String[] dados = (codigo + "#" + ate).split("#");
		escrever.writeNext(dados, false);
		escrever.close();
		
	}
	
	private static LocalDate suspensoAte(int codigo) throws IOException {
		
		//variavel auxiliar para armazenar ultima data de suspensao
		LocalDate aux = LocalDate.now();
		//abrir arquivo de suspensao
		String csvArquivo = "files/suspensao.csv";
		CSVReader ler = new CSVReader(new FileReader(csvArquivo));
		String[] li = null;
		//loop para encontrar os registros das suspensoes
		while((li = ler.readNext()) != null) {
			int codUser = Integer.parseInt(li[0]);
			if(codigo == codUser) {
				LocalDate ate = LocalDate.parse(li[1]);
				//registrar data mais recente de suspensao
				if(aux.isBefore(ate)) {
					aux = ate;
				}
			}
		}
		ler.close();
		return aux;
		
	}
	
	public static DateTime modificarData() {
		if(Tempo.modificado == true) {
			DateTime data = new DateTime(Tempo.ano + "-" + Tempo.mes + "-" + Tempo.dia);
			return data;
		} else {
			DateTime data = new DateTime();
			return data;
		}
	}

}
