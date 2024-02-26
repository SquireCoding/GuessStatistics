import java.text.DecimalFormat;
import java.util.Random;
public class Main {
    final static int size=20000;
    public static void main(String[] args) {
        Question[] q = new Question[60];
        fill(q);
        guessByConsistent(q);
        System.out.println("Consistent:");
        analyze(q);

        fill(q);
        guessByRandom(q);
        System.out.println("Random:");
        analyze(q);

        fill(q);
        guessByInc(q);
        System.out.println("Incr:");
        analyze(q);
    }
    public static void fill(Question[] qu) {
        for (int i = 0; i<qu.length; i++) {
            qu[i]=new Question(new Random().nextInt(4)+1);
        }
    }

    public static void analyze(Question[] qu) {
        double total = qu.length;
        double corr = 0;
        for (Question q: qu) {
            if (q.correct) {
                //System.out.println("Question correct!");
                corr++;
            }// else System.out.println("Question incorrect!");
        }
        DecimalFormat df=new DecimalFormat("#.##");
        System.out.println("Correct: "+corr+", Incorrect: "+(total-corr)+", Score: %"+df.format((corr/total)*100.0));
    }
    public static void guessByConsistent(Question[] qu) {
        int one = new Random().nextInt(4)+1;
        for (Question q: qu) {
            q.guess(one);
        }
    }

    public static void guessByRandom(Question[] qu) {
        for (Question q: qu) {
            q.guess(new Random().nextInt(4)+1);
        }
    }

    public static void guessByInc(Question[] qu) {
        int ans = 1;
        for (Question q: qu) {
            q.guess(ans);
            ans++;
            if (ans>=5) ans=1;
        }
    }
}
class Question {
    int ans = 0;
    boolean correct=false;
    public Question(int a) {
        ans=a;
    }
    public void guess(int g) {
        correct=(g==ans);
    }
}
