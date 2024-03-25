import excecoes.cliente.ClienteJaCadastradoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroClienteController implements ActionListener {
    SalaoInterface rasage;
    JFrame janelaPrincipal;

    public CadastroClienteController(SalaoInterface sistemaSalao, JFrame janelaPrincipal){
        this.rasage = sistemaSalao;
        this.janelaPrincipal = janelaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame frame = new JFrame("Formulário de Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel para o formulário
        JPanel panel = new JPanel(new BorderLayout());

        // Painel para os rótulos e campos de texto
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Componentes
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);
        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField(20);
        JLabel lblNumeroCelular = new JLabel("Número de Celular:");
        JTextField txtNumeroCelular = new JTextField(20);

        // Adicionando componentes ao painel do formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblNome, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNome, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblCPF, gbc);
        gbc.gridx = 1;
        formPanel.add(txtCPF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblNumeroCelular, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNumeroCelular, gbc);

        // Botão de cadastro
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String cpf = txtCPF.getText();
                String numeroCelular = txtNumeroCelular.getText();
                Cliente cliente = new Cliente(nome, cpf, numeroCelular);

                try {
                    if(rasage.cadastrarCliente(cliente))
                        JOptionPane.showMessageDialog(frame,"Cliente cadastrado com sucesso!");
                    frame.dispose();
                } catch (ClienteJaCadastradoException erro) {
                    JOptionPane.showMessageDialog(frame, erro.getMessage());
                }
            }
        });

        // Adicionando componentes ao painel principal
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(btnCadastrar, BorderLayout.SOUTH);

        // Adicionando o painel principal ao frame
        frame.getContentPane().add(panel);

        frame.pack(); // Ajusta o tamanho do frame conforme o conteúdo
        frame.setLocationRelativeTo(null); // Centraliza o frame na tela
        frame.setVisible(true);
    }
}