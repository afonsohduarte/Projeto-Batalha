import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Personagem {
    protected String nome;
    protected int pontosVida;
    protected int armadura;

    public Personagem(String nome, int pontosVida, int armadura) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.armadura = armadura;
    }

    public abstract int calcularAtaque();

    public void receberDano(int dano) {
        pontosVida -= dano;
        if (pontosVida < 0) {
            pontosVida = 0;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public int getArmadura() {
        return armadura;
    }
}

class Elfo extends Personagem {
    public Elfo(String nome, int pontosVida, int armadura) {
        super(nome, pontosVida, armadura);
    }

    @Override
    public int calcularAtaque() {
        int dado1 = new Random().nextInt(101);
        int dado2 = new Random().nextInt(101);
        return Math.max(dado1, dado2) + (this instanceof Elfo ? 10 : 0);
    }
}

class Hobbit extends Personagem {
    public Hobbit(String nome, int pontosVida, int armadura) {
        super(nome, pontosVida, armadura);
    }

    @Override
    public int calcularAtaque() {
        int dado = new Random().nextInt(101);
        return dado - (this instanceof Hobbit ? 5 : 0);
    }
}

class Humano extends Personagem {
    public Humano(String nome, int pontosVida, int armadura) {
        super(nome, pontosVida, armadura);
    }

    @Override
    public int calcularAtaque() {
        return new Random().nextInt(101);
    }
}

class Orque extends Personagem {
    public Orque(String nome, int pontosVida, int armadura) {
        super(nome, pontosVida, armadura);
    }

    @Override
    public int calcularAtaque() {
        int dado = new Random().nextInt(91);
        return dado;
    }
}

class Troll extends Personagem {
    public Troll(String nome, int pontosVida, int armadura) {
        super(nome, pontosVida, armadura);
    }

    @Override
    public int calcularAtaque() {
        int dado = new Random().nextInt(91);
        return dado;
    }
}

public class Batalha {
    public static void main(String[] args) {
        List<Personagem> exercitoHerois = new ArrayList<>();
        exercitoHerois.add(new Humano("Gandalf", 300, 30));
        exercitoHerois.add(new Humano("Boromir", 100, 60));

        List<Personagem> exercitoBestas = new ArrayList<>();
        exercitoBestas.add(new Troll("Uglúk", 500, 30));
        exercitoBestas.add(new Troll("Mauhúr", 200, 30));

        simularBatalha(exercitoHerois, exercitoBestas);
    }

    public static void simularBatalha(List<Personagem> exercito1, List<Personagem> exercito2) {
        int turno = 1;
        while (!exercito1.isEmpty() && !exercito2.isEmpty()) {
            System.out.println("Turno " + turno + ":");
            lutarTurno(exercito1, exercito2);
            turno++;

            // Verifica se algum exército foi eliminado
            if (exercito1.isEmpty()) {
                System.out.println("VITÓRIA DAS BESTAS!!");
                return; // Termina a simulação, pois as bestas ganharam
            } else if (exercito2.isEmpty()) {
                System.out.println("VITÓRIA DOS HERÓIS!!");
                return; // Termina a simulação, pois os heróis ganharam
            }
        }

        // Se ainda não houve vitória, é um empate
        System.out.println("EMPATE!! AMBOS OS EXÉRCITOS FORAM DERROTADOS!!");
    }

    public static void lutarTurno(List<Personagem> exercito1, List<Personagem> exercito2) {
        for (int i = 0; i < Math.min(exercito1.size(), exercito2.size()); i++) {
            Personagem heroi = exercito1.get(i);
            Personagem besta = exercito2.get(i);

            int ataqueHeroi = heroi.calcularAtaque();
            int ataqueBesta = besta.calcularAtaque();

            System.out.println("Luta entre " + heroi.getNome() + " (Vida=" + heroi.getPontosVida() +
                    " Armadura=" + heroi.getArmadura() + ") e " + besta.getNome() +
                    " (Vida=" + besta.getPontosVida() + " Armadura=" + besta.getArmadura() + ")");

            if (ataqueHeroi > besta.getArmadura()) {
                int danoHeroi = ataqueHeroi - besta.getArmadura();
                besta.receberDano(danoHeroi);
                System.out.println(heroi.getNome() + " saca " + ataqueHeroi + " e tira " + danoHeroi + " de vida a " + besta.getNome());
            } else {
                System.out.println(heroi.getNome() + " não causa dano a " + besta.getNome());
            }

            if (ataqueBesta > heroi.getArmadura()) {
                int danoBesta = ataqueBesta - heroi.getArmadura();
                heroi.receberDano(danoBesta);
                System.out.println(besta.getNome() + " obtem " + ataqueBesta + " e tira " + danoBesta + " de vida a " + heroi.getNome());
            } else {
                System.out.println(besta.getNome() + " não causa dano a " + heroi.getNome());
            }

            if (besta.getPontosVida() <= 0) {
                System.out.println("  Morre " + besta.getNome() + "!");
                exercito2.remove(i);
            }
            if (heroi.getPontosVida() <= 0) {
                System.out.println("  Morre " + heroi.getNome() + "!");
                exercito1.remove(i);
            }
        }
    }
}








