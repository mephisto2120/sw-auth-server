package com.tryton.small_world.auth.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "role", schema = "auth", catalog = "")
public class RoleEntity {
    private long rolId;
    private String rolName;
    private String rolDesc;

    @Id
    @GeneratedValue
    @Column(name = "rol_id")
    public long getRolId() {
        return rolId;
    }

    public void setRolId(long rolId) {
        this.rolId = rolId;
    }

    @Basic
    @Column(name = "rol_name")
    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    @Basic
    @Column(name = "rol_desc")
    public String getRolDesc() {
        return rolDesc;
    }

    public void setRolDesc(String rolDesc) {
        this.rolDesc = rolDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return rolId == that.rolId && Objects.equals(rolName, that.rolName) && Objects.equals(rolDesc, that.rolDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolId, rolName, rolDesc);
    }
}
