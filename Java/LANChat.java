// =====================================
// 🔹 Title: Simple Chat (Console Version)
// =====================================

import java.util.*;

public class SimpleChat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> chat = new ArrayList<>();
        System.out.println("===== SIMPLE CHAT =====");
        while (true) {
            System.out.print("You: ");
            String msg = sc.nextLine();
            if (msg.equalsIgnoreCase("exit")) break;
            chat.add("You: " + msg);
            chat.add("Bot: " + reply(msg));
            chat.forEach(System.out::println);
            chat.clear();
        }
        sc.close();
    }

    private static String reply(String msg) {
        msg = msg.toLowerCase();
        if (msg.contains("hello")) return "Hi there!";
        if (msg.contains("how are you")) return "I'm good, thanks!";
        if (msg.contains("bye")) return "Goodbye!";
        return "I don't understand.";
    }
}
