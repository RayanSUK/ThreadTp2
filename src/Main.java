import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		semaphoreBinaire sem = new semaphoreBinaire(1);

		Thread TA = new Thread(() -> imprimer("AAA", sem));
		Thread TB = new Thread(() -> imprimer("BB",  sem));

		TA.start();
		TB.start();



	}

	static void imprimer(String texte, semaphoreBinaire sem) {
		sem.syncWait();                             // Wait() : prendre le verrou
		try {
			System.out.println("j'entre en section critique");
			for (int i=0; i<texte.length(); i++) {
				System.out.print(texte.charAt(i));
				try { Thread.sleep(100); } catch (InterruptedException e) {}
			}
			System.out.println("\nje sors de section critique");
		} finally {
			sem.syncSignal();                         // Signal() : libérer le verrou
		}
	}
}
