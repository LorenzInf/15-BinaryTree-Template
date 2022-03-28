package control;

import model.BinaryTree;
import view.DrawingPanel;
import view.treeView.TreeNode;
import view.treeView.TreePath;

import java.util.Random;

/**
 * Created by Jean-Pierre on 12.01.2017.
 */
public class MainController {

    private BinaryTree<String> binaryTree;

    public MainController(){
        binaryTree = new BinaryTree<>(""); // Ein Baum ohne Wurzel-Inhalt ist auf dauer ein leerer Baum - es lassen sich laut Dokumentation nichtmal Bäume anhängen. Also wird die Wurzel gefüllt.
        createMorseTree();
    }

    /**
     * Zur Präsentation des Programms wird der Morsecode im Baum dargestellt.
     */
    private void createMorseTree(){
        BinaryTree<String> five = new BinaryTree<>("5");
        BinaryTree<String> four = new BinaryTree<>("4");

        BinaryTree<String> three = new BinaryTree<>("3");

        BinaryTree<String> two = new BinaryTree<>("2");

        BinaryTree<String> one = new BinaryTree<>("1");

        BinaryTree<String> six = new BinaryTree<>("6");

        BinaryTree<String> seven = new BinaryTree<>("7");

        BinaryTree<String> eight = new BinaryTree<>("8");

        BinaryTree<String> nine = new BinaryTree<>("9");
        BinaryTree<String> zero = new BinaryTree<>("0");


        BinaryTree<String> h = new BinaryTree<>("h",five,four);
        BinaryTree<String> v = new BinaryTree<>("v");
        v.setRightTree(three);

        BinaryTree<String> f = new BinaryTree<>("f");

        BinaryTree<String> l = new BinaryTree<>("l");

        BinaryTree<String> p = new BinaryTree<>("p");
        BinaryTree<String> j = new BinaryTree<>("j");
        j.setRightTree(one);

        BinaryTree<String> b = new BinaryTree<>("b");
        b.setLeftTree(six);

        BinaryTree<String> x = new BinaryTree<>("x");

        BinaryTree<String> c = new BinaryTree<>("c");

        BinaryTree<String> y = new BinaryTree<>("y");

        BinaryTree<String> z = new BinaryTree<>("z");
        z.setLeftTree(seven);

        BinaryTree<String> q = new BinaryTree<>("q");

        BinaryTree<String> toEight = new BinaryTree<>("");
        toEight.setLeftTree(eight);

        BinaryTree<String> toNineZero = new BinaryTree<>("",nine,zero);


        BinaryTree<String> s = new BinaryTree<>("s",h,v);
        BinaryTree<String> u = new BinaryTree<>("u");
        u.setLeftTree(f);

        BinaryTree<String> r = new BinaryTree<>("r");
        r.setLeftTree(l);
        BinaryTree<String> w = new BinaryTree<>("w",p,j);

        BinaryTree<String> d = new BinaryTree<>("d",b,x);
        BinaryTree<String> k = new BinaryTree<>("k",c,y);

        BinaryTree<String> g = new BinaryTree<>("g",z,q);
        BinaryTree<String> o = new BinaryTree<>("o",toEight,toNineZero);


        BinaryTree<String> i = new BinaryTree<>("i",s,u);
        BinaryTree<String> a = new BinaryTree<>("a",r,w);

        BinaryTree<String> n = new BinaryTree<>("n",d,k);
        BinaryTree<String> m = new BinaryTree<>("m",g,o);


        BinaryTree<String> e = new BinaryTree<>("e",i,a);
        BinaryTree<String> t = new BinaryTree<>("t",n,m);
        binaryTree.setLeftTree(e);
        binaryTree.setRightTree(t);
    }

    /**
     * Der Baum wird im übergebenem Panel dargestellt.
     * Dazu wird zunächst die alte Zeichnung entfernt.
     * Im Anschluss wird eine eine internete Hilfsmethode aufgerufen.
     * @param panel Das DrawingPanel-Objekt, auf dem gezeichnet wird.
     */
    public void showTree(DrawingPanel panel){
        panel.removeAllObjects();
        //Der Baum wird in der Mitte des Panels beginnend gezeichnet: x = panel.getWidth()/2
        //Der linke und rechte Knoten in Tiefe 1 sind jeweils ein Viertel der Breite des Panels entfernt: spaceToTheSide = panel.getWidth()/4
        showTree(binaryTree, panel, panel.getWidth()/2, 50, panel.getWidth()/4);
		
	    //Aufruf fordert das Panel zur Aktualisierung auf.
	    panel.repaint();
    }

    /**
     * Hilfsmethode zum Zeichnen des Baums.
     * Für jeden Knoten wird ein neues TreeNode-Objekt dem panel hinzugefügt.
     * Für jede Kante wird ein neues TreePath-Pbjekt dem panel hinzugefügt.
     * @param tree Der zu zeichnende (Teil)Binärbaum.
     * @param panel Das DrawingPanel-Objekt, auf dem gezeichnet wird.
     * @param startX x-Koordinate seiner Wurzel
     * @param startY y-Koordinate seiner Wurzel
     * @param spaceToTheSide Gibt an, wie weit horizontal entfernt die folgenden Bäume gezeichnet werden soll.
     */
    private void showTree(BinaryTree<String> tree, DrawingPanel panel, double startX, double startY, double spaceToTheSide) {
        if (!tree.isEmpty()) {
            TreeNode node = new TreeNode(startX, startY, 7, tree.getContent(), false);
            panel.addObject(node);
            if(!tree.getLeftTree().isEmpty()) {
                panel.addObject(new TreePath(startX - 20, startY - 10, startX - spaceToTheSide - 7, startY + 75 - 14, 7, new Random().nextBoolean()));
                showTree(tree.getLeftTree(),panel,startX - spaceToTheSide,startY + 75, spaceToTheSide/2);
            }
            if(!tree.getRightTree().isEmpty()) {
                panel.addObject(new TreePath(startX + 7, startY - 10, startX + spaceToTheSide - 5, startY + 75 - 14, 7, new Random().nextBoolean()));
                showTree(tree.getRightTree(),panel,startX + spaceToTheSide, startY + 75, spaceToTheSide/2);
            }
        }
    }

    /**
     * Es wird das Ergebnis einer Traversierung bestimmt.
     * Ruft dazu eine interne Hilfsmethode auf.
     * @return Das Ergebnis der Traversierung als Zeichenkette.
     */
    public String traverse(){
        return traverse(binaryTree);
    }

    /**
     * Interne hilfsmethode zur Traversierung.
     * @param tree Der zu traversierende Binärbaum.
     * @return Das Ergebnis der Traversierung als Zeichenkette.
     */
    private String traverse(BinaryTree<String> tree){
        if(tree.getLeftTree().isEmpty()) {
            if(tree.getRightTree().isEmpty()) {
                return tree.getContent();
            }
            return traverse(tree.getRightTree()) + ", " + tree.getContent() + ", ";
        }
        return traverse(tree.getLeftTree()) + ", " + traverse(tree.getRightTree()) + ", " + tree.getContent() + ", ";
    }
	
    /**
     * Interne Übungsmethode zur Traversierung.
     * @param tree Der zu traversierende Binärbaum.
     * @return Die Anzahl der Knoten in diesem Baum
     */
    private int countNodes(BinaryTree<String> tree){
        //TODO 05: Übungsmethode
	return 0;
    }
}
