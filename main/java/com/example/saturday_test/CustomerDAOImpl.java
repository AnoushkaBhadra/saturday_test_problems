package com.example.saturday_test;
import com.example.saturday_test.entities.Customer;
import jakarta.persistence.*;
import java.util.List;

public class CustomerDAOImpl {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-unit");

    public void insertCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(customer);
        tx.commit();

        em.close();
    }
    public void updateCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.merge(customer);
        tx.commit();

        em.close();
    }

    // Delete customer by id
    public void deleteCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Customer c = em.find(Customer.class, id);
        if (c != null) {
            em.remove(c);
        }
        tx.commit();

        em.close();
    }

    public Customer getCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        Customer c = em.find(Customer.class, id);
        em.close();
        return c;
    }
    public Customer getCustomerByEmail(String email) {

        EntityManager em = emf.createEntityManager();

        String jpql = "select c from Customer c where c.email = :email";

        Customer customer = em.createQuery(jpql, Customer.class)
                .setParameter("email", email)
                .getSingleResult();

        em.close();
        return customer;
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();

        List<Customer> list = em.createQuery("FROM Customer", Customer.class).getResultList();

        em.close();
        return list;
    }
}