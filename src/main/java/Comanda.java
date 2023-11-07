import java.util.Objects;

public class Comanda {
    private int id;
    private Cliente cliente;
    private Servico servico;
    private String tipoPagamento;
    public static final String CREDITO = "Crédito";
    public static final String DEBITO = "Débito";
    public static final String DINHEIRO_OU_PIX = "Dinheiro ou Pix";
    private String data;

    private static int proximoID = 1;

    public Comanda(Cliente cliente, Servico servico, String tipoPagamento, String data){
        this.id = proximoID;
        this.cliente = cliente;
        this.servico = servico;
        this.tipoPagamento = tipoPagamento;
        this.data = data;

        proximoID++;
    }

    public Comanda(){
        this(null, null, "Sem pagamento", "dd/mm/yyyy");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return Objects.equals(servico, comanda.servico) && Objects.equals(tipoPagamento, comanda.tipoPagamento)
                && Objects.equals(data, comanda.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servico, tipoPagamento, data);
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

    public int getId(){
        return this.id;
    }



    @Override
    public String toString(){
        return  "------------------------------------------------"+
                "\nID: "+ this.id+
                "\nNome do cliente: "+ cliente.getNome()+
                "\nCPF: "+ cliente.getCpf()+
                "\nNúmero de Celular: "+ cliente.getNumCelular()+
                "\nServiço realizado: "+ this.servico+
                "\nData: "+ this.data+
                "\nValor pago: "+ servico.getValor()+
                "\nTipo de pagamento: "+ this.tipoPagamento+
                "\n------------------------------------------------";

    }
}
