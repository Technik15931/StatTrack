package technik.stattrack;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {
    private static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String hexColor(String text) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, String.valueOf(ChatColor.valueOf(color)));
            matcher = pattern.matcher(text);
        }
        return color(text);
    }
}
