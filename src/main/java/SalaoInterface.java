import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface SalaoInterface {

    boolean registrarComanda(Comanda comanda) throws ComandaJaExisteException;
    List<Comanda> listarComandas() throws NaoHaComandasNoSistemaException;
    List<Comanda> pesquisarComandasPorData(String data) throws DataSemComandaException;
    boolean apagarComanda(int id);

    boolean cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException;
    List<Cliente> listarClientes() throws NaoHaClientesCadastradosException;
    Cliente pesquisarCliente(String cpf) throws ClienteNaoExisteException;
    boolean apagarCliente(String cpf);

    Collection<Comanda> getComandas();
    Collection<Cliente> getClientes();

    void recuperarDadosComandas() throws IOException;
    void salvarDadosComandas() throws IOException;
    void recuperarDadosClientes() throws IOException;
    void salvarDadosClientes() throws IOException;
}
