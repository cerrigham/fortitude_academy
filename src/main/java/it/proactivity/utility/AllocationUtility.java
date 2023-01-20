package it.proactivity.utility;

import it.proactivity.model.Allocation;
import it.proactivity.model.HumanResource;
import it.proactivity.model.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

import static it.proactivity.utility.HumanResourceUtility.getAHumanResourceById;
import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class AllocationUtility {

    public static Boolean insertOrUpdateAllocation(Session session, Long id, Long humanResourceId,
                                                   Long projectId) {
        if (session == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            if (humanResourceId == null || projectId == null) {
                endSession(session);
                return false;
            }
            Allocation allocation = createAnAllocation(session, humanResourceId, projectId);
            session.persist(allocation);
            endSession(session);
            return true;
        } else {
            Allocation allocation = getAllocationById(session, id);
            if (allocation == null) {
                endSession(session);
                return null;
            } else {
                checkAndSetForUpdate(session, allocation, humanResourceId, projectId);
                session.persist(session);
                endSession(session);
                return true;
            }
        }
    }
    public static Boolean deleteAnAllocation(Session session, Long id){
        if (session == null) {
            return false;
        }
        if (id == null) {
            endSession(session);
            return false;
        }
        checkSession(session);
        final String query = "SELECT a " +
                "FROM Allocation a " +
                "WHERE a.id = :id";
        Query<Allocation> allocationQuery = session.createQuery(query).setParameter("id", id);
        List<Allocation> allocationList = allocationQuery.getResultList();
        if (allocationList.size() > 1 || allocationList == null) {
            endSession(session);
            return false;
        }
        session.delete(allocationList.get(0));
        endSession(session);
        return true;
    }
    private static Allocation createAnAllocation(Session session, Long humanResouerceId, Long projectId) {
        HumanResource humanResource = getAHumanResourceById(session, humanResouerceId);
        Project project = getProjectById(session, projectId);
        if (humanResource == null || project == null) {
            endSession(session);
            return null;
        }
        Allocation allocation = new Allocation();
        allocation.setHumanResource(humanResource);
        allocation.setProject(project);
        return allocation;
    }

    private static Allocation getAllocationById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Allocation> cr = cb.createQuery(Allocation.class);
        Root<Allocation> root = cr.from(Allocation.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<Allocation> query = session.createQuery(cr);
        List<Allocation> allocationsList = query.getResultList();

        if (allocationsList == null) {
            return null;
        } else if (allocationsList.isEmpty()) {
            return null;
        } else if (allocationsList.size() > 1) {
            throw new NoSuchElementException("There are more than one result");
        } else
            return allocationsList.get(0);
    }

    private static void checkAndSetForUpdate(Session session, Allocation allocation, Long humanResourceId, Long projectId) {
        HumanResource humanResource = getAHumanResourceById(session, humanResourceId);
        Project project = getProjectById(session, projectId);

        if (humanResourceId == null) {
            allocation.setHumanResource(humanResource);
        }
        if (projectId == null) {
            allocation.setProject(project);
        }
    }
}
