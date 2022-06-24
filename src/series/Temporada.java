package series;

import java.io.Serializable;

public class Temporada implements Serializable{
	private int numeroTemporada;
	private String descripcionTemporada;
	private int cantCapitulos;
	
	public Temporada(String descripcionTemporada,int numeroTemporada,int cantCapitulos)
	{
		this.descripcionTemporada=descripcionTemporada;
		this.numeroTemporada = numeroTemporada;
		this.cantCapitulos=cantCapitulos;
	}

	public String getDescripcionTemporada() {
		return descripcionTemporada;
	}

	public void setDescripcionTemporada(String descripcionTemporada) {
		this.descripcionTemporada = descripcionTemporada;
	}

	public int getNumeroTemporada() {
		return numeroTemporada;
	}

	public void setNumeroTemporada(int numeroTemporada) {
		this.numeroTemporada = numeroTemporada;
	}

	public int getCantCapitulos() {
		return cantCapitulos;
	}

	public void setCantCapitulos(int cantCapitulos) {
		this.cantCapitulos = cantCapitulos;
	}
	
	@Override
	public String toString() {
		return "\n\n [+] Temporada "+getNumeroTemporada()+" | "+getCantCapitulos()+" cap�tulos "+"\n * Descripcion: "+getDescripcionTemporada();
	}

}
