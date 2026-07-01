package com.negoreserva.internal.organization.feature.user.repository;

import com.negoreserva.common.feature.concrete.user.model.User;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrgUserRepository extends ConcreteRepository<User> {
    Optional<User> findByEmail(String email);

    @Query(
            value = """
                    select distinct u from User u
                    join u.userOrganizations uo
                    where uo.organization.id = :organizationId
                    and u.deletedAt is null
                    """,
            countQuery = """
                    select count(distinct u) from User u
                    join u.userOrganizations uo
                    where uo.organization.id = :organizationId
                    and u.deletedAt is null
                    """
    )
    Page<User> findAllByOrganizationId(@Param("organizationId") long organizationId, Pageable pageable);

    @Query("""
            select distinct u from User u
            join u.userOrganizations uo
            where uo.organization.id = :organizationId
            and u.deletedAt is null
            """)
    List<User> findAllUsersByOrganizationId(@Param("organizationId") long organizationId);

    @Query("""
            select distinct u from User u
            join u.userOrganizations uo
            join uo.organization o
            where o.slug = :slug
            and u.deletedAt is null
            """)
    List<User> findAllByOrganizationSlug(@Param("slug") String slug);
}
