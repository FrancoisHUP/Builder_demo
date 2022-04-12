package vue;

import serveur.Serveur;
import serveur.Data;
import serveur.UniqId;
import personnel.*;
import utils.Paire;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserInterface extends JFrame {

    // objects
    private JPanel jPanel;
    private JButton jButton;
    private JLabel jLabel;
    private JList<String> jList;
    private JScrollPane scrollPane;
    private JDialog jDialog;
    private DefaultListModel<String> model;
    private ArrayList<Paire> uniqIds = new ArrayList<>();

    // frame data
    public final int WIDTH = 430;
    public final int HEIGHT = 280;
    private final Dimension DIMENSION_MAIN_FRAME = new Dimension(WIDTH,HEIGHT);
    private final String TITLE_MAIN_FRAME = "User interface";

    // interface de lapplication
    private personnel.BuilderInterface builderInterface;

    public UserInterface() {
        this.builderInterface = new Builder();

        buildFrameNotResizable(TITLE_MAIN_FRAME,DIMENSION_MAIN_FRAME);
        jPanel = new JPanel();
        jPanel.setLayout(null);
        this.setContentPane(jPanel);

        buildLabels(jPanel);
        buildButtons(jPanel);
        buildList(jPanel);
        jPanel.repaint();
    }

    private void buildLabels(JPanel jPanel) {
        jLabel = new JLabel("Ajout Medecin");
        jLabel.setBounds(10,10,200,30);
        jPanel.add(jLabel);
        jLabel = new JLabel("Ajoute infirmiere");
        jLabel.setBounds(10,50,200,30);
        jPanel.add(jLabel);
        jLabel = new JLabel("Ajouter une Receptionniste");
        jLabel.setBounds(10,90,200,30);
        jPanel.add(jLabel);
    }

    private void buildButtons(JPanel jPanel) {
        jButton = new JButton("Ajout");
        jButton.setBounds(WIDTH-110,10,90,25);
        jButton.addActionListener(evt -> {
            buildDialogAddPersonnel(Emploie.MEDECIN);
        });
        jPanel.add(jButton);

        jButton = new JButton("Ajout");
        jButton.setBounds(WIDTH-110,50,90,25);
        jButton.addActionListener(evt -> {
            buildDialogAddPersonnel(Emploie.INFIRMIER);
        });
        jPanel.add(jButton);
        jButton = new JButton("Ajout");
        jButton.setBounds(WIDTH-110,90,90,25);
        jButton.addActionListener(evt -> {
            buildDialogAddPersonnel(Emploie.RECEPTIONNISTE);
        });
        jPanel.add(jButton);
    }

    private void buildList(JPanel jPanel) {
        model = new DefaultListModel<>();
        jList = new JList<>( model );
        scrollPane = new JScrollPane(jList);
        scrollPane.setBounds(10, 130, 400,100);
        jList.addListSelectionListener(evt -> {
            if(evt.getValueIsAdjusting()) {
                // utilisation du singleton pour les appels au serveur
                Data personnelData = null;
                Paire<Integer,UniqId> paire = uniqIds.get(jList.getSelectedIndex());
                UniqId uniqId = paire.second;
                try {
                    personnelData = Serveur.getInstance().getData("patient",uniqId);
                } catch (Serveur.TableNotFound | Serveur.RowNotFound e) {
                    System.err.println(e.getMessage());
                    buildFenetreErr();
                }
                buildDialogFichePatient(uniqId,personnelData);
            }
        });
        jPanel.add(scrollPane);
    }

    private void buildDialogAddPersonnel(Emploie emploie) {
        jDialog = new JDialog(this, "Add Patient");
        int height = 135;
        jDialog.setBounds(500, 400,250,height);
        jDialog.setLayout(null);
        //label
        JLabel jLabel = new JLabel("Nom patient");
        jLabel.setBounds(10,10,100,25);
        jDialog.add(jLabel);
        //field
        JTextField jTextField = new JTextField();
        jTextField.setBounds(10,30,200,25);
        jDialog.add(jTextField );
        //button
        JButton jButton = new JButton("ok");
        jButton.setBounds(80,height-75,60,25);

        //jButton.requestFocus();
        jButton.addActionListener(evt -> {
            // getting data
            Data fieldData = new Data();
            fieldData.setKeyValue("nom",jTextField.getText());

            // build employe
            Personnel personnel = builderInterface.buildEmploye(fieldData,emploie);

            // mettre les infos du personnel dans le serveur
            UniqId uniqId = new UniqId(personnel.getNom());

            Serveur.getInstance().setDoc("patient",uniqId,personnel);

            uniqIds.add(new Paire<>(jList.getModel().getSize(),uniqId));
            model.addElement(uniqId.getNom());

            jDialog.dispose();
        });
        jDialog.add(jButton);
        jDialog.setVisible(true);

    }

    private void buildDialogFichePatient(UniqId uniqId,Data patientData) {
        jDialog = new JDialog(this,"Patient " + uniqId.getNom());
        jDialog.setBounds(400, 300,300,300);
        jDialog.setLayout(null);
        jDialog.setVisible(true);

        JButton jButton = new JButton("fermer");
        jButton.setBounds( 100,300-75, 100,25);
        jButton.addActionListener(evt -> {
            jDialog.dispose();
        });
        jDialog.add(jButton);

        // nom
        JLabel jLabelNom = new JLabel("Nom");
        jLabelNom.setBounds(10,10,100,25);
        jDialog.add(jLabelNom);
        String nom = (String) patientData.getValueOfkey("nom");
        JLabel jLabelNomAffichage = new JLabel(nom);
        jLabelNomAffichage.setBounds(10,30,100,25);
        jDialog.add(jLabelNomAffichage);

        // emploie
        JLabel jLabelEmploi = new JLabel("Emploi");
        jLabelEmploi.setBounds(10,60,100,25);
        jDialog.add(jLabelEmploi);

        Emploie emploi = (Emploie) patientData.getValueOfkey("emploie");
        JLabel jLabelEmploiAffichage = new JLabel(emploi.toString().toLowerCase());
        jLabelEmploiAffichage.setBounds(10,80,100,25);
        jDialog.add(jLabelEmploiAffichage);

    }

    private void buildFenetreErr() {
        jDialog = new JDialog(this,"Erreur");
        jDialog.setBounds(400, 300,300,300);
        jDialog.setLayout(null);
        jDialog.setVisible(true);

        JButton jButton = new JButton("fermer");
        jButton.setBounds( 100,300-75, 100,25);
        jButton.addActionListener(evt -> {
            jDialog.dispose();
        });
        jDialog.add(jButton);
        JLabel jLabelNomAffichage = new JLabel("Oups l employer n a pas ete trouve !");
        jLabelNomAffichage.setBounds(10,10,300,25);
    }

    private void buildFrameNotResizable(String titre, Dimension dimension){
        this.setResizable(false);
        this.setTitle(titre);
        this.setBounds(500, 350, dimension.width, dimension.height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }
}
