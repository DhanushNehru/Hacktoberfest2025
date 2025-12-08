import java.util.ArrayList;
import java.util.List;

public class CompareTriplets {
    static List<Integer> compareTriplets(List<Integer> alice, List<Integer> bob){
        List<Integer> pontuation = new ArrayList<>();
        int pointsBob = 0, pointsAlice = 0;

        for(int i = 0; i < 3; i++){
            if(alice.get(i) > bob.get(i)){
                pointsAlice ++;
            } else if (alice.get(i) < bob.get(i)){
                pointsBob ++;
            }
        }

        pontuation.add(pointsAlice);
        pontuation.add(pointsBob);

        return pontuation;
    }

    public static void main(String[] args) {
        List<Integer> alice = new ArrayList<>(3);
        List<Integer> bob = new ArrayList<>(3);

        alice.add(5);
        alice.add(6);
        alice.add(7);

        bob.add(3);
        bob.add(6);
        bob.add(10);

        System.out.println(compareTriplets(alice, bob));
    }
}
