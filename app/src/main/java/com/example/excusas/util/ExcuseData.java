package com.example.excusas.util;

import java.util.List;

public class ExcuseData {
    public List<String> intros;
    public List<String> cores;
    public List<String> outcomes;

    public ExcuseData(List<String> intros, List<String> cores, List<String> outcomes) {
        this.intros = intros;
        this.cores = cores;
        this.outcomes = outcomes;
    }

    public List<String> getIntros() {
        return intros;
    }

    public void setIntros(List<String> intros) {
        this.intros = intros;
    }

    public List<String> getCores() {
        return cores;
    }

    public void setCores(List<String> cores) {
        this.cores = cores;
    }

    public List<String> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<String> outcomes) {
        this.outcomes = outcomes;
    }
}
