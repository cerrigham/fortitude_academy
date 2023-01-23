package it.proactivity.methods;


import it.proactivity.model.Technology;
import it.proactivity.utility.SessionUtility;
import it.proactivity.utility.Utility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

public class TechnologyMethod {

    public static Boolean createOrUpdateTechnology(Session session, Long id, String name) throws NoSuchElementException {
        Utility utility = new Utility();
        if (session == null) {
            return false;
        }


        if (id == null) {
            //Create technology
            if(utility.isNullOrEmpty(name)) {
                SessionUtility.endSession(session);
                return false;
            }
            Technology technology = createTechnology(name);
            session.persist(technology);
            SessionUtility.endSession(session);
            return true;
        } else {
            // update technology
            Technology technology = getTechnologyById(session, id);
            if (technology == null || utility.isNullOrEmpty(name)) {
                SessionUtility.endSession(session);
                return false;
            } else {
                setParametersForUpdate(technology, name);
                SessionUtility.endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteFromTechnology(Session session, Long id) throws NoSuchElementException {
        if(session == null) {
            return false;
        }
        if (id == null || id.equals(0l)) {
            SessionUtility.endSession(session);
            return false;
        }

        Technology technology = getTechnologyById(session, id);

        if (technology == null) {
            SessionUtility.endSession(session);
            return false;
        } else {
            session.delete(technology);
            SessionUtility.endSession(session);
            return true;
        }
    }

    private static Technology createTechnology(String name) {
        Technology technology = new Technology();
        technology.setName(name);
        return technology;
    }

    private static Technology getTechnologyById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Technology> cq = cb.createQuery(Technology.class);
        Root<Technology> root = cq.from(Technology.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        Query query = session.createQuery(cq);
        List<Technology> technologies = query.getResultList();
        if(technologies == null) {
            return null;
        } else if(technologies.isEmpty()) {
            return null;
        } else if(technologies.size() > 1) {
            throw new NoSuchElementException("The result query get more than one result");
        } else
            return  technologies.get(0);
    }

    private static void setParametersForUpdate(Technology technology, String name) {

        Utility utility = new Utility();
        if (!utility.isNullOrEmpty(name)) {
            technology.setName(name);
        }
    }
}

