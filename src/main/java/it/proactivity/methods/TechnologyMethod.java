package it.proactivity.methods;

import it.proactivity.model.Project;
import it.proactivity.model.Technology;
import it.proactivity.utility.SessionUtility;
import it.proactivity.utility.Utility;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Comparator;
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

    public static Technology findTechnologyFromId(Session session, Long id) throws NoSuchElementException{
        if (session == null) {
            return null;
        }
        if (id == null) {
            SessionUtility.endSession(session);
            return null;
        }


        Technology technology = (Technology) Utility.findObjectFromLong(session, id,"Technology");
        if (technology == null) {
            SessionUtility.endSession(session);
            return null;
        }else {
            SessionUtility.endSession(session);
            return technology;
        }
    }

    public static Technology findTechnologyFromIdWithCriteria(Session session, Long id) throws NoSuchElementException {
        if (session == null) {
            return null;
        }
        if (id == null || id.equals(0l)) {
            SessionUtility.endSession(session);
            return null;
        }

        Object o = Utility.findObjectFromLongWithCriteria(session,id, Technology.class);
        SessionUtility.endSession(session);

        if(o == null) {
            SessionUtility.endSession(session);
            return null;
        }

        Technology technology = (Technology) o;
        return technology;
    }

    public static List<Technology> findTechnologiesFromName(Session session, String attributeValue) {
        if (session == null) {
            return null;
        }
        if (attributeValue == null || attributeValue.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objectList =  Utility.findObjectFromString(session, "name", attributeValue, "Technology");

        if (objectList == null || objectList.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Technology> technologies = new ArrayList<>();

        objectList.stream()
                .forEach(e -> technologies.add((Technology) e));

        technologies.stream()
                .sorted(Comparator.comparing(Technology::getId));

        SessionUtility.endSession(session);
        return technologies;
    }

    public static List<Technology> findTechnologyFromNameWithCriteria(Session session, String name) {
        if (session == null) {
            return null;
        }
        if (name == null || name.isEmpty()) {
            SessionUtility.endSession(session);
            return null;
        }

        List<Object> objects = Utility.findObjectsFromStringWithCriteria(session, "name", name, Technology.class);
        if (objects == null) {
            SessionUtility.endSession(session);
            return  null;
        }
        List<Technology> customers = new ArrayList<>();

        objects.stream()
                .forEach(e -> customers.add((Technology) e));

        customers.stream()
                .sorted(Comparator.comparing(Technology::getId));

        SessionUtility.endSession(session);

        return customers;
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

