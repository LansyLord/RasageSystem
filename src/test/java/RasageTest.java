import excecoes.cliente.ClienteJaCadastradoException;
import excecoes.cliente.ClienteNaoExisteException;
import excecoes.comanda.ComandaJaExisteException;
import excecoes.comanda.ComandaNaoEncontradaException;
import excecoes.comanda.NaoHaComandasNoSistemaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RasageTest {
    @Test
    public void testaCadastroClientes() {
        SalaoInterface rasage = new Rasage();
        Cliente cliente = new Cliente("Felipe", "2837", "839");

        // Verifica se o cliente não existe antes de cadastrá-lo
        assertThrows(ClienteNaoExisteException.class, () -> {
            rasage.existeCliente("2837");
        });

        // Tenta cadastrar o cliente
        try {
            rasage.cadastrarCliente(cliente);
        } catch (ClienteJaCadastradoException ex) {
            // Se o cliente já estiver cadastrado, imprime a mensagem de exceção
            System.out.println(ex.getMessage());
        }

        // Verifica se a lista de clientes não está vazia após o cadastro
        assertFalse(rasage.getClientes().isEmpty());

        // Verifica se o cliente existe após o cadastro
        assertDoesNotThrow(() -> {
            rasage.existeCliente("2837");
        });
    }

    @Test
    public void testaCadastroComandas() {
        SalaoInterface rasage = new Rasage();
        Cliente cliente = new Cliente("Felipe", "2837", "839");
        Servico servico = new Unha(true, true);
        Comanda comanda = new Comanda(cliente, servico, "Débito");
        assertThrows(ComandaNaoEncontradaException.class, () -> {
            rasage.pesquisaComandasPorCliente("Felipe");
        });

        try{
            rasage.cadastrarCliente(cliente);
            rasage.registrarComanda(comanda);
        }catch(ClienteJaCadastradoException e){
            System.out.println(e.getMessage());
        }


        assertFalse(rasage.getComandas().isEmpty());

        assertDoesNotThrow(() -> {
            assertEquals(1,rasage.pesquisarComandasPorData(comanda.getData()).size());
        });
    }
}
