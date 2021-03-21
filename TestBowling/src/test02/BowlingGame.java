package test02;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

class Bowling {
	HashMap<String, Integer> players;

	Bowling() {
		players = new HashMap<String, Integer>();
	}

	public void addPlayer(String name, int p) {
		players.put(name, p);
	}

	// Version 1 mit einem Stream und Optional Sieger
	public void getWinnerV1() {
		// Ausgabe aller Spieler:
		System.out.println(players);

		// Comparator um die Entrys vergleichbar zu machen.
		Comparator<Entry<String, Integer>> comp = (entry1, entry2) -> entry1.getValue() - entry2.getValue();

		Optional<Entry<String, Integer>> winner = players.entrySet().stream().max(comp);
		if (winner.isPresent()) {
			System.out.println("Der Sieger ist:" + winner.get().getKey() + " Punkte:" + winner.get().getValue());
		}
	}

	// Version 2 mit Stream
	public void getWinnerV2() {
		// Ausgabe aller Spieler:
		System.out.println(players);

		// Comparator um die Entrys vergleichbar zu machen.

		Optional<Entry<String, Integer>> winner = players.entrySet().stream()
				.sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue()).findFirst();
		if (winner.isPresent()) {
			System.out.println("Der Sieger ist:" + winner.get().getKey() + " Punkte:" + winner.get().getValue());
		}

	}

	// Version 3 mit einem Durchlauf über die Entrys der HashMap um den Entry mit dem größten Value zu finden.
	public void getWinner() {
		// Ausgabe aller Spieler:
		System.out.println(players);
		Entry<String, Integer> theWinner = null;
		for (Entry<String, Integer> actEntry : players.entrySet()) {
			if (theWinner == null) {
				theWinner = actEntry;
			} else if (actEntry.getValue() > theWinner.getValue()) {
				theWinner = actEntry;
			}
		}
		if (theWinner != null) {
			System.out.println("Der Sieger ist:" + theWinner.getKey() + " Punkte:" + theWinner.getValue());
		}
	}
}

public class BowlingGame {

	public static void main(String[] args) {

		Bowling game = new Bowling();
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 3; i++) {
			String input = sc.nextLine();
			String[] values = input.split(" ");
			String name = values[0];
			int points = Integer.parseInt(values[1]);
			game.addPlayer(name, points);
		}
		game.getWinner();
	}

}
