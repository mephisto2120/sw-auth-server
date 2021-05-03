package com.tryton.small_world.auth.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users_roles", schema = "auth", catalog = "")
@IdClass(UsersRolesEntityPK.class)
public class UsersRolesEntity {
    private long urUsrId;
    private long urRolId;
    private String urDateInserted;
    private Timestamp urDateModified;

    @Id
    @GeneratedValue
    @Column(name = "ur_usr_id")
    public long getUrUsrId() {
        return urUsrId;
    }

    public void setUrUsrId(long urUsrId) {
        this.urUsrId = urUsrId;
    }

    @Id
    @Column(name = "ur_rol_id")
    public long getUrRolId() {
        return urRolId;
    }

    public void setUrRolId(long urRolId) {
        this.urRolId = urRolId;
    }

    @Basic
    @Column(name = "ur_date_inserted")
    public String getUrDateInserted() {
        return urDateInserted;
    }

    public void setUrDateInserted(String urDateInserted) {
        this.urDateInserted = urDateInserted;
    }

    @Basic
    @Column(name = "ur_date_modified")
    public Timestamp getUrDateModified() {
        return urDateModified;
    }

    public void setUrDateModified(Timestamp urDateModified) {
        this.urDateModified = urDateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRolesEntity that = (UsersRolesEntity) o;
        return urUsrId == that.urUsrId && urRolId == that.urRolId && Objects.equals(urDateInserted, that.urDateInserted) && Objects.equals(urDateModified, that.urDateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urUsrId, urRolId, urDateInserted, urDateModified);
    }
}
