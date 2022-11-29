package com.example.sampleproject.Model;

public class UserRoles {

    private String id;
    private String name;
    private String description;
    private String composite;
    private String assigned;

    public UserRoles(String id, String name, String description, String  composite, String assigned) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.composite = composite;
        this.assigned = assigned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComposite() {
        return composite;
    }

    public void setComposite(String composite) {
        this.composite = composite;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    @Override
    public String   toString() {
        return "UserRoles{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", composite='" + composite + '\'' +
                ", assigned='" + assigned + '\'' +
                '}';
    }
}
