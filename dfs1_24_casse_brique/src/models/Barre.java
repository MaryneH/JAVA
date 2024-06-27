package models;

import application.Fenetre;

import java.awt.*;

public class Barre extends Rectangle {

    protected int vitesse;

    public Barre(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, largeur, hauteur, couleur);
        this.vitesse = 20;
    }

    public Barre() {
        super(Fenetre.LARGEUR / 2 - 75, Fenetre.HAUTEUR - 100, 150, 20, Color.BLUE);
        this.vitesse = 20;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }

    public void deplacerGauche() {
        if (x > 0) {
            x -= vitesse;
        }
    }

    public void deplacerDroite() {
        if (x + largeur < Fenetre.LARGEUR) {
            x += vitesse;
        }
    }

    public boolean isCollide(Balle balle) {
        Rectangle barreRect = new Rectangle(x, y, largeur, hauteur, couleur);
        Rectangle balleRect = new Rectangle(balle.getX(), balle.getY(), balle.diametre, balle.diametre, balle.couleur);
        return barreRect.intersects(balleRect);
    }
}
