import java.io.*;
import java.text.DecimalFormat;
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
        this.nome = listaDeRespostas.getFirst();
        this.id = numero + "-" + nome.replace(" ", "").toUpperCase(); // gera o id (nome do txt do usuario)

        DecimalFormat formatoMetros = new DecimalFormat("0,00"); //define um formato para metros
        // separa o valor numerico do restante e substitui "," por "."
        String[] partes = listaDeRespostas.get(3).replace(",", "").replace(".", "").split(" ");
        if (!Character.isDigit(partes[0].charAt(0))) { // verifica se a resposta possui um número
            throw new NumberFormatException("Erro: altura informada invalida! (apenas numeros devem ser inseridos)");
        } else {
            double alturaConvertida = Double.parseDouble(partes[0]); // converte a altura em double
            String alturaAjustada = formatoMetros.format(alturaConvertida); //transforma a altura em string no formato desejado
            listaDeRespostas.set(3, alturaAjustada); // subtitui a altura antiga por uma altura formatada nova
        }
    }

    public void criaUsuario() throws IOException { // metodo para criar usuario
        capturaInput();
        List<String> busca = new PastaUsuarios().busca(); // puxa a lista de busca da classe pasta usuarios
        String[] buscaEmail = busca.toArray(new String[0]); // lista vira string array local

        String email = listaDeRespostas.get(1); // define o que é o email
        if (listaDeRespostas.get(0).length() < 10) { // se o nome tiver menos de 10 caracteres
            throw new InputMismatchException("Erro: NOME COMPLETO inválido! (deve conter ao menos 10 caracteres)");
        } else {
            if (!email.contains("@")) { // se o email nao tiver o @
                throw new InputMismatchException("Erro: EMAIL inválido! (deve conter @)");
            } else if ((Integer.parseInt(listaDeRespostas.get(2)) < 18)) { // se o input de idade for menor que 18 anos
                throw new IllegalArgumentException("Erro: Usuario deve ter mais de 18 anos"); // lanca a exe
            } else if (buscaEmail.toString().contains(" " + email.trim()) | buscaEmail.toString().contains(email)) { // verifca se o email ja existe
                throw new IllegalArgumentException("Erro: O email informado ja é cadastrado!");
            } else

                respostasTxt = new File("C:/Users/João V Nardi/Desktop/Joao/java/DesafioSistemaDeCadastro/usuarios", id + ".txt");
            //bloco para escrever no txt
            OutputStream os = new FileOutputStream(respostasTxt);
            Writer wr = new OutputStreamWriter(os);
            BufferedWriter br = new BufferedWriter(wr);
            //
            br.write(listaDeRespostas.toString()); //escreve a lista de reposta no txt
            br.close();
            System.out.println("Cadastro realizado com sucesso!");
        }
    }
}
