import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComandaDataSearchController implements ActionListener {
    private MainMenu janelaPrincipal;
    private SalaoInterface rasage;

    public ComandaDataSearchController(SalaoInterface salao, MainMenu janelaPrincipal){
        this.rasage = salao;
        this.janelaPrincipal = janelaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String data = JOptionPane.showInputDialog(janelaPrincipal, "Insira a data da comanda (dd/mm/yyyy):");
        StringBuilder formattedComandas = new StringBuilder();

        for (Comanda comanda : rasage.getComandas()) {
            if(comanda.getData().startsWith(data)){
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
        }

        JTextArea textArea = new JTextArea(formattedComandas.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Comandas na data "+ data,
                JOptionPane.PLAIN_MESSAGE);
    }
}
