package com.tryton.small_world.auth.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StatusEntityPK implements Serializable {
    private long stsId;
    private long usersUsrId;

    @Column(name = "sts_id")
    @Id
    public long getStsId() {
        return stsId;
    }

    public void setStsId(long stsId) {
        this.stsId = stsId;
    }

    @Column(name = "users_usr_id")
    @Id
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
        StatusEntityPK that = (StatusEntityPK) o;
        return stsId == that.stsId && usersUsrId == that.usersUsrId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stsId, usersUsrId);
    }
}
