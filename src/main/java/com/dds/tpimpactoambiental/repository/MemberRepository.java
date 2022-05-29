package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Miembro, Long> {
}
