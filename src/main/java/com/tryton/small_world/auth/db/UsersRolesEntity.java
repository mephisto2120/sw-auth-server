package com.tryton.small_world.auth.db;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "users_roles")
@NamedQueries({
    @NamedQuery(name = "UsersRolesEntity.findAll", query = "SELECT u FROM UsersRolesEntity u"),
    @NamedQuery(name = "UsersRolesEntity.findByUrUsrId", query = "SELECT u FROM UsersRolesEntity u WHERE u.usersRolesEntityPK.urUsrId = :urUsrId"),
    @NamedQuery(name = "UsersRolesEntity.findByUrRolId", query = "SELECT u FROM UsersRolesEntity u WHERE u.usersRolesEntityPK.urRolId = :urRolId"),
    @NamedQuery(name = "UsersRolesEntity.findByUrDateInserted", query = "SELECT u FROM UsersRolesEntity u WHERE u.urDateInserted = :urDateInserted"),
    @NamedQuery(name = "UsersRolesEntity.findByUrDateModified", query = "SELECT u FROM UsersRolesEntity u WHERE u.urDateModified = :urDateModified")})
public class UsersRolesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersRolesEntityPK usersRolesEntityPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ur_date_inserted")
    private String urDateInserted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ur_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date urDateModified;
    @JoinColumn(name = "ur_rol_id", referencedColumnName = "rol_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RoleEntity roleEntity;
    @JoinColumn(name = "ur_usr_id", referencedColumnName = "usr_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UsersEntity usersEntity;

    public UsersRolesEntity() {
    }

    public UsersRolesEntity(UsersRolesEntityPK usersRolesEntityPK) {
        this.usersRolesEntityPK = usersRolesEntityPK;
    }

    public UsersRolesEntity(UsersRolesEntityPK usersRolesEntityPK, String urDateInserted, Date urDateModified) {
        this.usersRolesEntityPK = usersRolesEntityPK;
        this.urDateInserted = urDateInserted;
        this.urDateModified = urDateModified;
    }

    public UsersRolesEntity(long urUsrId, long urRolId) {
        this.usersRolesEntityPK = new UsersRolesEntityPK(urUsrId, urRolId);
    }

    public UsersRolesEntityPK getUsersRolesEntityPK() {
        return usersRolesEntityPK;
    }

    public void setUsersRolesEntityPK(UsersRolesEntityPK usersRolesEntityPK) {
        this.usersRolesEntityPK = usersRolesEntityPK;
    }

    public String getUrDateInserted() {
        return urDateInserted;
    }

    public void setUrDateInserted(String urDateInserted) {
        this.urDateInserted = urDateInserted;
    }

    public Date getUrDateModified() {
        return urDateModified;
    }

    public void setUrDateModified(Date urDateModified) {
        this.urDateModified = urDateModified;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
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
        hash += (usersRolesEntityPK != null ? usersRolesEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersRolesEntity)) {
            return false;
        }
        UsersRolesEntity other = (UsersRolesEntity) object;
        if ((this.usersRolesEntityPK == null && other.usersRolesEntityPK != null) || (this.usersRolesEntityPK != null && !this.usersRolesEntityPK.equals(other.usersRolesEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.small_world.auth.db.UsersRolesEntity[ usersRolesEntityPK=" + usersRolesEntityPK + " ]";
    }
    
}
