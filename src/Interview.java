import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Interview extends QuestionsReviewer {

    private final HashMap<String, List<String>> questions = new LinkedHashMap<>();

    protected void addQuestions(String question, List<String> keyWords) {
        questions.put(question, keyWords);
    }

    public int startInterview() {
        AtomicInteger fullScore = new AtomicInteger();
        questions.forEach((question, keyWords) ->
                {
                    System.out.println(question);
                    fullScore.addAndGet(score(keyWords));
                }

        );
        return fullScore.get() / questions.size();
    }

    public static void main(String[] args) {

        InterviewBuilder ib = new InterviewBuilder();
        Interview interview = ib.warmUp("What are your hobbies?",
                        List.of("hiking", "trekking", "board", "run", "jog", "mindfulness", "programming", "java"))
                .firstTechnical("What is the difference between ArrayList and LinkedList in Java?",
                        List.of("arraylist", "linkedlist", "data", "performance", "random", "iteration", "memory", "insertion", "deletion", "collections", "efficiency"))
                .secondTechnical("Explain the concept of multithreading in Java. How do you create and manage threads in Java?",
                        List.of("multithreading", "threads", "concurrency", "runnable", "thread", "synchronization", "safety", "deadlock", "java.util.concurrent"))
                .thirdTechnical("What are the differences between equals() and == in Java? When should you use one over the other?",
                        List.of("equals", "==", "object", "comparison", "value", "reference", "overriding", "primitive", "operator"))
                .build();

        int score = interview.startInterview();

        System.out.println("Your score is " + score + " out of 5");

    }
}

class QuestionsReviewer {

    public int score(List<String> keyWords) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        String formattedText = sb.toString().toLowerCase();
        int score = 0;

        for (String keyWord : keyWords) {
            if (formattedText.contains(keyWord)) {
                score++;
            }
        }
        return Math.min(score, 5);
    }
}

class InterviewBuilder implements InterviewStages {

    private final Interview interview = new Interview();

    public Interview build() {
        return interview;
    }

    @Override
    public InterviewBuilder warmUp(String warmUp, List<String> keyWords) {
        interview.addQuestions(warmUp, keyWords);
        return this;
    }

    @Override
    public InterviewBuilder firstTechnical(String technical, List<String> keyWords) {
        interview.addQuestions(technical, keyWords);
        return this;
    }

    @Override
    public InterviewBuilder secondTechnical(String technical, List<String> keyWords) {
        interview.addQuestions(technical, keyWords);
        return this;
    }

    @Override
    public InterviewBuilder thirdTechnical(String technical, List<String> keyWords) {
        interview.addQuestions(technical, keyWords);
        return this;
    }

    @Override
    public InterviewBuilder solutioning(String solution, List<String> keyWords) {
        interview.addQuestions(solution, keyWords);
        return this;
    }
}

interface InterviewStages {
    InterviewBuilder warmUp(String warmUp, List<String> keyWords);

    InterviewBuilder firstTechnical(String technical, List<String> keyWords);

    InterviewBuilder secondTechnical(String technical, List<String> keyWords);

    InterviewBuilder thirdTechnical(String technical, List<String> keyWords);

    InterviewBuilder solutioning(String solution, List<String> keyWords);
}
