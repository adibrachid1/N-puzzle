import java.util.Arrays;
/*This class is almost the same as DFS but we changed it the run's method.
 *We added two parameters the limit, and the depth so we can stop the program at depth l.
*/
class DLS {
	private static boolean  displayInOrder(Node root,int[][] initial,Node node) {
	    if (root != null) {
	      if(Arrays.deepEquals(root.tab, initial) && root!=node){
	    	 	return false;
	      }
	      return(
	      displayInOrder(root.up,initial,node)&&
	      displayInOrder(root.down,initial,node)&&
	      displayInOrder(root.left,initial,node)&&
	      displayInOrder(root.right,initial,node)
	      );
	    }
	    return true;
	  }
		
	public static void run(Node node, Node parent, int i, int j,Arbre a ,int l,int d) {
		if(!correct(node.tab,a)) {//we will do it if we did not reach the answer
			// by up we mean that the empty case will move up
			//d means the depth of the tree ; while it s less or equal than the limit, we still search
			if(d<=l){
			if (i != 0) {//to make sure that the empty case(0 in the code) is not in the first row
				node.up = new Node("up", node.tab, node.i, node.j, node.parent,a.N);//create the new node
				if(displayInOrder(a.racine,node.up.tab,node.up)){//if the node does not exist in the tree the return will be true
					node.up.afficher(a.N);//afficher will show the current puzzle
					run(node.up,node,node.up.i,node.up.j,a,l,d+1);//we will do the child of the node we are at
				}else{
					node.up=null;//if the table exist in the tree the return will be false we will delete the node that we created
				}
			}
			if (j != 0) {//we make sure that the empty case is not in the first column 
				node.left = new Node("left", node.tab, node.i, node.j, node.parent,a.N);
				if(displayInOrder(a.racine,node.left.tab,node.left)){
					node.left.afficher(a.N);
					run(node.left,node,node.left.i,node.left.j,a,l,d+1);
				}else{
					node.left=null;
				}
			}
			
			if (j != a.N-1) {//we make sure that the empty case is not in the last column
				node.right = new Node("right", node.tab, node.i, node.j, node.parent,a.N);
				if(displayInOrder(a.racine,node.right.tab,node.right)){
					node.right.afficher(a.N);
				run(node.right,node,node.right.i,node.right.j,a,l,d+1);
				}else{
					node.right=null;
				}
			}
			if (i != a.N-1) {//we make sure that the empty case is not in the last row
				node.down = new Node("down", node.tab, node.i, node.j, node,a.N);
				if(displayInOrder(a.racine,node.down.tab,node.down)){
					node.down.afficher(a.N);
				run(node.down,node,node.down.i,node.down.j,a,l,d+1);
				}else{
					node.down=null;
				}
			}return;}
			else{return;}
			
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
		//Remark: not all cases are solvable
		int tab[][] = {{4,0,2,3 }, { 5,1,6,7 }, {8,9,10,11 },{12,13,14,15}};
		int N=4;
		int coord[]=Arbre.getCoordinates(tab, N);
		int i = coord[0];
		int j = coord[1];
		int l=3;
		Arbre a = new Arbre(new Node("does_not_matter", tab, i, j, null,N),N);
		a.racine.afficher(N);
		run(a.racine, null, i, j, a,l,1);
	}}

