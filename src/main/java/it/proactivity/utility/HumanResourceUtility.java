package it.proactivity.utility;

import it.proactivity.model.Company;
import it.proactivity.model.HumanResource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;

import java.util.List;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class HumanResourceUtility {
    public static HumanResource createAHumanResource(String name, String surname,
                                                     String email, String phoneNumber, String vatCode, Boolean isCeo,
                                                     Boolean isCda) {
        if (name == null || surname == null || email == null || phoneNumber == null || vatCode == null || isCeo == null
                || isCda == null) {
            return null;
        }
        HumanResource humanResource = new HumanResource();
        humanResource.setName(name);
        humanResource.setSurname(surname);
        humanResource.setEmail(email);
        humanResource.setPhoneNumber(phoneNumber);
        humanResource.setVatCode(vatCode);
        humanResource.setIsCeo(isCeo);
        humanResource.setIsCda(isCda);
        return humanResource;
    }
    private static List<HumanResource> checkAHumanResource(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<HumanResource> cr = cb.createQuery(HumanResource.class);
        Root<HumanResource> root = cr.from(HumanResource.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<HumanResource> query = session.createQuery(cr);
        List<HumanResource> humanResourceList = query.getResultList();
        if (humanResourceList.size() > 1) {
            return null;
        }
        return humanResourceList;
    }

    private static void checkForUpdate(HumanResource humanResource, String name, String surname,
                                       String email, String phoneNumber, String vatCode, Boolean isCeo,
                                       Boolean isCda) {
        if (name == null) {
            humanResource.setName(name);
        }
        if (surname == null) {
            humanResource.setSurname(surname);
        }
        if (email == null) {
            humanResource.setEmail(email);
        }
        if (phoneNumber == null) {
            humanResource.setPhoneNumber(phoneNumber);
        }
        if (vatCode == null) {
            humanResource.setVatCode(vatCode);
        }
        if (isCeo == null) {
            humanResource.setIsCeo(isCeo);
        }
        if (isCda == null) {
            humanResource.setIsCda(isCda);
        }
    }

    public static Boolean insertOrUpdateHumanResource(Session session, String name, Long id, String surname,
                                                      String email, String phoneNumber, String vatCode, Boolean isCeo,
                                                      Boolean isCda) {
        if (session == null || name == null || surname == null || email == null || phoneNumber == null ||
                vatCode == null || isCeo == null || isCda == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            HumanResource humanResource = createAHumanResource(name, surname, email, phoneNumber, vatCode, isCeo, isCda);
            session.persist(humanResource);
            endSession(session);
            return true;
        }
        List<HumanResource> humanResourceList = checkAHumanResource(session, id);
        if (humanResourceList.size() == 0) {
            endSession(session);
        } else {
            HumanResource humanResource = humanResourceList.get(0);
            checkForUpdate(humanResource, name, surname, email, phoneNumber, vatCode, isCeo, isCda);
            endSession(session);
            return true;
        }
        return false;
    }

    public static Boolean deleteAHumanResource(Session session, Long id) {
        if (session == null || id == null) {
            return false;
        }
        checkSession(session);

        final String query = "SELECT h " +
                "FROM HumanResource h " +
                "WHERE h.id = :id";
        Query<HumanResource> humanResourceQuery = session.createQuery(query).setParameter("id", id);
        List<HumanResource> humanResourceList = humanResourceQuery.getResultList();
        if (humanResourceList.size() > 1 || humanResourceList == null) {
            endSession(session);
            return false;
        }
        session.delete(humanResourceList.get(0));
        endSession(session);
        return true;
    }

}
