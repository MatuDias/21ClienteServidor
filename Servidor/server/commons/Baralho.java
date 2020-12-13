package commons;

import java.util.ArrayList;

public class Baralho {

	private ArrayList<Carta> baralho;

	
	public Baralho() {
		baralho = new ArrayList<>();
		
		for (int i = 0; i < PropriedadeCartas.NOMES.length; i++)
		{
			for (int j = 0; j < PropriedadeCartas.SIMBOLOS.length; j++)
			{
				baralho.add(new Carta(PropriedadeCartas.NOMES[i], PropriedadeCartas.SIMBOLOS[j]));
			}
		}
	}

	public Carta comprarUmaCarta() {
		int indice = (int) (Math.random() * baralho.size()); // Escolher um indice entre 0 e o tamanho do vetor
		Carta carta = baralho.get(indice);
		baralho.remove(indice);

		return carta;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.baralho = cartas;
	}

	public ArrayList<Carta> getCartas() {
		return baralho;
	}

	public int getTamanho() {
		return baralho.size();
	}

	@Override
	public int hashCode()
	{
		int ret = 12;

		for (Carta carta: baralho)
			ret = ret * 3 + carta.hashCode();

		return ret;
	}

	@Override
	public String toString()
	{
		String str = "[";
		
		for (int i = 0; i < baralho.size(); i++) {
			if(str.length() == 15) {
				str += "\n" + baralho.get(i).toString() + ", ";
			}
				
			str += baralho.get(i).toString() + ", ";
		}
		return str.substring(0, str.length() - 2) + "]";
	}

	@Override
	public boolean equals(Object o) //TODO Verificar equals - Baralho
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Baralho baralho1 = (Baralho) o;

		return baralho.equals(baralho1.baralho);
	}
}
