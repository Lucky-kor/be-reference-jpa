package com.springboot.entity_mapping_test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * 연관 관계 매핑 결과 테스트
 */
@Profile("entity-mapping-solution")
@EntityScan(basePackageClasses = {EntityMappingSolutionConfig.class})
@Configuration
public class EntityMappingSolutionConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();
        System.out.println("# Active Profile: entity-mapping-solution");

        return args -> registerOrder();
    }

    private void registerOrder() {
        tx.begin();
        Member member = new Member("hgd@gmail.com", "Hong Gil Dong", "010-1111-1111");
        Stamp stamp = new Stamp();
        Order order = new Order();
        Coffee coffee = new Coffee("카페라떼", "Cafe Latte", 4000, "CFL");

        OrderCoffee orderCoffee = new OrderCoffee();
        orderCoffee.setQuantity(3);
        stamp.setStampCount(orderCoffee.getQuantity());

        member.addStamp(stamp);
        member.addOrder(order);
        orderCoffee.addCoffee(coffee);
        orderCoffee.addOrder(order);

        em.persist(coffee);
        em.persist(member);
        em.persist(stamp);
        em.persist(order);
        em.persist(orderCoffee);

        tx.commit();

        Order resultOrder = em.find(Order.class, 1L);

        resultOrder.getOrderCoffees().stream()
                .forEach(resultOrderCoffee -> {
                    Coffee orderedCoffee = resultOrderCoffee.getCoffee();
                    System.out.printf("# 주문정보- 커피명: %s(%s), 주문 수량: %d",
                            orderedCoffee.getKorName(), orderedCoffee.getEngName(), resultOrderCoffee.getQuantity());
                });
    }
}
