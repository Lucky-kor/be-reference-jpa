package com.springboot.fetch_strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Profile("fetch-strategy")
@EntityScan(basePackageClasses = {FetchStrategyConfig.class})
@Configuration
public class FetchStrategyConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaFetchStrategyRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();
        System.out.println("# Active Profile: many-to-one-bi");

        return args -> {
            oneToManyFetchLazyTest();   // @oneToMany 일 경우 지연 로딩 테스트
//            oneToManyFetchLazyJPQLTest(); // JPQL에 해당되는 엔티티만 조회.
        };
    }

    private void oneToManyFetchLazyTest() throws InterruptedException {
        tx.begin();
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong","010-1111-1111");
        Order order1 = new Order();
        member.addOrder(order1);
        order1.addMember(member);

        Order order2 = new Order();
        member.addOrder(order2);
        order2.addMember(member);

        em.persist(member);
        em.persist(order1);
        em.persist(order2);

        tx.commit();


        em.clear();   // 테스트를 위해 영속성 컨텍스트에서 조회하지 않도록 영속성 컨텍스트의 엔티티를 제거한다.

        Member findMember = em.find(Member.class, member.getMemberId());

        List<Order> orders = findMember.getOrders();

        System.out.println("######### waiting...");
        Thread.sleep(5000L);

        orders.stream().forEach(order -> System.out.println("# orderId : " + order.getOrderId()));
    }

    private void oneToManyFetchLazyJPQLTest() throws InterruptedException {
        tx.begin();
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong",
                "010-1111-1111");
        Order order1 = new Order();
        member.addOrder(order1);
        Order order2 = new Order();
        member.addOrder(order1);
        member.addOrder(order2);

        order1.addMember(member);
        order2.addMember(member);
        em.persist(member);
        em.persist(order1);
        em.persist(order2);
        tx.commit();

        em.clear();

        // JPQL 사용
        TypedQuery<Member> query =
                em.createQuery("select m from Member m where" + " m.memberId = " + ":memberId", Member.class);
        query.setParameter("memberId", 1L);

        Member findMember = query.getSingleResult();

        List<Order> orders = findMember.getOrders();

        System.out.println("######### waiting...");
        Thread.sleep(5000L);

        orders.stream().forEach(order -> System.out.println(order.getOrderId()));
    }
}

