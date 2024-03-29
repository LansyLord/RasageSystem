import java.io.Serializable;

public class Servico implements Serializable {
    private double valor;
    private CodigoServico codigoServico;

    public Servico(double valor, CodigoServico codigoServico) {
        this.valor = valor;
        this.codigoServico = codigoServico;
    }

    public Servico() {
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

    public String toString() {
        if (this.codigoServico == CodigoServico.UNHA) return "Pedicure,Manicure,Pedicure e Manicure";

        if (this.codigoServico == CodigoServico.CABELO) return "Corte e Selagem de cabelo,Corte e escova de cabelo" +
                ",Corte de cabelo,Selagem de cabelo,Escova de cabelo";

        if (this.codigoServico == CodigoServico.DEPILACAO) return "Depilação de barba na cera,Depilação de barba" +
                " na foto depiliação,Depilação de barba na lâmina,Depilação de axila na cera,Depilação de axila na" +
                " foto depilação,Depilação de axila na lâmina,Depilação de pernas na cera,Depilação de pernas na" +
                " foto depilação,Depilação de pernas na lâmina";
        return "";
    }
}
