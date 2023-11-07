package excecoes.cliente;

public class ClienteNaoExisteException extends Exception{

    public ClienteNaoExisteException(){
        super();
    }

    public ClienteNaoExisteException(String mensagem){
        super(mensagem);
    }
}
