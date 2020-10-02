/*In this class, we are calling in a iterative way the main method used in the DLS 
 * increasing the limit by one at each iteration.
 */
public class IDS {

	public static void main(String[] args) {
		int tab[][] = { { 4, 0, 2, 3 }, { 5, 1, 6, 7 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };
		int N = 4;
		int coord[] = Arbre.getCoordinates(tab, N);
		int i = coord[0];
		int j = coord[1];
		Arbre a = new Arbre(new Node("does_not_matter", tab, i, j, null, N), N);
		int k = 0;
		//in the while loop, the incrementation of the limit is done by the variable k
		while (true) {
			System.out.println("\n\nIteration : " + k + "\n\n");
			a.racine.afficher(N);
			// we show the initial state manually and the code will generate the the tables of the children
			DLS.run(a.racine, null, i, j, a, k, 1);
			k++;
		}
	}

}
