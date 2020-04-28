package abc.home.zarni.welcomevoting.model;

public class Admin {
    private  String permission;

    public Admin() {
    }

    public Admin(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
