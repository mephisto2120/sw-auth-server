package com.tryton.small_world.auth.db;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "status")
@NamedQueries({
    @NamedQuery(name = "StatusEntity.findAll", query = "SELECT s FROM StatusEntity s"),
    @NamedQuery(name = "StatusEntity.findByStsId", query = "SELECT s FROM StatusEntity s WHERE s.statusEntityPK.stsId = :stsId"),
    @NamedQuery(name = "StatusEntity.findByStsName", query = "SELECT s FROM StatusEntity s WHERE s.stsName = :stsName"),
    @NamedQuery(name = "StatusEntity.findByStsDesc", query = "SELECT s FROM StatusEntity s WHERE s.stsDesc = :stsDesc"),
    @NamedQuery(name = "StatusEntity.findByUsersUsrId", query = "SELECT s FROM StatusEntity s WHERE s.statusEntityPK.usersUsrId = :usersUsrId")})
public class StatusEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StatusEntityPK statusEntityPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sts_name")
    private String stsName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sts_desc")
    private String stsDesc;
    @JoinColumn(name = "users_usr_id", referencedColumnName = "usr_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UsersEntity usersEntity;

    public StatusEntity() {
    }

    public StatusEntity(StatusEntityPK statusEntityPK) {
        this.statusEntityPK = statusEntityPK;
    }

    public StatusEntity(StatusEntityPK statusEntityPK, String stsName, String stsDesc) {
        this.statusEntityPK = statusEntityPK;
        this.stsName = stsName;
        this.stsDesc = stsDesc;
    }

    public StatusEntity(long stsId, long usersUsrId) {
        this.statusEntityPK = new StatusEntityPK(stsId, usersUsrId);
    }

    public StatusEntityPK getStatusEntityPK() {
        return statusEntityPK;
    }

    public void setStatusEntityPK(StatusEntityPK statusEntityPK) {
        this.statusEntityPK = statusEntityPK;
    }

    public String getStsName() {
        return stsName;
    }

    public void setStsName(String stsName) {
        this.stsName = stsName;
    }

    public String getStsDesc() {
        return stsDesc;
    }

    public void setStsDesc(String stsDesc) {
        this.stsDesc = stsDesc;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusEntityPK != null ? statusEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusEntity)) {
            return false;
        }
        StatusEntity other = (StatusEntity) object;
        if ((this.statusEntityPK == null && other.statusEntityPK != null) || (this.statusEntityPK != null && !this.statusEntityPK.equals(other.statusEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.small_world.auth.db.StatusEntity[ statusEntityPK=" + statusEntityPK + " ]";
    }
    
}
