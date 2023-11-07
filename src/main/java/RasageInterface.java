import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import java.util.List;

public interface RasageInterface {

    boolean registrarComanda(Comanda comanda) throws ComandaJaExisteException;
    List<Comanda> listarComandas() throws NaoHaComandasNoSistemaException;
    List<Comanda> pesquisarComandasPorData(String data) throws DataSemComandaException;
    boolean apagarComanda(int id);

    boolean cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException;
    List<Cliente> listarClientes() throws NaoHaClientesCadastradosException;
    Cliente pesquisarCliente(String nome, String cpf) throws ClienteNaoExisteException;
    boolean apagarCliente(String cpf);

    List<Comanda> getComandas();
    List<Cliente> getClientes();
}
