package excecoes.comanda;

public class DataSemComandaException extends Exception{

    public DataSemComandaException(){
        super();
    }

    public DataSemComandaException(String mensagem){
        super(mensagem);
    }
}
