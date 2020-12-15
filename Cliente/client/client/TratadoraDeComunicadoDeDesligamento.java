package client;
import commons.*;

import java.util.Locale;

public class TratadoraDeComunicadoDeDesligamento extends Thread
{
    private Parceiro servidor;

    public TratadoraDeComunicadoDeDesligamento(Parceiro servidor) throws Exception
    {
        if (servidor==null)
            throw new Exception ("Porta invalida");

        this.servidor = servidor;
    }

    public void run ()
    {
        Comunicado comunicado = null;
        for(;;)
        {
            try {
                comunicado = servidor.espiar();
            }catch(Exception e){}
            try
			{
                if(comunicado instanceof ComunicadoDeDesligamento)
                {
                    System.err.println("\nO servidor vai ser desligado agora..");
                    System.err.println("Volte mais tarde!\n");
                    servidor.encerrar();
                    System.exit(0);
                }
                else if(comunicado instanceof ComunicadoDeVitoria)
                {
                    System.out.println("Você venceu a partida");

                    servidor.envie();

                    if(servidor.espiar() instanceof ComunicadoDeRestart)
                    {
                        System.out.println("Chegou o restart");
                        servidor.envie();

                        String opcao;
                        do{
                            System.out.print("Deseja reiniciar a partida? (s/n) ");
                            opcao = Teclado.getUmString().toLowerCase();

                        }while (!opcao.equals("s") && !opcao.equals("n"));

                        if(opcao.equals("s"))
                            servidor.receba(new Pedido(null, "REINICIAR"));
                        else
                            servidor.receba(new Pedido(null, "DESLIGAR"));


                    }

                }
                else if(comunicado instanceof ComunicadoDeDerrota)
                {
                    System.out.println("Você perdeu :( Alguém já venceu a partida");

                    servidor.envie();

                    if(servidor.espiar() instanceof ComunicadoDeRestart)
                    {
                        System.out.println("Chegou o restart");
                        servidor.envie();

                        String opcao;
                        do{
                            System.out.print("Deseja reiniciar a partida? (s/n) ");
                            opcao = Teclado.getUmString().toLowerCase();

                        }while (!opcao.equals("s") && !opcao.equals("n"));

                        if(opcao.equals("s"))
                            servidor.receba(new Pedido(null, "REINICIAR"));
                        else
                            servidor.receba(new Pedido(null, "DESLIGAR"));


                    }
                }
			}
			catch (Exception erro)
			{}
        }
    }

}
