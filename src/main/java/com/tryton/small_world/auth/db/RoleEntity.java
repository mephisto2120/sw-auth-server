package com.tryton.small_world.auth.db;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "role")
//@NamedQueries({
//    @NamedQuery(name = "RoleEntity.findAll", query = "SELECT r FROM RoleEntity r"),
//    @NamedQuery(name = "RoleEntity.findByRolId", query = "SELECT r FROM RoleEntity r WHERE r.rolId = :rolId"),
//    @NamedQuery(name = "RoleEntity.findByRolName", query = "SELECT r FROM RoleEntity r WHERE r.rolName = :rolName"),
//    @NamedQuery(name = "RoleEntity.findByRolDesc", query = "SELECT r FROM RoleEntity r WHERE r.rolDesc = :rolDesc")})
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "rol_id")
    private Long rolId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rol_name")
    private String rolName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "rol_desc")
    private String rolDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleEntity", fetch = FetchType.EAGER)
    private List<UsersRolesEntity> usersRolesEntityList;

    public RoleEntity() {
    }

    public RoleEntity(Long rolId) {
        this.rolId = rolId;
    }

    public RoleEntity(Long rolId, String rolName, String rolDesc) {
        this.rolId = rolId;
        this.rolName = rolName;
        this.rolDesc = rolDesc;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getRolDesc() {
        return rolDesc;
    }

    public void setRolDesc(String rolDesc) {
        this.rolDesc = rolDesc;
    }

    public List<UsersRolesEntity> getUsersRolesEntityList() {
        return usersRolesEntityList;
    }

    public void setUsersRolesEntityList(List<UsersRolesEntity> usersRolesEntityList) {
        this.usersRolesEntityList = usersRolesEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleEntity)) {
            return false;
        }
        RoleEntity other = (RoleEntity) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.small_world.auth.db.RoleEntity[ rolId=" + rolId + " ]";
    }
    
}
