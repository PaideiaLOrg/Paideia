package com.api.paideia.repositories.reference;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.paideia.domain.reference.Reference;

public interface ReferenceRepository extends JpaRepository<Reference, String> {

}
