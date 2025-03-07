import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class PastaUsuarios {
    protected int numeroDeUsuarios;
    protected String diretorioPastaUsers = "C:/Users/João V Nardi/Desktop/Joao/java/DesafioSistemaDeCadastro2/usuarios/";
    List<String> listaBusca = new ArrayList<>();
    File pastaUsers = new File(diretorioPastaUsers); // pasta users
    int contador = 0;
    File[] arrayDeUsuarios = pastaUsers.listFiles();


    public PastaUsuarios() throws FileNotFoundException {
    }


    public int contaUsuarios() throws FileNotFoundException {
        if (!pastaUsers.exists()){
            throw new FileNotFoundException("Pasta de usuario nao encontrada!");
        }
        for (File arquivo : arrayDeUsuarios) { // para cada arquivo no array
            if (arquivo.isFile()) {
                contador++; // para cada arquivo contador cresce
            }
        }
        numeroDeUsuarios = contador;
        return numeroDeUsuarios;
    }

    public void listarUsuarios() { //metodo para listar usuarios
        if (arrayDeUsuarios == null || pastaUsers.listFiles().length == 0){
            throw new IllegalArgumentException("Erro: Nenhum usuario cadastrado!");
        } else {
            for (File arquivo : arrayDeUsuarios) { // para cada arquivo na lista de aquivos (dentro da pasta)
                if (!arquivo.exists() || !arquivo.isFile()) {
                    throw new IllegalArgumentException("Erro ao ler arquivo");
                }
                try {
                    Scanner leitor1linha = new Scanner(arquivo); //cria um scanner pra ler os arquivos dentro da pasta
                    if (leitor1linha.hasNextLine()) {
                        String[] linha = leitor1linha.nextLine().split(","); // le e armazena o primeio indice de cada txt
                        String linhaLimpa = linha[0].replace(",", "").replace("[", "• "); // embeleza o string
                        System.out.println(linhaLimpa); // imprime o string
                        leitor1linha.close();

                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public List busca() {
        try {
            if (!pastaUsers.exists()) {
                throw new FileNotFoundException("Pasta usuarios nao encontrada");
            } else {
                for (File arquivo : arrayDeUsuarios) { // para cada arquivo na lista de aquivos (dentro da pasta)
                    Scanner leitor1linha = new Scanner(arquivo); //cria um scanner pra ler os arquivos dentro da pasta
                    String[] linha = leitor1linha.nextLine().toLowerCase().split(","); // le e armazena os indices de cada txt

                    String linhaLimpa = linha[0].replace("[", "*NOME: "); // embeleza o string
                    linhaLimpa += "     *EMAIL: " + linha[1];
                    linhaLimpa += "     *IDADE: " + linha[2] + " anos";
                    listaBusca.add(linhaLimpa);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listaBusca;
    }
    public void buscaUsuario() throws FileNotFoundException, IllegalArgumentException {
        busca();
        Scanner busca = new Scanner(System.in);
            String[] nomes = listaBusca.toArray(new String[0]); // converte lista para array de string
            String buscado = busca.nextLine(); // input da busca
        if (Stream.of(nomes).noneMatch(f -> f.contains(buscado.trim()))){
            throw new InputMismatchException("Nenhum usuario encontrado.");
        }
            if (buscado.length() < 2 || buscado.isBlank()) { // possiveis inputs invalidos
                throw new IllegalArgumentException("Erro: Dados para busca invalidos!");
            } else {
                Stream.of(nomes).sorted().filter(f -> f.contains(buscado)).forEach(System.out::println); // filtra a busca
            }
        }


    public int getNumeroDeUsuarios() {
        return numeroDeUsuarios;
    }

    public String getDiretorioPastaUsers() {
        return diretorioPastaUsers;
    }
}