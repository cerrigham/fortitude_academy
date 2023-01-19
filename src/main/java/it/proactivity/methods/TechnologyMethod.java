package it.proactivity.methods;


import it.proactivity.model.Technology;
import it.proactivity.utility.SessionUtility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

public class TechnologyMethod {

    public static Boolean insertOrUpdateTechnology(Session session, Long id, String name) {
        if (checkInputParameter(session, name)) {
            SessionUtility.endSession(session);
            return false;
        }

        if (id == null) {
            Technology technology = createTechnology(name);
            session.persist(technology);
            SessionUtility.endSession(session);
            return true;
        } else {

            List<Technology> technologies = checkTechnology(session, id);
            if (technologies.size() == 0) {
                SessionUtility.endSession(session);
                return false;
            } else {
                Technology technology = technologies.get(0);
                checkParameterForUpdate(technology, name);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromTechnology(Session session, Long id) {
        if ((checkInputParameter(session, id))) {
            SessionUtility.endSession(session);
            return false;
        }

        List<Technology> customers = checkTechnology(session, id);

        if (customers.isEmpty()) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(customers.get(0));
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static Technology createTechnology(String name) {
        Technology technology = new Technology();
        technology.setName(name);
        return technology;
    }

    private static List<Technology> checkTechnology(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Technology> cq = cb.createQuery(Technology.class);
        Root<Technology> root = cq.from(Technology.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Technology> technologies = query.getResultList();
        if (technologies.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        }

        return technologies;
    }

    private static Boolean checkInputParameter(Session session, String name) {

        return session == null || name == null;
    }

    private static Boolean checkInputParameter(Session session, Long id) {
        return session == null || id == null || id.equals(0L);
    }

    private static void checkParameterForUpdate(Technology technology, String name) {

        if (!(name.isEmpty())) {
                technology.setName(name);
        }
    }
}

