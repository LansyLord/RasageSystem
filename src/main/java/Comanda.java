import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Comanda implements Serializable {
    private int id;
    private static int idAnterior = 0;
    private Cliente cliente;
    private Servico servico;
    private String tipoPagamento;
    public static final String CREDITO = "Crédito";
    public static final String DEBITO = "Débito";
    public static final String DINHEIRO_OU_PIX = "Dinheiro ou Pix";
    private transient DateTimeFormatter f;
    private String data;


    public Comanda(Cliente cliente, Servico servico, String tipoPagamento) {
        this.id = ++idAnterior;
        this.cliente = cliente;
        this.servico = servico;
        this.tipoPagamento = tipoPagamento;
        this.f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.now().format(f);
    }

    public Comanda(int id, Cliente cliente, Servico servico, String tipoPagamento) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.tipoPagamento = tipoPagamento;
        this.f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.now().format(f);
    }

    public Comanda() {
        this(new Cliente(), null, "Sem pagamento");
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
        this.cliente.setNome(cliente.getNome());
        this.cliente.setCpf(cliente.getCpf());
        this.cliente.setNumCelular(cliente.getNumCelular());
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

    public int getId() {
        return this.id;
    }

    public void setId(int novoId) {
        this.id = novoId;
    }

    @Override
    public String toString() {
        return "------------------------------------------------" +
                "\nID: " + this.id +
                "\nNome do cliente: " + cliente.getNome() +
                "\nCPF: " + cliente.getCpf() +
                "\nNúmero de Celular: " + cliente.getNumCelular() +
                "\nServiço realizado: " + this.servico +
                "\nCódigo do serviço: " + servico.getCodigoServico() +
                "\nData: " + this.data +
                "\nValor pago: " + servico.getValor() +
                "\nTipo de pagamento: " + this.tipoPagamento +
                "\n------------------------------------------------";

    }
}
