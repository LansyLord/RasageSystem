public class TesteUnha {
    public static void main(String[] args) {
        Servico unha = new Unha(true, true
                , "Azul","nao"
                , "nenhum");

        System.out.println(unha.getValor());

        Servico unha2 = new Unha(true, false
                , "Azul","nao"
                , "nenhum");

        System.out.println(unha2.getValor());

        Servico unha3 = new Unha(false, true
                , "Azul","nao"
                , "nenhum");

        System.out.println(unha3.getValor());

        Servico unha4 = new Unha(false, false
                , "Azul","nao"
                , "nenhum");

        System.out.println(unha4.getValor());
    }




}
