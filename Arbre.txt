
public class Arbre {
	int solution[][];
	Node racine;
	int N;

	Arbre() {
		racine = null;
	}

	Arbre(Node a, int n) {
		racine = a;
		int x = 0;
		solution = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				solution[i][j] = x++;
			}
		}
		N = n;
	}

	public static int[] getCoordinates(int tab[][], int n) {
		int tableau[] = new int[2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tab[i][j] == 0) {
					tableau[0] = i;
					tableau[1] = j;
				}
			}
		}
		return tableau;
	}

	// method to find the necessary number of moves to arrive to the right
	// destination
	public static int functionG(int tab[][], int N, int[][] sol) {
		int sum = 0;
		for (int i1 = 0; i1 < N; i1++) {
			for (int j1 = 0; j1 < N; j1++) {
				sum += distance(tab, i1, j1, sol, N);
			}
		}
		return sum;
	}

	public static int distance(int[][] tab, int i1, int j1, int tab_sol[][], int N) {
		int ii = tab[i1][j1] / N;
		int jj = tab[i1][j1] % N;
		return Math.abs(ii - i1) + Math.abs(jj - j1);
	}
}
