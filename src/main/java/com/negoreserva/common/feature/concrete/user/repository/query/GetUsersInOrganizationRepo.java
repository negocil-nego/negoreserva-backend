package com.negoreserva.common.feature.concrete.user.repository.query;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.concrete.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface GetUsersInOrganizationRepo extends CrudRepository<User, Long> {
    @Query("""
            select distinct u from User u
            join u.userOrganizations uo
            where uo.organization.id = :organizationId
            and u.deletedAt is null
            """)
    List<User> findAllByOrganizationId(@Param("organizationId") long organizationId);

    @Query(
            value = """
                select distinct u from User u
                join u.userOrganizations uo
                where uo.organization = :organization
                and u.deletedAt is null
                """,
            countQuery = """
                select count(distinct u) from User u
                join u.userOrganizations uo
                where uo.organization = :organization
                and u.deletedAt is null
                """
    )
    Page<User> findAllByOrganization(Organization organization, Pageable pageable);
}