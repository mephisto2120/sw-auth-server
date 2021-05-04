package com.tryton.small_world.auth.db;

import lombok.Builder;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Builder
@Embeddable
public class UsersRolesEntityPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ur_usr_id")
    private long urUsrId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ur_rol_id")
    private long urRolId;

    public UsersRolesEntityPK() {
    }

    public UsersRolesEntityPK(long urUsrId, long urRolId) {
        this.urUsrId = urUsrId;
        this.urRolId = urRolId;
    }

    public long getUrUsrId() {
        return urUsrId;
    }

    public void setUrUsrId(long urUsrId) {
        this.urUsrId = urUsrId;
    }

    public long getUrRolId() {
        return urRolId;
    }

    public void setUrRolId(long urRolId) {
        this.urRolId = urRolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) urUsrId;
        hash += (int) urRolId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersRolesEntityPK)) {
            return false;
        }
        UsersRolesEntityPK other = (UsersRolesEntityPK) object;
        if (this.urUsrId != other.urUsrId) {
            return false;
        }
        if (this.urRolId != other.urRolId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.small_world.auth.db.UsersRolesEntityPK[ urUsrId=" + urUsrId + ", urRolId=" + urRolId + " ]";
    }
    
}
