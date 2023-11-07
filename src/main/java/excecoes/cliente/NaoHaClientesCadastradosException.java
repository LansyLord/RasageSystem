package excecoes.cliente;

public class NaoHaClientesCadastradosException extends Exception {

    public NaoHaClientesCadastradosException(){
        super();
    }
    public NaoHaClientesCadastradosException(String mensagem){
        super(mensagem);
    }
}
