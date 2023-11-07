package excecoes.comanda;

public class NaoHaComandasNoSistemaException extends Exception{

    public NaoHaComandasNoSistemaException() {
        super();
    }

    public NaoHaComandasNoSistemaException(String mensagem){
        super(mensagem);
    }
}
