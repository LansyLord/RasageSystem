public class Testes {

    public static void main(String[] args) {
        Servico cabelo = new Cabelo(true, false, true);
        System.out.println(cabelo.getValor());

        Servico cabelo2 = new Cabelo(true, true, false);
        System.out.println(cabelo2.getValor());

        Servico cabelo3 = new Cabelo(true, false, false);
        System.out.println(cabelo3.getValor());

        Servico cabelo4 = new Cabelo(false, false, true);
        System.out.println(cabelo4.getValor());

        Servico cabelo5 = new Cabelo(false, true, false);
        System.out.println(cabelo5.getValor());

        Servico cabelo6 = new Cabelo(false, false, false);
        System.out.println(cabelo6.getValor());
    }

}
