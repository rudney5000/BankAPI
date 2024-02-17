package com.dedytech.bankapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "name", unique = true, nullable = false)
    var name: String,
    @ManyToMany(mappedBy = "roles")
    var accounts: List<Account> = mutableListOf(),
    @CreationTimestamp
    var createdAt: Timestamp? = null,
    @UpdateTimestamp
    var updatedAt: Timestamp? = null
)