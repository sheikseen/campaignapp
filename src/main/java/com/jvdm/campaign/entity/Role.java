package com.jvdm.campaign.entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role
{

    @Id
    @Enumerated(EnumType.STRING)
    private RoleId id;

    private String name;

    public Role() {}

    public RoleId getId() {
        return id;
    }

    public Role setId(RoleId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public enum RoleId {
        ADMIN,
        GUEST,
        MANAGER

    }
}
