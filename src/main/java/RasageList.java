import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import java.util.ArrayList;
import java.util.List;

public class RasageList implements RasageInterface {
    private List<Comanda> comandas;
    private List<Cliente> clientes;

    public RasageList(List<Comanda> comandas, List<Cliente> clientes) {
        this.comandas = comandas;
        this.clientes = clientes;
    }

    public RasageList() {
        this.comandas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    @Override
    public boolean registrarComanda(Comanda comanda) throws ComandaJaExisteException {
        if (this.comandas.size() > 0) {
            for (Comanda cm : this.comandas) {
                if (cm.equals(comanda)) {
                    throw new ComandaJaExisteException("Comanda " + cm.toString() + " já existe no sistema!");
                }
            }
        }
        this.comandas.add(comanda);
        return true;
    }

    @Override
    public List<Comanda> listarComandas() throws NaoHaComandasNoSistemaException {
        if (this.comandas.size() > 0) {
            return this.comandas;
        }
        throw new NaoHaComandasNoSistemaException("Não há nenhuma comanda cadastrada no sistema!");
    }

    @Override
    public List<Comanda> pesquisarComandasPorData(String data) throws DataSemComandaException {
        boolean existeComanda = false;
        List<Comanda> comandasDaData = new ArrayList<>();
        for (Comanda cm : this.comandas) {
            if (cm.getData().equals(data)) {
                comandasDaData.add(cm);
                existeComanda = true;
            }
        }
        if (existeComanda) {
            return comandasDaData;
        }
        throw new DataSemComandaException("Não há nenhuma comanda de data "
                + data + " cadastrada no sistema!");
    }

    @Override
    public boolean apagarComanda(int id) {
        for (Comanda cm : this.comandas) {
            if (cm.getId() == (id)) {
                this.comandas.remove(cm);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException {
        if (this.clientes.size() > 0) {
            for (Cliente cl : this.clientes) {
                if (cl.equals(cliente)) {
                    throw new ClienteJaCadastradoException("Cliente de nome " + cl.getNome() +
                            "e de CPF " + cl.getCpf() + " já está cadastrado!");
                }
            }
        }
        this.clientes.add(cliente);
        return true;
    }

    @Override
    public List<Cliente> listarClientes() throws NaoHaClientesCadastradosException {
        if (this.clientes.size() > 0) {
            return this.clientes;
        }
        throw new NaoHaClientesCadastradosException("Não há nenhum cliente cadastrado no sistema!");
    }

    @Override
    public Cliente pesquisarCliente(String nome, String cpf) throws ClienteNaoExisteException {
        for (Cliente cl : this.clientes) {
            if (cl.getNome().equalsIgnoreCase(nome) && cl.getCpf().equalsIgnoreCase(cpf)) {
                return cl;
            }
        }
        throw new ClienteNaoExisteException("O cliente de nome " + nome
                + "e CPF " + cpf + " não está cadastrado!");
    }

    @Override
    public boolean apagarCliente(String cpf) {
        for (Cliente cl : this.clientes) {
            if (cl.getCpf().equals(cpf)) {
                clientes.remove(cl);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Cliente> getClientes() {
        return this.clientes;
    }

    @Override
    public List<Comanda> getComandas() {
        return this.comandas;
    }
}