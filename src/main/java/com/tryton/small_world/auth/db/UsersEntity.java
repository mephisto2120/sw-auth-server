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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
//@NamedQueries({
//    @NamedQuery(name = "UsersEntity.findAll", query = "SELECT u FROM UsersEntity u"),
//    @NamedQuery(name = "UsersEntity.findByUsrId", query = "SELECT u FROM UsersEntity u WHERE u.usrId = :usrId"),
//    @NamedQuery(name = "UsersEntity.findByUsrEmail", query = "SELECT u FROM UsersEntity u WHERE u.usrEmail = :usrEmail"),
//    @NamedQuery(name = "UsersEntity.findByUsrPassword", query = "SELECT u FROM UsersEntity u WHERE u.usrPassword = :usrPassword"),
//    @NamedQuery(name = "UsersEntity.findByUsrLastName", query = "SELECT u FROM UsersEntity u WHERE u.usrLastName = :usrLastName"),
//    @NamedQuery(name = "UsersEntity.findByUsrFirstName", query = "SELECT u FROM UsersEntity u WHERE u.usrFirstName = :usrFirstName"),
//    @NamedQuery(name = "UsersEntity.findByUsrDateInserted", query = "SELECT u FROM UsersEntity u WHERE u.usrDateInserted = :usrDateInserted"),
//    @NamedQuery(name = "UsersEntity.findByUsrDateModified", query = "SELECT u FROM UsersEntity u WHERE u.usrDateModified = :usrDateModified")})
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "usr_entity_seq", sequenceName = "usr_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_entity_seq")
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_id")
    private Long usrId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usr_email")
    private String usrEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "usr_password")
    private String usrPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "usr_last_name")
    private String usrLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "usr_first_name")
    private String usrFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usr_date_inserted")
    private String usrDateInserted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrDateModified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersEntity", fetch = FetchType.EAGER)
    private List<UsersRolesEntity> usersRolesEntityList;
    @JoinColumn(name = "usr_sts_id", referencedColumnName = "sts_id")
    @ManyToOne
    private StatusEntity usrStsId;

    public UsersEntity() {
    }

    public UsersEntity(Long usrId) {
        this.usrId = usrId;
    }

    public UsersEntity(Long usrId, String usrEmail, String usrPassword, String usrLastName, String usrFirstName, String usrDateInserted, Date usrDateModified) {
        this.usrId = usrId;
        this.usrEmail = usrEmail;
        this.usrPassword = usrPassword;
        this.usrLastName = usrLastName;
        this.usrFirstName = usrFirstName;
        this.usrDateInserted = usrDateInserted;
        this.usrDateModified = usrDateModified;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrLastName() {
        return usrLastName;
    }

    public void setUsrLastName(String usrLastName) {
        this.usrLastName = usrLastName;
    }

    public String getUsrFirstName() {
        return usrFirstName;
    }

    public void setUsrFirstName(String usrFirstName) {
        this.usrFirstName = usrFirstName;
    }

    public String getUsrDateInserted() {
        return usrDateInserted;
    }

    public void setUsrDateInserted(String usrDateInserted) {
        this.usrDateInserted = usrDateInserted;
    }

    public Date getUsrDateModified() {
        return usrDateModified;
    }

    public void setUsrDateModified(Date usrDateModified) {
        this.usrDateModified = usrDateModified;
    }

    public List<UsersRolesEntity> getUsersRolesEntityList() {
        return usersRolesEntityList;
    }

    public void setUsersRolesEntityList(List<UsersRolesEntity> usersRolesEntityList) {
        this.usersRolesEntityList = usersRolesEntityList;
    }

    public StatusEntity getUsrStsId() {
        return usrStsId;
    }

    public void setUsrStsId(StatusEntity usrStsId) {
        this.usrStsId = usrStsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersEntity)) {
            return false;
        }
        UsersEntity other = (UsersEntity) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tryton.small_world.auth.db.UsersEntity[ usrId=" + usrId + " ]";
    }
    
}
