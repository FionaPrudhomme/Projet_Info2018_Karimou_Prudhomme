package fiches;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import connexionBDD.Connexion;
import panneau_principal.PanneauPrincipal;
/**
* Classe affichant la liste des clients enregistrés dans la base de donnée
* 
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/

public class AfficheClient extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int id_client;

	private String[] li1=new String[10];//colonne table
	private String[] li2=new String[10];//colonne table
	private String[] li3=new String[10];//colonne table
	private String[] li4=new String[10];//colonne table
	private String[] li5 =new String[10];//colonne table
	private JButton button1 = new JButton("Menu");
	public JFrame frame;
	
	private String[][] list1=new String[20][20];//colonnes finales tableau
	
	public AfficheClient(){
		
        try {
        	//récupération des données
			for(int i=1; i<10 ; i++){
        		PreparedStatement preparedState = Connexion.getInstance().prepareStatement("SELECT id from client where id = '"+i+"'");
            	ResultSet resultat = preparedState.executeQuery();
                while(resultat.next()){
                	System.out.println(li1);
                	li1[i-1] = String.valueOf(resultat.getInt(1));
                	
                	
                }
                PreparedStatement preparedState1 = Connexion.getInstance().prepareStatement("SELECT nom from client where id = '"+i+"'");
            	ResultSet resultat1 = preparedState1.executeQuery();
                while(resultat1.next()){
                	
                	li2[i-1] = resultat1.getString(1);
                }  
                
                PreparedStatement preparedState2 = Connexion.getInstance().prepareStatement("SELECT prenom from client where id = '"+i+"'");
            	ResultSet resultat2 = preparedState2.executeQuery();
                while(resultat2.next()){
                	li3[i-1] = resultat2.getString(1);
                	
                }
                
                PreparedStatement preparedState3 = Connexion.getInstance().prepareStatement("SELECT email from client where id = '"+i+"'");
            	ResultSet resultat3 = preparedState3.executeQuery();
                while(resultat3.next()){
                	li4[i-1] = resultat3.getString(1);
                	
                }
                
                PreparedStatement preparedState4 = Connexion.getInstance().prepareStatement("SELECT point_de_fidelite from client where id = '"+i+"'");
            	ResultSet resultat4 = preparedState4.executeQuery();
                while(resultat4.next()){
                	li5[i-1] = Integer.toString(resultat4.getInt(1));
                	
                }
  	
        	}
     
        }
        catch (Exception e3) {
				e3.printStackTrace();
		}
        
        
       
        for(int i =0; i<li1.length; i++){
        	
        	 list1[i][0] = li1[i];
        	 list1[i][1] = li2[i];
        	 list1[i][2] = li3[i];
        	 list1[i][3] = li4[i];
        	 list1[i][4] = li5[i];
        	
        }
        String[] entetes = {"Id", "Nom", "Prenom", "email", "points de fidélités"};//entetes
        
        JTable tableau = new JTable(list1,entetes);//creation tableau
        
        setTitle("JTable basique dans un JPanel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
        
 
        pack();
        this.getContentPane().add(button1, BorderLayout.SOUTH);
        button1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {//action si l'on appuis sur le bouton 
				dispose();
				PanneauPrincipal pann = new PanneauPrincipal();
				pann.setVisible(true);
				
			}
			
		});
        
	}
	
	public static void main(String[] args) {
        new AfficheClient().setVisible(true);//visibilité
    }



}
