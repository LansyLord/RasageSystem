import java.util.List;

public interface RasageInterface {
    boolean registrarComanda(Comanda comanda);
    Comanda atualizarComanda(Comanda comanda);
    List<Comanda> listarComandas();
    List<Comanda> pesquisarComandasPorData(String data);
    boolean apagarComanda(Comanda comanda);
//    boolean cadastrarCliente(Cliente cliente);
//    List<Cliente> listarClientes();
//    Cliente atualizarCliente(Cliente cliente);
//    boolean pesquisarCliente(String nome, String cpf);
//    boolean apagarCliente(Cliente cliente);
}
