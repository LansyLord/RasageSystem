import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.ComandaNaoEncontradaException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import java.io.IOException;
import java.util.*;

public class Rasage implements SalaoInterface {
    private Map<Integer, Comanda> comandas;
    private Map<String, Cliente> clientes;
    private GerenciadorDeDados gerenciadorDadosComanda = new GerenciadorDeDados();
    private GerenciadorDeDados gerenciadorDadosCliente = new GerenciadorDeDados();

    public Rasage() {
        this.comandas = new HashMap<>();
        this.clientes = new HashMap<>();
        recuperarDadosClientes();
        recuperarDadosComandas();
    }

    @Override
    public boolean registrarComanda(Comanda comanda) {
        if (!this.comandas.containsKey(comanda.getId())) {
            this.comandas.put(comanda.getId(), comanda);
            return true;
        } else {
            comanda.setId(comanda.getId() + 1);
            return true;
        }
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
    public List<Comanda> pesquisaComandasPorCliente(String dadoDeBusca) throws ComandaNaoEncontradaException {
        List<Comanda> cList = new ArrayList<>();
        for(Comanda c: this.comandas.values()){
            if(c.getCliente().getCpf().equals(dadoDeBusca) || c.getCliente().getNome().toLowerCase().startsWith(dadoDeBusca.toLowerCase())){
                cList.add(c);
            }
        }
        if(cList.isEmpty())
            throw new ComandaNaoEncontradaException("Comanda não encontrada no sistema");
        return cList;
    }

    @Override
    public List<Comanda> pesquisaComandasPorServico(String servico) throws ComandaNaoEncontradaException {
        List<Comanda> comandasDoServico = new ArrayList<>();
        String[] listaServicos = new String[0];

        for(Comanda c: this.comandas.values()){
            listaServicos = c.getServico().toString().split(",");
            for(String s: listaServicos){
              if(s.toLowerCase().startsWith(servico.toLowerCase())) comandasDoServico.add(c);
            }
        }
        if(comandasDoServico.isEmpty())
            throw new ComandaNaoEncontradaException("Comanda não encontrada");

        return comandasDoServico;
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
        } else
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
    public boolean existeCliente(String cpf) throws ClienteNaoExisteException {
        if (this.clientes.containsKey(cpf)) {
            return true;
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
            this.comandas = this.gerenciadorDadosComanda.recuperarComandas();
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
    public void recuperarDadosClientes() {
        try {
            this.clientes = this.gerenciadorDadosCliente.recuperarClientes();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void salvarDadosClientes() {
        try {
            this.gerenciadorDadosCliente.salvarClientes(this.clientes);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public Collection<Comanda> getComandas() {
        return this.comandas.values();
    }

    @Override
    public Map<Integer, Comanda> getComandasMap(){ return this.comandas; }

    @Override
    public Map<String, Cliente> getClientesMap(){ return this.clientes; }
}