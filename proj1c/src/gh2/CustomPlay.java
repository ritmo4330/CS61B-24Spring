package gh2;

public class CustomPlay {
    public static void main(String[] args) {
        System.out.println("This is a custom play file, you can play any midi you want!");
        GuitarPlayer player = new GuitarPlayer(new java.io.File("src/gh2/midi/kamado-tanjiro-no-uta-advanced.mid"));
        player.play();
    }
}
