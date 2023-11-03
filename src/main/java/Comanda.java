import java.util.Objects;

public class Comanda {
    private Cliente cliente;
    private Servico servico;
    private String tipoPagamento;
    public static final String CREDITO = "Crédito";
    public static final String DEBITO = "Débito";
    public static final String DINHEIRO_OU_PIX = "Dinheiro ou Pix";
    private String data;
    private CodigoServico cdigoServico;

    public Comanda(Cliente cliente, Servico servico, String tipoPagamento, String data, CodigoServico codigoServico){
        this.cliente = cliente;
        this.servico = servico;
        this.tipoPagamento = tipoPagamento;
        this.data = data;
        this.cdigoServico = codigoServico;
    }

    public Comanda(){
        this(null, null, "Sem pagamento", "dd/mm/yyyy", null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return Objects.equals(servico, comanda.servico) && Objects.equals(tipoPagamento, comanda.tipoPagamento)
                && Objects.equals(data, comanda.data) && cdigoServico == comanda.cdigoServico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(servico, tipoPagamento, data, cdigoServico);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public CodigoServico getCdigoServico() {
        return cdigoServico;
    }

    public void setCdigoServico(CodigoServico cdigoServico) {
        this.cdigoServico = cdigoServico;
    }

    @Override
    public String toString(){
        return "Nome do cliente: "+ cliente.getNome()+
                "CPF: "+ cliente.getCpf()+
                "Número de Celular: "+ cliente.getNumCelular()+
                "Serviço realizado: "+ this.servico+
                "Código do Serviço: "+ this.cdigoServico+
                "Data: "+ this.data+
                "Valor pago: "+ servico.getValor()+
                "Tipo de pagamento: "+ this.tipoPagamento;

    }
}
