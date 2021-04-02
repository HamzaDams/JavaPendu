import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JeuPendu {

    public static final String[] MOTS = {
            "TEST"
    };

    public static final Random RANDOM = new Random();

    public static final int MOT_MAX = 8;

    public String motATrouver;

    private char[] motTrouver;

    private int nbRater;

    private ArrayList<String> lettres = new ArrayList<String>();

    private String motSuivant() {
        return MOTS[RANDOM.nextInt(MOTS.length)];
    }

    public void nouvellePartie() {
        nbRater = 0;
        lettres.clear();
        motATrouver = motSuivant();

        motTrouver = new char[motATrouver.length()];

        for(int i = 0; i < motTrouver.length; i++){
            motTrouver[i] = '_';
        }

    }

    public boolean motTrouver() {
        return motATrouver.contentEquals(new String(motTrouver));
    }

    private void entrer(String c) {
        if(!lettres.contains(c)){
            if(motATrouver.contentEquals(c)){
                int index = motATrouver.indexOf(c);

                while (index >= 0){
                    motTrouver[index] = c.charAt(0);
                    index = motATrouver.indexOf(c, index + 1);
                }
            }else {
                nbRater++;
            }

            lettres.add(c);
        }
    }

    private String motTrouverContent() {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < motTrouver.length; i++){
            builder.append(motTrouver[i]);

            if(i < motTrouver.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public void play() {
        try (Scanner input = new Scanner(System.in)){
            while(nbRater < MOT_MAX) {
                System.out.println("\nEntrer une lettre : ");
                String str = input.next();
                if(str.length() > 1) {
                    str = str.substring(0, 1);
                }

                entrer(str);

                System.out.println("\n" + motTrouverContent());

                if(motTrouver()) {
                    System.out.println("\n Gagner");
                    break;
                } else {
                    System.out.println("\n Nombre d'essaie " + nbRater);
                }
            }

            if (nbRater == MOT_MAX) {
                System.out.println("\n Perdu");
            }

        }
    }

    public static void main(String[] args){
        System.out.println("\n Jeu lancer");
        JeuPendu jeuPendu = new JeuPendu();
        jeuPendu.nouvellePartie();
        jeuPendu.play();
    }
}
