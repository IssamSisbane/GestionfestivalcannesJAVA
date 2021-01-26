/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ControllerVip.afficherInvites;
import Modele.Projection;
import Modele.MembreJury;
import Modele.MembreEquipe;
import Modele.Invite;
import Modele.Film;
import Modele.Salle;
import Modele.ProjectionManager;
import Modele.FilmManager;
import Modele.SalleManager;
import Modele.InviteManager;
import Modele.MembreEquipeManager;
import Modele.MembreJuryManager;
import Vue.VueAfficherVips;
import Vue.VueAjoutProjection;
import Vue.VueAjouterVipsProjection;
import Vue.VueProjectionAffiche;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Issam
 */
public class ControllerProjection {
    
    private static VueAjoutProjection vueAjoutProjection;
    private static VueProjectionAffiche vueAfficheProjection;
    private static VueAjouterVipsProjection vueAjouterVipsProjection;
    private static ProjectionManager pm;
    private static FilmManager fm;
    private static SalleManager sm;
    private static InviteManager im;
    private static MembreJuryManager mjm;
    private static MembreEquipeManager mem;
    private static int id_planning;
    
    /**
     * Constructeur
     * @param id du planning
     */
    public ControllerProjection(int id)
    {
        // on recupère les variables utiles
        id_planning = id;
        
        // On instancie les vues dont on a besoin
        vueAjoutProjection = new VueAjoutProjection();
        vueAfficheProjection = new VueProjectionAffiche();
        vueAjouterVipsProjection = new VueAjouterVipsProjection();
        
        // On appelle la fonction qui va gere l'affichage de la vue
        afficherProjections();
    }
    
    /**
     * Affiche la vue avec la fentre d'ajout d'une projection
     */
    private static void afficherAjoutProjection()
    {
        vueAjoutProjection.setVisible(true);
        // On appelle la fonction qui va remplir la liste des films
        remplirListeFilms();
       
        // On appelle la fonction qui va remplir la liste des salles
        remplirListeSalles();
        
        // Recupere l'action correspondant au bouton est pressé
        vueAjoutProjection.submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // On appelle la fonction d'ajout de projections
                ajouterProjections();
            }
        });
    }
    
    /**
     * Recupère en BDD la liste des films et l'ajoute a la jlist
     */
    public static void remplirListeFilms()
    {
        // On crée le manager pour les films
        fm = new FilmManager();
        
        String filtre = vueAjoutProjection.rechercheBarreFilm.getText();
        
        
        // On recupère tous les films
        ArrayList<Film> films = fm.recupererFilms(filtre);
        
        // On crée la liste des differents films que l'utilisateur pourra selectionner
        int tailleF = films.size();
        Film[] noms = new Film[tailleF];
        int i = 0;
        for(Film film: films){
            noms[i] = film;
            //System.out.print(noms[i]);
            i = i + 1;
        }
        
        vueAjoutProjection.filmList.setListData(noms);
    }
    
    /**
     * Recupère en BDD la liste des salles et l'ajoute a la jlist
     */
    public static void remplirListeSalles(){
        // On crée le manager de salle
        sm = new SalleManager();
        
        String filtre = vueAjoutProjection.rechercheBarreSalle.getText();
        
        // On recupère toutes les salles
        ArrayList<Salle> salles = sm.rechercheSalles(filtre);
        
        // On crée la liste des differentes salles que l'utilisateur pourra selectionner
        int tailleS = salles.size();
        Salle[] sal = new Salle[tailleS];
        int j = 0;
        for(Salle sa: salles)
        {
            sal[j] = sa;
            j = j+1;
        }
        
        vueAjoutProjection.salleList.setListData(sal);
    }
    
    /**
     * Creer une projection avec les éléments recupèrer et l'ajoute en base de données
     */
    public static void ajouterProjections()
    {
        // On affiche la fenetre d'ajout de projection
        vueAjoutProjection.setVisible(true);
        try{
                
                // On recupère la salle
                Salle salle = vueAjoutProjection.salleList.getSelectedValue();

                // On recupère le film
                Film film = vueAjoutProjection.filmList.getSelectedValue();

                // On recupère l'id de la projection
                int id = Integer.parseInt(vueAjoutProjection.idText.getText()) ;
                Boolean b = vueAjoutProjection.SeanceLendemainCheckBox.isSelected();

                // On recupère la date de debut
                LocalDate d = vueAjoutProjection.dateDebutPicker.datePicker.getDate();
                LocalTime t = vueAjoutProjection.dateDebutPicker.timePicker.getTime();
                Timestamp dateDebut = calculerDate(d,t);

                // On recupère la date de fin
                t = vueAjoutProjection.dateFinPicker.getTime();
                Timestamp dateFin = calculerDate(d,t);

                // On crée le manager de projection
                pm = new ProjectionManager();
                
                // On verifie si la date est valide
                if(dateDebut.before(dateFin))
                {
                    // On crée l'objet projection et on l'ajoute en base de donnée
                    Projection p = new Projection(id,dateDebut,dateFin,salle,film,b,id_planning); 
                    pm.ajouterProjection(p);

                    // Si la projection n'est pas une seance du lendemain les membres respecrif de l'equipe de film et du jury sont ajoutés
                    if (p.getSeanceLendemain() == false){
                        // On ajoute les membres de l'equipe du film qui doivent assister a la projection
                        mem = new MembreEquipeManager();
                        ArrayList<MembreEquipe> membresEquipe = mem.rechercheMembreEquipeSpecifique(p.getFilm().getId());
                        for(int i = 0; i< membresEquipe.size() ; i++)
                        {
                            pm.ajouterProjectionVip(p.getId(),membresEquipe.get(i).getId(),"MembreEquipe");
                        }

                        // On ajoute les membres du jury qui doivent assister a la projection selon la competition du film
                        mjm = new MembreJuryManager();
                        ArrayList<MembreJury> membresJury = mjm.rechercheMembresJurySpecifique(p.getFilm().getId());
                        for(int i = 0; i< membresJury.size() ; i++)
                        {
                            pm.ajouterProjectionVip(p.getId(),membresJury.get(i).getId(),"MembreJury");
                        }
                    }
                    

                    // On met a jour l'affichage des projections
                    remplirTableProjections();

                    // On affiche la fenetre d'ajout d'invité à la projection
                    afficherAjouterInviteProjection(p.getId());

                    // On ferme la fenetre
                    vueAjoutProjection.dispose();
                }
                else
                {
                    // On affiche une erreur
                    JOptionPane.showMessageDialog(vueAjoutProjection, "Probleme de date","Erreur",JOptionPane.PLAIN_MESSAGE);
                }
                    
                   
            }
            catch(HeadlessException | NumberFormatException ex)
            {
                System.out.print(ex);
            } 
    }
    
    /**
     * Affiche la vue contenant les projections
     */
    public static void afficherProjections()
    {
        // On affiche la vue
        vueAfficheProjection.setVisible(true);
        
        // On remplit la Jtable des projections
        remplirTableProjections();
        
        // On recupère l'action de double click sur une ligne et on renvoi au planning associé à la ligne
        vueAfficheProjection.JTable2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
             
            // On detecte les doubles clicks de souris
            if (me.getClickCount() == 2) {     
                
                // On recupère la cible de la souris
               JTable target = (JTable)me.getSource(); 
               
               // on recupère la ligne selectionné
               int row = target.getSelectedRow(); 
               
               // On recupère l'id de la ligne cliqué
               int id_projection = (int) vueAfficheProjection.JTable2.getValueAt(row, 0);
               Salle salle =  (Salle) vueAfficheProjection.JTable2.getValueAt(row, 3);
               
               // On affiche le planning selectionné
               new ControllerVip(id_projection, salle);
               
               /*
               vueAffichePlanning = new VueAffichePlanning(id_planning);
               vueAffichePlanning.setVisible(true);
               */
                }
            }});
        
        vueAfficheProjection.supprimerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               
               // on recupère la ligne selectionné
               int row = vueAfficheProjection.JTable2.getSelectedRow(); 
               
               // On recupère l'id de la ligne cliqué
               int id_projection = (int) vueAfficheProjection.JTable2.getValueAt(row, 0);
               
               // On demande à l'utilisateur de confirmer son choix
               int retour = JOptionPane.showConfirmDialog(vueAfficheProjection, "Voulez vous vraiment supprimer cette projection ?", "Avertissement", JOptionPane.YES_NO_OPTION);
               
               // Si le bouton cliqué est oui on suppromer le planning
               if (retour == 0){
                    pm = new ProjectionManager();
                    pm.supprimeProjection(id_projection);

                    remplirTableProjections();
               }
               
                    }
                });
        
        // Action lorsque le bouton ajouter est appuyé
        vueAfficheProjection.ajouterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // On appelle la fonction qui affiche la vue d'ajout
                afficherAjoutProjection();
                
                // On recupère l'action de presser la touche entré sur le champs de recherche afin d'appliquer un filtre
                vueAjoutProjection.rechercheBarreFilm.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        remplirListeFilms();
                        }
                    }
                });
                
                // On recupère l'action de presser la touche entré sur le champs de recherche afin d'appliquer un filtre
                vueAjoutProjection.rechercheBarreSalle.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        remplirListeSalles();
                        }
                    }
                });
                
                
                
            }});
    }
    
    /**
     * Rempli la Jtable projections avec les projections dans la BDD
     */
    public static void remplirTableProjections()
    {
        // On crée le manager de projection
        pm = new ProjectionManager();
        
        // On recupère toutes les projections
        ArrayList<Projection> projections = pm.rechercheProjections(id_planning);

        // On remplit la Jtable avec toutes les projections
        DefaultTableModel model = (DefaultTableModel) vueAfficheProjection.JTable2.getModel();
        model.setRowCount(0);
        
        if(!projections.isEmpty()){
            Object[] row = new Object[7];
            for(int i=0; i<projections.size();i++)
            {
                row[0] = projections.get(i).getId();
                row[1] = projections.get(i).getDateDebut();
                row[2] = projections.get(i).getDateFin();
                row[3] = projections.get(i).getSalle();
                row[4] = projections.get(i).getFilm();
                row[5] = projections.get(i).getSeanceLendemain();
                row[6] = projections.get(i).getFilm().getCompetition();
                model.addRow(row);
            }
        }else{
            // On affiche une erreur
            JOptionPane.showMessageDialog(vueAfficheProjection, "Aucun film trouvé","Erreur",JOptionPane.PLAIN_MESSAGE);
        }
        
    }
    
    /**
     * Renvoi dans le format adequat la date pour la base de données
     * @param date
     * @param time
     * @return Timestamp dateComplete
     */
    public static Timestamp calculerDate(LocalDate date, LocalTime time)
    {
        LocalDateTime dateTs = LocalDateTime.of(date,time);
        java.sql.Timestamp dateComplete = Timestamp.valueOf(dateTs);
        return dateComplete;
    }
    
    /**
     * Affiche la vue pour ajouter des invite à la projection
     * @param projection_id 
     */
    public static void afficherAjouterInviteProjection(int projection_id){
        
        // On remplit la table avec les invites et on affiche la vue d'ajout d'invité
        remplirTableInvite();
        
        // On affiche la vue
        vueAjouterVipsProjection.setVisible(true);
        
        
        // On recupère l'action de presser la touche entré sur le champs de recherche afin d'appliquer un filtre
        vueAjouterVipsProjection.rechercheBarre.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        remplirTableInvite();
                        }
                    }
                });
        
        // Action lorsque le bouton ajouter est appuyé
        vueAjouterVipsProjection.submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    // On recupère les lignes selectionnées et donc les invités à ajouter à la projection
                    int[] selection = vueAjouterVipsProjection.inviteTable.getSelectedRows();
                    for (int i = 0; i<selection.length ; i++)
                    {
                        // On recupère l'id de l'invité
                        selection[i] = vueAjouterVipsProjection.inviteTable.convertRowIndexToModel(selection[i]);
                        int nb = (Integer) vueAjouterVipsProjection.inviteTable.getValueAt(selection[i], 0);
                        
                        // On ajoute le vip en base de données
                        pm.ajouterProjectionVip(projection_id,nb,"Invite");
                        ControllerVip.nombreInvites = afficherInvites();
                        
                        vueAjouterVipsProjection.dispose();
                        
                    }
                }
                catch(Exception z)
                {
                    System.out.print(z);
                }
            }}); 
        
    }
    
    /**
     * Remplit la Jtable invite
     */
    public static void remplirTableInvite()
    {
        // On crée le manager d'invité
        im = new InviteManager();
        
        // On recupère le texte dans le champ de recherche
        String filtre = vueAjouterVipsProjection.rechercheBarre.getText();
        
        // On recupère les invites correspondant au filtre s'il existe
        ArrayList<Invite> invites = im.rechercheInvites(filtre);
        
        // On ajoute à la Jtable les invites recupèrés
        DefaultTableModel model = (DefaultTableModel) vueAjouterVipsProjection.inviteTable.getModel();
        model.setRowCount(0);
        
        if(!invites.isEmpty()){
            Object[] row = new Object[6];
            for(int i=0; i<invites.size(); i++)
            {
                row[0] = invites.get(i).getId();
                row[1] = invites.get(i).getNom();
                row[2] = invites.get(i).getPrenom();
                model.addRow(row);
            }
        }
        else{
            // On affiche une erreur
            JOptionPane.showMessageDialog(vueAjouterVipsProjection, "Aucun film trouvé","Erreur",JOptionPane.PLAIN_MESSAGE);
        }
        
        
        
    }
}
