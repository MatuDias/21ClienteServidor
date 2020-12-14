package server;

import commons.Comunicado;
import commons.ComunicadoDeDesligamento;
import commons.ComunicadoDeRestart;
import commons.Parceiro;

import java.util.ArrayList;

public class TratadoraDeReinicio extends Thread
{

    private ArrayList<Parceiro> usuarios;

    public TratadoraDeReinicio(ArrayList<Parceiro> usuarios) throws Exception
    {
        if(usuarios == null)
            throw new Exception("Usuario Nulo na tratadora");

        this.usuarios = usuarios;
    }


    public void run()
    {
        /*
        ObjectOutputStream transmissor;

        try
        {
            transmissor = new ObjectOutputStream(conexao.getOutputStream());
        }
        catch(Exception e)
        {
            return;
        }

        ObjectInputStream receptor;

        try
        {
            receptor = new ObjectInputStream(conexao.getInputStream());
        }
        catch(Exception e)
        {
            try
            {
                transmissor.close();
            }
            catch(Exception e1)
            {}
            return;
        }*/

        while (true)
        {
            Comunicado com;
            synchronized (usuarios)
            {
                try
                {
                    com = usuarios.get(0).espiar();
                    if (com instanceof ComunicadoDeRestart)
                    {
                        for (Parceiro usuario : usuarios)
                        {
                            usuario.receba(new ComunicadoDeRestart());
                        }


                    } else if (com instanceof ComunicadoDeDesligamento)
                    {
                        for (Parceiro usuario : usuarios)
                        {
                            usuario.receba(new ComunicadoDeDesligamento());
                        }
                        return;
                    }
                }
                catch (Exception e) {}

            }
        }
    }
}