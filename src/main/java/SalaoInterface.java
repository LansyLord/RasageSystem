import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaNaoEncontradaException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SalaoInterface {

    boolean registrarComanda(Comanda comanda);
    List<Comanda> listarComandas() throws NaoHaComandasNoSistemaException;
    List<Comanda> pesquisarComandasPorData(String data) throws DataSemComandaException;
    List<Comanda> pesquisaComandasPorCliente(String dadoDeBusca) throws ComandaNaoEncontradaException;
    List<Comanda> pesquisaComandasPorServico(String servico)throws ComandaNaoEncontradaException;
    boolean apagarComanda(int id);

    boolean cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException;
    List<Cliente> listarClientes() throws NaoHaClientesCadastradosException;
    boolean existeCliente(String cpf) throws ClienteNaoExisteException;
    boolean apagarCliente(String cpf);

    Collection<Comanda> getComandas();
    Collection<Cliente> getClientes();
    Map<Integer, Comanda> getComandasMap();
    Map<String, Cliente> getClientesMap();

    void recuperarDadosComandas() throws IOException;
    void salvarDadosComandas() throws IOException;
    void recuperarDadosClientes() throws IOException;
    void salvarDadosClientes() throws IOException;
}
