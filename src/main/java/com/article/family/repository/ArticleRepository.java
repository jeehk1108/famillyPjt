package com.article.family.repository;

import com.article.family.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public void save(Article article){
        em.persist(article);
    }

    //단건조회 find(타입, pk)
    public Article findOne(Long id) {
        return em.find(Article.class, id);
    }

    //리스트 조회
    public List<Article> findNewsAll() {
        //                                  JPQL              ,     반환타입
        return em.createQuery("select a from Article a where a.category = '소식' or a.category = '공지' order by a.id desc", Article.class)
                .getResultList();
    }

    //스토리 리스트 조회
    public List<Article> findStoryAll() {
        //                                  JPQL              ,     반환타입
        return em.createQuery("select a from Article a where a.category ='일상' or a.category = '정보' or a.category = '축하' order by a.id desc", Article.class)
                .getResultList();
    }

    public List<Article> findByName(String name) {
        return em.createQuery("select a from Article a where a.name =? 1", Article.class)
                .setParameter(1 , name)
                .getResultList();
    }

    public void delete(Long id) {
        em.createQuery("delete from Article a where a.id =: id")
                .setParameter("id", id);
    }


//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
// 하단은 추후 수정 예정
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
//    /**
//    * 동적 쿼리 JPQL로 처리
//     */
//    public List<Order> findAllByString(OrderSearch orderSearch) {
//        //language=JPAQL
//        String jpql = "select o From Order o join o.member m";
//        boolean isFirstCondition = true;
//        //주문 상태 검색
//        if (orderSearch.getOrderStatus() != null) {
//            if (isFirstCondition) {
//                jpql += " where";
//                isFirstCondition = false;
//            } else {
//                jpql += " and";
//            }
//            jpql += " o.status = :status";
//        }
//        //회원 이름 검색
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            if (isFirstCondition) {
//                jpql += " where";
//                isFirstCondition = false;
//            } else {
//                jpql += " and";
//            }
//            jpql += " m.name like :name";
//        }
//        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
//                .setMaxResults(1000); //최대 1000건
//        if (orderSearch.getOrderStatus() != null) {
//            query = query.setParameter("status", orderSearch.getOrderStatus());
//        }
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            query = query.setParameter("name", orderSearch.getMemberName());
//        }
//        return query.getResultList();
//    }
//
//
//    /**
//     * JPA Criteria
//     */
//    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
//        Root<Order> o = cq.from(Order.class);
//        Join<Order, Member> m = o.join("member", JoinType.INNER); //회원과 조인
//        List<Predicate> criteria = new ArrayList<>();
//        //주문 상태 검색
//        if (orderSearch.getOrderStatus() != null) {
//            Predicate status = cb.equal(o.get("status"),
//                    orderSearch.getOrderStatus());
//            criteria.add(status);
//        }
//        //회원 이름 검색
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            Predicate name =
//                    cb.like(m.<String>get("name"), "%" +
//                            orderSearch.getMemberName() + "%");
//            criteria.add(name);
//        }
//        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
//        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); //최대1000건
//        return query.getResultList();
//    }


    /**
     * Querydsl
     * 주문 검색 기능 개발 (19:30)
     */
//    public List<Order> findAll(OrderSearch orderSearch) {
//        QOrder order = QOrder.order;
//        QMember member = QMember.member;
//
//        return query
//                .select(order)
//                .from(order)
//                .join(order.member, member)
//                .where(statusEq(orderSearch.getOrderStatus()),
//                        nemeLike(orderSearch.getMemberName()))
//                .limit(1000)
//                .fetch();
//    }
//
//    private BooleanExpression statusEq(OrderStatus statusCond) {
//        if (statusCond == null) {
//            return null;
//        }
//        return order.status.eq(statusCond);
//    }
//
//    private BooleanExpression nameLike(String nameCond) {
//        if (!StringUtils.hasText(nameCond)) {
//            return null;
//        }
//        return member.name.like(nameCond);
//    }


}
