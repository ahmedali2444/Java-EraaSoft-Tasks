package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class EnvironmentConfiguration {

    private static final Map<String, String> VALUES = load();

    private EnvironmentConfiguration() {
    }

    public static String get(String key) {
        String environmentValue = System.getenv(key);
        if (environmentValue != null && !environmentValue.trim().isEmpty()) {
            return environmentValue.trim();
        }
        return VALUES.get(key);
    }

    public static String getOrDefault(String key, String fallback) {
        String value = get(key);
        return value == null || value.trim().isEmpty() ? fallback : value;
    }

    private static Map<String, String> load() {
        Map<String, String> values = new ConcurrentHashMap<>();
        for (Path candidate : findCandidates()) {
            if (!Files.isRegularFile(candidate)) {
                continue;
            }
            try {
                parse(Files.readAllLines(candidate), values);
                break;
            } catch (IOException exception) {
            }
        }
        return values;
    }

    private static Set<Path> findCandidates() {
        Set<Path> candidates = new LinkedHashSet<>();
        String configuredPath = System.getProperty("ewallet.config.path");
        if (configuredPath == null || configuredPath.trim().isEmpty()) {
            configuredPath = System.getProperty("ewallet.env.path");
        }
        if (configuredPath == null || configuredPath.trim().isEmpty()) {
            configuredPath = System.getenv("EWALLET_ENV_PATH");
        }
        if (configuredPath != null && !configuredPath.trim().isEmpty()) {
            candidates.add(Paths.get(configuredPath.trim()));
        }

        String userHome = System.getProperty("user.home", ".");
        candidates.add(Paths.get(userHome, ".ewallet", "smtp.properties"));

        String catalinaBase = System.getProperty("catalina.base");
        if (catalinaBase != null && !catalinaBase.trim().isEmpty()) {
            candidates.add(Paths.get(catalinaBase, "conf", "ewallet.properties"));
        }

        addParentCandidates(candidates, Paths.get(System.getProperty("user.dir", ".")));
        try {
            Path classLocation = Paths.get(EnvironmentConfiguration.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
            addParentCandidates(candidates, Files.isDirectory(classLocation)
                    ? classLocation : classLocation.getParent());
        } catch (URISyntaxException | SecurityException exception) {
        }
        return candidates;
    }

    private static void addParentCandidates(Set<Path> candidates, Path start) {
        Path current = start == null ? null : start.toAbsolutePath().normalize();
        for (int level = 0; current != null && level < 7; level++) {
            candidates.add(current.resolve(".env"));
            current = current.getParent();
        }
    }

    private static void parse(List<String> lines, Map<String, String> values) {
        for (String line : lines) {
            String value = line.trim();
            if (value.isEmpty() || value.startsWith("#")) {
                continue;
            }
            int separator = value.indexOf('=');
            if (separator < 1) {
                continue;
            }
            String key = value.substring(0, separator).trim();
            String setting = value.substring(separator + 1).trim();
            boolean quotedWithDoubleQuotes = setting.startsWith("\"") && setting.endsWith("\"");
            boolean quotedWithSingleQuotes = setting.startsWith("'") && setting.endsWith("'");
            if (setting.length() >= 2 && (quotedWithDoubleQuotes || quotedWithSingleQuotes)) {
                setting = setting.substring(1, setting.length() - 1);
            }
            values.put(key, setting);
        }
    }
}
