public class Servico {
    private double valor;
    private CodigoServico codigoServico;

    public Servico(double valor, CodigoServico codigoServico){
        this.valor = valor;
        this.codigoServico = codigoServico;
    }
    public Servico(){
        this(0.0, null);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public CodigoServico getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(CodigoServico codigoServico) {
        this.codigoServico = codigoServico;
    }
}
