package com.example.saturday_test;

import com.example.saturday_test.entities.Order;
import jakarta.persistence.*;

public class OrderDAOImpl {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-unit");


    public void updateOrder(Order order) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.merge(order);
        tx.commit();

        em.close();
    }

    public Order getOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        Order order = em.find(Order.class, id);
        em.close();
        return order;
    }
}