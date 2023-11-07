package excecoes.cliente;

public class ClienteJaCadastradoException extends Exception{

    public ClienteJaCadastradoException(){
        super();
    }

    public ClienteJaCadastradoException(String mensagem){
        super(mensagem);
    }
}
