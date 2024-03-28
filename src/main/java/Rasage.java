import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import java.io.IOException;
import java.util.*;

public class Rasage implements SalaoInterface {
    private Map<Integer, Comanda> comandas;
    private Map<String, Cliente> clientes;
    private GerenciadorDeDados gerenciadorDadosComanda;
    private GerenciadorDeDados gerenciadorDadosCliente;

    public Rasage(Map<Integer, Comanda> comandas, Map<String, Cliente> clientes) {
        this.comandas = comandas;
        this.clientes = clientes;
        gerenciadorDadosComanda = new GerenciadorDeDados();
        gerenciadorDadosCliente = new GerenciadorDeDados();
    }

    public Rasage() {
        this.comandas = new HashMap<>();
        this.clientes = new HashMap<>();
        gerenciadorDadosComanda = new GerenciadorDeDados();
        gerenciadorDadosCliente = new GerenciadorDeDados();
    }

    @Override
    public boolean registrarComanda(Comanda comanda) throws ComandaJaExisteException {
        if (!this.comandas.containsKey(comanda.getId())) {
            this.comandas.put(comanda.getId(), comanda);
            return true;
        }else
            throw new ComandaJaExisteException("Comanda " + comanda.toString() + " já existe no sistema!");
    }


    @Override
    public List<Comanda> listarComandas() throws NaoHaComandasNoSistemaException {
        if (!this.comandas.isEmpty()) {
            return new ArrayList<>(this.comandas.values());
        }
        throw new NaoHaComandasNoSistemaException("Não há nenhuma comanda cadastrada no sistema!");
    }

    @Override
    public List<Comanda> pesquisarComandasPorData(String data) throws DataSemComandaException {
        boolean existeComanda = false;
        List<Comanda> comandasDaData = new ArrayList<>();
        for (Comanda cm : this.comandas.values()) {
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
        for (Comanda cm : this.comandas.values()) {
            if (cm.getId() == (id)) {
                this.comandas.remove(cm);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException {

        if (!this.clientes.containsKey(cliente.getCpf())) {
            this.clientes.put(cliente.getCpf(), cliente);
            return true;
        }else
            throw new ClienteJaCadastradoException("Cliente de nome " + cliente.getNome() +
                " e de CPF " + cliente.getCpf() + " já está cadastrado!");

    }

    @Override
    public List<Cliente> listarClientes() throws NaoHaClientesCadastradosException {
        if (!this.clientes.isEmpty()) {
            return new ArrayList<>(this.clientes.values());
        }
        throw new NaoHaClientesCadastradosException("Não há nenhum cliente cadastrado no sistema!");
    }

    @Override
    public Cliente pesquisarCliente(String cpf) throws ClienteNaoExisteException {
        if (this.clientes.containsKey(cpf)) {
            return this.clientes.get(cpf);
        }
        throw new ClienteNaoExisteException("O cliente de CPF " + cpf + " não está cadastrado!");
    }

    @Override
    public boolean apagarCliente(String cpf) {
        this.clientes.remove(cpf);
        return false;
    }

    @Override
    public Collection<Cliente> getClientes() {
        return this.clientes.values();
    }

    @Override
    public void recuperarDadosComandas() {
        try {
            this.gerenciadorDadosComanda.recuperarComandas();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void salvarDadosComandas() {
        try {
            this.gerenciadorDadosComanda.salvarComandas(this.comandas);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void recuperarDadosClientes() throws IOException {
        if (!this.clientes.isEmpty()) {
            try {
                this.gerenciadorDadosCliente.recuperarClientes();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void salvarDadosClientes() throws IOException {
        if (!this.clientes.isEmpty()) {
            try {
                this.gerenciadorDadosCliente.salvarClientes(this.clientes);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public Collection<Comanda> getComandas() {
        return this.comandas.values();
    }
}