package org.softwire.training.bookish.restService.models;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class passHashChecker {
    private String hashedPass;

    public passHashChecker(String hashedPass) {
        this.hashedPass = Hashing.sha256()
                .hashString(hashedPass, StandardCharsets.UTF_8)
                .toString();
    }

    public String getHashedPass() {
        return hashedPass;
    }

    public void setHashedPass(String hashedPass) {
        this.hashedPass = hashedPass;
    }


    public boolean equals(String userPassword) {
        return hashedPass.equals(userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashedPass);
    }
}
