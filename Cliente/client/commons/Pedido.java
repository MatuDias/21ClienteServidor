package commons;

public class Pedido extends Comunicado {
    //Esse pedido é algo genérico, pode ser tanto a MaoDoJogador quanto a String do nome da carta que será descartada
    private MaoDoJogador mao;
    private String pedido;

    public Pedido(MaoDoJogador mao, String pedido)throws Exception {
        if(mao == null)
            throw new Exception("Mao do jogador invalida");

        if(pedido == null || pedido.isEmpty())
            throw new Exception("Pedido vazio");

        this.mao = mao;
        this.pedido = pedido;
    }

    public MaoDoJogador getMao() {
        return mao;
    }

    public String getPedido() {
        return pedido;
    }

    @Override
    public boolean equals(Object o) //TODO VERIFICACAO equals - Pedido
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido1 = (Pedido) o;

        if (!mao.equals(pedido1.mao)) return false;
        return pedido.equals(pedido1.pedido);
    }

    @Override
    public int hashCode() //TODO VERIFICACAO hashCode - Pedido
    {
        int result = mao.hashCode();
        result = 31 * result + pedido.hashCode();
        return result;
    }
}
