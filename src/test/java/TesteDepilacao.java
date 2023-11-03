public class TesteDepilacao {
    public static void main(String[] args) {
        Servico depilacao = new Depilacao(Depilacao.CERA, Depilacao.BARBA);
        System.out.println(depilacao.getValor());

        Servico depilacao2 = new Depilacao(Depilacao.FOTO_DEPILACAO, Depilacao.BARBA);
        System.out.println(depilacao2.getValor());

        Servico depilacao3 = new Depilacao(Depilacao.LAMINA, Depilacao.BARBA);
        System.out.println(depilacao3.getValor());

        Servico depilacao4 = new Depilacao(Depilacao.CERA, Depilacao.AXILA);
        System.out.println(depilacao4.getValor());

        Servico depilacao5 = new Depilacao(Depilacao.FOTO_DEPILACAO, Depilacao.AXILA);
        System.out.println(depilacao5.getValor());

        Servico depilacao6 = new Depilacao(Depilacao.LAMINA, Depilacao.AXILA);
        System.out.println(depilacao6.getValor());

        Servico depilacao7 = new Depilacao(Depilacao.CERA, Depilacao.PERNAS);
        System.out.println(depilacao7.getValor());

        Servico depilacao8 = new Depilacao(Depilacao.FOTO_DEPILACAO, Depilacao.PERNAS);
        System.out.println(depilacao8.getValor());

        Servico depilacao9 = new Depilacao(Depilacao.LAMINA, Depilacao.PERNAS);
        System.out.println(depilacao9.getValor());
    }
}
