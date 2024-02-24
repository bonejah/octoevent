package com.octoevent.repositories;

import com.octoevent.models.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommitEventRepository extends JpaRepository<Commit, Long> {

}
