package com.gopal.spacexcrews.modal;

import java.util.List;

public class ReponseModal {

    List<CrewsModal> docs;

    @Override
    public String toString() {
        return "ReponseModal{" +
                "docs=" + docs +
                '}';
    }

    public List<CrewsModal> getDocs() {
        return docs;
    }

    public void setDocs(List<CrewsModal> docs) {
        this.docs = docs;
    }

    public ReponseModal(List<CrewsModal> docs) {
        this.docs = docs;
    }
}
