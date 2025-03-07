import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoPerguntas {
    String caminhoPerguntas = "C:/Users/João V Nardi/Desktop/Joao/java/DesafioSistemaDeCadastro2/perguntas.txt";
    List<String> linhasTxt = new ArrayList<>(); // lista para armazenar o que vai ser lido do arquivo
    FileReader arquivoPerguntas = new FileReader(caminhoPerguntas); //txt de perguntas
    Scanner leitorTxt = new Scanner(arquivoPerguntas); // inicializa o scanner que vai ler o txt
    Scanner leitor = new Scanner(System.in);


    public ArquivoPerguntas() throws FileNotFoundException {
    }

    public void refresh() throws IOException {
        converteTxtParaString();
        //bloco para escrever num txt existente
        OutputStream os = new FileOutputStream(caminhoPerguntas);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);
        //
        while (leitorTxt.hasNextLine()) { //enquanto tiver linhas para ler
            String[] partes = leitorTxt.nextLine().split(" - ");
            var p = new Pergunta(linhasTxt.size() + 1, partes[1].trim());
            linhasTxt.add(p.toString());
        }

        br.write(linhasTxt.toString().replace("[","").replace("]","").replace(", ",""));
        br.close();
    }
    public List converteTxtParaString() { // metodo para converter o txt em lista string
        while (leitorTxt.hasNextLine()) { //enquanto tiver linhas para ler
            String[] partes = leitorTxt.nextLine().split(" - ");
            var p = new Pergunta(linhasTxt.size()+1,partes[1].trim());
                linhasTxt.add(p.toString() + "\n"); // adiconar linha por linha na lista
            }
        return linhasTxt; //retorna a lista
    }

    public void criaPergunta() throws IOException {
        converteTxtParaString();
        String linhasLimpas = linhasTxt.toString().replace("[","").replace("]","").replace(", ",""); //arruma esteticamente a lista
        String novaPerugunta = linhasTxt.size() + 1 + " - " + leitor.nextLine(); // gerar a nova pergunta, numero baseado no tamanho da lista texto
        //bloco para reescrever o txt das perguntas
        OutputStream os = new FileOutputStream(caminhoPerguntas);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);
        //
        br.write( linhasLimpas + novaPerugunta); //escreve a lista de perguntas no txt
        br.close();
    }

    public void deletaPergunta() throws IOException {
            converteTxtParaString();
            if (linhasTxt.size() == 4){throw new IllegalArgumentException("Erro: Nenhuma pergunta cadastrada!");}
            System.out.println("-------------------------");
            System.out.println("Deseja apagar qual pergunta?");

            for (int i = 4; i < linhasTxt.size(); i++) {
                System.out.println(linhasTxt.get(i).toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(", ", "")
                        .replace("\n", "")); //arruma esteticamente a lista;
            }
            System.out.println("-------------------------");
            int perguntaDeletavel = leitor.nextInt();
        if (perguntaDeletavel > linhasTxt.size()) {throw new IndexOutOfBoundsException("Erro: Pergunta ("+  perguntaDeletavel + ") não " + "existe");}
            if (perguntaDeletavel <= 4) {throw new IllegalArgumentException("                Atenção!!!" + "\n" + "Não é possivel deletar as perguntas padrão");
            } else
                linhasTxt.remove(perguntaDeletavel - 1);

            converteTxtParaString();
            refresh();

}
    public List<String> getLinhasTxt() {
        return linhasTxt;
    }

    public Scanner getLeitorTxt() {
        return leitorTxt;
    }
}
