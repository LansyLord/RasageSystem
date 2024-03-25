import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.cliente.NaoHaClientesCadastradosException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.DataSemComandaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaRasage {
    public static void main(String[] args) {


        SalaoInterface rasageSystem = new Rasage();

//        try{
//            rasageSystem.recuperarDadosComandas();
//            rasageSystem.recuperarDadosClientes();
//            JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso!");
//        }catch (IOException e){
//            JOptionPane.showMessageDialog(null, "Não há dados para recuperar!");
//        }

        int escolha = -1;
        while (escolha != 2) {
            String[] opcoesIniciais = {"Gerenciar Comandas",
                    "Gerenciar Clientes",
                    "Sair"};

            escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "RasageSystem",
                    0, JOptionPane.PLAIN_MESSAGE, null, opcoesIniciais, opcoesIniciais[0]);

            switch (escolha) {
                case 0:
                    int escolhaComanda = -1;
                    while (escolhaComanda != 4) {
                        String[] opcoesComanda = {"Registrar Comanda",
                                "Exibir Comandas",
                                "Pesquisar Comandas por data",
                                "Apagar Comanda",
                                "Voltar"};

                        escolhaComanda = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "RasageSystem/GerenciarComandas",
                                0, JOptionPane.PLAIN_MESSAGE, null, opcoesComanda, opcoesComanda[0]);

                        switch (escolhaComanda) {
                            case 0:
                                if (!rasageSystem.getClientes().isEmpty()) {
                                    JPanel panelRegistroComanda = new JPanel();
                                    panelRegistroComanda.setLayout(new GridLayout(5, 1));

                                    JComboBox<Cliente> clienteComboBox = new JComboBox<>(rasageSystem.getClientes().toArray(new Cliente[0]));
                                    panelRegistroComanda.add(new JLabel("Cliente: "));
                                    panelRegistroComanda.add(clienteComboBox);

                                    ArrayList<String> listaServicos = new ArrayList<>() {{
                                        //Unha
                                        add("Manicure");
                                        add("Pedicure");
                                        add("Manicure e Pedicure");
                                        //Cabelo
                                        add("Corte");
                                        add("Corte e Selagem");
                                        add("Corte e Escova");
                                        add("Selagem");
                                        add("Escova");
                                        //Depilacao
                                        add("Barba na cera");
                                        add("Barba na foto depilação");
                                        add("Barba na lâmina");
                                        add("Axila na cera");
                                        add("Axila na foto depilação");
                                        add("Axila na lâmina");
                                        add("Pernas na cera");
                                        add("Pernas na foto depilação");
                                        add("Pernas na lâmina");
                                    }};

                                    JComboBox<String> servicoComboBox = new JComboBox<>(listaServicos.toArray(new String[0]));
                                    panelRegistroComanda.add(new JLabel("Serviço: "));
                                    panelRegistroComanda.add(servicoComboBox);

                                    String[] tiposPagamento = {"Crédito", "Débito", "Dinheiro ou Pix"};

                                    JComboBox<String> pagamentoComboBox = new JComboBox<>(tiposPagamento);
                                    panelRegistroComanda.add(new JLabel("Tipo de pagamento: "));
                                    panelRegistroComanda.add(pagamentoComboBox);

                                    JLabel dataLabel = new JLabel("Data: ");
                                    JTextField dataField = new JTextField(20);
                                    panelRegistroComanda.add(dataLabel);
                                    panelRegistroComanda.add(dataField);

                                    int result = JOptionPane.showConfirmDialog(null, panelRegistroComanda, "Registrar Comanda", JOptionPane.OK_CANCEL_OPTION);

                                    if (result == JOptionPane.OK_OPTION) {
                                        Cliente clienteSelecionado = (Cliente) clienteComboBox.getSelectedItem();
                                        String servicoSelecionado = (String) servicoComboBox.getSelectedItem();
                                        Servico servicoComanda = new Unha();
                                        switch (servicoSelecionado) {

                                            //Caso o servico seja Unha
                                            case "Manicure":
                                                servicoComanda = new Unha(true, false, "", "", "");
                                                break;
                                            case "Pedicure":
                                                servicoComanda = new Unha(false, true, "", "", "");
                                                break;
                                            case "Manicure e Pedicure":
                                                servicoComanda = new Unha(true, true, "", "", "");
                                                break;

                                            //Caso o servico seja Cabelo
                                            case "Corte":
                                                servicoComanda = new Cabelo(true, false, false);
                                                break;
                                            case "Corte e Selagem":
                                                servicoComanda = new Cabelo(true, false, true);
                                                break;
                                            case "Corte e Escova":
                                                servicoComanda = new Cabelo(true, true, false);
                                                break;
                                            case "Selagem":
                                                servicoComanda = new Cabelo(false, false, true);
                                                break;
                                            case "Escova":
                                                servicoComanda = new Cabelo(false, true, false);
                                                break;

                                            //Caso o servico seja Depilacao
                                            case "Barba na cera":
                                                servicoComanda = new Depilacao("Cera", "Barba");
                                                break;
                                            case "Barba na foto depilação":
                                                servicoComanda = new Depilacao("Foto Depilação", "Barba");
                                                break;
                                            case "Barba na lâmina":
                                                servicoComanda = new Depilacao("Lâmina", "Barba");
                                                break;
                                            case "Axila na cera":
                                                servicoComanda = new Depilacao("Cera", "Axila");
                                                break;
                                            case "Axila na foto depilação":
                                                servicoComanda = new Depilacao("Foto Depilação", "Axila");
                                                break;
                                            case "Axila na lâmina":
                                                servicoComanda = new Depilacao("Lâmina", "Axila");
                                                break;
                                            case "Pernas na cera":
                                                servicoComanda = new Depilacao("Cera", "Pernas");
                                                break;
                                            case "Pernas na foto depilação":
                                                servicoComanda = new Depilacao("Foto Depilação", "Pernas");
                                                break;
                                            case "Pernas na lâmina":
                                                servicoComanda = new Depilacao("Lâmina", "Pernas");
                                                break;
                                        }

                                        String tipoPagamentoSelecionado = (String) pagamentoComboBox.getSelectedItem();
                                        String data = dataField.getText();

                                        Comanda comanda = new Comanda(clienteSelecionado, servicoComanda, tipoPagamentoSelecionado, data);
                                        comanda.setId(rasageSystem.getComandas().size() + 1);
                                        try {
                                            if (rasageSystem.registrarComanda(comanda)) {
                                                JOptionPane.showMessageDialog(null, "Comanda cadastrada com sucesso!");
                                            }
                                        } catch (ComandaJaExisteException e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                        }
                                    }
                                    break;
                                }
                                JOptionPane.showMessageDialog(null, "Não existe nenhum cliente cadastrado!\nCadastre um cliente primeiro!");
                                break;

                            case 1:
                                try {
                                    exibirComandas(rasageSystem.listarComandas());
                                } catch (NaoHaComandasNoSistemaException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;

                            case 2:
                                if (rasageSystem.getComandas().size() > 0) {
                                    String dataComanda = JOptionPane.showInputDialog(null, "Insira a data a pesquisar");
                                    try {
                                        exibirComandas(rasageSystem.pesquisarComandasPorData(dataComanda));
                                    } catch (DataSemComandaException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma comanda cadastrada no sistema!");
                                }
                                break;

                            case 3:
                                if (rasageSystem.getComandas().size() > 0) {
                                    int idComanda = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o ID da comanda a ser apagada"));

                                    if (rasageSystem.apagarComanda(idComanda)) {
                                        JOptionPane.showMessageDialog(null, "Comanda apagada com sucesso!");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Comanda não encontrada");
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Não há nenhuma comanda cadastrada no sistema!");
                                }
                        }
                    }
                    break;

                case 1:
                    int escolhaCliente = -1;
                    while (escolhaCliente != 4) {
                        String[] opcoesCliente = {"Cadastrar Cliente",
                                "Listar Clientes",
                                "Pesquisar Cliente",
                                "Apagar Cliente",
                                "Voltar"};

                        escolhaCliente = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "RasageSystem/GerenciarClientes",
                                0, JOptionPane.PLAIN_MESSAGE, null, opcoesCliente, opcoesCliente[0]);

                        switch (escolhaCliente) {
                            case 0:
                                JPanel panelCadastroCliente = new JPanel();
                                panelCadastroCliente.setLayout(new GridLayout(3, 2));

                                JLabel nomeLabel = new JLabel("Nome: ");
                                JTextField nomeField = new JTextField(20);
                                panelCadastroCliente.add(nomeLabel);
                                panelCadastroCliente.add(nomeField);

                                JLabel cpfLabel = new JLabel("CPF: ");
                                JTextField cpfField = new JTextField(20);
                                panelCadastroCliente.add(cpfLabel);
                                panelCadastroCliente.add(cpfField);

                                JLabel numCelularLabel = new JLabel("Número de Celular: ");
                                JTextField numCelularField = new JTextField(20);
                                panelCadastroCliente.add(numCelularLabel);
                                panelCadastroCliente.add(numCelularField);

                                int result = JOptionPane.showConfirmDialog(null, panelCadastroCliente, "Cadastro de Cliente", JOptionPane.OK_CANCEL_OPTION);

                                if (result == JOptionPane.OK_OPTION) {
                                    String nome = nomeField.getText();
                                    String cpf = cpfField.getText();
                                    String numCelular = numCelularField.getText();

                                    Cliente cliente = new Cliente(nome, cpf, numCelular);
                                    try {
                                        if (rasageSystem.cadastrarCliente(cliente)) {
                                            JOptionPane.showMessageDialog(null, "Cliente " + cliente.getNome() + " cadastrado com sucesso!");
                                        }
                                    } catch (ClienteJaCadastradoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                }
                                break;

                            case 1:
                                try {
                                    listarClientes(rasageSystem.listarClientes());
                                } catch (NaoHaClientesCadastradosException e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                                break;

                            case 2:
                                if (rasageSystem.getClientes().size() > 0) {
                                    String nomeCliente = JOptionPane.showInputDialog(null, "Insira o nome do cliente a pesquisar");
                                    String cpfCliente = JOptionPane.showInputDialog(null, "Insira o CPF do cliente a pesquisar");
                                    try {
                                        exibirCliente(rasageSystem.pesquisarCliente(cpfCliente));
                                    } catch (ClienteNaoExisteException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Não há nenhum cliente cadastrado no sistema!");
                                }
                                break;

                            case 3:
                                if (rasageSystem.getClientes().size() > 0) {
                                    String cpfCliente = JOptionPane.showInputDialog(null, "Insira o CPF do cliente a ser apagado");
                                    if (rasageSystem.apagarCliente(cpfCliente)) {
                                        JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso!");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Não há nenhum cliente cadastrado no sistema!");
                                }

                        }
                    }
                    break;

                case 2:
//                    try{
//                        rasageSystem.salvarDadosComandas();
//                        rasageSystem.salvarDadosClientes();
//                        JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
//                    }catch (IOException e){
//                        JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!");
//                    }
            }
        }
    }

    public static void exibirComandas(List<Comanda> comandas) {
        StringBuilder formattedComandas = new StringBuilder();

        for (Comanda comanda : comandas) {
            formattedComandas.append("------------------------------------------------\n");
            formattedComandas.append("ID da comanda: ").append(comanda.getId()).append("\n");
            formattedComandas.append("Nome do cliente: ").append(comanda.getCliente().getNome()).append("\n");
            formattedComandas.append("CPF: ").append(comanda.getCliente().getCpf()).append("\n");
            formattedComandas.append("Número de Celular: ").append(comanda.getCliente().getNumCelular()).append("\n");
            formattedComandas.append("Serviço realizado: ").append(comanda.getServico()).append("\n");
            formattedComandas.append("Código do Serviço: ").append(comanda.getServico().getCodigoServico()).append("\n");
            formattedComandas.append("Data: ").append(comanda.getData()).append("\n");
            formattedComandas.append("Valor pago: ").append(comanda.getServico().getValor()).append("\n");
            formattedComandas.append("Tipo de pagamento: ").append(comanda.getTipoPagamento()).append("\n");
            formattedComandas.append("------------------------------------------------\n\n");
        }

        JTextArea textArea = new JTextArea(formattedComandas.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Comandas", JOptionPane.PLAIN_MESSAGE);
    }

    public static void listarClientes(List<Cliente> clientes) {
        StringBuilder formattedClientes = new StringBuilder();

        for (Cliente cliente : clientes) {
            formattedClientes.append("------------------------------------------------\n");
            formattedClientes.append("Nome do Cliente: ").append(cliente.getNome()).append("\n");
            formattedClientes.append("CPF: ").append(cliente.getCpf()).append("\n");
            formattedClientes.append("Número de Celular: ").append(cliente.getNumCelular()).append("\n");
            formattedClientes.append("------------------------------------------------\n\n");
        }

        JTextArea textArea = new JTextArea(formattedClientes.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Clientes", JOptionPane.PLAIN_MESSAGE);
    }

    public static void exibirCliente(Cliente cliente) {
        StringBuilder formattedCliente = new StringBuilder();

        formattedCliente.append("Nome do Cliente: ").append(cliente.getNome()).append("\n");
        formattedCliente.append("CPF: ").append(cliente.getCpf()).append("\n");
        formattedCliente.append("Número de Celular: ").append(cliente.getNumCelular()).append("\n");

        JTextArea textArea = new JTextArea(formattedCliente.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(null, scrollPane, "Detalhes do Cliente " + cliente.getNome(), JOptionPane.PLAIN_MESSAGE);
    }
}