package com.tryton.small_world.auth.db;

import lombok.Builder;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Builder
@Embeddable
public class StatusEntityPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "sts_id")
    private long stsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "users_usr_id")
    private long usersUsrId;

    public StatusEntityPK() {
    }

    public StatusEntityPK(long stsId, long usersUsrId) {
        this.stsId = stsId;
        this.usersUsrId = usersUsrId;
    }

    public long getStsId() {
        return stsId;
    }

    public void setStsId(long stsId) {
        this.stsId = stsId;
    }

    public long getUsersUsrId() {
        return usersUsrId;
    }

    public void setUsersUsrId(long usersUsrId) {
        this.usersUsrId = usersUsrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) stsId;
        hash += (int) usersUsrId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusEntityPK)) {
            return false;
        }
        StatusEntityPK other = (StatusEntityPK) object;
        if (this.stsId != other.stsId) {
            return false;
        }
        if (this.usersUsrId != other.usersUsrId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.small_world.auth.db.StatusEntityPK[ stsId=" + stsId + ", usersUsrId=" + usersUsrId + " ]";
    }
    
}
