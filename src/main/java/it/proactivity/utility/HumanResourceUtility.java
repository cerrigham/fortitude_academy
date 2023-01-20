package it.proactivity.utility;

import it.proactivity.model.HumanResource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;
import static it.proactivity.utility.Utility.checkIfNullOrEmpty;

public class HumanResourceUtility {

    public static Boolean insertOrUpdateHumanResource(Session session, Long id, String name, String surname,
                                                      String email, String phoneNumber, String vatCode, Boolean isCeo,
                                                      Boolean isCda) {
        if (session == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            if (checkIfNullOrEmpty(name) || checkIfNullOrEmpty(surname) || checkIfNullOrEmpty(email)
                    || checkIfNullOrEmpty(phoneNumber) || checkIfNullOrEmpty(phoneNumber) || checkIfNullOrEmpty(vatCode)) {
                endSession(session);
                return false;
            }
            HumanResource humanResource = createAHumanResource(name, surname, email, phoneNumber, vatCode, isCeo, isCda);
            session.persist(humanResource);
            endSession(session);
            return true;
        } else {
            HumanResource humanResource = getAHumanResourceById(session, id);
            if (humanResource == null) {
                endSession(session);
                return null;
            } else {
                checkForUpdate(humanResource, name, surname,
                        email, phoneNumber, vatCode, isCeo, isCda);
                session.persist(session);
                endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteAHumanResource(Session session, Long id) {
        if (session == null) {
            return false;
        }
        if (id == null) {
            endSession(session);
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

    public static HumanResource createAHumanResource(String name, String surname,
                                                     String email, String phoneNumber, String vatCode, Boolean isCeo,
                                                     Boolean isCda) {
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

    public static HumanResource getAHumanResourceById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<HumanResource> cr = cb.createQuery(HumanResource.class);
        Root<HumanResource> root = cr.from(HumanResource.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<HumanResource> query = session.createQuery(cr);
        List<HumanResource> humanResourceList = query.getResultList();
        if (humanResourceList == null) {
            return null;
        } else if (humanResourceList.isEmpty()) {
            return null;
        } else if (humanResourceList.size() > 1) {
            throw new NoSuchElementException("There are more than one result");
        } else
            return humanResourceList.get(0);
    }

    private static void checkForUpdate(HumanResource humanResource, String name, String surname,
                                       String email, String phoneNumber, String vatCode, Boolean isCeo,
                                       Boolean isCda) {
        if (checkIfNullOrEmpty(name)) {
            humanResource.setName(name);
        }
        if (checkIfNullOrEmpty(surname)) {
            humanResource.setSurname(surname);
        }
        if (checkIfNullOrEmpty(email)) {
            humanResource.setEmail(email);
        }
        if (checkIfNullOrEmpty(phoneNumber)) {
            humanResource.setPhoneNumber(phoneNumber);
        }
        if (checkIfNullOrEmpty(vatCode)) {
            humanResource.setVatCode(vatCode);
        }
        if (checkIfCeo(humanResource)) {
            humanResource.setIsCeo(isCeo);
        }
        if (checkIfCda(humanResource)) {
            humanResource.setIsCda(isCda);
        }
    }

    private static Boolean checkIfCeo(HumanResource humanResource) {
        List<HumanResource> ceoList = new ArrayList<>();
        ceoList.add(humanResource);
        if (ceoList.size() > 1) {
            return false;
        }
        return true;
    }

    private static Boolean checkIfCda(HumanResource humanResource) {
        List<HumanResource> cdaList = new ArrayList<>();
        cdaList.add(humanResource);
        if (cdaList.size() > 4) {
            return false;
        }
        return true;
    }
}
