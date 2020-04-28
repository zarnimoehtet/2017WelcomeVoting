package abc.home.zarni.welcomevoting.model;

public class Selection {
    String name,section,fb;
    int profile,view;

    public Selection() {
    }

    public Selection(String name, String section, String fb, int profile, int view) {
        this.name = name;
        this.section = section;
        this.fb = fb;
        this.profile = profile;
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
