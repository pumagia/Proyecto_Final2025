package com.pumagia.loscardoscdu.Repository;

import com.pumagia.loscardoscdu.Modelo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {

}
