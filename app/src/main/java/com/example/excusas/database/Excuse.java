package com.example.excusas.database;

import androidx.annotation.NonNull;

public class Excuse {
    String intro, core, outcome;

    public Excuse(String intro, String core, String outcome) {
        this.intro = intro;
        this.core = core;
        this.outcome = outcome;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @NonNull
    @Override
    public String toString() {
        return intro + " " + core + " " + outcome;
    }
}
