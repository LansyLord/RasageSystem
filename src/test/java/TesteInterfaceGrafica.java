import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TesteInterfaceGrafica {
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        // Suponha que você já tenha alguns clientes cadastrados
        clientes.add(new Cliente("Cliente 1"));
        clientes.add(new Cliente("Cliente 2"));
        clientes.add(new Cliente("Cliente 3"));

        // Crie um JComboBox com os nomes dos clientes
        JComboBox<Cliente> clienteComboBox = new JComboBox<>(clientes.toArray(new Cliente[0]));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Cliente:"));
        panel.add(clienteComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Registrar Comanda", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Obtém o cliente selecionado
            Cliente clienteSelecionado = (Cliente) clienteComboBox.getSelectedItem();

            // Agora você pode usar 'clienteSelecionado' para registrar a comanda
            System.out.println("Comanda registrada para o cliente: " + clienteSelecionado.getNome());
        }
    }

    static class Cliente {
        private String nome;

        public Cliente(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return nome;
        }
    }
}
