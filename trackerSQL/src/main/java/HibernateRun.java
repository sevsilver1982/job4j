import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.UUID;

public class HibernateRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            Item item = create(new Item("Learn Hibernate"), sf);
            System.out.println(item);
            item.setName("Learn Hibernate 5.");

            update(item, sf);
            System.out.println(item);

            Item rsl = findById(item.getId(), sf);
            System.out.println(rsl);

            delete(rsl.getId(), sf);

            findAll(sf).forEach(System.out::println);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static Item create(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public static void update(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(UUID id, SessionFactory sf) {
        Item item = new Item(null);
        item.setId(id);
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(item);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    public static List<Item> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        List<Item> items = session.createQuery("FROM Item", Item.class).list();
        session.close();
        return items;
    }

    public static Item findById(UUID id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

}