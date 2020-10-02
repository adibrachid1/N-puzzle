import java.util.Arrays;

class DFS {
	private static boolean  displayInOrder(Node root,int[][] initial,Node node) {//check if the node already exists in the tree
	    if (root != null) {
	      if(Arrays.deepEquals(root.tab, initial) && root!=node){//compare the table we have with the node we are at
	    	 	return false;//if it is the same return false
	      }
	      return(
	      displayInOrder(root.up,initial,node)&&
	      displayInOrder(root.down,initial,node)&&
	      displayInOrder(root.left,initial,node)&&
	      displayInOrder(root.right,initial,node)
	      );//if we have 1 method that returns false the answer will became false because of the and
	    }
	    return true;
	  }
		public static void run(Node node, Node parent, int i, int j,Arbre a ) {
			if(!correct(node.tab,a)) {//we will do it if we did not reach the answer
				// by up we mean that the empty case will move up
				if (i != 0) {//to make sure that the empty case(0 in the code) is not in the first row
					node.up = new Node("up", node.tab, node.i, node.j, node.parent,a.N);//create the new node
					if(displayInOrder(a.racine,node.up.tab,node.up)){//if the node does not exist in the tree the return will be true
						node.up.afficher(a.N);//afficher will show the current puzzle
						run(node.up,node,node.up.i,node.up.j,a);//we will do the child of the node we are at
					}else{
						node.up=null;//if the table exist in the tree the return will be false we will delete the node that we created
					}
				}
				if (j != 0) {//we make sure that the empty case is not in the first column 
					node.left = new Node("left", node.tab, node.i, node.j, node.parent,a.N);
					if(displayInOrder(a.racine,node.left.tab,node.left)){
						node.left.afficher(a.N);
						run(node.left,node,node.left.i,node.left.j,a);
					}else{
						node.left=null;
					}
				}
				
				if (j != a.N-1) {//we make sure that the empty case is not in the last column
					node.right = new Node("right", node.tab, node.i, node.j, node.parent,a.N);
					if(displayInOrder(a.racine,node.right.tab,node.right)){
						node.right.afficher(a.N);
					run(node.right,node,node.right.i,node.right.j,a);
					}else{
						node.right=null;
					}
				}
				if (i != a.N-1) {//we make sure that the empty case is not in the last row
					node.down = new Node("down", node.tab, node.i, node.j, node,a.N);
					if(displayInOrder(a.racine,node.down.tab,node.down)){
						node.down.afficher(a.N);
					run(node.down,node,node.down.i,node.down.j,a);
					}else{
						node.down=null;
					}
				}
				
			}else System.out.println("I solved your 8 puzzle game");System.exit(5);;
			//if we reached the solution we will exit the program

		}
	

	public static boolean correct(int[][] tab,Arbre a) {//compare two tables used to see if we reached the answer
		int solution [][]=a.solution;
		if (Arrays.deepEquals(tab, solution)) {
			return true;
		}
		return false;

	}

	
	public static void main(String[] args) {
		//Remark: not all combinations are solvable
		int tab[][] = {{4,0,2,3 }, { 5,1,6,7 }, {8,9,10,11 },{12,13,14,15}};
		int N=4;
		//i and j correspond to the location of the empty case(0 in our program)
		int coord[]=Arbre.getCoordinates(tab, N);//coordinates of the 0 number which means the empty case
		int i = coord[0];//row of the 0
		int j = coord[1];//column of the 0
		Arbre a = new Arbre(new Node("does_not_matter", tab, i, j, null,N),N);//does_not_matter means not up not down nor left nor right
		a.racine.afficher(N);
		run(a.racine, null, i, j, a);
	}}

