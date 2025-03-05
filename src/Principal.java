import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, IllegalStateException {
        Scanner leitura = new Scanner(System.in);
        new ArquivoPerguntas().refresh();
        System.out.println("Seja Bem vindo!");
        while (true) {
            System.out.println(
                    """
                            1 - Cadastrar o usuário
                            2 - Listar todos usuários cadastrados
                            3 - Cadastrar nova pergunta no formulário
                            4 - Deletar pergunta do formulário
                            5 - Pesquisar usuário por nome ou idade ou email""");
            switch (leitura.nextInt()) {
                case 1:
                    try {
                        new Usuario().criaUsuario();

                    } catch (IllegalArgumentException | FileNotFoundException | InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("*************************");
                        System.out.println("Usuarios cadatrados:");
                        new PastaUsuarios().listarUsuarios();
                        System.out.println("*************************");
                    }catch (FileNotFoundException | IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Digite a pergunta que deseja adicionar:");
                    new ArquivoPerguntas().criaPergunta();
                    new ArquivoPerguntas().refresh();
                    System.out.println("Pergunta adicionada com sucesso!");
                    break;
                case 4:
                    try {
                        new ArquivoPerguntas().deletaPergunta();
                        new ArquivoPerguntas().refresh();
                        System.out.println("Pergunta deletada com sucesso!");
                    }catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Digite o nome a ser buscado: ");
                    System.out.println("(a busca deve conter pelo menos 3 caracteres)");
                    try {
                        new PastaUsuarios().buscaUsuario();
                    }catch (FileNotFoundException | IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("\n");
                    System.out.println("**************************************");
                    System.out.println("Atenção! selecione uma opção valida!");
                    System.out.println("**************************************");
                    System.out.println("\n");
            }


        }
    }
}