package com.springboot.n_plus_one_problem;

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

@Profile("n-plus-one-problem")
@EntityScan(basePackageClasses = {NPlusOneProblemConfig.class})
@Configuration
public class NPlusOneProblemConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaFetchStrategyRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();
        System.out.println("# Active Profile: many-to-one-bi");

        return args -> {
//            nPlusOneProblemExample();   // N + 1 문제가 발생하는 예제
            fetchJoinJPQLTest();            // Fetch Join을 이용해서 한 번에 가져오기
        };
    }

    private void nPlusOneProblemExample() throws InterruptedException {
        tx.begin();
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong", "010-1111-1111");
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
                em.createQuery("select m from Member m", Member.class);

        Member findMember = query.getSingleResult();

        List<Order> orders = findMember.getOrders();
        orders.stream()
                .forEach(order -> System.out.println("# orderId: " + order.getOrderId()));
    }

    private void fetchJoinJPQLTest() throws InterruptedException {
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
                em.createQuery("select m from Member m join fetch m.orders p", Member.class);

        List<Member> findMembers = query.getResultList();

        System.out.println(findMembers.size());
    }
}

