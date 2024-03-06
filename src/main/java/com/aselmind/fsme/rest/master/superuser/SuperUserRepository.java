package com.aselmind.fsme.rest.master.superuser;

import com.aselmind.fsme.rest.common.BaseRepository;

import java.util.Optional;

interface SuperUserRepository extends BaseRepository<SuperUserEntity> {
    Optional<SuperUserEntity> findByUsername(String username);
}
