package com.tripfolio.locationService.domain.util;


import org.springframework.stereotype.Component;

@Component
public class InputSanitizer {


    // Use this for *plain text search* — strip tags and untrusted content
    public String sanitizeForSearch(String input) {
        if (input == null) return null;
        // 1) Basic trim
        String trimmed = input.trim();
        if (trimmed.isEmpty()) return null;

        // 2) Remove NULL chars
        trimmed = trimmed.replace("\u0000", "");

        // 3) Use sanitizer to remove risky tags/attributes (this yields plain-ish text if input is HTML)

        //    This avoids "javascript:" or "onerror=" leftovers that could be part of user input
        String cleaned = trimmed.replaceAll("(?i)javascript:", "")
                .replaceAll("(?i)on\\w+\\s*=", "")
                .trim();

        // 5) If cleaned becomes empty, treat as invalid
        if (cleaned.isEmpty()) return null;

        return cleaned;
    }
}
