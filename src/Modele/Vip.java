package Modele;

import java.sql.Date;
public class Vip{
		
	    private int id;
	    private String prenom;
	    private String nom;
	    private Date dateDeNaissance;
	    private int serviceHebergement;

	    public Vip(int id,String prenom,String nom,Date dateDeNaissance,int serviceHebergement)
	    {
	        this.id = id;
	        this.prenom = prenom;
	        this.nom = nom;
	        this.dateDeNaissance = dateDeNaissance;
	        this.serviceHebergement = serviceHebergement;
	    }

	    public String toString()
	    {
	        return "\nIdentifiant du VIP : "+getId()
	                +"\nPrénom du VIP : "+getPrenom()
	                +"\nNom du VIP : "+getNom()
	                +"\nDate de naissance du VIP : "+getDateDeNaissance()
	                +"\nService d'hébergement du VIP : "+getServiceHebergement();	
	    }


	    public Date getDateDeNaissance() {
	        return dateDeNaissance;
	    }
	    public void setDateDeNaissance(Date dateDeNaissance) {
	        this.dateDeNaissance = dateDeNaissance;
	    }
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getPrenom() {
	        return prenom;
	    }
	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }
	    public String getNom() {
	        return nom;
	    }
	    public void setNom(String nom) {
	        this.nom = nom;
	    }
	    public int getServiceHebergement() {
	        return serviceHebergement;
	    }
	    public void setServiceHebergement(int serviceHebergement) {
	        this.serviceHebergement = serviceHebergement;
	    }
}

