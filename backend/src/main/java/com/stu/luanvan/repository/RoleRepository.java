package com.stu.luanvan.repository;

import com.stu.luanvan.model.role.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel,Integer> {
}
