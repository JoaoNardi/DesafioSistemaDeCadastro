import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    protected int numero;
    protected String nome;
    protected String id;
    protected List<String> listaDeRespostas = new ArrayList<>(); // lista para armazenar respostas
    protected File respostasTxt;

    public String getNome() {
        return nome;
    }

    ArquivoPerguntas pergunta = new ArquivoPerguntas(); //inicializa a classe que converteu o txt para uma lista string
    Scanner leitorResposta = new Scanner(System.in); // inicializa a classe scanner para capturar input

    public Usuario() throws FileNotFoundException {
    }

    public void capturaInput() throws FileNotFoundException {

            while (pergunta.leitorTxt.hasNextLine()) { // enquanto tiver linhas nao lidas
                pergunta.converteTxtParaString(); // aciona metodo para jogar as linhas do txt na lista

                for (int i = 0; i < pergunta.linhasTxt.size(); i++) { //para cada linha
                    System.out.println(pergunta.linhasTxt.get(i)); // pergunta ao usuario
                    String resposta = leitorResposta.nextLine();
                    listaDeRespostas.add(resposta); // captura o input e colocar na lista de repostas
                }
            }
            this.numero = new PastaUsuarios().contaUsuarios() + 1; // gera um numero para o usuario com base na quantidade de usuarios que existem
            this.nome = listaDeRespostas.get(0);
            this.id = numero + "-" + nome.replace(" ", "").toUpperCase(); // gera o id (nome do txt do usuario)


    }
    public void criaUsuario() throws IOException { // metodo para criar usuario
        try {
            capturaInput();
            if (listaDeRespostas.get(0).length() < 10){
                throw new InputMismatchException("Erro: NOME COMPLETO inválido! (deve conter ao menos 10 caracteres)");
            }else {
                String email = listaDeRespostas.get(1).trim();
                System.out.println("email" + email);
                if (!email.contains("@")){
                    throw new InputMismatchException("Erro: EMAIL inválido! (deve conter @)");
                } else {
                    if ((Integer.parseInt(listaDeRespostas.get(2)) <18)){
                        throw new IllegalArgumentException("Erro: Usuario deve ter mais de 18 anos");

                    }
                }
                respostasTxt = new File("C:/Users/João V Nardi/Desktop/Joao/java/DesafioSistemaDeCadastro2/usuarios", id + ".txt");
                respostasTxt.createNewFile(); // cria o txt com base no diretorio acima
                //bloco para escrever no txt
                OutputStream os = new FileOutputStream(respostasTxt);
                Writer wr = new OutputStreamWriter(os);
                BufferedWriter br = new BufferedWriter(wr);
                //
                br.write(listaDeRespostas.toString()); //escreve a lista de reposta no txt
                br.close();

            }
        } finally {

        }
    }
    public File getRespostasTxt() {
        return respostasTxt;
    }

}
