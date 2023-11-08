import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RasageList implements RasageInterface {
    private List<Comanda> comandas;
    private List<Cliente> clientes;
    private GerenciadorDeDados gerenciadorDadosComanda;
    private GerenciadorDeDados gerenciadorDadosCliente;

    public RasageList(List<Comanda> comandas, List<Cliente> clientes) {
        this.comandas = comandas;
        this.clientes = clientes;
        gerenciadorDadosComanda = new GerenciadorDeDados("comandas.txt");
        gerenciadorDadosCliente = new GerenciadorDeDados("clientes.txt");
    }

    public RasageList() {
        this.comandas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        gerenciadorDadosComanda = new GerenciadorDeDados("comandas.txt");
        gerenciadorDadosCliente = new GerenciadorDeDados("clientes.txt");
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
    public void recuperarDadosComandas() throws IOException {
        List<String> comandasSalvas = gerenciadorDadosComanda.lerDados();
        for(String s: comandasSalvas){
            String [] dadosComandas = s.split("#");
            Cliente cliente = new Cliente(dadosComandas[1], dadosComandas[2], dadosComandas[3]);

            double valorComanda = Double.parseDouble(dadosComandas[7]);
            CodigoServico codigoServicoComanda = CodigoServico.valueOf(dadosComandas[5]);
            Servico servico = new Servico(valorComanda, codigoServicoComanda);

            int id = Integer.parseInt(dadosComandas[0]);
            Comanda comanda = new Comanda(id, cliente, servico, dadosComandas[8], dadosComandas[6]);

            this.comandas.add(comanda);
        }

    }

    @Override
    public void salvarDadosComandas() throws IOException {
        List<String> dadosComandas = new ArrayList<>();
        for(Comanda cm: this.comandas){
            dadosComandas.add(cm.getId()+"#"+cm.getCliente().getNome()+"#"+
                              cm.getCliente().getCpf()+"#"+cm.getCliente().getNumCelular()+"#"+
                              cm.getServico()+"#"+cm.getServico().getCodigoServico()+"#"+
                              cm.getData()+"#"+cm.getServico().getValor()+"#"+cm.getTipoPagamento());
        }
        gerenciadorDadosComanda.gravarDados(dadosComandas);
    }

    @Override
    public void recuperarDadosClientes() throws IOException {
        List<String> clientesSalvos = gerenciadorDadosCliente.lerDados();
        for(String s: clientesSalvos){
            String [] dadosClientes = s.split("#");
            Cliente cliente = new Cliente(dadosClientes[0], dadosClientes[1], dadosClientes[2]);
            this.clientes.add(cliente);
        }
    }

    @Override
    public void salvarDadosClientes() throws IOException {
        List<String> dadosClientes = new ArrayList<>();
        for(Cliente cl: this.clientes){
            dadosClientes.add(cl.getNome()+"#"+cl.getCpf()+"#"+cl.getNumCelular());
        }
        gerenciadorDadosCliente.gravarDados(dadosClientes);
    }

    @Override
    public List<Comanda> getComandas() {
        return this.comandas;
    }
}