package com.tryton.small_world.auth.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users", schema = "auth")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr_id")
    private Long id;
    @Basic
    @Column(name = "usr_email")
    private String email;
    @Basic
    @Column(name = "usr_password")
    private String password;
    @Basic
    @Column(name = "usr_last_name")
    private String usrLastName;
    @Basic
    @Column(name = "usr_first_name")
    private String usrFirstName;
    @Basic
    @Column(name = "usr_date_inserted")
    private String usrDateInserted;
    @Basic
    @Column(name = "usr_date_modified")
    private Timestamp usrDateModified;
}