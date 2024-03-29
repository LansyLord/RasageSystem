import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class MainMenu extends JFrame {
    JMenuBar operationsMenuBar = new JMenuBar();
    ImageIcon rasageLogoImg = new ImageIcon("./imgs/RasageLogo.jpg");
    Image rasageLogoRedonda = createRoundedImage(rasageLogoImg.getImage());
    Rasage rasageSys = new Rasage();

    private JTable comandasTable = new JTable(new DefaultTableModel(
            new Object[]{"ID", "Cliente", "CPF", "Celular", "Serviço", "Data", "Valor", "Pagamento"}, 0));

    public MainMenu() {
        setTitle("Rasage System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setIconImage(rasageLogoRedonda);
        setSize(800, 600); //tamanho da janela
        setLocation(150, 150);
        setResizable(false);
        setBackground(Color.white);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                rasageSys.salvarDadosClientes();
                rasageSys.salvarDadosComandas();

                int option = JOptionPane.showConfirmDialog(MainMenu.super.rootPane, "Tem certeza que deseja " +
                        "sair do progama?\n As alterações foram salvas automaticamente");
                if(option == JOptionPane.YES_OPTION)
                    dispose();

            }
        });

        JTable tableComandas = new JTable(rasageSys.getComandas().size(), 1);

        int ultimoId = 0;
        //Reconstruir tabela de comandas ao iniciar
        DefaultTableModel model = (DefaultTableModel) comandasTable.getModel();
        for(Comanda c: rasageSys.getComandas()){
            model.addRow(new Object[]{c.getId(),
                    c.getCliente().getNome(),
                    c.getCliente().getCpf(),
                    c.getCliente().getNumCelular(),
                    c.getServico(),
                    c.getData(),
                    c.getServico().getValor(),
                    c.getTipoPagamento()});
            ultimoId = c.getId();
        }

        //Menu de operações de Cliente
        JMenu menuCliente = getMenuCliente();

        //Menu de operações de Comanda
        JMenu menuComanda = getMenuComanda();

        operationsMenuBar.add(menuCliente);
        operationsMenuBar.add(menuComanda);
        setJMenuBar(operationsMenuBar);

        JLabel lblComandas = new JLabel("COMANDAS");
        getContentPane().add(lblComandas, BorderLayout.NORTH);

        // Adicionando a tabela a um painel com barra de rolagem
        JScrollPane scrollPane = new JScrollPane(comandasTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame janela = new MainMenu();
        janela.setVisible(true);

    }

    public void atualizarTabelaComandas() {
        DefaultTableModel model = (DefaultTableModel) comandasTable.getModel();

        Comanda ultimaComanda = null;
        for (Comanda c : rasageSys.getComandas()) {
            ultimaComanda = c;
        }
        //ultimaComanda.setIdAnterior(ultimaComanda.getId());

        model.addRow(new Object[]{ultimaComanda.getId(),
                ultimaComanda.getCliente().getNome(),
                ultimaComanda.getCliente().getCpf(),
                ultimaComanda.getCliente().getNumCelular(),
                ultimaComanda.getServico(),
                ultimaComanda.getData(),
                ultimaComanda.getServico().getValor(),
                ultimaComanda.getTipoPagamento()});

    }

    // Método para criar uma imagem com bordas redondas
    public static Image createRoundedImage(Image image) {
        BufferedImage roundedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Criando uma máscara elíptica para o recorte
        Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, image.getWidth(null), image.getHeight(null));

        // Obtendo o contexto gráfico da imagem
        Graphics2D g2 = roundedImage.createGraphics();
        g2.setClip(ellipse);

        // Desenhando a imagem dentro da forma elíptica
        g2.drawImage(image, 0, 0, null);

        // Liberando recursos
        g2.dispose();

        return roundedImage;
    }

    //Método para criação do menu cliente extraído para melhor leitura do código
    private JMenu getMenuCliente() {
        JMenu menuCliente = new JMenu("Cliente");
        JMenuItem cadastrarCliente = new JMenuItem("Novo Cliente");
        JMenuItem pesqisarCliente = new JMenuItem("Pesquisar");
        JMenuItem removerCliente = new JMenuItem("Remover");
        JMenuItem attDadosCliente = new JMenuItem("Atualizar Dados");

        cadastrarCliente.addActionListener(new CadastroClienteController(rasageSys, this));

        menuCliente.add(cadastrarCliente);
        menuCliente.add(attDadosCliente);
        menuCliente.add(pesqisarCliente);
        menuCliente.add(removerCliente);
        return menuCliente;
    }

    private JMenu getMenuComanda() {
        JMenu menuComanda = new JMenu("Comanda");
        JMenuItem novaComanda = new JMenuItem("Nova Comanda");
        JMenuItem removerComanda = new JMenuItem("Remover");

        novaComanda.addActionListener(new RegistroComandaController(rasageSys, this));

        menuComanda.add(novaComanda);
        menuComanda.add(getMenuPesquisarComanda());
        menuComanda.add(removerComanda);

        return menuComanda;
    }

    private JMenu getMenuPesquisarComanda(){
        JMenu menuPesquisar = new JMenu("Pesquisar");
        JMenuItem id = new JMenuItem("Id");
        JMenuItem cliente = new JMenuItem("Cliente");
        JMenuItem data = new JMenuItem("Data");
        JMenuItem servico = new JMenuItem("Serviço");

        id.addActionListener(new ComandaIDSearchController(rasageSys, this));

        data.addActionListener(new ComandaDataSearchController(rasageSys, this));

        cliente.addActionListener(new ComandaClientSearchController(rasageSys, this));

        servico.addActionListener(new ComandaServiceSearchController(rasageSys, this));

        menuPesquisar.add(id);
        menuPesquisar.add(cliente);
        menuPesquisar.add(data);
        menuPesquisar.add(servico);

        return menuPesquisar;
    }
}