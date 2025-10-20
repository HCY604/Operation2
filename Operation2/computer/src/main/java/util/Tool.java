package util;

import model.Member;

public class Tool {
    private static Member currentMember;

    public static void saveMember(Member m) {
        currentMember = m;
    }

    public static Member loadMember() {
        return currentMember;
    }

    public static void clearMember() {
        currentMember = null;
    }
}
