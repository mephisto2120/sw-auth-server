package com.tryton.small_world.auth.db;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "status")
@NamedQueries({
        @NamedQuery(name = "StatusEntity.findAll", query = "SELECT s FROM StatusEntity s"),
        @NamedQuery(name = "StatusEntity.findByStsId", query = "SELECT s FROM StatusEntity s WHERE s.stsId = :stsId"),
        @NamedQuery(name = "StatusEntity.findByStsName", query = "SELECT s FROM StatusEntity s WHERE s.stsName = :stsName"),
        @NamedQuery(name = "StatusEntity.findByStsDesc", query = "SELECT s FROM StatusEntity s WHERE s.stsDesc = :stsDesc")})
public class StatusEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "sts_id")
    private Long stsId;
    @NotNull
    @Size(min = 1, max = 45)
    @Basic(optional = false)
    @Column(name = "sts_name")
    private String stsName;
    @NotNull
    @Size(min = 1, max = 100)
    @Basic(optional = false)
    @Column(name = "sts_desc")
    private String stsDesc;
    @OneToMany(mappedBy = "usrStsId")
    private List<UsersEntity> usersEntityList;

    public StatusEntity() {
    }

    public StatusEntity(Long stsId) {
        this.stsId = stsId;
    }

    public StatusEntity(Long stsId, String stsName, String stsDesc) {
        this.stsId = stsId;
        this.stsName = stsName;
        this.stsDesc = stsDesc;
    }

    public Long getStsId() {
        return stsId;
    }

    public void setStsId(Long stsId) {
        this.stsId = stsId;
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

    public List<UsersEntity> getUsersEntityList() {
        return usersEntityList;
    }

    public void setUsersEntityList(List<UsersEntity> usersEntityList) {
        this.usersEntityList = usersEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stsId != null ? stsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusEntity)) {
            return false;
        }
        StatusEntity other = (StatusEntity) object;
        if ((this.stsId == null && other.stsId != null) || (this.stsId != null && !this.stsId.equals(other.stsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.small_world.auth.db.StatusEntity[ stsId=" + stsId + " ]";
    }
    
}
