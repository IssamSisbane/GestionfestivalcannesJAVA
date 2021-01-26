package Modele;

import java.sql.Date;

/**
 * 
 * @author Issam
 */
public class MembreJury extends Vip{

	private int id_jury;
	
	
	public MembreJury(int id, String prenom, String nom, Date dateDeNaissance, int serviceHebergement, int id_jury) {
		super(id, prenom, nom, dateDeNaissance, serviceHebergement);
		this.id_jury = id_jury;
		// TODO Auto-generated constructor stub
	}
	
	public int getId_jury() {
		return id_jury;
	}

	public void setId_jury(int id_jury) {
		this.id_jury = id_jury;
	}


}
