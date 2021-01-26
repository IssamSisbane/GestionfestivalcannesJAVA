package Modele;

import java.sql.Date;

/**
 * 
 * @author Issam
 */
public class MembreEquipe extends Vip {
	
	private int id_equipe;

	public MembreEquipe(int id, String prenom, String nom, Date dateDeNaissance, int serviceHebergement, int id_equipe) {
		super(id, prenom, nom, dateDeNaissance, serviceHebergement);
		this.id_equipe = id_equipe;
		// TODO Auto-generated constructor stub
	}

	public int getId_equipe() {
		return id_equipe;
	}

	public void setId_equipe(int id_equipe) {
		this.id_equipe = id_equipe;
	}

}
