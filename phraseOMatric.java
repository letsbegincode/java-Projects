public class phraseOMatric {

public static void main(String[] args) {
    String[] wordListone = {"agnostic","opinionated","voice-activated","haptically driven","extensible","reactive"};
    
    String[] wordListTwo = {"loosely coupled", "six sigma","asynchronous","event driven","pub-sub","IoT"};
    String[] wordListThree = {"framework", "lirary","DSL","REST API","repository","pipeline"};

    int oneLength = wordListone.length;
    int twoLength = wordListTwo.length;
    int threeLength = wordListThree.length;

    java.util.Random randomGenerator = new java.util.Random();

    int rand1 = randomGenerator.nextInt(oneLength);
    int rand2 = randomGenerator.nextInt(twoLength);
    int rand3 = randomGenerator.nextInt(threeLength);

    System.out.println(rand1);

}
    
}
