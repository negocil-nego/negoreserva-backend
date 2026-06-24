package com.negoreserva.internal.organization.feature.payment.repository;

import com.negoreserva.common.feature.concrete.organization.model.Organization;
import com.negoreserva.common.feature.core.repository.ConcreteRepository;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentMethod;
import com.negoreserva.common.feature.concrete.payment.enums.PaymentStatus;
import com.negoreserva.common.feature.concrete.payment.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgPaymentRepo extends ConcreteRepository<Payment> {
    @Query("SELECT p FROM Payment p JOIN p.transaction t JOIN t.product pr WHERE pr.organization = :organization ORDER BY p.createdAt DESC")
    List<Payment> findAllByOrganizationOrderByCreatedAtDesc(@Param("organization") Organization organization);

    @Query("SELECT COUNT(p) FROM Payment p JOIN p.transaction t JOIN t.product pr WHERE pr.organization = :organization")
    long countByOrganization(@Param("organization") Organization organization);

    @Query("SELECT p.status, COUNT(p) FROM Payment p JOIN p.transaction t JOIN t.product pr WHERE pr.organization = :organization GROUP BY p.status")
    List<Object[]> countByStatusGroupedByOrganization(@Param("organization") Organization organization);

    @Query("SELECT p.type, COUNT(p) FROM Payment p JOIN p.transaction t JOIN t.product pr WHERE pr.organization = :organization GROUP BY p.type")
    List<Object[]> countByMethodGroupedByOrganization(@Param("organization") Organization organization);

    @Query("SELECT MONTH(p.createdAt), COUNT(p) FROM Payment p JOIN p.transaction t JOIN t.product pr WHERE pr.organization = :organization AND YEAR(p.createdAt) = :year GROUP BY MONTH(p.createdAt)")
    List<Object[]> countMonthlyByOrganizationAndYear(@Param("organization") Organization organization, @Param("year") int year);

    @Query("SELECT MONTH(p.createdAt), SUM(t.price) FROM Payment p JOIN p.transaction t JOIN t.product pr WHERE pr.organization = :organization AND YEAR(p.createdAt) = :year GROUP BY MONTH(p.createdAt)")
    List<Object[]> totalMonthlyByOrganizationAndYear(@Param("organization") Organization organization, @Param("year") int year);
}
