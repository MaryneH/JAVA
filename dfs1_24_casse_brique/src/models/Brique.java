package models;

import java.awt.*;

public class Brique extends Rectangle {
    protected int largeur;
    protected int hauteur;
    protected Color couleur;

    public Brique(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, largeur, hauteur, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.couleur = couleur;
    }

    public Brique(int x, int y, Color couleur) {
        super(x, y, 45, 5, Color.BLACK); // Largeur et hauteur par défaut
        this.largeur = 45;
        this.hauteur = 5;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }

    public boolean isCollide(Balle balle) {
        Rectangle briqueRect = new Rectangle(x, y, largeur, hauteur, couleur);
        Rectangle balleRect = new Rectangle(balle.getX(), balle.getY(), balle.diametre, balle.diametre, balle.couleur);
        return briqueRect.intersects(balleRect);
    }
}
