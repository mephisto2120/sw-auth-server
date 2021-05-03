package com.tryton.small_world.auth.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsersRolesEntityPK implements Serializable {
    private long urUsrId;
    private long urRolId;

    @Column(name = "ur_usr_id")
    @Id
    public long getUrUsrId() {
        return urUsrId;
    }

    public void setUrUsrId(long urUsrId) {
        this.urUsrId = urUsrId;
    }

    @Column(name = "ur_rol_id")
    @Id
    public long getUrRolId() {
        return urRolId;
    }

    public void setUrRolId(long urRolId) {
        this.urRolId = urRolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRolesEntityPK that = (UsersRolesEntityPK) o;
        return urUsrId == that.urUsrId && urRolId == that.urRolId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(urUsrId, urRolId);
    }
}
