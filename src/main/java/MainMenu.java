import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class MainMenu extends JFrame {
    JMenuBar operationsMenuBar = new JMenuBar();
    ImageIcon rasageLogoImg = new ImageIcon("./imgs/RasageLogo.jpg");
    Image rasageLogoRedonda = createRoundedImage(rasageLogoImg.getImage());
    Rasage rasageSys = new Rasage();

    public MainMenu(){
        setTitle("Rasage System");

        setIconImage(rasageLogoRedonda);
        setSize(800, 600); //tamanho da janela
        setLocation(150, 150);
        setResizable(false);
        setBackground(Color.white);
        JTable tableComandas = new JTable(rasageSys.getComandas().size(),1);

        //Menu de operações de Cliente
        JMenu menuCliente = getMenuCliente();

        //Menu de operações de Comanda
        JMenu menuComanda = getMenuComanda();

        operationsMenuBar.add(menuCliente);
        operationsMenuBar.add(menuComanda);
        setJMenuBar(operationsMenuBar);
    }



    public static void main(String[] args) {
        JFrame janela = new MainMenu();
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        cadastrarCliente.addActionListener(new CadastroClienteController(rasageSys, null));

        menuCliente.add(cadastrarCliente);
        menuCliente.add(attDadosCliente);
        menuCliente.add(pesqisarCliente);
        menuCliente.add(removerCliente);
        return menuCliente;
    }

    private static JMenu getMenuComanda(){
        JMenu menuComanda = new JMenu("Comanda");
        JMenuItem novaComanda = new JMenuItem("Nova Comanda");
        JMenuItem pesquisarComanda = new JMenuItem("Pesquisar");
        JMenuItem removerComanda = new JMenuItem("Remover");

        menuComanda.add(novaComanda);
        menuComanda.add(pesquisarComanda);
        menuComanda.add(removerComanda);

        return menuComanda;
    }
}