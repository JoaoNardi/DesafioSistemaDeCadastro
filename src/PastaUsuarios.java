import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class PastaUsuarios {
    protected int numeroDeUsuarios;
    protected String diretorioPastaUsers = "C:/Users/João V Nardi/Desktop/Joao/java/DesafioSistemaDeCadastro2/usuarios/";

    File pastaUsers = new File(diretorioPastaUsers); // pasta users
    int contador = 0;
    File[] listaDeUsuarios = pastaUsers.listFiles();


    public PastaUsuarios() throws FileNotFoundException {
    }


    public int contaUsuarios() {

        for (File file : listaDeUsuarios) {
            if (file.isFile()) {
                contador++;
            }
        }
        numeroDeUsuarios = contador;
        return numeroDeUsuarios;
    }

    public void listarUsuarios() { //metodo para listar usuarios

        try {
            if (!pastaUsers.exists()) {
                throw new FileNotFoundException("Pasta usuarios nao encontrada");
            } else {
                for (File arquivo : listaDeUsuarios) { // para cada arquivo na lista de aquivos (dentro da pasta)
                    Scanner leitor1linha = new Scanner(arquivo); //cria um scanner pra ler os arquivos dentro da pasta
                    String linha = leitor1linha.nextLine(); // le e armazena a primeira linha de cada txt
                    String linhaLimpa = linha.replace(",", "").replace("[", "• "); // embeleza o string
                    System.out.println(linhaLimpa); // imprime o string
                    leitor1linha.close();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void buscaUsuario() throws FileNotFoundException, IllegalArgumentException {
        List<String> listaBusca = new ArrayList<>();
        Scanner busca = new Scanner(System.in);

        if (!pastaUsers.exists()) {
            throw new FileNotFoundException("Pasta usuarios nao encontrada");
        } else {
            for (File arquivo : listaDeUsuarios) { // para cada arquivo na lista de aquivos (dentro da pasta)
                Scanner leitor1linha = new Scanner(arquivo); //cria um scanner pra ler os arquivos dentro da pasta
                String linha = leitor1linha.nextLine(); // le e armazena a primeira linha de cada txt
                String linhaLimpa = linha.replace(",", "").replace("[", ""); // embeleza o string
                listaBusca.add(linhaLimpa);

            }

            String[] nomes = listaBusca.toArray(new String[0]); // converte lista para array de string
            String buscado = busca.nextLine(); // input da busca
            if (buscado.length() <= 2 || buscado.contains("  ")) { // possiveis inputs invalidos
                throw new IllegalArgumentException("Erro: caracteres invalidos");
            } else {
                Stream.of(nomes).map(String::toLowerCase).sorted().filter(f -> f.contains(buscado)).forEach(System.out::println); // filtra a busca
            }
        }
    }
    public int getNumeroDeUsuarios() {
        return numeroDeUsuarios;
    }

    public String getDiretorioPastaUsers() {
        return diretorioPastaUsers;
    }

}
