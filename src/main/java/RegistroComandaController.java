import excecoes.comanda.ComandaJaExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegistroComandaController implements ActionListener {
    private SalaoInterface rasage;
    private MainMenu janelaPrincipal;

    public RegistroComandaController(SalaoInterface sistemaSalao, MainMenu janelaPrincipal) {
        this.rasage = sistemaSalao;
        this.janelaPrincipal = janelaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (rasage.getClientes().isEmpty())
            JOptionPane.showMessageDialog(janelaPrincipal, "Não há clientes cadastrados, cadastre pelo menos um cliente");
        else {
            JFrame frame = new JFrame("Registro de Comanda");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            JPanel panelRegistroComanda = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JComboBox<Cliente> clienteComboBox = new JComboBox<>(this.rasage.getClientes().toArray(new Cliente[0]));
            addComponent(panelRegistroComanda, new JLabel("Cliente: "), gbc, 0, 0);
            addComponent(panelRegistroComanda, clienteComboBox, gbc, 1, 0);

            ArrayList<Servico> listaServicos = getServicos();


            JComboBox<Servico> servicoComboBox = new JComboBox<>(listaServicos.toArray(new Servico[0]));
            addComponent(panelRegistroComanda, new JLabel("Serviço: "), gbc, 0, 1);
            addComponent(panelRegistroComanda, servicoComboBox, gbc, 1, 1);

            String[] tiposPagamento = {"Crédito", "Débito", "Dinheiro ou Pix"};
            JComboBox<String> pagamentoComboBox = new JComboBox<>(tiposPagamento);
            addComponent(panelRegistroComanda, new JLabel("Tipo de pagamento: "), gbc, 0, 2);
            addComponent(panelRegistroComanda, pagamentoComboBox, gbc, 1, 2);

            JLabel dataLabel = new JLabel("Data: ");
            JTextField dataField = new JTextField(20);
            addComponent(panelRegistroComanda, dataLabel, gbc, 0, 3);
            addComponent(panelRegistroComanda, dataField, gbc, 1, 3);

            JButton registroBtn = new JButton("Registrar");


            registroBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Cliente cliente = (Cliente) clienteComboBox.getSelectedItem();
                    Servico servico = (Servico) servicoComboBox.getSelectedItem();
                    String pagamento = (String) pagamentoComboBox.getSelectedItem();
                    String data = (String) dataField.getText();

                    Comanda comanda = new Comanda(cliente, servico, pagamento, data);
                    try {
                        if (rasage.registrarComanda(comanda)) {
                            JOptionPane.showMessageDialog(janelaPrincipal, "Comanda registrada com sucesso!");
                            janelaPrincipal.atualizarTabelaComandas();
                        }
                        frame.dispose();

                    } catch (ComandaJaExisteException erro) {
                        JOptionPane.showMessageDialog(janelaPrincipal, erro.getMessage());
                    }
                }
            });

            addComponent(panelRegistroComanda, registroBtn, gbc, 1, 4);

            frame.add(panelRegistroComanda);
            frame.setVisible(true);
        }
    }


    private void addComponent(Container container, Component component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(component, gbc);
    }


    private static ArrayList<Servico> getServicos() {
        ArrayList<Servico> listaServicos = new ArrayList<>();
        //Unha
        Servico manicure = new Unha(true, false);
        Servico pedicure = new Unha(false, true);
        Servico manicurePedicure = new Unha(true, true);
        listaServicos.add(manicure);
        listaServicos.add(pedicure);
        listaServicos.add(manicurePedicure);

        //Cabelo
        Servico corte = new Cabelo(true, false, false);
        Servico corteSelagem = new Cabelo(true, false, true);
        Servico corteEscova = new Cabelo(true, true, false);
        Servico selagem = new Cabelo(false, false, true);
        Servico escova = new Cabelo(false, true, false);
        listaServicos.add(corte);
        listaServicos.add(corteSelagem);
        listaServicos.add(corteEscova);
        listaServicos.add(selagem);
        listaServicos.add(escova);

        //Depilacao
        Servico barbaC = new Depilacao(Depilacao.CERA, "Barba");
        Servico barbaF = new Depilacao(Depilacao.FOTO_DEPILACAO, "Barba");
        Servico barbaL = new Depilacao(Depilacao.LAMINA, "Barba");
        Servico axilaC = new Depilacao(Depilacao.CERA, "Axila");
        Servico axilaF = new Depilacao(Depilacao.FOTO_DEPILACAO, "Axila");
        Servico axilaL = new Depilacao(Depilacao.LAMINA, "Axila");
        Servico pernaC = new Depilacao(Depilacao.CERA, "Pernas");
        Servico pernaF = new Depilacao(Depilacao.FOTO_DEPILACAO, "Pernas");
        Servico pernaL = new Depilacao(Depilacao.LAMINA, "Pernas");
        listaServicos.add(barbaC);
        listaServicos.add(barbaF);
        listaServicos.add(barbaL);
        listaServicos.add(axilaC);
        listaServicos.add(axilaF);
        listaServicos.add(axilaL);
        listaServicos.add(pernaC);
        listaServicos.add(pernaF);
        listaServicos.add(pernaL);
        return listaServicos;
    }
}