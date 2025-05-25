import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class QuizApp {
    static Scanner scanner = new Scanner(System.in);
    static final int QUESTION_TIME_LIMIT = 45;

    public static void main(String[] args) {
        System.out.println("=== Welcome to the CLI Quiz Application ===");
        System.out.println("Instructions:");
        System.out.println("1. Choose a topic, mode, and format.");
        System.out.println("2. You must answer each question within " + QUESTION_TIME_LIMIT + " seconds.\n");

        System.out.print("Enter Topic (Java/Python/C++/HTML/SQL): ");
        String topic = scanner.nextLine().trim();

        System.out.print("Enter Mode (Beginner/Advanced): ");
        String mode = scanner.nextLine().trim();

        System.out.print("Enter Format (Direct/MCQ): ");
        String format = scanner.nextLine().trim();

        List<Question> questions = loadQuestions("questions.txt", topic, mode, format);
        if (questions.isEmpty()) {
            System.out.println("No questions found for the selected topic, mode, and format.");
            return;
        }

        Collections.shuffle(questions);
        int score = 0;

        for (int i = 0; i < Math.min(5, questions.size()); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.question);
            if (q.isMCQ) {
                for (int j = 0; j < q.options.size(); j++) {
                    System.out.println((j + 1) + ". " + q.options.get(j));
                }
            }

            System.out.print("Your Answer (within " + QUESTION_TIME_LIMIT + "s): ");
            String userAnswer = getTimedInput(QUESTION_TIME_LIMIT);

            if (userAnswer == null) {
                System.out.println("Time's up! No answer recorded.");
            } else if (q.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct Answer: " + q.answer);
            }
        }

        System.out.println("\nQuiz Completed! Your Score: " + score + " out of 5");
    }

    public static List<Question> loadQuestions(String filename, String topic, String mode, String format) {
        List<Question> questionList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean validSection = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("[Topic:")) {
                    validSection = line.toLowerCase().contains(topic.toLowerCase()) &&
                                   line.toLowerCase().contains(mode.toLowerCase()) &&
                                   line.toLowerCase().contains(format.toLowerCase());
                    continue;
                }

                if (validSection && line.startsWith("Q: ")) {
                    String question = line.substring(3);
                    List<String> options = new ArrayList<>();
                    String optionLine;
                    for (int i = 0; i < 4; i++) {
                        br.mark(1000);
                        optionLine = br.readLine();
                        if (optionLine != null && optionLine.matches("\\d\\. .*")) {
                            options.add(optionLine.substring(3));
                        } else {
                            br.reset();
                            break;
                        }
                    }

                    String answerLine = br.readLine();
                    if (answerLine != null && answerLine.startsWith("A: ")) {
                        String answer = answerLine.substring(3);
                        if (options.size() == 4) {
                            questionList.add(new Question(question, options, answer));
                        } else {
                            questionList.add(new Question(question, answer));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading questions file.");
        }
        return questionList;
    }

    public static String getTimedInput(int seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> scanner.nextLine());
        try {
            return future.get(seconds, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
        } catch (Exception ignored) {}
        executor.shutdownNow();
        return null;
    }
}
