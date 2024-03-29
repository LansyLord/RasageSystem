
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ComandaIDSearchController implements ActionListener {
    private SalaoInterface rasage;
    private MainMenu janelaPrincipal;

    public ComandaIDSearchController(SalaoInterface salao, MainMenu janelaPrincipal) {
        this.rasage = salao;
        this.janelaPrincipal = janelaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = Integer.parseInt(JOptionPane.showInputDialog(janelaPrincipal, "Insira o id da comanda"));

        if (rasage.getComandasMap().containsKey(id)){
            exibeComanda(janelaPrincipal, rasage.getComandasMap().get(id));
        }else
            JOptionPane.showMessageDialog(janelaPrincipal, "Comanda não encontrada");

    }

    public static void exibeComanda(JFrame janela, Comanda comanda) {

        String formattedComandas = "------------------------------------------------\n" +
                "ID da comanda: " + comanda.getId() + "\n" +
                "Nome do cliente: " + comanda.getCliente().getNome() + "\n" +
                "CPF: " + comanda.getCliente().getCpf() + "\n" +
                "Número de Celular: " + comanda.getCliente().getNumCelular() + "\n" +
                "Serviço realizado: " + comanda.getServico() + "\n" +
                "Data: " + comanda.getData() + "\n" +
                "Valor pago: " + comanda.getServico().getValor() + "\n" +
                "Tipo de pagamento: " + comanda.getTipoPagamento() + "\n" +
                "------------------------------------------------\n\n";


        JTextArea textArea = new JTextArea(formattedComandas);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(janela, scrollPane, "Comandas com ID "+ comanda.getId(),
                JOptionPane.PLAIN_MESSAGE);
    }
}
