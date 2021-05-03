package com.tryton.small_world.auth.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "status", schema = "auth", catalog = "")
@IdClass(StatusEntityPK.class)
public class StatusEntity {
    private long stsId;
    private String stsName;
    private String stsDesc;
    private long usersUsrId;

    @Id
    @GeneratedValue
    @Column(name = "sts_id")
    public long getStsId() {
        return stsId;
    }

    public void setStsId(long stsId) {
        this.stsId = stsId;
    }

    @Basic
    @Column(name = "sts_name")
    public String getStsName() {
        return stsName;
    }

    public void setStsName(String stsName) {
        this.stsName = stsName;
    }

    @Basic
    @Column(name = "sts_desc")
    public String getStsDesc() {
        return stsDesc;
    }

    public void setStsDesc(String stsDesc) {
        this.stsDesc = stsDesc;
    }

    @Id
    @Column(name = "users_usr_id")
    public long getUsersUsrId() {
        return usersUsrId;
    }

    public void setUsersUsrId(long usersUsrId) {
        this.usersUsrId = usersUsrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return stsId == that.stsId && usersUsrId == that.usersUsrId && Objects.equals(stsName, that.stsName) && Objects.equals(stsDesc, that.stsDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stsId, stsName, stsDesc, usersUsrId);
    }
}
