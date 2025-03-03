public class Pergunta {
    protected int numero;
    protected String texto;

    public Pergunta(int numero, String texto) {
        this.numero = numero;
        this.texto = texto;
    }

    @Override
    public String toString() {
        return  numero + " - " + texto;
    }
}
