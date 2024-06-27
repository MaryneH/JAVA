package application;

import models.Balle;
import models.Barre;
import models.Brique;
import models.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Fenetre extends Canvas implements KeyListener {

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;
    public static final int NB_BRIQUES_LIGNE = 10;
    public static final int NB_BRIQUES_RANGEE = 5;
    public static final int ESPACE_ENTRE_BRIQUES = 5;

    protected boolean toucheEspace = false;

    ArrayList<Balle> listeBalles = new ArrayList<>();
    ArrayList<Sprite> listeSprites = new ArrayList<>();
    ArrayList<Brique> listeBriques = new ArrayList<>();
    Barre barre;

    Fenetre() throws InterruptedException {

        JFrame fenetre = new JFrame();

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);

        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);

        Container panneau = fenetre.getContentPane();
        panneau.add(this);

        fenetre.setVisible(true);
        this.createBufferStrategy(2);

        this.demarrer();
    }

    public void demarrer() throws InterruptedException {

        barre = new Barre();
        listeSprites.add(barre);

        Balle balle = new Balle(100, 200, Color.GREEN, 30);

        listeBalles.add(balle);
        listeSprites.add(balle);

        ajouterBriques();

        while (true) {

            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, LARGEUR, HAUTEUR);

            //----- app -----
            for (Balle b : listeBalles) {
                b.deplacement();
            }

            for (Balle b : listeBalles) {
                if (barre.isCollide(b)) {
                    b.setVitesseY(-b.getVitesseY());
                }
            }

            Iterator<Brique> iterator = listeBriques.iterator();
            while (iterator.hasNext()) {
                Brique brique = iterator.next();
                for (Balle b : listeBalles) {
                    if (brique.isCollide(b)) {
                        b.setVitesseY(-b.getVitesseY());
                        iterator.remove(); // Retire la brique de la liste
                        listeSprites.remove(brique); // Retire la brique de la liste des sprites pour qu'elle ne soit plus dessinée
                        break;
                    }
                }
            }

            for (Sprite s : listeSprites) {
                s.dessiner(dessin);
            }

            if (toucheEspace) {
                listeBalles.add(new Balle(200, 400, Color.BLUE, 50));
            }
            //---------------

            dessin.dispose();
            this.getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }

    }

    private void ajouterBriques() {
        int largeurBrique = (LARGEUR - (NB_BRIQUES_LIGNE + 1) * ESPACE_ENTRE_BRIQUES) / NB_BRIQUES_LIGNE;
        int hauteurBrique = 15;

        for (int i = 0; i < NB_BRIQUES_RANGEE; i++) {
            for (int j = 0; j < NB_BRIQUES_LIGNE; j++) {
                int x = j * (largeurBrique + ESPACE_ENTRE_BRIQUES) + ESPACE_ENTRE_BRIQUES;
                int y = i * (hauteurBrique + ESPACE_ENTRE_BRIQUES) + ESPACE_ENTRE_BRIQUES;
                Brique brique = new Brique(x, y, largeurBrique, hauteurBrique, Color.RED);
                listeBriques.add(brique);
                listeSprites.add(brique);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            toucheEspace = true;
        } else if (key == KeyEvent.VK_LEFT) {
            barre.deplacerGauche();
        } else if (key == KeyEvent.VK_RIGHT) {
            barre.deplacerDroite();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            toucheEspace = false;
        }
    }
}
